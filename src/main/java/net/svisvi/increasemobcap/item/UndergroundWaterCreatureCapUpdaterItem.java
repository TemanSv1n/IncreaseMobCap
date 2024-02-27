package net.svisvi.increasemobcap.item;

import net.svisvi.increasemobcap.network.ModVariables;
import net.minecraft.world.level.LevelAccessor;

public class UndergroundWaterCreatureCapUpdaterItem extends AbstractCapUpdaterItem {
    public UndergroundWaterCreatureCapUpdaterItem() {
        super();
    }
    public int getVar(LevelAccessor world){
        return ModVariables.MapVariables.get(world).Underground_water_cap;
    }
    public void setVar(LevelAccessor world, int n){
        ModVariables.MapVariables.get(world).Underground_water_cap = n;
        ModVariables.MapVariables.get(world).syncData(world);
    }
}

