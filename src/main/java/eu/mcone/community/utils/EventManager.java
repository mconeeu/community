package eu.mcone.community.utils;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.utils.events.Event;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class EventManager {

    @Getter
    public HashSet<Event> currentEvent = new HashSet<>();
    @Getter
    public HashMap<Player, Event> eventList = new HashMap<>();

    private boolean isLoading = false;

    public void auto(Player player, Event event) {
        if (!isLoading) {
            if (!currentEvent.contains(event)) {
                toggleEvent(event, true);
                eventList.put(player, event);
            } else {
                toggleEvent(event, false);
                eventList.remove(player, event);
            }
        } else {
            CommunityPlugin.getInstance().getMessenger().send(player, "§4Bitte warte bis das Event gestartet wurde!");
        }
    }

    private void toggleEvent(Event event, boolean start) {
        for (CorePlayer cp : CoreSystem.getInstance().getOnlineCorePlayers()) {
            CoreSystem.getInstance().getSoundManager().playClick(cp.bukkit());
            if (start) {
                isLoading = true;

                CommunityPlugin.getInstance().getMessenger().send(cp.bukkit(), "§2Ein §a" + event.getName() + "§2 Event startet in 5 Sekunden!");
                Bukkit.getScheduler().runTaskLater(CommunityPlugin.getInstance(), () -> {
                    CommunityPlugin.getInstance().getMessenger().send(cp.bukkit(), "§2Das §a" + event.getName() + "§2 Event startet in 4 Sekunden!");
                    CoreSystem.getInstance().getSoundManager().playClick(cp.bukkit());
                    Bukkit.getScheduler().runTaskLater(CommunityPlugin.getInstance(), () -> {
                        CommunityPlugin.getInstance().getMessenger().send(cp.bukkit(), "§2Das §a" + event.getName() + "§2 Event startet in 3 Sekunden!");
                        CoreSystem.getInstance().getSoundManager().playClick(cp.bukkit());
                        Bukkit.getScheduler().runTaskLater(CommunityPlugin.getInstance(), () -> {
                            CommunityPlugin.getInstance().getMessenger().send(cp.bukkit(), "§2Das §a" + event.getName() + "§2 Event startet in §l2 Sekunden!");
                            CoreSystem.getInstance().getSoundManager().playClick(cp.bukkit());
                            Bukkit.getScheduler().runTaskLater(CommunityPlugin.getInstance(), () -> {
                                CommunityPlugin.getInstance().getMessenger().send(cp.bukkit(), "§2Das §a" + event.getName() + "§2 Event startet in §leiner Sekunde!");
                                CoreSystem.getInstance().getSoundManager().playClick(cp.bukkit());
                                Bukkit.getScheduler().runTaskLater(CommunityPlugin.getInstance(), () -> {

                                    CoreSystem.getInstance().createTitle().fadeIn(1).fadeOut(1).stay(2).title("§a" + event.getName() + " §lEvent").send(cp.bukkit());
                                    CommunityPlugin.getInstance().getMessenger().send(cp.bukkit(), "§2Das §a" + event.getName() + "§2 §lEvent§2 startet nun!");
                                    CoreSystem.getInstance().getSoundManager().playEpic(cp.bukkit());

                                    currentEvent.add(event);
                                    isLoading = false;

                                }, 20);
                            }, 20);
                        }, 20);
                    }, 20);
                }, 20);

            } else {
                cancelEventMessages(event);
                currentEvent.remove(event);
            }
        }
    }

    public void cancelEventFromPlayer(Player p) {
        if (eventList.containsKey(p)) {

            cancelEventMessages(eventList.get(p));
            currentEvent.remove(eventList.get(p));
            eventList.remove(p);
        }
    }

    private void cancelEventMessages(Event event) {
        for (CorePlayer cp : CoreSystem.getInstance().getOnlineCorePlayers()) {
            CoreSystem.getInstance().createTitle().fadeIn(1).fadeOut(1).stay(2).title("§4" + event.getName() + " §lEvent").send(cp.bukkit());
            CommunityPlugin.getInstance().getMessenger().send(cp.bukkit(), "§4Das §c" + event.getName() + "§4 Event ist nun beendet!");
        }
    }
}
