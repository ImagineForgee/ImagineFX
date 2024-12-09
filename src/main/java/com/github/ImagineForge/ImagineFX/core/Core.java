package com.github.ImagineForge.ImagineFX.core;

import com.github.ImagineForge.ImagineFX.IModule;

public class Core implements IModule {
    @Override
    public void initialize() {
        logger.info("Starting Core");
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        logger.info("Stopping Core");
    }
}
