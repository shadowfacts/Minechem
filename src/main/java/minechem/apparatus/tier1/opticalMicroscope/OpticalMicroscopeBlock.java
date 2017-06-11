package minechem.apparatus.tier1.opticalMicroscope;

import minechem.apparatus.prefab.block.BasicBlockContainer;
import minechem.Compendium;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class OpticalMicroscopeBlock extends BasicBlockContainer
{

    private static final AxisAlignedBB BOX = new AxisAlignedBB(0.2F, 0F, 0.2F, 0.8F, 1.0F, 0.8F);

    public OpticalMicroscopeBlock()
    {
        super(Compendium.Naming.opticalMicroscope, Material.IRON, SoundType.METAL);
    }

    @Override
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOX;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new OpticalMicroscopeTileEntity();
    }

}
