package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.TypePath;

import com.hallowizer.displaySlot.plugin.apiLoader.ProcrastinatingAnnotationVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitLocalVariableAnnotationInstruction implements VisitInstruction<MethodVisitor> {
	private final ProcrastinatingAnnotationVisitor av;
	private final int typeRef;
	private final TypePath typePath;
	private final Label[] start;
	private final Label[] end;
	private final int[] index;
	private final String descriptor;
	private final boolean visible;
	
	@Override
	public void execute(MethodVisitor mv) {
		AnnotationVisitor notProcrastinating = mv.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, descriptor, visible);
		av.stopProcrastinating(notProcrastinating);
	}
}
