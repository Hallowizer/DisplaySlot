package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitInvokeDynamicInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final String name;
	private final String descriptor;
	private final Handle bootstrapMethodHandle;
	private final Object[] bootstrapMethodArguments;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
	}
}
