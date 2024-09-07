package net.svisvi.increasemobcap.network.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

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
        Collection<? extends Entity> n;
        try {
            n = EntityArgument.getEntities(context, "targets");
        } catch (IllegalArgumentException e) {
            n = new ArrayList<>();
            // do nothing
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }

        HashMap<String, Integer> chickens = new HashMap<String, Integer>();
        if (!n.isEmpty()) {

            Iterator var2 = n.iterator();

            while (var2.hasNext()) {
                Entity ent = (Entity) var2.next();
                String abas = ent.getEncodeId();
                chickens.put(abas, chickens.containsKey(abas) ? chickens.get(abas) + 1 : 1);
            }
        }
        if (!world.isClientSide() && world.getServer() != null)
            context.getSource().getPlayer().sendSystemMessage(Component.literal(chickens.toString()));

        return 1;
    }
}
