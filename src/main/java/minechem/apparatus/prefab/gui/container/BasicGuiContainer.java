package minechem.apparatus.prefab.gui.container;

import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.TabBase;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import minechem.apparatus.prefab.gui.tab.BasicGuiTab;
import minechem.apparatus.prefab.gui.tab.PatreonGuiTab;
import minechem.helper.LinkHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Basic GUI container class for extending
 */
public class BasicGuiContainer extends GuiBase
{
    protected ResourceLocation backgroundTexture;
    private BasicContainer container;

    public BasicGuiContainer(BasicContainer container)
    {
        super(container);
        this.container = container;
    }

    public BasicContainer getContainer()
    {
        return container;
    }

    public int getXSize()
    {
        return xSize;
    }

    public int getYSize()
    {
        return ySize;
    }

    public EntityPlayer getPlayer()
    {
        return Minecraft.getMinecraft().player;
    }

    public World getWorld()
    {
        return FMLClientHandler.instance().getWorldClient();
    }

    public List<BasicGuiTab> getTabs()
    {
        List<BasicGuiTab> basicGuiTabs = new ArrayList<BasicGuiTab>();
        for (TabBase tabBase : tabs)
        {
            if (tabBase instanceof BasicGuiTab)
            {
                basicGuiTabs.add((BasicGuiTab) tabBase);
            }
        }
        return basicGuiTabs;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        addTab(new PatreonGuiTab(this));
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseButton) throws IOException
    {

        TabBase guiTab = getTabAtPosition(mouseX, mouseY);

        if (guiTab instanceof PatreonGuiTab)
        {
            PatreonGuiTab patreonTab = (PatreonGuiTab) guiTab;

            if (patreonTab.isFullyOpened())
            {
                if (patreonTab.isLinkAtOffsetPosition(x - this.guiLeft, y - this.guiTop))
                {
                    LinkHelper.openLink(patreonTab.getLink(), this);
                    // return here so the machine tab doesn't get closed
                    return;
                }
            }
        }
        super.mouseClicked(x, y, mouseButton);
    }

}
