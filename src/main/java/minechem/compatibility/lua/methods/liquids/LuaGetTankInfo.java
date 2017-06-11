package minechem.compatibility.lua.methods.liquids;

import java.util.HashMap;
import java.util.Map;
import minechem.Compendium;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;

public class LuaGetTankInfo extends LuaFluidMethod
{
    public LuaGetTankInfo()
    {
        super("getTankInfo", "(Direction direction)", 0, 1, String.class);
    }

    @Override
    public Object[] action(TileEntity te, Object[] args) throws Exception
    {
        EnumFacing facing;
        if (args.length == 0)
        {
            facing = EnumFacing.NORTH;
        } else
        {
            facing = EnumFacing.valueOf((String) args[1]);
        }
        return new Object[]
        {
            tanksToMap(te.getCapability(FLUID_HANDLER_CAPABILITY, facing).getTankProperties())
        };
    }

    public static Map<Number, Map<String, Object>> tanksToMap(IFluidTankProperties[] tanks)
    {
        Map<Number, Map<String, Object>> result = new HashMap<>();
        for (int i = 0; i < tanks.length; i++)
        {
            if (tanks[i] != null)
            {
                result.put(i, getTankMap(tanks[i]));
            }
        }
        return result;
    }

    public static Map<String, Object> getTankMap(IFluidTankProperties tank)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        if (tank.getContents() != null)
        {
            result.put(Compendium.NBTTags.fluid, tank.getContents().getFluid().getName());
            result.put(Compendium.NBTTags.amount, tank.getContents().amount);
        }
        result.put(Compendium.NBTTags.capacity, tank.getCapacity());
        return result;
    }

}
