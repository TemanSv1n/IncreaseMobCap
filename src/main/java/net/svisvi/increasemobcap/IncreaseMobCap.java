package net.svisvi.increasemobcap;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.svisvi.increasemobcap.network.ModEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod("increasemobcap")
public class IncreaseMobCap {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "imc";
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;
	
	public IncreaseMobCap() {

//		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.COMMON_SPEC);
//		Config.loadConfig(Config.COMMON_SPEC, FMLPaths.CONFIGDIR.get().resolve("increasemobcap.toml"));

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModItems.REGISTRY.register(bus);
		MinecraftForge.EVENT_BUS.register(new ModEvents());
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		
		//Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
//		ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> Pair.of(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));

//		ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, ()->new IExtensionPoint.DisplayTest(()-> NetworkConstants.IGNORESERVERONLY, (s,b)->true));
	}
	public void change(){
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

//			mobCap.setInt(MobCategory.MONSTER, ModVariables.MapVariables.get());
//			mobCap.setInt(MobCategory.CREATURE, Config.CREATURE_CAP.get());
//			mobCap.setInt(MobCategory.AMBIENT, Config.AMBIENT_CAP.get());
//			mobCap.setInt(MobCategory.WATER_CREATURE, Config.WATER_CREATURE_CAP.get());
//			mobCap.setInt(MobCategory.WATER_AMBIENT, Config.WATER_AMBIENT_CAP.get());
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		//change();

		
//		for (EntityClassification classification : EntityClassification.values()) {
//			LOGGER.error(String.format("%s: %d", classification.getName(), classification.getMaxNumberOfCreature()));
//		}
		


//		for (EntityClassification classification : EntityClassification.values()) {
//			LOGGER.error(String.format("%s: %d", classification.getName(), classification.getMaxNumberOfCreature()));
//		}
		
	}

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}
}
