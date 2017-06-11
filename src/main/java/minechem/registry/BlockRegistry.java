package minechem.registry;

import net.minecraftforge.fml.common.registry.GameRegistry;
import minechem.Compendium;
import minechem.apparatus.tier1.centrifuge.CentrifugeBlock;
import minechem.apparatus.tier1.centrifuge.CentrifugeTileEntity;
import minechem.apparatus.tier1.electricCrucible.ElectricCrucibleBlock;
import minechem.apparatus.tier1.electricCrucible.ElectricCrucibleTileEntity;
import minechem.apparatus.tier1.electrolysis.ElectrolysisBlock;
import minechem.apparatus.tier1.electrolysis.ElectrolysisTileEntity;
import minechem.apparatus.tier1.opticalMicroscope.OpticalMicroscopeBlock;
import minechem.apparatus.tier1.opticalMicroscope.OpticalMicroscopeTileEntity;
import minechem.blocks.BlockLight;
import minechem.blocks.BlockRedstone;

public class BlockRegistry
{
    public static OpticalMicroscopeBlock opticalMicroscope;
    public static ElectrolysisBlock electrolysisBlock;
    public static ElectricCrucibleBlock electricCrucibleBlock;
    public static CentrifugeBlock centrifugeBlock;
    public static BlockLight blockLight;
    public static BlockRedstone blockRedstone;

    public static void init()
    {
        opticalMicroscope = new OpticalMicroscopeBlock();
        GameRegistry.register(opticalMicroscope);
        GameRegistry.registerTileEntity(OpticalMicroscopeTileEntity.class, Compendium.Naming.opticalMicroscope + "TileEntity");

        electricCrucibleBlock = new ElectricCrucibleBlock();
        GameRegistry.register(electricCrucibleBlock);
        GameRegistry.registerTileEntity(ElectricCrucibleTileEntity.class, Compendium.Naming.electricCrucible + "TileEntity");

        centrifugeBlock = new CentrifugeBlock();
        GameRegistry.register(centrifugeBlock);
        GameRegistry.registerTileEntity(CentrifugeTileEntity.class, Compendium.Naming.centrifuge + "TileEntity");

        electrolysisBlock = new ElectrolysisBlock();
        GameRegistry.register(electrolysisBlock);
        GameRegistry.registerTileEntity(ElectrolysisTileEntity.class, Compendium.Naming.electrolysis + "TileEntity");

        blockLight = new BlockLight();
        GameRegistry.register(blockLight);

        blockRedstone = new BlockRedstone();
        GameRegistry.register(blockRedstone);
    }
}
