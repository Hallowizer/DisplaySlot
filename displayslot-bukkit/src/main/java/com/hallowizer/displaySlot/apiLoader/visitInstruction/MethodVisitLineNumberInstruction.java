package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitLineNumberInstruction implements VisitInstruction<MethodVisitor> {
	private final int line;
	private final Label start;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitLineNumber(line, start);
	}
}
