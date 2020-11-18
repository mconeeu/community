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
        CommunityPlugin.getGamePlugin().getMessenger().sendTransl(player, "community.command.community.1");
        CommunityPlugin.getGamePlugin().getMessenger().sendTransl(player, "community.command.community.2");
        CommunityPlugin.getGamePlugin().getMessenger().sendTransl(player, "community.command.community.3");
        CommunityPlugin.getGamePlugin().getMessenger().sendTransl(player, "community.command.community.4");
        CommunityPlugin.getGamePlugin().getMessenger().sendTransl(player, "community.command.community.5");
        CommunityPlugin.getGamePlugin().getMessenger().sendTransl(player, "community.command.community.1");
        return false;
    }
}
