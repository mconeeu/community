package eu.mcone.community.commands;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import org.bukkit.entity.Player;

public class CommunityCMD extends CorePlayerCommand {

    public CommunityCMD() {
        super("community");
    }

    @Override
    public boolean onPlayerCommand(Player player, String[] args) {

        CommunityPlugin.getGamePlugin().getMessager().send(player, "§8§m---------- §r§5§lMCONE-Community §8§m----------");
        CommunityPlugin.getGamePlugin().getMessager().send(player, "§7Entwickelt von §fDrMarv");
        CommunityPlugin.getGamePlugin().getMessager().send(player, "§r");
        CommunityPlugin.getGamePlugin().getMessager().send(player, "§7§oWir bemühen uns darum alle Systeme und Spielmodi so effizient wie möglich zu gestalten.");
        CommunityPlugin.getGamePlugin().getMessager().send(player, "§7§oDeshalb sind auch alle von uns verwendeten Plugins ausschließlich selbst entwickelt!");
        CommunityPlugin.getGamePlugin().getMessager().send(player, "§8§m---------- §r§5§lMCONE-Community §8§m----------");


        return false;
    }
}
