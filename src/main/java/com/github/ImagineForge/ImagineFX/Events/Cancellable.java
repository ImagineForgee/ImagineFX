package com.github.ImagineForge.ImagineFX.Events;

public interface Cancellable {
    boolean isCancelled();
    void setCancelled(boolean cancelled);
}
