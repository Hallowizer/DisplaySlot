package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitTableSwitchInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final int min;
	private final int max;
	private final Label dflt;
	private final Label[] labels;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitTableSwitchInsn(min, max, dflt, labels);
	}
}
