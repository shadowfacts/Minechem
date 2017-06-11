package minechem.item.augment.augments;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AugmentFlint extends AugmentBase
{
    public AugmentFlint()
    {
        super("flint");
    }

    @Override
    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ, int level)
    {
        pos = pos.offset(side);
        if (!world.isRemote && player != null && player.isSneaking() && player.canPlayerEdit(pos, side, null) && world.isAirBlock(pos) && Blocks.FIRE.canPlaceBlockAt(world, pos))
        {
            if (consumeAugment(stack, level) > -1)
            {
                world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1f, rand.nextFloat() * 0.4f + 0.8f);
                world.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.PASS;
    }
}
