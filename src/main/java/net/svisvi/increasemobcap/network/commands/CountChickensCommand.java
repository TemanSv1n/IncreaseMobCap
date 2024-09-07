package net.svisvi.increasemobcap.network.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.svisvi.increasemobcap.network.ModVariables;

import java.lang.reflect.Field;
import java.util.Collection;

public class CountChickensCommand implements Command<CommandSourceStack> {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("mobcap")
                .then(Commands.literal("count")
                        .then(Commands.argument("targets", EntityArgument.entities())
                                        .executes(new CountChickensCommand())
                                ));
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) {
        ServerLevel world = context.getSource().getLevel();
        
        return 1;
    }
}
