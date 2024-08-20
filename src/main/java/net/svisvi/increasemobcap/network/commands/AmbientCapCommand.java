package net.svisvi.increasemobcap.network.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.svisvi.increasemobcap.network.ModVariables;

import java.lang.reflect.Field;

public class AmbientCapCommand implements Command<CommandSourceStack> {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("mobcap")
                .then(Commands.literal("set")
                        .then(Commands.literal("ambient")
                                .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                                    .executes(new AmbientCapCommand())
                )));
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) {
        ServerLevel world = context.getSource().getLevel();
        int n = 0;
        try {
            n = IntegerArgumentType.getInteger(context, "amount");
        } catch (IllegalArgumentException e) {
            // do nothing
        }
        ModVariables.MapVariables.get(world).Ambient_cap = n;
        ModVariables.MapVariables.get(world).syncData(world);
        return 1;
    }
}
