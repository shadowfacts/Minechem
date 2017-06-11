package minechem.apparatus.tier1.opticalMicroscope;

import minechem.apparatus.prefab.gui.container.BasicContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.SlotItemHandler;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class OpticalMicroscopeContainer extends BasicContainer
{
    /**
     * Container object for the opticalMicroscope
     *
     * @param inventoryPlayer   the player's inventory
     * @param opticalMicroscope the microscope TileEntity
     */
    public OpticalMicroscopeContainer(InventoryPlayer inventoryPlayer, OpticalMicroscopeTileEntity opticalMicroscope)
    {
        bindPlayerInventory(inventoryPlayer);
        addSlotToContainer(new SlotItemHandler(opticalMicroscope.getCapability(ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH), 0, 32, 32));
    }

}
