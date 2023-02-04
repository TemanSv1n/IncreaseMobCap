package com.syric.increasemobcap;

import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

@Mod("increasemobcap")
public class IncreaseMobCap {
	private static final Logger LOGGER = LogManager.getLogger();
	
	public IncreaseMobCap() {

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC, "increasemobcap-common.toml");
		Config.loadConfig(Config.COMMON_SPEC, FMLPaths.CONFIGDIR.get().resolve("increasemobcap-common.toml"));

//		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.COMMON_SPEC);
//		Config.loadConfig(Config.COMMON_SPEC, FMLPaths.CONFIGDIR.get().resolve("increasemobcap.toml"));
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		
		//Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
//		ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> Pair.of(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));

//		ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, ()->new IExtensionPoint.DisplayTest(()-> NetworkConstants.IGNORESERVERONLY, (s,b)->true));
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		
//		for (EntityClassification classification : EntityClassification.values()) {
//			LOGGER.error(String.format("%s: %d", classification.getName(), classification.getMaxNumberOfCreature()));
//		}
		
		try {
			Field mobCap = ObfuscationReflectionHelper.findField(MobCategory.class, "f_21586_");
			mobCap.setAccessible(true);
			
//			Field modifierField = Field.class.getDeclaredField("modifiers");
//			modifierField.setAccessible(true);
//			modifierField.setInt(mobCap, mobCap.getModifiers() & ~Modifier.FINAL);

//			final Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
//			unsafeField.setAccessible(true);
//			final Unsafe unsafe = (Unsafe) unsafeField.get(null);
//
//			final Object staticFieldBase = unsafe.staticFieldBase(mobCap);
//			final long staticFieldOffset = unsafe.staticFieldOffset(mobCap);
//			unsafe.putObject(staticFieldBase, staticFieldOffset, "it works");
			
			mobCap.setInt(MobCategory.MONSTER, Config.MONSTER_CAP.get());
			mobCap.setInt(MobCategory.CREATURE, Config.CREATURE_CAP.get());
			mobCap.setInt(MobCategory.AMBIENT, Config.AMBIENT_CAP.get());
			mobCap.setInt(MobCategory.WATER_CREATURE, Config.WATER_CREATURE_CAP.get());
			mobCap.setInt(MobCategory.WATER_AMBIENT, Config.WATER_AMBIENT_CAP.get());
			mobCap.setInt(MobCategory.AXOLOTLS, Config.AXOLOTL_CAP.get());
			mobCap.setInt(MobCategory.UNDERGROUND_WATER_CREATURE, Config.UNDERGROUND_WATER_CAP.get());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

//		for (EntityClassification classification : EntityClassification.values()) {
//			LOGGER.error(String.format("%s: %d", classification.getName(), classification.getMaxNumberOfCreature()));
//		}
		
	}
}
