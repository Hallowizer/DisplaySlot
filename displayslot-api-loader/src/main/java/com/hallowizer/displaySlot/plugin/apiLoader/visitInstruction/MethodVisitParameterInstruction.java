package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitParameterInstruction implements VisitInstruction<MethodVisitor> {
	private final String name;
	private final int access;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitParameter(name, access);
	}
}
