package com.github.ImagineForge.ImagineFX.Minecraft;

import com.github.ImagineForge.ImagineFX.Events.EventBus;
import com.github.ImagineForge.ImagineFX.Events.Events.GameTickEvent;
import com.github.ImagineForge.ImagineFX.Events.Events.PlayerUpdateEvent;
import com.github.ImagineForge.ImagineFX.ImagineFX;
import com.github.ImagineForge.ImagineFX.ModuleManager;
import com.github.ImagineForge.ImagineFX.modules.networking.PlayerUpdatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;

public class Overrides {
    private final Minecraft mc;
    private final EventBus eventBus;
    private final ModuleManager moduleManager = ImagineFX.getModuleManager();

    public Overrides(EventBus eventBus, Minecraft mc) {
        this.mc = mc;
        this.eventBus = eventBus;
    }

    private long tickCount = 0;
    private long lastTickTime = System.nanoTime();

    public void hookIntoGameTick() {
        mc.addScheduledTask(() -> {
            tickCount++;

            float deltaTime = (System.nanoTime() - lastTickTime) / 1_000_000_000f;
            lastTickTime = System.nanoTime();
            moduleManager.onTick();
            eventBus.post(new GameTickEvent(tickCount, deltaTime, mc.theWorld));
        });
    }

    public void hookIntoPlayerUpdate() {
        Packet<?> myPacket = new PlayerUpdatePacket();
        mc.getNetHandler().addToSendQueue(myPacket);
        if (mc.thePlayer != null) {
            eventBus.post(new PlayerUpdateEvent(mc.thePlayer));
        }
    }

//    private void hookIntoWorldUpdate() {
        // Hook into the world update to track world events, chunk loading, etc.
//        mc.get(new WorldUpdateListener() {
//            @Override
//            public void onWorldUpdate(World world) {
                // Send world updates to your event bus
//                eventBus.post(new WorldUpdateEvent(world));
//            }
//        });
//    }

    public void cleanup() {
    }
}
