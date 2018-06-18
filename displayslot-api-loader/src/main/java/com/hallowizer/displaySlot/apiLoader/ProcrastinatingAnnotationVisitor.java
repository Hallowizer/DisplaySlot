package com.hallowizer.displaySlot.apiLoader;

import static org.objectweb.asm.Opcodes.ASM5;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.AnnotationVisitor;

import com.hallowizer.displaySlot.apiLoader.visitInstruction.AnnotationVisitAnnotationInstruction;
import com.hallowizer.displaySlot.apiLoader.visitInstruction.AnnotationVisitArrayInstruction;
import com.hallowizer.displaySlot.apiLoader.visitInstruction.AnnotationVisitEnumInstruction;
import com.hallowizer.displaySlot.apiLoader.visitInstruction.AnnotationVisitInstruction;
import com.hallowizer.displaySlot.apiLoader.visitInstruction.VisitInstruction;

public final class ProcrastinatingAnnotationVisitor extends AnnotationVisitor {
	public ProcrastinatingAnnotationVisitor() {
		super(ASM5, null);
	}

	private final List<VisitInstruction<AnnotationVisitor>> procrastinatedInstructions = new ArrayList<>();

	public void stopProcrastinating(AnnotationVisitor av) {
		for (VisitInstruction<AnnotationVisitor> instruction : procrastinatedInstructions)
			instruction.execute(av);

		av.visitEnd();
	}
	
	@Override
	public void visit(final String name, final Object value) {
		procrastinatedInstructions.add(new AnnotationVisitInstruction(name, value));
	}

	@Override
	public void visitEnum(final String name, final String descriptor, final String value) {
		procrastinatedInstructions.add(new AnnotationVisitEnumInstruction(name, descriptor, value));
	}

	@Override
	public AnnotationVisitor visitAnnotation(final String name, final String descriptor) {
		ProcrastinatingAnnotationVisitor procrastinating = new ProcrastinatingAnnotationVisitor();
		procrastinatedInstructions.add(new AnnotationVisitAnnotationInstruction(procrastinating, name, descriptor));
		return procrastinating;
	}

	@Override
	public AnnotationVisitor visitArray(final String name) {
		ProcrastinatingAnnotationVisitor procrastinating = new ProcrastinatingAnnotationVisitor();
		procrastinatedInstructions.add(new AnnotationVisitArrayInstruction(procrastinating, name));
		return procrastinating;
	}
}
