package nl.officialfox.politiemod;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class GPSOverlay {
    private double playerX;
    private double playerY;
    private double playerZ;
    Player player;

    private double destX = 100;
    private double destY = 64;
    private double destZ = 0;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        BlockPos pos = BlockPos.containing(player.getPosition(Minecraft.getInstance().gameRenderer.getRenderDistance()));
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        // Get player position
        playerX = player.posX;
        playerY = player.posY;
        playerZ = player.posZ;

    }

    public void render(float partialTicks) {

        // Calculate distance
        double distance = Math.sqrt(
                Math.pow(destX - playerX, 2) +
                        Math.pow(destY - playerY, 2) +
                        Math.pow(destZ - playerZ, 2)
        );

        // Draw line
        if(distance < 100) {
            drawLine(playerX, playerY, playerZ, destX, destY, destZ, 0, 255, 0);
        } else {
            drawLine(playerX, playerY, playerZ, destX, destY, destZ, 255, 0, 0);
        }

    }

    private void drawLine(double x1, double y1, double z1,
                          double x2, double y2, double z2,
                          int red, int green, int blue) {

        double xDiff = x2 - x1;
        double yDiff = y2 - y1;
        double zDiff = z2 - z1;
        double length = Math.sqrt(xDiff*xDiff + yDiff*yDiff + zDiff*zDiff);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        buffer.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR);
        buffer.vertex(x1, y1, z1).color(red, green, blue, 255).endVertex();
        buffer.vertex(x2, y2, z2).color(red, green, blue, 255).endVertex();

        buffer.draw();
    }

}