package eu.mcone.community.player;


import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.player.plugin.GamePlayer;

public class CommunityPlayer extends GamePlayer<CommunityPlayerProfile> {

    public CommunityPlayer(CorePlayer player) {
        super(player);
    }

    @Override
    protected CommunityPlayerProfile loadData() {
        return CommunityPlugin.getInstance().loadGameProfile(corePlayer.bukkit(), CommunityPlayerProfile.class);
    }

    @Override
    protected void saveData() {
        CommunityPlugin.getInstance().saveGameProfile(new CommunityPlayerProfile(corePlayer.bukkit()));
    }
}
