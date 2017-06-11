package minechem.handler.message;

import minechem.Minechem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Base class for Messages this place should be used for common {@link net.minecraftforge.fml.common.network.simpleimpl.IMessage} methods You do need to implement
 * {@link net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler}
 */
public abstract class BaseMessage implements IMessage
{
    /**
     * Constructor needed for reflection
     */
    public BaseMessage()
    {
    }

    /**
     * Get the World from the MessageContext
     *
     * @param ctx
     * @return the current World
     */
    public World getWorld(MessageContext ctx)
    {
        return Minechem.proxy.getWorld(ctx);
    }

    /**
     * Get the EntityPlayer from the MessageContext
     *
     * @param ctx
     * @return the current EntityPlayer
     */
    public EntityPlayer getPlayer(MessageContext ctx)
    {
        return Minechem.proxy.getPlayer(ctx);
    }

    /**
     * Get the EntityPlayer from the MessageContext forced on server side
     *
     * @param ctx
     * @return the current EntityPlayer
     */
    public EntityPlayer getServerPlayer(MessageContext ctx)
    {
        return ctx.getServerHandler().playerEntity;
    }

    /**
     * Get the World from the MessageContext forced on server side
     *
     * @param ctx
     * @return the current World
     */
    public World getServerWorld(MessageContext ctx)
    {
        return ctx.getServerHandler().playerEntity.world;
    }

}
