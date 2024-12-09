package com.github.ImagineForge.ImagineFX.Events.Events;

import com.github.ImagineForge.ImagineFX.Events.Cancellable;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.Queue;

public class GameTickEvent implements Cancellable {
    private boolean cancelled;

    private final long timestamp;
    private final long tickCount;
    private final float deltaTime;
    private final World worldState;
    private final Queue<Runnable> postTickTasks = new LinkedList<>();

    public GameTickEvent(long tickCount, float deltaTime, World worldState) {
        this.timestamp = System.currentTimeMillis();
        this.tickCount = tickCount;
        this.deltaTime = deltaTime;
        this.worldState = worldState;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getTickCount() {
        return tickCount;
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public World getWorldState() {
        return worldState;
    }

    public void addPostTickTask(Runnable task) {
        postTickTasks.add(task);
    }

    public Queue<Runnable> getPostTickTasks() {
        return postTickTasks;
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
