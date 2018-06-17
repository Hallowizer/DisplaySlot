package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitAnnotableParameterCountInstruction implements VisitInstruction<MethodVisitor> {
	private final int parameterCount;
	private final boolean visible;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitAnnotableParameterCount(parameterCount, visible);
	}
}
