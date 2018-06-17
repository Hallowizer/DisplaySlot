package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitLabelInstruction implements VisitInstruction<MethodVisitor> {
	private final Label label;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitLabel(label);
	}
}
