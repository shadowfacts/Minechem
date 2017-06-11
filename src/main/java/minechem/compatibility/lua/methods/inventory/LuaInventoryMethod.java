package minechem.compatibility.lua.methods.inventory;

import minechem.compatibility.lua.methods.LuaMethod;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public abstract class LuaInventoryMethod extends LuaMethod
{
    public LuaInventoryMethod(String methodName)
    {
        super(methodName);
    }

    public LuaInventoryMethod(String methodName, String args, Class... classes)
    {
        super(methodName, args, classes);
    }

    public LuaInventoryMethod(String methodName, String args, int minArgs, int maxArgs, Class... classes)
    {
        super(methodName, args, minArgs, maxArgs, classes);
    }

    @Override
    public boolean applies(TileEntity te)
    {
        return te.hasCapability(ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
    }
}
