package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitLocalVariableInstruction implements VisitInstruction<MethodVisitor> {
	private final String name;
	private final String descriptor;
	private final String signature;
	private final Label start;
	private final Label end;
	private final int index;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitLocalVariable(name, descriptor, signature, start, end, index);
	}
}
