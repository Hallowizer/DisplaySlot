package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitIincInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final int var;
	private final int increment;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitIincInsn(var, increment);
	}
}
