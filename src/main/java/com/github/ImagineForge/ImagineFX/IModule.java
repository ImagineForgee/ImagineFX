package com.github.ImagineForge.ImagineFX;


import org.apache.logging.log4j.Logger;

public interface IModule {

    void initialize();

    void start();

    void stop();

    Logger logger = ImagineFX.getLogger();

}
