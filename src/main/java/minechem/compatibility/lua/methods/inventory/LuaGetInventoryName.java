package minechem.compatibility.lua.methods.inventory;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IWorldNameable;

public class LuaGetInventoryName extends LuaInventoryMethod
{
    public LuaGetInventoryName()
    {
        super("getInventoryName");
    }

    @Override
    public Object[] action(TileEntity te, Object[] args) throws Exception
    {
        return new Object[]
        {
            ((IWorldNameable) te).getName()
        };
    }

    @Override
    public boolean applies(TileEntity te)
    {
        return te instanceof IWorldNameable;
    }

}
