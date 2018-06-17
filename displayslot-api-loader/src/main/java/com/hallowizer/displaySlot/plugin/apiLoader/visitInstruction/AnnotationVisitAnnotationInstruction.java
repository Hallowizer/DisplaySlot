package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.AnnotationVisitor;

import com.hallowizer.displaySlot.plugin.apiLoader.ProcrastinatingAnnotationVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AnnotationVisitAnnotationInstruction implements VisitInstruction<AnnotationVisitor> {
	private final ProcrastinatingAnnotationVisitor procrastinating;
	private final String name;
	private final String descriptor;
	
	@Override
	public void execute(AnnotationVisitor av) {
		AnnotationVisitor notProcrastinating = av.visitAnnotation(name, descriptor);
		procrastinating.stopProcrastinating(notProcrastinating);
	}
}
