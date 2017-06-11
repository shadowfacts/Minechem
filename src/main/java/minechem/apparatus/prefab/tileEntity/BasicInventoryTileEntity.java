package minechem.apparatus.prefab.tileEntity;

import minechem.apparatus.prefab.peripheral.TilePeripheralBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

/**
 * Defines basic properties for TileEntities
 */
public abstract class BasicInventoryTileEntity extends TilePeripheralBase
{
    protected ItemStackHandler inventory;

    public BasicInventoryTileEntity(String name, int inventorySize)
    {
        super(name);
        inventory = new ItemStackHandler(inventorySize);
    }

    @Override
    public void markDirty()
    {
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        inventory.deserializeNBT(tag.getCompoundTag("inventory"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setTag("inventory", inventory.serializeNBT());

        return tag;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (capability == ITEM_HANDLER_CAPABILITY)
        {
            return (T)inventory;
        }
        else
        {
            return super.getCapability(capability, facing);
        }
    }

}
