package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.AnnotationVisitor;

import com.hallowizer.displaySlot.plugin.apiLoader.ProcrastinatingAnnotationVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AnnotationVisitArrayInstruction implements VisitInstruction<AnnotationVisitor> {
	private final ProcrastinatingAnnotationVisitor procrastinating;
	private final String name;
	
	@Override
	public void execute(AnnotationVisitor av) {
		AnnotationVisitor notProcrastinating = av.visitArray(name);
		procrastinating.stopProcrastinating(notProcrastinating);
	}
}
