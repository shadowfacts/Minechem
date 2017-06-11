package minechem.item.augment.augments;

import com.google.common.collect.Multimap;
import java.util.Random;
import minechem.Compendium;
import minechem.helper.LocalizationHelper;
import minechem.item.augment.IAugmentItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidContainerItem;

public abstract class AugmentBase implements IAugment
{
    private final String key;
    private final String localizationKey;
    public static final Random rand = new Random(System.currentTimeMillis());

    public AugmentBase(String key)
    {
        this.key = key;
        this.localizationKey = "augment." + key;
    }

    @Override
    public final String getKey()
    {
        return key;
    }

    /**
     * @param stack
     * @param level
     * @return max Level of damage can be applied
     */
    @Override
    public int getUsableLevel(ItemStack stack, int level)
    {
        NBTTagCompound augment = stack.getTagCompound().getCompoundTag(this.getKey());
        ItemStack augmentItem = ItemStack.loadItemStackFromNBT(augment.getCompoundTag(Compendium.NBTTags.item));
        if (augmentItem == null || augmentItem.getItem() == null)
        {
            return -1;
        }
        return dischargeAugment(augmentItem, level, false);
    }

    @Override
    public int getMaxLevel()
    {
        return 5;
    }

    /**
     * @return Localized String name of the augment;
     */
    @Override
    public String getLocalizedName()
    {
        return LocalizationHelper.localize(localizationKey);
    }

    /**
     * @param level
     * @return flavour text to display for level
     */
    @Override
    public String getDisplayText(int level)
    {
        return String.valueOf(level);
    }

    /**
     * @param stack
     * @param level
     * @return int level of augment applied
     */
    @Override
    public int consumeAugment(ItemStack stack, int level)
    {
        NBTTagCompound augment = stack.getTagCompound().getCompoundTag(this.getKey());
        ItemStack augmentItem = ItemStack.loadItemStackFromNBT(augment.getCompoundTag(Compendium.NBTTags.item));
        if (augmentItem == null || augmentItem.getItem() == null)
        {
            return -1;
        }
        int discharged = dischargeAugment(augmentItem, level, true);
        augment.setTag(Compendium.NBTTags.item, augmentItem.writeToNBT(new NBTTagCompound()));
        return discharged;
    }

    /**
     * Actually handles the draining/damaging of Augments from the stored container
     *
     * @param stack
     * @param level
     * @param discharge
     * @return int: max level <= {@param level} that can be used
     */
    public int dischargeAugment(ItemStack stack, int level, boolean discharge)
    {
        if (stack.getItem() instanceof IFluidContainerItem)
        {
            while (!this.drain((IFluidContainerItem) stack.getItem(), stack, this.getVolumeConsumed(level), false) && level >= 0)
            {
                level--;
            }
            if (discharge && level >= 0)
            {
                drain((IFluidContainerItem) stack.getItem(), stack, this.getVolumeConsumed(level), true);
            }
            return level;
        } else if (stack.getItem() instanceof IAugmentItem)
        {
            if (discharge)
            {
                return ((IAugmentItem) stack.getItem()).consumeLevel(stack, this, this.getVolumeConsumed(level));
            }
            return ((IAugmentItem) stack.getItem()).getMaxLevel(stack, this, this.getVolumeConsumed(level));
        } else if (stack.isItemStackDamageable())
        {
            while (this.getDamageDone(level) > stack.getMaxDamage() - stack.getItemDamage() && level >= 0)
            {
                level--;
            }
            if (discharge && level >= 0)
            {
                stack.attemptDamageItem(this.getDamageDone(level), rand);
            }
            return level;
        }
        return -1;
    }

    /**
     * Attempts to drain passed ItemStack for FluidContainer augments
     *
     * @param item
     * @param stack
     * @param volume
     * @param doDrain
     * @return true for volume is drainable
     */
    public boolean drain(IFluidContainerItem item, ItemStack stack, int volume, boolean doDrain)
    {
        return volume == item.drain(stack, volume, doDrain).amount;
    }

    /**
     * @param level
     * @return Fluid to drain from FluidContainer Augment for given Augment level
     */
    public int getVolumeConsumed(int level)
    {
        return level * 10 + 5;
    }

