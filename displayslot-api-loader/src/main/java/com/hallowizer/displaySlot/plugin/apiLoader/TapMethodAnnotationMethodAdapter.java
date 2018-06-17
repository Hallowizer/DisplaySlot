package com.hallowizer.displaySlot.plugin.apiLoader;

import static org.objectweb.asm.Opcodes.ASM5;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.TypePath;

import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitAnnotableParameterCountInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitAnnotationDefaultInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitAttributeInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitCodeInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitFieldInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitFrameInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitIincInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitInsnAnnotationInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitIntInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitInvokeDynamicInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitJumpInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitLabelInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitLdcInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitLineNumberInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitLocalVariableAnnotationInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitLocalVariableInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitLookupSwitchInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitMaxsInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitMethodInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitMultiANewArrayInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitParameterAnnotationInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitParameterInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitTableSwitchInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitTryCatchAnnotationInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitTryCatchBlockInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitTypeAnnotationInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitTypeInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.MethodVisitVarInsnInstruction;
import com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction.VisitInstruction;

public final class TapMethodAnnotationMethodAdapter extends MethodVisitor {
	private ClassVisitor cv;
	private int access;
	private String name;
	private String descriptor;
	private String signature;
	private String[] exceptions;
	boolean invalid;
	private ApiLoaderContext ctx;

	public TapMethodAnnotationMethodAdapter(ApiLoaderContext ctx, ClassVisitor cv, int access, String name, String descriptor, String signature, String[] exceptions) {
		super(ASM5, null);
		this.cv = cv;
		this.access = access;
		this.name = name;
		this.descriptor = descriptor;
		this.signature = signature;
		this.exceptions = exceptions;
		this.ctx = ctx;
	}

	@Override
	public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
		AnnotationVisitor av = mv.visitAnnotation(descriptor, visible);

		if (descriptor.equals("com.hallowizer.displaySlot.api.platform.PlatformOnly"))
			return new TapMethodAnnotationAnnotationAdapter(av, this, ctx);

