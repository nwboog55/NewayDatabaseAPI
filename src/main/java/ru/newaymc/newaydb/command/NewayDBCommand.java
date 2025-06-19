
package ru.newaymc.newaydb.command;

import ru.newaymc.newaydb.procedures.PlayerSaveProcedure;
import ru.newaymc.newaydb.procedures.PlayerSaveLogicProcedure;
import ru.newaymc.newaydb.procedures.PlayerRemoveProcedure;
import ru.newaymc.newaydb.procedures.PlayerRemoveLogicProcedure;
import ru.newaymc.newaydb.procedures.PlayerLoadProcedure;
import ru.newaymc.newaydb.procedures.PlayerLoadLogicProcedure;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

import com.mojang.brigadier.arguments.StringArgumentType;

@Mod.EventBusSubscriber
public class NewayDBCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        if (event.getCommandSelection() == Commands.CommandSelection.INTEGRATED)
            event.getDispatcher().register(Commands.literal("newaydb").requires(s -> s.hasPermission(3)).then(Commands.literal("save").then(Commands.argument("force", StringArgumentType.word()).executes(arguments -> {
                Level world = arguments.getSource().getUnsidedLevel();
                double x = arguments.getSource().getPosition().x();
                double y = arguments.getSource().getPosition().y();
                double z = arguments.getSource().getPosition().z();
                Entity entity = arguments.getSource().getEntity();
                if (entity == null && world instanceof ServerLevel _servLevel)
                    entity = FakePlayerFactory.getMinecraft(_servLevel);
                Direction direction = Direction.DOWN;
                if (entity != null)
                    direction = entity.getDirection();

                PlayerSaveLogicProcedure.execute(entity);
                return 0;
            })).executes(arguments -> {
                Level world = arguments.getSource().getUnsidedLevel();
                double x = arguments.getSource().getPosition().x();
                double y = arguments.getSource().getPosition().y();
                double z = arguments.getSource().getPosition().z();
                Entity entity = arguments.getSource().getEntity();
                if (entity == null && world instanceof ServerLevel _servLevel)
                    entity = FakePlayerFactory.getMinecraft(_servLevel);
                Direction direction = Direction.DOWN;
                if (entity != null)
                    direction = entity.getDirection();

                PlayerSaveProcedure.execute(world, entity);
                return 0;
            })).then(Commands.literal("load").then(Commands.argument("force", StringArgumentType.word()).executes(arguments -> {
                Level world = arguments.getSource().getUnsidedLevel();
                double x = arguments.getSource().getPosition().x();
                double y = arguments.getSource().getPosition().y();
                double z = arguments.getSource().getPosition().z();
                Entity entity = arguments.getSource().getEntity();
                if (entity == null && world instanceof ServerLevel _servLevel)
                    entity = FakePlayerFactory.getMinecraft(_servLevel);
                Direction direction = Direction.DOWN;
                if (entity != null)
                    direction = entity.getDirection();

                PlayerLoadLogicProcedure.execute(entity);
                return 0;
            })).executes(arguments -> {
                Level world = arguments.getSource().getUnsidedLevel();
                double x = arguments.getSource().getPosition().x();
                double y = arguments.getSource().getPosition().y();
                double z = arguments.getSource().getPosition().z();
                Entity entity = arguments.getSource().getEntity();
                if (entity == null && world instanceof ServerLevel _servLevel)
                    entity = FakePlayerFactory.getMinecraft(_servLevel);
                Direction direction = Direction.DOWN;
                if (entity != null)
                    direction = entity.getDirection();

                PlayerLoadProcedure.execute(world, entity);
                return 0;
            })).then(Commands.literal("delete").then(Commands.argument("force", StringArgumentType.word()).executes(arguments -> {
                Level world = arguments.getSource().getUnsidedLevel();
                double x = arguments.getSource().getPosition().x();
                double y = arguments.getSource().getPosition().y();
                double z = arguments.getSource().getPosition().z();
                Entity entity = arguments.getSource().getEntity();
                if (entity == null && world instanceof ServerLevel _servLevel)
                    entity = FakePlayerFactory.getMinecraft(_servLevel);
                Direction direction = Direction.DOWN;
                if (entity != null)
                    direction = entity.getDirection();

                PlayerRemoveLogicProcedure.execute();
                return 0;
            })).executes(arguments -> {
                Level world = arguments.getSource().getUnsidedLevel();
                double x = arguments.getSource().getPosition().x();
                double y = arguments.getSource().getPosition().y();
                double z = arguments.getSource().getPosition().z();
                Entity entity = arguments.getSource().getEntity();
                if (entity == null && world instanceof ServerLevel _servLevel)
                    entity = FakePlayerFactory.getMinecraft(_servLevel);
                Direction direction = Direction.DOWN;
                if (entity != null)
                    direction = entity.getDirection();

                PlayerRemoveProcedure.execute(world);
                return 0;
            })));
    }
}
