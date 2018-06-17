package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitVarInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final int opcode;
	private final int var;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitVarInsn(opcode, var);
	}
}
