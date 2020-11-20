package eu.mcone.community.utils;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.player.LobbyPlayer;

import java.time.LocalDateTime;

public class TimeManager implements Runnable {

    @Override
    public void run() {
        for (GamePlayer gp : CommunityPlugin.getInstance().getOnlineGamePlayers()) {
            if (gp != null) {
                setCurrentRealTime(gp);
            }
        }
    }

    public void setCurrentRealTime(GamePlayer gp) {
            LocalDateTime date = LocalDateTime.now();
            gp.bukkit().setPlayerTime((long) (((double) ((date.getHour() * 60) + date.getMinute())) * (24000D / 1440D) - 6000), false);
    }
}
