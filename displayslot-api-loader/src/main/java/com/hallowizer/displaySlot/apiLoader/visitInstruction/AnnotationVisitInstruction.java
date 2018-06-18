package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.AnnotationVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AnnotationVisitInstruction implements VisitInstruction<AnnotationVisitor> {
	private final String name;
	private final Object value;
	
	@Override
	public void execute(AnnotationVisitor av) {
		av.visit(name, value);
	}
}
