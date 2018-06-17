package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

public interface VisitInstruction<V> {
	public abstract void execute(V visitor);
}
