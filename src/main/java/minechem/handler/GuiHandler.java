package minechem.handler;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.IGuiHandler;
import minechem.apparatus.tier1.opticalMicroscope.OpticalMicroscopeContainer;
import minechem.apparatus.tier1.opticalMicroscope.OpticalMicroscopeGUI;
import minechem.apparatus.tier1.opticalMicroscope.OpticalMicroscopeTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    /**
     * Get the GUI container object for the server
     *
     * @param ID     GUI element ID, unused
     * @param player Player entity
     * @param world  World object
     * @param x      World x coordinate
     * @param y      World y coordinate
     * @param z      World z coordinate
     * @return GUI object for the TileEntity
     */
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null)
        {
            // use instanceof to return the correct GUI object
            if (tileEntity instanceof OpticalMicroscopeTileEntity)
            {
                return new OpticalMicroscopeGUI(player.inventory, (OpticalMicroscopeTileEntity) tileEntity);
            }
        }
        return null;
    }

    /**
     * Get the GUI container object for the server
     *
     * @param ID     GUI element ID, unused
     * @param player Player entity
     * @param world  World object
     * @param x      World x coordinate
     * @param y      World y coordinate
     * @param z      World z coordinate
     * @return Container object for the TileEntity
     */
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null)
        {
            // use instanceof to return the correct container object
            if (tileEntity instanceof OpticalMicroscopeTileEntity)
            {
                return new OpticalMicroscopeContainer(player.inventory, (OpticalMicroscopeTileEntity) tileEntity);
            }
        }
        return null;
    }
}
