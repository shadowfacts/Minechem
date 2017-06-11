package minechem.apparatus.tier1.opticalMicroscope;

import minechem.apparatus.prefab.renderer.BasicTileEntityRenderer;
import minechem.Compendium;

public class OpticalMicroscopeTileEntityRenderer extends BasicTileEntityRenderer<OpticalMicroscopeTileEntity>
{

    public OpticalMicroscopeTileEntityRenderer()
    {
        super(0.4F, 0.0625F);

        setOffset(0.5D, 0.6D, 0.5D);

        model = new OpticalMicroscopeModel();
        texture = Compendium.Resource.Model.microscope;
    }

}
