package net.svisvi.increasemobcap.item;

import net.svisvi.increasemobcap.network.ModVariables;
import net.minecraft.world.level.LevelAccessor;

public class AxolotlCapUpdaterItem extends AbstractCapUpdaterItem {
    public AxolotlCapUpdaterItem() {
        super();
    }
    public int getVar(LevelAccessor world){
        return ModVariables.MapVariables.get(world).Axolotl_cap;
    }
    public void setVar(LevelAccessor world, int n){
        ModVariables.MapVariables.get(world).Axolotl_cap = n;
        ModVariables.MapVariables.get(world).syncData(world);
    }
}

