package minechem.apparatus.tier1.electrolysis;

import minechem.Compendium;
import minechem.apparatus.prefab.block.BasicBlockContainer;
import minechem.chemical.ChemicalBase;
import minechem.item.chemical.ChemicalItem;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ElectrolysisBlock extends BasicBlockContainer
{

    private static final AxisAlignedBB BOX = new AxisAlignedBB(0.2F, 0F, 0.2F, 0.8F, 0.85F, 0.8F);

    public ElectrolysisBlock()
    {
        super(Compendium.Naming.electrolysis, Material.GLASS, SoundType.GLASS);
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
        return new ElectrolysisTileEntity();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        // @TODO: add "player.capabilities.isCreativeMode" checks before removing/adding items to inventory
        TileEntity activatedTileEntity = world.getTileEntity(pos);
        if (activatedTileEntity instanceof ElectrolysisTileEntity)
        {
            ElectrolysisTileEntity electrolysis = (ElectrolysisTileEntity) activatedTileEntity;
            acquireResearch(player, world);
            if (heldItem != null)
            {
                if (heldItem.getItem() instanceof ChemicalItem)
                {
                    ChemicalBase chemicalBase = ChemicalItem.getChemicalBase(heldItem);
                    if (chemicalBase != null)
                    {
                        byte slot = electrolysis.addItem(heldItem);
                        if (slot == 0 || slot == 1)
                        {
                            electrolysis.fillWithChemicalBase(chemicalBase, slot);
                            player.inventory.decrStackSize(player.inventory.currentItem, 1);
                        }

                    }
                }
            } else
            {
                ChemicalItem chemItem = null;
                if (electrolysis.getRightTube() != null)
                {
                    chemItem = electrolysis.removeItem(1);
                } else if (electrolysis.getLeftTube() != null)
                {
                    chemItem = electrolysis.removeItem(0);
                }

                if (chemItem != null)
                {
                    if (heldItem != null && heldItem.getItem() instanceof ChemicalItem)
                    {
                        // @TODO: attempt to merge held items
                    } else
                    {
                        player.inventory.setInventorySlotContents(player.inventory.getFirstEmptyStack(), new ItemStack(chemItem));
                    }
                }
            }
        }
        return false;
    }

}
