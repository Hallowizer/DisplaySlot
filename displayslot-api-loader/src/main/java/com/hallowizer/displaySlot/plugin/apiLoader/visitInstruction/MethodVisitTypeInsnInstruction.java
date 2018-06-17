package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitTypeInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final int opcode;
	private final String type;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitTypeInsn(opcode, type);
	}
}
