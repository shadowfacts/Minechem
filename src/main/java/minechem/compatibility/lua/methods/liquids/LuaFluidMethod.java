package minechem.compatibility.lua.methods.liquids;

import minechem.compatibility.lua.methods.LuaMethod;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;

public abstract class LuaFluidMethod extends LuaMethod
{
    public LuaFluidMethod(String methodName)
    {
        super(methodName);
    }

    public LuaFluidMethod(String methodName, String args, Class... classes)
    {
        super(methodName, args, classes);
    }

    public LuaFluidMethod(String methodName, String args, int numArgs, Class... classes)
    {
        super(methodName, args, numArgs, classes);
    }

    public LuaFluidMethod(String methodName, String args, int minArgs, int maxArgs, Class... classes)
    {
        super(methodName, args, minArgs, maxArgs, classes);
    }

    @Override
    public boolean applies(TileEntity te)
    {
        return te.hasCapability(FLUID_HANDLER_CAPABILITY, EnumFacing.NORTH);
    }
}