		return av;
	}

	private List<VisitInstruction<MethodVisitor>> procrastinatedInstructions = new ArrayList<>();

	@Override
	public void visitEnd() {
		MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);

		if (!invalid)
			for (VisitInstruction<MethodVisitor> instruction : procrastinatedInstructions)
				instruction.execute(mv);

		mv.visitEnd();
	}
	
	// -----------------------------------------------------------------------------------------------
	// The following code probably isn't that interesting. It just has an implementation for
	// every visit method that adds itself to the procrastinatedInstructions list.
	// -----------------------------------------------------------------------------------------------
	
	@Override
	public void visitParameter(final String name, final int access) {
		procrastinatedInstructions.add(new MethodVisitParameterInstruction(name, access));
	}

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		ProcrastinatingAnnotationVisitor av = new ProcrastinatingAnnotationVisitor();
		procrastinatedInstructions.add(new MethodVisitAnnotationDefaultInstruction(av));
		return av;
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(
			final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
		ProcrastinatingAnnotationVisitor av = new ProcrastinatingAnnotationVisitor();
		procrastinatedInstructions.add(new MethodVisitTypeAnnotationInstruction(av, typeRef, typePath, descriptor, visible));
		return av;
	}

	@Override
	public void visitAnnotableParameterCount(final int parameterCount, final boolean visible) {
		procrastinatedInstructions.add(new MethodVisitAnnotableParameterCountInstruction(parameterCount, visible));
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(
			final int parameter, final String descriptor, final boolean visible) {
		ProcrastinatingAnnotationVisitor av = new ProcrastinatingAnnotationVisitor();
		procrastinatedInstructions.add(new MethodVisitParameterAnnotationInstruction(av, parameter, descriptor, visible));
		return av;
	}

	@Override
	public void visitAttribute(final Attribute attribute) {
		procrastinatedInstructions.add(new MethodVisitAttributeInstruction(attribute));
	}

	@Override
	public void visitCode() {
		procrastinatedInstructions.add(new MethodVisitCodeInstruction());
	}

	@Override
	public void visitFrame(
			final int type,
			final int nLocal,
			final Object[] local,
			final int nStack,
			final Object[] stack) {
		procrastinatedInstructions.add(new MethodVisitFrameInstruction(type, nLocal, local, nStack, stack));
	}

	@Override
	public void visitInsn(final int opcode) {
		procrastinatedInstructions.add(new MethodVisitInsnInstruction(opcode));
	}

	@Override
	public void visitIntInsn(final int opcode, final int operand) {
		procrastinatedInstructions.add(new MethodVisitIntInsnInstruction(opcode, operand));
	}

	@Override
	public void visitVarInsn(final int opcode, final int var) {
		procrastinatedInstructions.add(new MethodVisitVarInsnInstruction(opcode, var));
	}

	@Override
	public void visitTypeInsn(final int opcode, final String type) {
		procrastinatedInstructions.add(new MethodVisitTypeInsnInstruction(opcode, type));
	}

	@Override
	public void visitFieldInsn(
			final int opcode, final String owner, final String name, final String descriptor) {
		procrastinatedInstructions.add(new MethodVisitFieldInsnInstruction(opcode, owner, name, descriptor));
	}

	@Override
	public void visitMethodInsn(
			final int opcode,
			final String owner,
			final String name,
			final String descriptor,
			final boolean isInterface) {
		procrastinatedInstructions.add(new MethodVisitMethodInsnInstruction(opcode, owner, name, descriptor, isInterface));
	}

	@Override
	public void visitInvokeDynamicInsn(
			final String name,
			final String descriptor,
			final Handle bootstrapMethodHandle,
			final Object... bootstrapMethodArguments) {
		procrastinatedInstructions.add(new MethodVisitInvokeDynamicInsnInstruction(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments));
	}

	@Override
	public void visitJumpInsn(final int opcode, final Label label) {
		procrastinatedInstructions.add(new MethodVisitJumpInsnInstruction(opcode, label));
	}

	@Override
	public void visitLabel(final Label label) {
		procrastinatedInstructions.add(new MethodVisitLabelInstruction(label));
	}

	// -----------------------------------------------------------------------------------------------
	// Special instructions
	// -----------------------------------------------------------------------------------------------

	@Override
	public void visitLdcInsn(final Object value) {
		procrastinatedInstructions.add(new MethodVisitLdcInsnInstruction(value));
	}

	@Override
	public void visitIincInsn(final int var, final int increment) {
		procrastinatedInstructions.add(new MethodVisitIincInsnInstruction(var, increment));
	}

	@Override
	public void visitTableSwitchInsn(
			final int min, final int max, final Label dflt, final Label... labels) {
		procrastinatedInstructions.add(new MethodVisitTableSwitchInsnInstruction(min, max, dflt, labels));
	}

	@Override
	public void visitLookupSwitchInsn(final Label dflt, final int[] keys, final Label[] labels) {
		procrastinatedInstructions.add(new MethodVisitLookupSwitchInsnInstruction(dflt, keys, labels));
	}

	@Override
	public void visitMultiANewArrayInsn(final String descriptor, final int numDimensions) {
		procrastinatedInstructions.add(new MethodVisitMultiANewArrayInsnInstruction(descriptor, numDimensions));
	}

	@Override
	public AnnotationVisitor visitInsnAnnotation(
			final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
		ProcrastinatingAnnotationVisitor av = new ProcrastinatingAnnotationVisitor();
		procrastinatedInstructions.add(new MethodVisitInsnAnnotationInstruction(av, typeRef, typePath, descriptor, visible));
		return av;
	}

	@Override
	public void visitTryCatchBlock(
			final Label start, final Label end, final Label handler, final String type) {
		procrastinatedInstructions.add(new MethodVisitTryCatchBlockInstruction(start, end, handler, type));
	}

	@Override
	public AnnotationVisitor visitTryCatchAnnotation(
			final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
		ProcrastinatingAnnotationVisitor av = new ProcrastinatingAnnotationVisitor();
		procrastinatedInstructions.add(new MethodVisitTryCatchAnnotationInstruction(av, typeRef, typePath, descriptor, visible));
		return av;
	}

	@Override
	public void visitLocalVariable(
			final String name,
			final String descriptor,
			final String signature,
			final Label start,
			final Label end,
			final int index) {
		procrastinatedInstructions.add(new MethodVisitLocalVariableInstruction(name, descriptor, signature, start, end, index));
	}

	@Override
	public AnnotationVisitor visitLocalVariableAnnotation(
			final int typeRef,
			final TypePath typePath,
			final Label[] start,
			final Label[] end,
			final int[] index,
			final String descriptor,
			final boolean visible) {
		ProcrastinatingAnnotationVisitor av = new ProcrastinatingAnnotationVisitor();
		procrastinatedInstructions.add(new MethodVisitLocalVariableAnnotationInstruction(av, typeRef, typePath, start, end, index, descriptor, visible));
		return av;
	}

	@Override
	public void visitLineNumber(final int line, final Label start) {
		procrastinatedInstructions.add(new MethodVisitLineNumberInstruction(line, start));
	}

	@Override
	public void visitMaxs(final int maxStack, final int maxLocals) {
		procrastinatedInstructions.add(new MethodVisitMaxsInstruction(maxStack, maxLocals));
	}
}
