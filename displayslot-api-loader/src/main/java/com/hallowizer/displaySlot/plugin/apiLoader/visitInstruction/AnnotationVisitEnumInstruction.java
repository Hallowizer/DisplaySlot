package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.AnnotationVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AnnotationVisitEnumInstruction implements VisitInstruction<AnnotationVisitor> {
	private final String name;
	private final String descriptor;
	private final String value;
	
	@Override
	public void execute(AnnotationVisitor av) {
		av.visitEnum(name, descriptor, value);
	}
}
