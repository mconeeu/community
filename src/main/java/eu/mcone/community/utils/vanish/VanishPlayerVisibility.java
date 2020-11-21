package eu.mcone.community.utils.vanish;

import eu.mcone.coresystem.api.bukkit.facades.Transl;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum VanishPlayerVisibility {

    EVERYBODY(
            "Spieler sichtbar",
            new ItemBuilder(Material.INK_SACK, 1, DyeColor.LIME.getDyeData())
                    .displayName(Transl.get("system.inventory.playerhider.all"))

    ),
    ONLY_VIPS(
            "Nur VIPs sichtbar",
            new ItemBuilder(Material.INK_SACK, 1, DyeColor.PURPLE.getDyeData())
                    .displayName(Transl.get("system.inventory.playerhider.vip"))

    ),
    //TODO: Add ONLY_FRIENDS (waiting for coresystem friend system feature)
    NOBODY(
            "Spieler unsichtbar",
            new ItemBuilder(Material.INK_SACK, 1, DyeColor.GRAY.getDyeData())
                    .displayName(Transl.get("system.inventory.playerhider.none"))
    );

    private final String name;
    private final ItemBuilder item;

    VanishPlayerVisibility(String name, ItemBuilder item) {
        this.name = name;
        this.item = item;
    }

    public VanishPlayerVisibility getNextVanishPlayerVisibility() {
        int i = ordinal();

        if (i >= 2) {
            return EVERYBODY;
        } else return values()[++i];
    }

}
