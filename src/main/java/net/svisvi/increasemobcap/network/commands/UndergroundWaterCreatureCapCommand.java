package net.svisvi.increasemobcap.network.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.svisvi.increasemobcap.network.ModVariables;

public class UndergroundWaterCreatureCapCommand implements Command<CommandSourceStack> {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("mobcap")
                .then(Commands.literal("set")
                        .then(Commands.literal("underground_water_creature")
                                .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                                    .executes(new UndergroundWaterCreatureCapCommand())
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
        ModVariables.MapVariables.get(world).Underground_water_cap = n;
        ModVariables.MapVariables.get(world).syncData(world);
        return 1;
    }
}
