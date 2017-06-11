package minechem.apparatus.prefab.energy.rf;

import cofh.api.energy.IEnergyProvider;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "cofh.api.energy.IEnergyProvider", modid = "CoFHAPI|energy")
public class RFHandlerBase extends RFBase implements IEnergyProvider
{
    @Override
    public boolean canConnectEnergy(EnumFacing from)
    {
        return true;
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate)
    {
        return this.energy.extractEnergy(maxExtract, !simulate);
    }

    @Override
    public int getEnergyStored(EnumFacing from)
    {
        return this.energy.getStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from)
    {
        return this.energy.getCapacity();
    }

}
