package minechem.apparatus.prefab.gui.element;

import java.util.ArrayList;
import java.util.List;
import minechem.helper.ColourHelper;
import minechem.helper.LocalizationHelper;
import minechem.Compendium;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * A tank to display in a GUI
 *
 * @author way2muchnoise
 */
public class GuiFluidTank extends GuiElement
{
    private IFluidTank tank;
    private int colour;

    /**
     * Make a take with given properties
     *
     * @param tank   the tank to display
     * @param posX   the x pos of the element (origin is the left-top of the parent gui)
     * @param posY   the y pos of the element (origin is the left-top of the parent gui)
     * @param width  the width of the element
     * @param height the height of the element
     */
    public GuiFluidTank(IFluidTank tank, int posX, int posY, int width, int height)
    {
        super(posX, posY, width, height);
        this.tank = tank;
        this.colour = Compendium.Color.TrueColor.blue;
    }

    /**
     * Make a tank using the default width and height
     *
     * @param tank the tank to display
     * @param posX the x pos of the element (origin is the left-top of the parent gui)
     * @param posY the y pos of the element (origin is the left-top of the parent gui)
     */
    public GuiFluidTank(IFluidTank tank, int posX, int posY)
    {
        this(tank, posX, posY, 18, 39);
    }

    /**
     * Set the colour of the line around the tank
     *
     * @param colour in Integer form
     * @return the GuiFluidTank
     */
    public GuiFluidTank setColour(int colour)
    {
        this.colour = colour;
        return this;
    }

    @Override
    public void draw(int guiLeft, int guiTop)
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glColor3f(ColourHelper.getRed(colour), ColourHelper.getGreen(colour), ColourHelper.getBlue(colour));

        bindTexture(Compendium.Resource.GUI.Element.fluidTank);
        drawTexturedModalRect(guiLeft + posX, guiTop + posY, 0, 0, 18, 39, width, height);

        GL11.glColor3f(1.0F, 1.0F, 1.0F);

        int iconHeightRemainder = (height - 4) % 16;
        int iconWidthRemainder = (width - 2) % 16;
        FluidStack fluidStack = tank.getFluid();

        if (fluidStack != null && fluidStack.amount > 0)
        {
//            TODO: fixme
//            bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
//
//            ResourceLocation fluidTex = fluidStack.getFluid().getStill(fluidStack);
//            // Top left corner
//
//            drawTexturedModelRectFromIcon(guiLeft + posX + 1, guiTop + posY + 2, fluidTex, iconWidthRemainder, iconHeightRemainder);
//            for (int i = 0; i <= (width - 4) / 16; i++)
//            {
//                // Top right only draw when more then 1 pass is needed
//                if (i > 0)
//                {
//                    drawTexturedModelRectFromIcon(guiLeft + posX + 1 + (i - 1) * 16 + iconWidthRemainder, guiTop + posY + 2, fluidTex, 16, iconHeightRemainder);
//                }
//                for (int ii = 0; ii < (height - 6) / 16; ii++)
//                {
//                    // Bottom right only draw when more then 1 pass is needed
//                    if (i > 0)
//                    {
//                        drawTexturedModelRectFromIcon(guiLeft + posX + 1 + (i - 1) * 16 + iconWidthRemainder, guiTop + posY + 2 + ii * 16 + iconHeightRemainder, fluidTex, 16, 16);
//                    }
//                    // Bottom left
//                    drawTexturedModelRectFromIcon(guiLeft + posX + 1, guiTop + posY + 2 + ii * 16 + iconHeightRemainder, fluidTex, iconWidthRemainder, 16);
//                }
//            }

            bindTexture(Compendium.Resource.GUI.Element.fluidTank);
            drawTexturedModalRect(guiLeft + posX + 2, guiTop + posY + 1, 1, 1, 15, 37 - ((int) ((38) * ((float) fluidStack.amount / tank.getCapacity()))), width - 3, height - 2 - ((int) ((height - 1) * ((float) fluidStack.amount / tank.getCapacity()))));
        }

        bindTexture(Compendium.Resource.GUI.Element.fluidTank);
        drawTexturedModalRect(guiLeft + posX + 1, guiTop + posY + 1, 19, 1, 16, 37, width - 2, height - 2);

        drawTooltip(Mouse.getX() - guiLeft, Mouse.getY() - guiTop);

        GL11.glEnable(GL11.GL_LIGHTING);
    }

    public void drawTooltip(int mouseX, int mouseY)
    {
        if (!mouseInTank(mouseX, mouseY))
        {
            return;
        }

        List<String> description = new ArrayList<String>();
        FluidStack fluidStack = tank.getFluid();

        if (fluidStack == null || fluidStack.getFluid() == null)
        {
            description.add(LocalizationHelper.localize("gui.element.tank.empty"));
        } else
        {
            if (fluidStack.amount > 0)
            {
                String amountToText = fluidStack.amount + LocalizationHelper.localize("gui.element.tank.mB");

                description.add(fluidStack.getLocalizedName());
                description.add(amountToText);
            }
        }
        drawHoveringText(description, mouseX, mouseY, getFontRenderer());
    }

    private boolean mouseInTank(int x, int y)
    {
        return x >= this.posX && x < this.posX + width - 2 && y >= this.posY && y < this.posY + height - 2;
    }
}
