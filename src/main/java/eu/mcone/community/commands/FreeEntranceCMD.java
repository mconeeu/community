package eu.mcone.community.commands;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.gameapi.api.player.GamePlayer;
import org.bukkit.entity.Player;

public class FreeEntranceCMD extends CorePlayerCommand {

    public FreeEntranceCMD() {
        super("FreeEntrance");
    }


    public static boolean freeEntrance = false;

    @Override
    public boolean onPlayerCommand(Player player, String[] args) {
        if (player.hasPermission("community.freeentrance")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    if (!freeEntrance) {
                        freeEntrance = true;
                        CommunityPlugin.getInstance().getMessenger().sendTransl(player, "community.freeentrance.activate.sender");
                        for (GamePlayer gamePlayer : CommunityPlugin.getInstance().getOnlineGamePlayers()) {
                            if (!gamePlayer.bukkit().hasPermission("community.bypass.entrance")) {
                                if (!gamePlayer.hasDefaultItem(DefaultItem.FESTIVAL_ENTRANCE)) {
                                    CommunityPlugin.getInstance().getMessenger().send(gamePlayer.bukkit(), "community.freeentrance.activate.players");
                                }
                            }
                        }
                    } else {
                        CommunityPlugin.getInstance().getMessenger().sendTransl(player, "community.freeentrance.alreadyactive");
                    }

                } else if (args[0].equalsIgnoreCase("off")) {
                    if (freeEntrance) {
                        freeEntrance = false;

                        player.closeInventory();
                        CommunityPlugin.getInstance().getMessenger().sendTransl(player, "community.freeentrance.deactivate.sender");

                        for (GamePlayer gamePlayer : CommunityPlugin.getInstance().getOnlineGamePlayers()) {
                            if (!gamePlayer.bukkit().hasPermission("community.bypass.entrance")) {
                                if (!gamePlayer.hasDefaultItem(DefaultItem.FESTIVAL_ENTRANCE)) {
                                    CommunityPlugin.getInstance().getMessenger().sendTransl(gamePlayer.bukkit(), "community.freeentrace.deactivate.players");
                                    CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(gamePlayer.bukkit(), "entrance-out");
                                }
                            }
                        }
                    } else {
                        CommunityPlugin.getInstance().getMessenger().sendTransl(player, "community.freeentrance.alreadydeactivated");
                    }


                }
            } else {
                CommunityPlugin.getInstance().getMessenger().sendTransl(player, "community.freeentrance.command.syntax");
            }
        } else {
            CommunityPlugin.getInstance().getMessenger().sendTransl(player, "system.command.noperm");
        }

        return false;
    }
}
