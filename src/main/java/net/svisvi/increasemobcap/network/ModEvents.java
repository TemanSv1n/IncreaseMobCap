package net.svisvi.increasemobcap.network;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.LastSeenMessages;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.svisvi.increasemobcap.IncreaseMobCap;
import net.svisvi.increasemobcap.network.commands.*;

@Mod.EventBusSubscriber(modid = IncreaseMobCap.MODID)
public class ModEvents {

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal(IncreaseMobCap.MODID)
                .requires(stack -> stack.hasPermission(2))
                .then(UpdateMobCapCommand.register())
                .then(AmbientCapCommand.register())
                .then(AxolotlCapCommand.register())
                .then(CreatureCapCommand.register())
                .then(MonsterCapCommand.register())
                .then(UndergroundWaterCreatureCapCommand.register())
                .then(WaterAmbientCapCommand.register())
                .then(WaterCreatureCapCommand.register())
                .then(CountChickensCommand.register())

        );
    }

}