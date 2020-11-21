package eu.mcone.community.commands;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.utils.events.Event;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import org.bukkit.entity.Player;

public class CurrentEventCMD extends CorePlayerCommand {

    public CurrentEventCMD() {
        super("currentevent");
    }

    @Override
    public boolean onPlayerCommand(Player p, String[] args) {

        if (!CommunityPlugin.getInstance().getEventManager().getCurrentEvent().isEmpty()) {
            Event event = CommunityPlugin.getInstance().getEventManager().getCurrentEvent().iterator().next();
            CommunityPlugin.getInstance().getMessenger().send(p, "§2Momentan findet das§a " + event.getName() + " §2Event statt!");
        } else {
            CommunityPlugin.getInstance().getMessenger().send(p, "§4Momentan findet kein §cEvent §4statt!");
        }

        return false;
    }
}
