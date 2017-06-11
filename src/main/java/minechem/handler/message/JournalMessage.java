package minechem.handler.message;

import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import minechem.item.journal.JournalItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Message used to write knowledge in on an journal on the server side
 */
public class JournalMessage extends BaseMessage implements IMessageHandler<JournalMessage, IMessage>
{
    private UUID uuid;
    private EnumHand hand;

    public JournalMessage()
    {
    }

    public JournalMessage(EntityPlayer player, EnumHand hand)
    {
        this(player.getUniqueID(), hand);
    }

    public JournalMessage(UUID uuid, EnumHand hand)
    {
        this.uuid = uuid;
        this.hand = hand;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.uuid = new UUID(buf.readLong(), buf.readLong());
        this.hand = EnumHand.values()[buf.readInt()];
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(this.uuid.getMostSignificantBits());
        buf.writeLong(this.uuid.getLeastSignificantBits());
        buf.writeInt(this.hand.ordinal());
    }

    @Override
    public IMessage onMessage(JournalMessage message, MessageContext ctx)
    {
        EntityPlayer player = getServerPlayer(ctx);
        if (player.getUniqueID().equals(message.uuid))
        {
            ItemStack heldItem = player.getHeldItem(message.hand);
            if (heldItem.getItem() instanceof JournalItem)
            {
                JournalItem journalItem = (JournalItem) heldItem.getItem();
                journalItem.writeKnowledge(heldItem, player, message.hand, false);
            }
        }
        return null;
    }

}
