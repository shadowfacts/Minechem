package minechem.item.augment.augments;

import minechem.registry.BlockRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AugmentRedstone extends AugmentBase
{
    private static final int[] levels = new int[]
    {
        5, 10, 15
    };

    public AugmentRedstone()
    {
        super("redstone");
    }

    @Override
    public int getUsableLevel(ItemStack stack, int level)
    {
        return level;
    }

    @Override
    public int getMaxLevel()
    {
        return levels.length;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int level)
    {
        pos = pos.offset(side);
        if (!world.isRemote && player != null && player.canPlayerEdit(pos, side, null))
        {
            if (world.isAirBlock(pos))
            {
                world.setBlockState(pos, BlockRegistry.blockRedstone.forLevel(levels[level]), 7);
            }
        }
        return false;
    }

}
