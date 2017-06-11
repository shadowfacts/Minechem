package minechem.apparatus.prefab.energy.rf;

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "cofh.api.energy.IEnergyReceiver", modid = "CoFHAPI|energy")
public class RFMachineBase extends RFHandlerBase implements IEnergyReceiver
{

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate)
    {
        return this.energy.inputEnergy(maxReceive, !simulate);
    }

}
