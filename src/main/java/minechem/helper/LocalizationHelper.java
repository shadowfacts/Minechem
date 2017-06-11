package minechem.helper;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.util.IllegalFormatException;

/**
 *
 *
 */
public class LocalizationHelper
{
    public static String localize(String key, Object... params)
    {
        return localize(key, false, params);
    }

    public static String localize(String key, boolean capitalize, Object... params)
    {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            String localString;
            try {
                localString = I18n.format(key, params);
            } catch (IllegalFormatException e) {
                localString = "Format error: " + key;
            }
            if (capitalize)
            {
                localString = localString.toUpperCase();
            }
            return localString;
        }
        return key;
    }

}
