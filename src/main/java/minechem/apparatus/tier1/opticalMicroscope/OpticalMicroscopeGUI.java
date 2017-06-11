package minechem.apparatus.tier1.opticalMicroscope;

import minechem.Compendium;
import minechem.apparatus.prefab.gui.container.BasicGuiContainer;
import minechem.chemical.ChemicalBase;
import minechem.chemical.Element;
import minechem.chemical.Molecule;
import minechem.helper.AchievementHelper;
import minechem.helper.LocalizationHelper;
import minechem.helper.ResearchHelper;
import minechem.item.chemical.ChemicalItem;
import minechem.proxy.client.render.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.lwjgl.opengl.GL11;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class OpticalMicroscopeGUI extends BasicGuiContainer
{

    private ItemStack prevStack;
    protected OpticalMicroscopeTileEntity opticalMicroscope;
    protected IItemHandler microscopeInventory;
    protected static final int eyePieceX = 13;
    protected static final int eyePieceY = 16;
    protected static final int eyePieceW = 54;
    protected static final int eyePieceH = 54;

    public OpticalMicroscopeGUI(InventoryPlayer inventoryPlayer, OpticalMicroscopeTileEntity opticalMicroscope)
    {
        super(new OpticalMicroscopeContainer(inventoryPlayer, opticalMicroscope));
        this.opticalMicroscope = opticalMicroscope;
        microscopeInventory = opticalMicroscope.getCapability(ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        texture = Compendium.Resource.GUI.opticalMicroscope;
        name = LocalizationHelper.localize("tile.opticalMicroscope.name");
    }

    public boolean isMouseInMicroscope()
    {
        return mouseX >= eyePieceX && mouseX <= eyePieceX + eyePieceW && mouseY >= eyePieceY && mouseY <= eyePieceY + eyePieceH;
    }

    private void drawMicroscopeOverlay()
    {
        RenderHelper.resetOpenGLColour();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        float z = this.zLevel;
        this.zLevel = 600.0F;
        drawTexturedModalRect(eyePieceX, eyePieceY, 176, 0, eyePieceH, eyePieceW);
        this.zLevel -= 10.0F;
        drawTexturedModalRect(eyePieceX, eyePieceY, 176, eyePieceH, eyePieceH, eyePieceW);
        this.zLevel = z;
    }

    private void drawInfo()
    {
        ItemStack itemStack = microscopeInventory.getStackInSlot(0);
        if (itemStack != null && itemStack.getItem() instanceof ChemicalItem)
        {
            ChemicalBase chemicalBase = ChemicalBase.readFromNBT(itemStack.getTagCompound());
            fontRendererObj.drawString(chemicalBase.fullName, eyePieceX + eyePieceH + 5, eyePieceY, 0);
            fontRendererObj.drawString("Formula:", eyePieceX + eyePieceH + 5, eyePieceY + 10, 0);
            fontRendererObj.drawString(chemicalBase.getFormula(), eyePieceX + eyePieceH + 5, eyePieceY + 20, 0);

            if (!chemicalBase.isElement())
            {
                RenderHelper.drawScaledTexturedRectUV(eyePieceX + eyePieceW + 50, eyePieceY + 5, 0, 0, 0, 200, 200, 0.3F, ((Molecule) chemicalBase).getStructureResource());
            }

            if (prevStack != itemStack)
            {
                prevStack = itemStack;
                if (chemicalBase.isElement())
                {
                    AchievementHelper.giveAchievement(getPlayer(), (Element) chemicalBase, getWorld().isRemote);
                }
                ResearchHelper.addResearch(getPlayer(), chemicalBase.getResearchKey(), getWorld().isRemote);
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int i1)
    {
        super.drawGuiContainerForegroundLayer(i, i1);
        drawMicroscopeOverlay();
        drawInfo();
    }

    @Override
    public void drawScreen(int x, int y, float z)
    {
        super.drawScreen(x, y, z);
        renderItemAndEffectIntoGUI(microscopeInventory.getStackInSlot(0), x, y);
        renderItemAndEffectIntoGUI(getContainer().getInventoryPlayer().getItemStack(), x, y);
    }

    private void renderItemAndEffectIntoGUI(ItemStack itemStack, int x, int y)
    {
        if (itemStack == null)
        {
            return;
        }

        RenderHelper.enableGUIStandardItemLighting();

        Slot slot = getItemHandlerSlot(microscopeInventory, 0);
        if (slot.getStack() != null)
        {
            GL11.glPushMatrix();
            RenderHelper.setScissor(xSize, ySize, OpticalMicroscopeGUI.eyePieceX, OpticalMicroscopeGUI.eyePieceY, OpticalMicroscopeGUI.eyePieceW, OpticalMicroscopeGUI.eyePieceH);
            int renderX = guiLeft + slot.xPos;
            int renderY = guiTop + slot.yPos;
            GL11.glTranslatef(renderX, renderY, 0.0F);
            GL11.glScalef(3.0F, 3.0F, 1.0F);
            GL11.glTranslatef(-renderX - 5.4F, -renderY - 4.5F, 540.0F);
            Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(slot.getStack(), renderX, renderY);
            RenderHelper.stopScissor();
            GL11.glPopMatrix();
        }

        if (itemStack == getContainer().getInventoryPlayer().getItemStack() && isMouseInMicroscope())
        {
            GL11.glPushMatrix();
            RenderHelper.setScissor(xSize, ySize, OpticalMicroscopeGUI.eyePieceX, OpticalMicroscopeGUI.eyePieceY, OpticalMicroscopeGUI.eyePieceW, OpticalMicroscopeGUI.eyePieceH);
            GL11.glTranslatef(x, y, 0.0F);
            GL11.glScalef(3.0F, 3.0F, 1.0F);
            GL11.glTranslatef(-x - 8.0F, -y - 8.0F, 540.0F);
            Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(itemStack, x, y);
            RenderHelper.stopScissor();
            GL11.glPopMatrix();
        }
    }

    private Slot getItemHandlerSlot(IItemHandler inventory, int index)
    {
        for (Slot slot : inventorySlots.inventorySlots) {
            if (slot instanceof SlotItemHandler && ((SlotItemHandler)slot).getItemHandler() == inventory && slot.getSlotIndex() == index) {
                return slot;
            }
        }
        return null;
    }

}
