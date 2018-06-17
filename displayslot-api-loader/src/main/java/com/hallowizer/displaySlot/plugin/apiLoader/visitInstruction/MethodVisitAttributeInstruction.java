package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitAttributeInstruction implements VisitInstruction<MethodVisitor> {
	private final Attribute attribute;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitAttribute(attribute);
	}
}
