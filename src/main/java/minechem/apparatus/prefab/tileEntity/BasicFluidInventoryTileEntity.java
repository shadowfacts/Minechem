package minechem.apparatus.prefab.tileEntity;

import minechem.apparatus.prefab.peripheral.TilePeripheralBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

/**
 * Defines basic properties for TileEntities
 */
public abstract class BasicFluidInventoryTileEntity extends TilePeripheralBase
{
    protected FluidTank fluidInventory;
    protected ItemStackHandler inventory;

    public BasicFluidInventoryTileEntity(String name, int inventorySize, int fluidInventorySize)
    {
        super(name);
        inventory = new ItemStackHandler(inventorySize);
        fluidInventory = new FluidTank(fluidInventorySize);
    }

    @Override
    public void markDirty()
    {
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);

        fluidInventory.readFromNBT(tag.getCompoundTag("fluidInventory"));
        inventory.deserializeNBT(tag.getCompoundTag("inventory"));
    }

    @Override
    public void update() {}

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);

        tag.setTag("fluidInventory", fluidInventory.writeToNBT(new NBTTagCompound()));
        tag.setTag("inventory", inventory.serializeNBT());

        return tag;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == ITEM_HANDLER_CAPABILITY || capability == FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == ITEM_HANDLER_CAPABILITY)
        {
            return (T)inventory;
        }
        else if (capability == FLUID_HANDLER_CAPABILITY)
        {
            return (T)fluidInventory;
        }
        else
        {
            return super.getCapability(capability, facing);
        }
    }

}
