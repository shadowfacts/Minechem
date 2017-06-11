package minechem.apparatus.prefab.gui.tab;

import java.util.List;
import minechem.Compendium;
import minechem.Config;
import minechem.apparatus.prefab.gui.container.BasicGuiContainer;
import minechem.helper.LocalizationHelper;
import minechem.helper.StringHelper;
import org.lwjgl.opengl.GL11;

public class PatreonGuiTab extends BasicGuiTab
{
    public static boolean enable = Config.enablePatreon;
    private String link;
    private int linkColor;
    private String linkText;

    public PatreonGuiTab(BasicGuiContainer gui)
    {
        super(gui, LocalizationHelper.localize("tab.patreon.text"), 0);
        this.backgroundColor = Compendium.Color.TrueColor.cyan;// I like cyan.
        this.enabled = Config.enablePatreon;
        this.link = Compendium.MetaData.patreon;
        this.linkText = LocalizationHelper.localize("tab.patreon.linkText");
        this.tabTitle = "tab.patreon.headerText";
        this.tabTooltip = "tab.patreon.tooltip";
        this.linkColor = Compendium.Color.TrueColor.yellow;
    }

    @Override
    protected void drawForeground()
    {
        super.drawForeground();
        if (isFullyOpened()) {
            getFontRenderer().drawStringWithShadow(linkText, getLinkX(), getLinkY(), linkColor);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    @Override
    public void addTooltip(List<String> tooltip)
    {
        if (isEnabled() && isFullyOpened() && isLinkAtOffsetPosition(gui.getMouseX(), gui.getMouseY()))
        {
            tooltip.add(link);
        }
    }

    @Override
    public String getIcon()
    {
        return "patreon";
    }

    public int getLinkX()
    {
        return posXOffset();
    }

    public int getLinkY()
    {
        return getPosY() + maxHeight - StringHelper.getSplitStringHeight(getFontRenderer(), linkText, getFontRenderer().getStringWidth(linkText)) - 5;
    }

    public String getLink()
    {
        return link;
    }

    public boolean isLinkAtOffsetPosition(int mouseX, int mouseY)
    {
        if (mouseX >= getLinkX())
        {
            if (mouseX <= getLinkX() + getFontRenderer().getStringWidth(linkText))
            {
                if (mouseY >= getLinkY())
                {
                    if (mouseY <= getLinkY() + StringHelper.getSplitStringHeight(getFontRenderer(), linkText, getFontRenderer().getStringWidth(linkText)))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
