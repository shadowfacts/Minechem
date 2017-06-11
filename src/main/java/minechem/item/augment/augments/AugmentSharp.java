package minechem.item.augment.augments;

import com.google.common.collect.Multimap;
import minechem.item.augment.AugmentedItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AugmentSharp extends AugmentBase
{
    public AugmentSharp()
    {
        super("sharp");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user, int level)
    {
        consumeAugment(stack, level);
        return false;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLivingBase, int level)
    {
        consumeAugment(stack, (int) (level / 0.4));
        return super.onBlockDestroyed(stack, world, state, pos, entityLivingBase, level);
    }

    @Override
    public int getHarvestLevelModifier(ItemStack stack, String toolClass, int level)
    {
        return (int) (level / 0.4) + 1;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack, int level)
    {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack, level);
        multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(AugmentedItem.ATTACK_DAMAGE_UUID, "Weapon modifier", (double) level, 0));
        return multimap;
    }
}
