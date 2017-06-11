package minechem.compatibility.openblocks;

import minechem.Compendium;
import minechem.compatibility.CompatBase;
import minechem.helper.LogHelper;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import java.lang.reflect.Method;

public class OpenBlocksCompat extends CompatBase
{
    @Override
    protected void init()
    {
        FMLInterModComms.sendMessage(mod.getModId(), "donateUrl", Compendium.MetaData.patreon);
    }
}
