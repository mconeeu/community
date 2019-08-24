package eu.mcone.community;

import eu.mcone.community.Listener.GeneralPlayerListener;
import eu.mcone.community.Listener.InventoryTriggerListener;
import eu.mcone.community.Listener.PlayerJoinListener;
import eu.mcone.community.Listener.WeatherChangeListener;
import eu.mcone.coresystem.api.bukkit.CorePlugin;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.world.BuildSystem;
import lombok.Getter;
import org.bukkit.ChatColor;

public class Community extends CorePlugin {

    @Getter
    private static Community instance;

    @Getter
    private BuildSystem buildSystem;

    public Community() {
        super("community", ChatColor.LIGHT_PURPLE, "community.prefix");
        instance = this;

        buildSystem = CoreSystem.getInstance().initialiseBuildSystem(BuildSystem.BuildEvent.BLOCK_BREAK, BuildSystem.BuildEvent.BLOCK_PLACE, BuildSystem.BuildEvent.INTERACT);

        registerEvents(
                new PlayerJoinListener(),
                new InventoryTriggerListener(),
                new GeneralPlayerListener(),
                new WeatherChangeListener()
        );
    }

    @Override
    public void onEnable() {
        sendConsoleMessage("§aVersion §f" + this.getDescription().getVersion() + "§a enabled...");
    }

    @Override
    public void onDisable() {
        sendConsoleMessage("§cPlugin disabled!");
    }

}
