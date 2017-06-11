package minechem.item.prefab;

import com.google.common.collect.Multimap;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class WrapperItem extends BasicItem
{
    public WrapperItem(String name)
    {
        super(name);
        this.setMaxDamage(Short.MAX_VALUE);
    }

    /**
     * @param wrapper Wrapped ItemStack
     * @return ItemStack contained
     */
    public abstract ItemStack getWrappedItemStack(ItemStack wrapper);

    /**
     * @param wrapper Wrapped ItemStack
     * @return Item contained
     */
    public Item getWrappedItem(ItemStack wrapper)
    {
        return getWrappedItemStack(wrapper).getItem();
    }

    /**
     * @param stack ItemStack to be wrapped
     * @return true if ItemStack is valid
     */
    public abstract boolean isWrappable(ItemStack stack);

    /**
     * @param wrapper ItemStack
     * @param stack   ItemStack to be wrapped
     */
    public abstract void setWrappedItemStack(ItemStack wrapper, ItemStack stack);

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped != null)
        {
            return wrapped.getItem().getItemStackDisplayName(wrapped);
        }
        return super.getItemStackDisplayName(stack);
    }


    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getAttributeModifiers(slot, stack);
        }
        return wrapped.getItem().getAttributeModifiers(slot, wrapped);
    }

//    TODO: IItemColor
//    @Override
//    public int getColorFromItemStack(ItemStack stack, int colour)
//    {
//        ItemStack wrapped = getWrappedItemStack(stack);
//        if (wrapped == null)
//        {
//            return super.getColorFromItemStack(stack, colour);
//        }
//        return wrapped.getItem().getColorFromItemStack(wrapped, colour);
//    }

    @Override
    public int getDamage(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getDamage(stack);
        }
        return wrapped.getItem().getDamage(wrapped);
    }

//    fixme
//    @Override
//    public float getDigSpeed(ItemStack stack, Block block, int metadata)
//    {
//        ItemStack wrapped = getWrappedItemStack(stack);
//        if (wrapped == null)
//        {
//            return super.getDigSpeed(stack, block, metadata);
//        }
//        return wrapped.getItem().getDigSpeed(wrapped, block, metadata);
//    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getDurabilityForDisplay(stack);
        }
        return wrapped.getItem().getDurabilityForDisplay(wrapped);
    }

    @Override
    public int getEntityLifespan(ItemStack stack, World world)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getEntityLifespan(stack, world);
        }
        return wrapped.getItem().getEntityLifespan(wrapped, world);
    }

    @Override
    public FontRenderer getFontRenderer(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getFontRenderer(stack);
        }
        return wrapped.getItem().getFontRenderer(wrapped);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getHarvestLevel(stack, toolClass);
        }
        return wrapped.getItem().getHarvestLevel(wrapped, toolClass);
    }

    @Override
    public boolean getIsRepairable(ItemStack stack, ItemStack stack2)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getIsRepairable(stack, stack2);
        }
        return wrapped.getItem().getIsRepairable(wrapped, stack2);
    }

    @Override
    public int getItemEnchantability(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getItemEnchantability(stack);
        }
        return wrapped.getItem().getItemEnchantability(wrapped);
    }

    @Override
    public int getItemStackLimit(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getItemStackLimit(stack);
        }
        return wrapped.getItem().getItemStackLimit(wrapped);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getItemUseAction(stack);
        }
        return wrapped.getItem().getItemUseAction(wrapped);
    }

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getMaxDamage(stack);
        }
        return wrapped.getItem().getMaxDamage(wrapped);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getMaxItemUseDuration(stack);
        }
        return wrapped.getItem().getMaxItemUseDuration(wrapped);
    }

