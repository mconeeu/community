package eu.mcone.community.utils;

import lombok.Getter;

@Getter
public enum CommunityRegion {

    MAINSTAGE("mainStage", false,"community.stage"),
    STAGEWALL1("stageWall1", false,"community.stage"),
    STAGEWALL2("stageWall2", false,"community.stage"),
    TEAMHOUSE("teamHouse",true, "community.stage"),
    PREMIUMSTAGE("premiumStage", false, "community.premium.stage"),
    STAGENTRANCE1("stageEntrance1", true, "community.backstage", "community.stage"),
    STAGENTRANCE2("stageEntrance2", true, "community.backstage", "community.stage"),
    PREMIUMSTAGEENTRANCE("premiumStageEntrance", true,"commmunity.premium.stage", "community.backstage", "community.stage");


    private final String regionName;
    private final boolean boost;
    private final String[] permissions;

    CommunityRegion(String regionName, boolean boost, String... permission) {
        this.regionName = regionName;
        this.boost = boost;
        this.permissions = permission;
    }

}
