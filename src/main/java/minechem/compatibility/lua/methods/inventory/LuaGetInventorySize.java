package minechem.compatibility.lua.methods.inventory;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class LuaGetInventorySize extends LuaInventoryMethod
{
    public LuaGetInventorySize()
    {
        super("getInventorySize");
    }

    @Override
    public Object[] action(TileEntity te, Object[] args) throws Exception
    {
        return new Object[]
            {
                te.getCapability(ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH).getSlots()
            };
    }
}
