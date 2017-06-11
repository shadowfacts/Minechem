package minechem.compatibility.lua.events.triggered.peripheral;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import minechem.apparatus.prefab.peripheral.TilePeripheralBase;
import minechem.compatibility.lua.events.triggered.TriggerEvent;

public abstract class PeripheralTriggerEvent<T extends Event & IPeripheralTriggerEvent> extends TriggerEvent<T>
{
    private final TilePeripheralBase peripheral;

    public PeripheralTriggerEvent(String name, EventBus bus, TilePeripheralBase peripheral)
    {
        super(name, bus);
        this.peripheral = peripheral;
    }

    @Override
    public boolean applies(T event)
    {
        return event.getTileEntity() == peripheral;
    }

    @Override
    public void announce(T event)
    {
        this.announce(peripheral, constructMessage(event));
    }
}
