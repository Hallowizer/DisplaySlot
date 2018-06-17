package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitJumpInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final int opcode;
	private final Label label;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitJumpInsn(opcode, label);
	}
}
