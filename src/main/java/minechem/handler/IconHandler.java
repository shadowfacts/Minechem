package minechem.handler;

import java.util.Map;
import java.util.TreeMap;

import minechem.Compendium;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;

/**
 *
 *
 */
public class IconHandler
{
    private static Map<String, TextureAtlasSprite> icons = new TreeMap<>();

    public static void addIcon(String iconName, String iconPath, TextureMap map)
    {
        icons.put(iconName, map.registerSprite(new ResourceLocation(iconPath)));
    }

    public static TextureAtlasSprite getIcon(String iconName)
    {
        if (icons.containsKey(iconName))
        {
            return icons.get(iconName);
        }
        return icons.get("default");
    }

    public static void registerIcons(TextureStitchEvent.Pre event)
    {
        if (event.getMap() == Minecraft.getMinecraft().getTextureMapBlocks())
        {
            IconHandler.addIcon("default", Compendium.Naming.id + ":guitab/default", event.getMap());
            IconHandler.addIcon("patreon", Compendium.Naming.id + ":guitab/patreon", event.getMap());
        }
    }
}
