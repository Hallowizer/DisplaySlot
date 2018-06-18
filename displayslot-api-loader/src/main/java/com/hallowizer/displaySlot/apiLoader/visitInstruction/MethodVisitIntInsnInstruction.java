package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitIntInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final int opcode;
	private final int operand;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitIntInsn(opcode, operand);
	}
}
