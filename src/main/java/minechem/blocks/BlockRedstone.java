package minechem.blocks;

import java.util.Random;

import minechem.Compendium;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockRedstone extends Block
{
    private static final Material myAir = new MaterialTransparent(MapColor.AIR);

    private static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);

    public BlockRedstone()
    {
        super(myAir);
        this.setUnlocalizedName(Compendium.Naming.redstone);
        this.setRegistryName(Compendium.Naming.redstone);
    }

    public IBlockState forLevel(int level)
    {
        return getDefaultState().withProperty(POWER, level);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, POWER);
    }

    @Override
    @Deprecated
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return null;
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
    {
        return false;
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        world.scheduleBlockUpdate(pos, this, 20, 1);
        world.notifyNeighborsOfStateChange(pos, this);
    }


    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (world.getBlockState(pos).getBlock() == this)
        {
            world.setBlockToAir(pos);
            world.notifyNeighborsOfStateChange(pos, this);
        }
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }


    @Override
    @Deprecated
    public int getWeakPower(IBlockState blockState, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return blockState.getValue(POWER);
    }

    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return blockState.getValue(POWER);
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
    {
        return false;
    }

    @Override
    @Deprecated
    public boolean canProvidePower(IBlockState state)
    {
        return true;
    }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return false;
    }

}