//    fixme
//    @Override
//    public String getPotionEffect(ItemStack stack)
//    {
//        ItemStack wrapped = getWrappedItemStack(stack);
//        if (wrapped == null)
//        {
//            return super.getPotionEffect(stack);
//        }
//        return wrapped.getItem().getPotionEffect(wrapped);
//    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getRarity(stack);
        }
        return wrapped.getRarity();
    }

    @Override
    public float getSmeltingExperience(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getSmeltingExperience(stack);
        }
        return wrapped.getItem().getSmeltingExperience(wrapped);
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getToolClasses(stack);
        }
        return getToolClasses(wrapped);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.getUnlocalizedName(stack);
        }
        return wrapped.getItem().getUnlocalizedName(wrapped);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.hitEntity(stack, target, user);
        }
        boolean result = wrapped.getItem().hitEntity(wrapped, target, user);
        setDamage(stack, wrapped.getItemDamage());
        if (stack.stackSize < 1 && user instanceof EntityPlayer)
        {
//            TODO: need hand argument to destroy item
//            ((EntityPlayer) user).destroyCurrentEquippedItem();
        }
        return result;
    }

    @Override
    public boolean isBeaconPayment(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.isBeaconPayment(stack);
        }
        return wrapped.getItem().isBeaconPayment(wrapped);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.isBookEnchantable(stack, book);
        }
        return wrapped.getItem().isBookEnchantable(wrapped, book);
    }

    @Override
    public boolean isDamaged(ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.isDamaged(stack);
        }
        return wrapped.getItem().isDamaged(wrapped);
    }

    @Override
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.isValidArmor(stack, armorType, entity);
        }
        return wrapped.getItem().isValidArmor(wrapped, armorType, entity);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.itemInteractionForEntity(stack, player, target, hand);
        }
        boolean result = wrapped.getItem().itemInteractionForEntity(wrapped, player, target, hand);
        if (stack.stackSize < 1)
        {
            player.setHeldItem(hand, null);
        }
        return result;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            onArmorTick(world, player, stack);
        } else
        {
            wrapped.getItem().onArmorTick(world, player, wrapped);
        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entity)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.onBlockDestroyed(stack, world, state, pos, entity);
        }
        boolean result = wrapped.getItem().onBlockDestroyed(wrapped, world, state, pos, entity);
        setDamage(stack, wrapped.getItemDamage());
        if (stack.stackSize < 1 && entity instanceof EntityPlayer)
        {
//            TODO: need hand argument to destroy item
//            ((EntityPlayer) entityLivingBase).destroyCurrentEquippedItem();
        }
        return result;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.onBlockStartBreak(stack, pos, player);
        }
        boolean result = wrapped.getItem().onBlockStartBreak(wrapped, pos, player);
        setDamage(stack, wrapped.getItemDamage());
        if (stack.stackSize < 1)
        {
//            TODO: need hand argument to destroy current item
//            player.destroyCurrentEquippedItem();
        }
        return result;
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            super.onCreated(stack, world, player);
        } else
        {
            wrapped.getItem().onCreated(wrapped, world, player);
        }
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.onDroppedByPlayer(stack, player);
        }
        return wrapped.getItem().onDroppedByPlayer(wrapped, player);
    }

//    TODO: figure out what happened to this
//    @Override
//    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
//    {
//        ItemStack wrapped = getWrappedItemStack(stack);
//        if (wrapped == null)
//        {
//            return super.onEaten(stack, world, player);
//        }
//        return wrapped.getItem().onEaten(wrapped, world, player);
//    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.onEntitySwing(entityLiving, stack);
        }
        return wrapped.getItem().onEntitySwing(entityLiving, wrapped);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.onItemRightClick(stack, world, player, hand);
        }
        ActionResult<ItemStack> res = wrapped.getItem().onItemRightClick(wrapped, world, player, hand);
        if (res.getResult() != null && res.getResult().stackSize > 0)
        {
            setWrappedItemStack(stack, res.getResult());
        }
        return new ActionResult<>(res.getType(), res.getResult());
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.onItemUse(stack, player, world, pos, hand, facing, hitX, hitY, hitZ);
        }
        EnumActionResult result = wrapped.getItem().onItemUse(wrapped, player, world, pos, hand, facing, hitX, hitY, hitZ);
        setDamage(stack, wrapped.getItemDamage());
        if (stack.stackSize < 1)
        {
            player.setHeldItem(hand, null);
        }
        return result;
    }

    @Override
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ, hand);
        }
        EnumActionResult result = wrapped.getItem().onItemUseFirst(wrapped, player, world, pos, side, hitX, hitY, hitZ, hand);
        setDamage(stack, wrapped.getItemDamage());
        if (stack.stackSize < 1)
        {
            player.setHeldItem(hand, null);
        }
        return result;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            return super.onLeftClickEntity(stack, player, entity);
        }
        return wrapped.getItem().onLeftClickEntity(wrapped, player, entity);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entity, int timeLeft)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            super.onPlayerStoppedUsing(stack, world, entity, timeLeft);
        } else
        {
            wrapped.getItem().onPlayerStoppedUsing(wrapped, world, entity, timeLeft);
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean bool)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            super.onUpdate(stack, world, entity, slot, bool);
        } else
        {
            wrapped.getItem().onUpdate(wrapped, world, entity, slot, bool);
        }
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            super.onUsingTick(stack, player, count);
        } else
        {
            wrapped.getItem().onUsingTick(wrapped, player, count);
        }
    }

    @Override
    public void setDamage(ItemStack stack, int damage) // @TODO: Damage stuff is a little screwy at the moment
    {
        ItemStack wrapped = getWrappedItemStack(stack);
        if (wrapped == null)
        {
            super.setDamage(stack, damage);
        } else
        {
            wrapped.getItem().setDamage(wrapped, damage);
            if (wrapped.getMaxDamage() <= damage)
            {
                stack.stackSize--;
            }
            setWrappedItemStack(stack, wrapped);
        }
    }
}
