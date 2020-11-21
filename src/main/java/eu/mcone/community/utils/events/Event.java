package eu.mcone.community.utils.events;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum Event {

    PVP("PVP", new ItemBuilder(Material.STONE_SWORD).displayName("§aPVP Event"));

    private final String name;
    private final ItemBuilder item;

    Event(String name, ItemBuilder item) {
        this.name = name;
        this.item = item;

    }
}
