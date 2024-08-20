package net.svisvi.increasemobcap.network.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.svisvi.increasemobcap.network.ModVariables;

import java.lang.reflect.Field;

public class UpdateMobCapCommand implements Command<CommandSourceStack> {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("mobcap")
                .then(Commands.literal("update")
                        .executes(new UpdateMobCapCommand())
                );
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) {
        ServerLevel world = context.getSource().getLevel();
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
        String cstr = "";
        cstr += "Monster: " + Integer.toString(ModVariables.MapVariables.get(world).Monster_cap) + " ; ";
        cstr += "Creature: " + Integer.toString(ModVariables.MapVariables.get(world).Creature_cap) + " ; ";
        cstr += "Ambient: " + Integer.toString(ModVariables.MapVariables.get(world).Ambient_cap) + " ; ";
        cstr += "Water Creature: " + Integer.toString(ModVariables.MapVariables.get(world).Water_creature_cap) + " ; ";
        cstr += "Water Ambient: " + Integer.toString(ModVariables.MapVariables.get(world).Water_ambient_cap) + " ; ";
        cstr += "Underground water creature: " + Integer.toString(ModVariables.MapVariables.get(world).Underground_water_cap) + " ; ";
        cstr += "Axolotl : " + Integer.toString(ModVariables.MapVariables.get(world).Axolotl_cap) + " ; ";
        if (!world.isClientSide() && world.getServer() != null)
            context.getSource().getPlayer().sendSystemMessage(Component.literal(cstr));
        return 1;
    }
}
