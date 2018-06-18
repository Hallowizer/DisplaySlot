package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitTryCatchBlockInstruction implements VisitInstruction<MethodVisitor> {
	private final Label start;
	private final Label end;
	private final Label handler;
	private final String type;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitTryCatchBlock(start, end, handler, type);
	}
}
