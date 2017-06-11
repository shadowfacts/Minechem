package minechem.compatibility.lua.methods.inventory;

import minechem.compatibility.lua.LuaParser;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class LuaGetStackInSlot extends LuaInventoryMethod
{
    public LuaGetStackInSlot()
    {
        super("getStackInSlot", "(int slot)", Number.class);
    }

    @Override
    public Object[] action(TileEntity te, Object[] args) throws Exception
    {
        ItemStack stack = te.getCapability(ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH).getStackInSlot(((Number) args[0]).intValue());
        if (stack != null)
        {
            return new Object[]
            {
                LuaParser.toLua(stack)
            };
        }
        return new Object[0];
    }
}
