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
    public static String getLocalString(String key, Object... params)
    {
        return getLocalString(key, false, params);
    }

    public static String getLocalString(String key, boolean capitalize, Object... params)
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
