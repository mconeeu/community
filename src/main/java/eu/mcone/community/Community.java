package eu.mcone.community;

import eu.mcone.community.Listener.*;
import eu.mcone.coresystem.api.bukkit.CorePlugin;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.world.BuildSystem;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import lombok.Getter;
import org.bukkit.ChatColor;

public class Community extends CorePlugin {

    @Getter
    private static Community instance;

    @Getter
    private BuildSystem buildSystem;
    @Getter
    private CoreWorld communityWorld;

    public Community() {
        super("community", ChatColor.LIGHT_PURPLE, "community.prefix");
    }



    @Override
    public void onEnable() {

        instance = this;
        communityWorld = CoreSystem.getInstance().getWorldManager().getWorld("Community");
        CoreSystem.getInstance().getTranslationManager().loadCategories(this);
        CoreSystem.getInstance().enableSpawnCommand(this, communityWorld, 0);

        buildSystem = CoreSystem.getInstance().initialiseBuildSystem(BuildSystem.BuildEvent.BLOCK_BREAK, BuildSystem.BuildEvent.BLOCK_PLACE, BuildSystem.BuildEvent.INTERACT);
        buildSystem.addFilter(BuildSystem.BuildEvent.INTERACT, 69);

        registerEvents(
                new PlayerJoinListener(),
                new InventoryTriggerListener(),
                new GeneralPlayerListener(),
                new WeatherChangeListener(),
                new EntitiyDamageListener(),
                new PlayerQuitListener(),
                new PlayerMoveListener()
        );

        sendConsoleMessage("§aVersion §f" + this.getDescription().getVersion() + "§a enabled...");
    }

    @Override
    public void onDisable() {
        sendConsoleMessage("§cPlugin disabled!");
    }

}
