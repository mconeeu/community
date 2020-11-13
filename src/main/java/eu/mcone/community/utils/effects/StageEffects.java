package eu.mcone.community.utils.effects;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum StageEffects {

    FIRE("Feuer", "fire", new ItemBuilder(Material.FLINT, 1).displayName("§c§lFire").create(), EffectTypes.FIRE, true, 4),
    BLASS("Blasen", "smoke", new ItemBuilder(Material.GLASS, 1).displayName("§c§lBlasen").create(), EffectTypes.BLASS, true, 4),
    SNOW("Schnee", "white-", new ItemBuilder(Material.SNOW_BALL, 1).displayName("§c§lSchnee").create(), EffectTypes.SNOW, true, 7),
    KONFETTI("Konfetti", "smoke", new ItemBuilder(Material.FIREWORK, 1).displayName("§c§lKonfetti").create(), EffectTypes.KONFETTI, true, 4),
    MUSIC("Musik Effekte", "music", new ItemBuilder(Material.NOTE_BLOCK, 1).displayName("§c§lNoten Effekte").create(), EffectTypes.MUSIC, true, 18),
    WATER("Wasser", "wather", new ItemBuilder(Material.WATER_BUCKET, 1).displayName("§c§lWasser Effekt").create(), EffectTypes.WATER, true, 4),


    PREMIUMFIRE("Premium Feuer Effekt", "firePremium", new ItemBuilder(Material.FLINT, 1).displayName("§c§lFire").create(), EffectTypes.FIRE_PREMIUM, false, 2);


    private final String name, locName;
    private final ItemStack item;
    private final EffectTypes particle;
    private final Boolean mainStage;
    private final Integer amount;

    StageEffects(String name, String locName, ItemStack item, EffectTypes particle, Boolean mainStage, Integer amount) {
        this.name = name;
        this.locName = locName;
        this.item = item;
        this.particle = particle;
        this.mainStage = mainStage;
        this.amount = amount;
    }
}
