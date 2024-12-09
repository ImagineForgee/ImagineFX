package com.github.ImagineForge.ImagineFX;

import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private final List<IModule> modules = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger();
    private boolean initialized = false;

    public void addModule(IModule module) {
        modules.add(module);
    }

    public void onTick() {
        if (initialized) return;

        if (Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().theWorld != null) {
            initialized = true;
            initializeModules();
        }
    }

    private void initializeModules() {
        for (IModule module : modules) {
            try {
                module.initialize();
                logger.info("Initialized module: {}", module.getClass().getName());
            } catch (Exception e) {
                logger.error("Failed to initialize module: {}", module.getClass().getName(), e);
            }
        }
    }

    public void startModules() {
        for (IModule module : modules) {
            module.start();
        }
    }

    public void stopModules() {
        for (IModule module : modules) {
            module.stop();
        }
    }
}
