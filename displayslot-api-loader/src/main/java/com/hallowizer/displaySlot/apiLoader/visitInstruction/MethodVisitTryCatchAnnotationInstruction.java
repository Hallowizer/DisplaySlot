package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.TypePath;

import com.hallowizer.displaySlot.apiLoader.ProcrastinatingAnnotationVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitTryCatchAnnotationInstruction implements VisitInstruction<MethodVisitor> {
	private final ProcrastinatingAnnotationVisitor av;
	private final int typeRef;
	private final TypePath typePath;
	private final String descriptor;
	private final boolean visible;
	
	@Override
	public void execute(MethodVisitor mv) {
		AnnotationVisitor notProcrastinating = mv.visitTryCatchAnnotation(typeRef, typePath, descriptor, visible);
		av.stopProcrastinating(notProcrastinating);
	}
}
