package net.svisvi.increasemobcap.item;

import net.svisvi.increasemobcap.network.ModVariables;
import net.minecraft.world.level.LevelAccessor;

public class WaterAmbientCapUpdaterItem extends AbstractCapUpdaterItem {
    public WaterAmbientCapUpdaterItem() {
        super();
    }
    public int getVar(LevelAccessor world){
        return ModVariables.MapVariables.get(world).Water_ambient_cap;
    }
    public void setVar(LevelAccessor world, int n){
        ModVariables.MapVariables.get(world).Water_ambient_cap = n;
        ModVariables.MapVariables.get(world).syncData(world);
    }
}

