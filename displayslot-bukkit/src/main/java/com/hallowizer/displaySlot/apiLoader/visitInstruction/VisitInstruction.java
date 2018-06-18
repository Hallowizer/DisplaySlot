package com.hallowizer.displaySlot.apiLoader.visitInstruction;

public interface VisitInstruction<V> {
	public abstract void execute(V visitor);
}
