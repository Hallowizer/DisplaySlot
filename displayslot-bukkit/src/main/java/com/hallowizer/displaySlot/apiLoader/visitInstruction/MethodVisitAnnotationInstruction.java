package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;

import com.hallowizer.displaySlot.apiLoader.ProcrastinatingAnnotationVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitAnnotationInstruction implements VisitInstruction<MethodVisitor> {
	private final ProcrastinatingAnnotationVisitor av;
	private final String descriptor;
	private final boolean visible;
	
	@Override
	public void execute(MethodVisitor mv) {
		AnnotationVisitor notProcrastinating = mv.visitAnnotation(descriptor, visible);
		av.stopProcrastinating(notProcrastinating);
	}
}
