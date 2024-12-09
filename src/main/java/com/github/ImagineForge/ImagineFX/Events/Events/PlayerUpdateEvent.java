package com.github.ImagineForge.ImagineFX.Events.Events;

import com.github.ImagineForge.ImagineFX.Events.Cancellable;
import net.minecraft.client.entity.EntityPlayerSP;

public class PlayerUpdateEvent implements Cancellable {
    private final EntityPlayerSP player;
    private boolean cancelled;
    public PlayerUpdateEvent(EntityPlayerSP player) {
        this.player = player;
    }

    public EntityPlayerSP getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
