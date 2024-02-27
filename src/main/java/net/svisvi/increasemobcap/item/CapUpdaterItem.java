package net.svisvi.increasemobcap.item;

import net.svisvi.increasemobcap.network.ModVariables;
import net.minecraft.Util;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

public class CapUpdaterItem extends Item {
    public CapUpdaterItem() {
        super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (!entity.isShiftKeyDown()){
            try {
                Field mobCap = ObfuscationReflectionHelper.findField(MobCategory.class, "f_21586_");
                mobCap.setAccessible(true);

                mobCap.setInt(MobCategory.MONSTER, ModVariables.MapVariables.get(world).Monster_cap);
                mobCap.setInt(MobCategory.CREATURE, ModVariables.MapVariables.get(world).Creature_cap);
                mobCap.setInt(MobCategory.AMBIENT, ModVariables.MapVariables.get(world).Ambient_cap);
                mobCap.setInt(MobCategory.WATER_CREATURE, ModVariables.MapVariables.get(world).Water_creature_cap);
                mobCap.setInt(MobCategory.WATER_AMBIENT, ModVariables.MapVariables.get(world).Water_ambient_cap);
                mobCap.setInt(MobCategory.UNDERGROUND_WATER_CREATURE, ModVariables.MapVariables.get(world).Underground_water_cap);
                mobCap.setInt(MobCategory.AXOLOTLS, ModVariables.MapVariables.get(world).Axolotl_cap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            String cstr = "";
            cstr += "Monster: " + Integer.toString(ModVariables.MapVariables.get(world).Monster_cap) + " ; ";
            cstr += "Creature: " + Integer.toString(ModVariables.MapVariables.get(world).Creature_cap) + " ; ";
            cstr += "Ambient: " + Integer.toString(ModVariables.MapVariables.get(world).Ambient_cap) + " ; ";
            cstr += "Water Creature: " + Integer.toString(ModVariables.MapVariables.get(world).Water_creature_cap) + " ; ";
            cstr += "Water Ambient: " + Integer.toString(ModVariables.MapVariables.get(world).Water_ambient_cap) + " ; ";
            cstr += "Underground water creature: " + Integer.toString(ModVariables.MapVariables.get(world).Underground_water_cap) + " ; ";
            cstr += "Axolotl : " + Integer.toString(ModVariables.MapVariables.get(world).Axolotl_cap) + " ; ";
            if (!world.isClientSide() && world.getServer() != null)
                world.getServer().getPlayerList().broadcastMessage(new TextComponent(cstr), ChatType.SYSTEM, Util.NIL_UUID);

        }

        return ar;
    }
}

