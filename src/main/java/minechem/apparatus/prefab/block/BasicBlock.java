package minechem.apparatus.prefab.block;

import minechem.Compendium;
import minechem.registry.CreativeTabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/*
 * Extendable class for simple non-container blocks
 */
public abstract class BasicBlock extends Block
{

    /**
     * Unnamed blocks are given a default name
     */
    public BasicBlock()
    {
        this(Compendium.Naming.name + " Basic Block");
    }

    /**
     * Create a basic block with a given name
     *
     * @param blockName unlocalized name of the block
     */
    public BasicBlock(String blockName)
    {
        this(blockName, Material.GRASS, SoundType.PLANT);
    }

    public BasicBlock(String blockName, Material material)
    {
        this(blockName, material, material == Material.CLOTH ? SoundType.CLOTH : material == Material.WOOD ? SoundType.WOOD : material == Material.GLASS ? SoundType.GLASS : material == Material.IRON ? SoundType.METAL : SoundType.PLANT);
    }

    public BasicBlock(String blockName, Material material, SoundType soundType)
    {
        super(material);
        setUnlocalizedName(blockName);
        setRegistryName(blockName);
        setSoundType(soundType);
        setCreativeTab(CreativeTabRegistry.TAB_PRIMARY);
    }

}
