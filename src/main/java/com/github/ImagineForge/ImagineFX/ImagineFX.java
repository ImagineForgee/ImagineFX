package com.github.ImagineForge.ImagineFX;

import com.github.ImagineForge.ImagineFX.Events.Listener.GameTickListener;
import com.github.ImagineForge.ImagineFX.Events.Listener.PlayerMoveListener;
import com.github.ImagineForge.ImagineFX.Minecraft.Overrides;
import com.github.ImagineForge.ImagineFX.Events.EventBus;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImagineFX {

    private static final EventBus eventBus = new EventBus();
    private static Overrides mcOverrides;
    private static final ModuleManager moduleManager = new ModuleManager();

    private static final Logger logger = LogManager.getLogger(ImagineFX.class.getName());

    public static void initialize() {
        eventBus.register(new PlayerMoveListener());
        eventBus.register(new GameTickListener());
        System.out.println("ImagineFX initialized successfully!");
    }

    public static void start(Minecraft mc) {
        mcOverrides = new Overrides(eventBus, mc);

        moduleManager.startModules();
        System.out.println("ImagineFX started!");
    }

    public static void stop() {
        moduleManager.stopModules();
        mcOverrides.cleanup();

        System.out.println("ImagineFX stopped!");
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static Overrides getMcOverrides() {
        return mcOverrides;
    }
}
