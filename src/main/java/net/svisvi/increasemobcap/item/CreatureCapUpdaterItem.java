package net.svisvi.increasemobcap.item;

import net.svisvi.increasemobcap.network.ModVariables;
import net.minecraft.world.level.LevelAccessor;

public class CreatureCapUpdaterItem extends AbstractCapUpdaterItem {
    public CreatureCapUpdaterItem() {
        super();
    }
    public int getVar(LevelAccessor world){
        return ModVariables.MapVariables.get(world).Creature_cap;
    }
    public void setVar(LevelAccessor world, int n){
        ModVariables.MapVariables.get(world).Creature_cap = n;
        ModVariables.MapVariables.get(world).syncData(world);
    }
}

