package net.svisvi.increasemobcap;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.svisvi.increasemobcap.item.*;

public class ModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, IncreaseMobCap.MODID);
    public static final RegistryObject<Item> MONSTER_CAP_UPDATER = REGISTRY.register("monster_cap_updater", () -> new MonsterCapUpdaterItem());
    public static final RegistryObject<Item> CREATURE_CAP_UPDATER = REGISTRY.register("creature_cap_updater", () -> new CreatureCapUpdaterItem());
    public static final RegistryObject<Item> AMBIENT_CAP_UPDATER = REGISTRY.register("ambient_cap_updater", () -> new AmbientCapUpdaterItem());
    public static final RegistryObject<Item> WATER_CREATURE_CAP_UPDATER = REGISTRY.register("water_creature_cap_updater", () -> new WaterCreatureCapUpdaterItem());
    public static final RegistryObject<Item> WATER_AMBIENT_CAP_UPDATER = REGISTRY.register("water_ambient_cap_updater", () -> new WaterAmbientCapUpdaterItem());
    public static final RegistryObject<Item> UNDERGROUND_WATER_CAP_UPDATER = REGISTRY.register("underground_water_cap_updater", () -> new UndergroundWaterCreatureCapUpdaterItem());
    public static final RegistryObject<Item> AXOLOTL_CAP_UPDATER = REGISTRY.register("axolotl_cap_updater", () -> new AxolotlCapUpdaterItem());
    public static final RegistryObject<Item> CAP_UPDATER = REGISTRY.register("cap_updater", () -> new CapUpdaterItem());

    private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

}
