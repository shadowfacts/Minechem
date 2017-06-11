package minechem.item.augment.augments;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import minechem.item.augment.IAugmentedItem;
import minechem.registry.BlockRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class AugmentLight extends AugmentBase
{
    public AugmentLight()
    {
        super("light");
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBlockHarvest(BlockEvent.HarvestDropsEvent event)
    {
        if (event.getHarvester() != null)
        {
//            TODO: this needs a hand parameter
            ItemStack stack = event.getHarvester().getHeldItem(EnumHand.MAIN_HAND);
            if (stack != null && stack.getItem() instanceof IAugmentedItem)
            {
                IAugmentedItem augmentedItem = (IAugmentedItem) stack.getItem();
                int level = augmentedItem.getAugmentLevel(stack, this);
                if (level > -1 && event.getWorld().getBlockState(event.getPos()).getLightValue(event.getWorld(), event.getPos()) < 8)
                {
                    consumeAugment(stack, level);
                    event.getWorld().setBlockState(event.getPos(), BlockRegistry.blockLight.forLevel(level), 3);
                }
            }
        }
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ, int level)
    {
        pos = pos.offset(side);
        if (!world.isRemote && world.isAirBlock(pos))
        {
            consumeAugment(stack, level * 2);
            world.setBlockState(pos, BlockRegistry.blockLight.forLevel((int) (level * 1.5F)), 3);
        }
        return EnumActionResult.SUCCESS;
    }

}
