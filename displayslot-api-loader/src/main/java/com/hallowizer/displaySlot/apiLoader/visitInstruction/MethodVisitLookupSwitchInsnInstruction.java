package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitLookupSwitchInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final Label dflt;
	private final int[] keys;
	private final Label[] labels;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitLookupSwitchInsn(dflt, keys, labels);
	}
}
