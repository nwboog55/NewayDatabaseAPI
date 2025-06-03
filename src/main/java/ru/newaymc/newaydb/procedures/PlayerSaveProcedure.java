package ru.newaymc.newaydb.procedures;

import ru.newaymc.newaydb.network.NewaydbModVariables;

import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

@Mod.EventBusSubscriber
public class PlayerSaveProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(NewaydbModVariables.NewayDBSettingsFile));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				NewaydbModVariables.NewayDBSettingsJsonObj = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (NewaydbModVariables.NewayDBSettingsJsonObj.get("force-world-save").getAsBoolean() == true) {
					PlayerSaveLogicProcedure.execute(entity);
				} else {
					if ((NewaydbModVariables.NewayDBSettingsJsonObj.get("world").getAsString())
							.equals(world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName())) {
						PlayerSaveLogicProcedure.execute(entity);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
