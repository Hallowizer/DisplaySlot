package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitMethodInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final int opcode;
	private final String owner;
	private final String name;
	private final String descriptor;
	private final boolean isInterface;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
	}
}
