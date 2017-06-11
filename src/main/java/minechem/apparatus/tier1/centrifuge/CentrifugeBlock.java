package minechem.apparatus.tier1.centrifuge;

import minechem.Compendium;
import minechem.apparatus.prefab.block.BasicBlockContainer;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 *
 * @author jakimfett
 */
public class CentrifugeBlock extends BasicBlockContainer
{

    private static final AxisAlignedBB BOX = new AxisAlignedBB(0.18F, 0F, 0.18F, 0.82F, 0.46F, 0.82F);

    public CentrifugeBlock()
    {
        super(Compendium.Naming.centrifuge, Material.ANVIL, SoundType.METAL);
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
        return new CentrifugeTileEntity();
    }
}
