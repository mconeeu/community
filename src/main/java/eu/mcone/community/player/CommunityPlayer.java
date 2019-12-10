package eu.mcone.community.player;


import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.player.GameAPIPlayer;

public class CommunityPlayer extends GameAPIPlayer<CommunityPlayerProfile> {

    public CommunityPlayer(CorePlayer player) {
        super(CommunityPlugin.getPlugin(), player);
    }

    @Override
    protected CommunityPlayerProfile loadData() {
        return CommunityPlugin.getInstance().loadGameProfile(corePlayer.bukkit(), CommunityPlayerProfile.class);
    }

    @Override
    protected void saveData() {
        super.saveData();
        CommunityPlugin.getInstance().saveGameProfile(new CommunityPlayerProfile(corePlayer.bukkit()));
    }

}
