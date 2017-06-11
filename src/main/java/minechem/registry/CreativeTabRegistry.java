package minechem.registry;

import minechem.Compendium;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabRegistry
{
    public static CreativeTab TAB_PRIMARY = new CreativeTab(Compendium.Naming.name);
    public static CreativeTab TAB_CHEMICALS = new CreativeTab(Compendium.Naming.name + " Chemicals");

    /**
     * Must be inited after the Blocks and Items If you want to used modded Items or Blocks
     */
    public static void init()
    {
        TAB_PRIMARY.setIcon(BlockRegistry.opticalMicroscope);
        TAB_CHEMICALS.setIcon(BlockRegistry.electrolysisBlock);
    }

    /**
     * Better implementation of the CreativeTab which allows {@link net.minecraft.item.ItemStack} to be passed Thus making it possible to add metaData to the Item
     */
    private static class CreativeTab extends CreativeTabs
    {
        private ItemStack iconItemStack;

        public CreativeTab(String label, Item iconItem)
        {
            this(label, new ItemStack(iconItem));
        }

        public CreativeTab(String label, Item iconItem, int meta)
        {
            this(label, new ItemStack(iconItem, 1, meta));
        }

        public CreativeTab(String label, Block iconBlock)
        {
            this(label, new ItemStack(iconBlock));
        }

        public CreativeTab(String label, Block iconBlock, int meta)
        {
            this(label, new ItemStack(iconBlock, 1, meta));
        }

        public void setIcon(Item iconItem)
        {
            this.iconItemStack = new ItemStack(iconItem);
        }

        public void setIcon(Item iconItem, int meta)
        {
            this.iconItemStack = new ItemStack(iconItem, 1, meta);
        }

        public void setIcon(Block iconBlock)
        {
            this.iconItemStack = new ItemStack(iconBlock);
        }

        public void setIcon(Block iconBlock, int meta)
        {
            this.iconItemStack = new ItemStack(iconBlock, 1, meta);
        }

        public CreativeTab(String label, ItemStack iconItemStack)
        {
            super(label);
            this.iconItemStack = iconItemStack;
        }

        public CreativeTab(String label)
        {
            super(label);
        }

        @Override
        public Item getTabIconItem()
        {
            return iconItemStack.getItem();
        }

        @Override
        public ItemStack getIconItemStack()
        {
            return this.iconItemStack;
        }
    }
}