    /**
     * @param level
     * @return Damage to do to an ItemStack augment for given Augment level
     */
    public int getDamageDone(int level)
    {
        return level + 1;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLivingBase, int level)
    {
        return false;
    }

    /**
     * @param stack
     * @param player
     * @param level
     * @return false to cancel drop
     */
    @Override
    public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player, int level)
    {
        return true;
    }

    /**
     * Called by the default implemetation of EntityItem's onUpdate method, allowing for cleaner control over the update of the item without having to write a subclass.
     *
     * @param stack
     * @param entityItem The entity Item
     * @param level
     * @return Return true to skip any further update code.
     */
    @Override
    public boolean onEntityItemUpdate(ItemStack stack, EntityItem entityItem, int level)
    {
        return false;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return True if something happen and false if it don't.
     * @param stack
     * @param player
     * @param world
     * @param pos
     * @param hand
     * @param side
     * @param hitX
     * @param hitY
     * @param hitZ
     * @param level
     */
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ, int level)
    {
        return EnumActionResult.PASS;
    }

    /**
     * @param stack
     * @param player
     * @param world
     * @param pos
     * @param side
     * @param hitX
     * @param hitY
     * @param hitZ
     * @param hand
     * @param level      @return true to prevent further processing
     * */
    @Override
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand, int level)
    {
        return EnumActionResult.PASS;
    }

    /**
     * Called when a entity tries to play the 'swing' animation.
     *
     * @param stack        The Item stack
     * @param entityLiving The entity swinging the item.
     * @param level
     * @return True to cancel any further processing by EntityLiving
     */
    @Override
    public boolean onEntitySwing(ItemStack stack, EntityLivingBase entityLiving, int level)
    {
        return false;
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     *  @param stack
     * @param player
     * @param entityLivingBase
     * @param hand
     * @param level
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entityLivingBase, EnumHand hand, int level)
    {
        return false;
    }

    /**
     * Called when the player Left Clicks (attacks) an entity. Processed before damage is done, if return value is true further processing is canceled and the entity is not attacked.
     *
     * @param stack  The Item being used
     * @param player The player that is attacking
     * @param entity The entity being attacked
     * @param level
     * @return True to cancel the rest of the interaction.
     */
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity, int level)
    {
        return false;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     * @param stack
     * @param world
     * @param player
     * @param hand
     * @param level
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand, int level)
    {
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player, int level)
    {
        return stack;
    }

    /**
     * Called each tick as long the item is on a player inventory.
     *
     * @param stack
     * @param world
     * @param entity
     * @param slot
     * @param bool
     * @param level
     */
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean bool, int level)
    {

    }

    /**
     * Called each tick while using an item.
     *  @param stack  The Item being used
     * @param player The Player using the item
     * @param count  The amount of time in tick the item has been used for continuously
     * @param level
     */
    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count, int level)
    {

    }

    /**
     * @param stack
     * @param prevDigSpeed unmodified dig speed
     * @param state
     * @param metadata
     * @param level        @return
     */
    @Override
    public float getModifiedDigSpeed(ItemStack stack, float prevDigSpeed, IBlockState state, int metadata, int level)
    {
        return prevDigSpeed;
    }

    /**
     * @param stack
     * @param toolClass
     * @param level
     * @return modifier to tool level
     */
    @Override
    public int getHarvestLevelModifier(ItemStack stack, String toolClass, int level)
    {
        return 0;
    }

    /**
     *
     * @param slot
     * @param stack
     * @param level
     * @return Attribute Modifiers to the base tools attributes.
     */
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack, int level)
    {
        return Items.DIAMOND.getAttributeModifiers(slot, null);
    }

    /**
     * @param stack
     * @param level
     * @return float value between 0 and 1 indicating probability of damage not being applied to the tool
     */
    @Override
    public float setDamageChance(ItemStack stack, int level)
    {
        return 0;
    }

    /**
     * @param stack
     * @param level
     * @return int modifier to EntityItem lifespan (base 6000)
     */
    @Override
    public int getEntityLifespanModifier(ItemStack stack, int level)
    {
        return 0;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user, int level)
    {
        return false;
    }
}
