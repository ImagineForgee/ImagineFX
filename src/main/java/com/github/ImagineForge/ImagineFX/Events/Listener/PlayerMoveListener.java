package com.github.ImagineForge.ImagineFX.Events.Listener;

import com.github.ImagineForge.ImagineFX.Events.Events.PlayerUpdateEvent;
import com.github.ImagineForge.ImagineFX.Events.Subscribe;
import com.github.ImagineForge.ImagineFX.ImagineFX;
import net.minecraft.client.entity.EntityPlayerSP;

public class PlayerMoveListener {

    @Subscribe
    public void onPlayerUpdate(PlayerUpdateEvent event) {
        EntityPlayerSP player = event.getPlayer();
        double posX = player.posX;
        double posY = player.posY;
        double posZ = player.posZ;

        // player.sendChatMessage("Player Location: X="+posX+", Y="+posY+", Z="+posZ);

        if (posX == 100 && posY == 65 && posZ == -100) {
            ImagineFX.getLogger().info("Player movement cancelled at location X=100, Y=65, Z=-100");
            event.setCancelled(true);
        }
    }
}
