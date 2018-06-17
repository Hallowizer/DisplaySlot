package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;

import com.hallowizer.displaySlot.plugin.apiLoader.ProcrastinatingAnnotationVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitAnnotationDefaultInstruction implements VisitInstruction<MethodVisitor> {
	private final ProcrastinatingAnnotationVisitor av;
	
	@Override
	public void execute(MethodVisitor mv) {
		AnnotationVisitor notProcrastinating = mv.visitAnnotationDefault();
		av.stopProcrastinating(notProcrastinating);
	}
}
