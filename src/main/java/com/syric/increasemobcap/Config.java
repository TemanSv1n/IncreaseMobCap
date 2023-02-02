package com.syric.increasemobcap;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

import static net.minecraft.world.entity.MobCategory.*;

@Mod.EventBusSubscriber
public class Config {

	public static ForgeConfigSpec COMMON_SPEC;
	public static ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

	public static ForgeConfigSpec.ConfigValue<Integer> MONSTER_CAP;
	public static ForgeConfigSpec.ConfigValue<Integer> CREATURE_CAP;
	public static ForgeConfigSpec.ConfigValue<Integer> AMBIENT_CAP;
	public static ForgeConfigSpec.ConfigValue<Integer> WATER_CREATURE_CAP;
	public static ForgeConfigSpec.ConfigValue<Integer> WATER_AMBIENT_CAP;
	public static ForgeConfigSpec.ConfigValue<Integer> UNDERGROUND_WATER_CAP;
	public static ForgeConfigSpec.ConfigValue<Integer> AXOLOTL_CAP;
	
	static {
		COMMON_BUILDER.push("Mob Caps");
		
		MONSTER_CAP = COMMON_BUILDER.comment("Mob cap for monsters. " +
				"Controls the maximum number of zombies, creepers, etc. Default: 70").define("monster", MONSTER.getMaxInstancesPerChunk());
		CREATURE_CAP = COMMON_BUILDER.comment("Mob cap for creatures. " +
				"Controls the maximum number of miscellaneous creatures. Default: 10").define("creature", CREATURE.getMaxInstancesPerChunk());
		AMBIENT_CAP = COMMON_BUILDER.comment("Mob cap for ambient creatures. " +
				"Controls the maximum number of bats. Default: 15").define("ambient", AMBIENT.getMaxInstancesPerChunk());
		WATER_CREATURE_CAP = COMMON_BUILDER.comment("Mob cap for water creatures. " +
				"Controls the maximum number of squid, dolphins, etc. Default: 5").define("water_creature", WATER_CREATURE.getMaxInstancesPerChunk());
		WATER_AMBIENT_CAP = COMMON_BUILDER.comment("Mob cap for ambient water creatures. " +
				"Controls the maximum number of fish. Default: 20").define("water_ambient", WATER_AMBIENT.getMaxInstancesPerChunk());
		UNDERGROUND_WATER_CAP = COMMON_BUILDER.comment("Mob cap for underground water creatures. " +
				"Controls the maximum number of glowsquid. Default: 5").define("underground_water_creature", UNDERGROUND_WATER_CREATURE.getMaxInstancesPerChunk());
		AXOLOTL_CAP = COMMON_BUILDER.comment("Mob cap for axolotls. " +
				"Controls the maximum number of axolotls. Default: 5").define("axolotl", AXOLOTLS.getMaxInstancesPerChunk());
		COMMON_BUILDER.pop();

		COMMON_SPEC = COMMON_BUILDER.build();
	}
	
	public static void loadConfig(ForgeConfigSpec spec, Path path) {
		final CommentedFileConfig configData = CommentedFileConfig.builder(path)
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();
		
		configData.load();
		spec.setConfig(configData);
	}
}
