package minechem.proxy.client.gui;

import net.minecraftforge.fml.client.config.GuiConfig;
import minechem.Config;
import minechem.Compendium;
import net.minecraft.client.gui.GuiScreen;

public class GuiModConfig extends GuiConfig
{
    public GuiModConfig(GuiScreen guiScreen)
    {
        super(guiScreen, Config.getConfigElements(), Compendium.Naming.id, false, false, GuiConfig.getAbridgedConfigPath(Config.config.toString()));
    }
}
