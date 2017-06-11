package minechem.achievement;

import java.util.Random;
import minechem.asm.MinechemHooks;
import minechem.proxy.client.render.RenderHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;

public class MinecraftAchievementPage
{
    public static void drawBackground(Minecraft mc, float z, float scale, int columnWidth, int rowHeight)
    {
        int i = columnWidth + 288 >> 4;
        int j = rowHeight + 288 >> 4;
        int k = (columnWidth + 288) % 16;
        int l = (rowHeight + 288) % 16;
        Random random = new Random();
        float scaled = 16.0F / scale;
        int row, column, icon;

        for (row = 0; row * scaled - l < 155.0F; ++row)
        {
            float grayScale = 0.6F - (j + row) / 25.0F * 0.3F;
            MinechemHooks.resetGreyscale(grayScale);

            for (column = 0; column * scaled - k < 224.0F; ++column)
            {
                random.setSeed(mc.getSession().getPlayerID().hashCode() + i + column + (j + row) * 16);
                icon = random.nextInt(1 + j + row) + (j + row) / 2;
                TextureAtlasSprite texture = getTexture(Blocks.BEDROCK);

                if (icon <= 37 && j + row != 35)
                {
                    if (icon == 22)
                    {
                        if (random.nextInt(2) == 0)
                        {
                            texture = getTexture(Blocks.DIAMOND_ORE);
                        }
                        else
                        {
                            texture = getTexture(Blocks.REDSTONE_ORE);
                        }
                    }
                    else if (icon == 10)
                    {
                        texture = getTexture(Blocks.IRON_ORE);
                    }
                    else if (icon == 8)
                    {
                        texture = getTexture(Blocks.COAL_ORE);
                    }
                    else if (icon > 4)
                    {
                        texture = getTexture(Blocks.STONE);
                    }
                    else if (icon > 0)
                    {
                        texture = getTexture(Blocks.DIRT);
                    }
                }

                mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                RenderHelper.drawTexturedRectUV(column * 16 - k, row * 16 - l, z, 16, 16, texture);
            }
        }
    }

    private static TextureAtlasSprite getTexture(Block block) {
        return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(block.getDefaultState());
    }

}
