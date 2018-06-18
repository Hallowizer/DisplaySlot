package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitFieldInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final int opcode;
	private final String owner;
	private final String name;
	private final String descriptor;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitFieldInsn(opcode, owner, name, descriptor);
	}
}
