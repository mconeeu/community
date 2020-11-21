package eu.mcone.community.commands;

import eu.mcone.community.inventory.EventInventory;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import org.bukkit.entity.Player;

public class EventCMD extends CorePlayerCommand {

    public EventCMD() {
        super("event", "community.events", "e");
    }

    @Override
    public boolean onPlayerCommand(Player p, String[] args) {
        new EventInventory(p);

        return false;
    }
}
