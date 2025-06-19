package ru.newaymc.newaydb.procedures;

import ru.newaymc.newaydb.network.NewaydbModVariables;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class PlayerLoadLogicProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(NewaydbModVariables.NewayDBPlayerSaveFile));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				NewaydbModVariables.NewayDBPlayerSaveJsonObj = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (entity instanceof Player _player)
					_player.giveExperienceLevels(-(236609312));
				if (entity instanceof Player _player)
					_player.giveExperienceLevels((int) NewaydbModVariables.NewayDBPlayerSaveJsonObj.get("exp-level").getAsDouble());
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth((float) NewaydbModVariables.NewayDBPlayerSaveJsonObj.get("health-level").getAsDouble());
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
										_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent),
								("attribute @s minecraft:generic.max_health base set " + NewaydbModVariables.NewayDBPlayerSaveJsonObj.get("max-health-level").getAsDouble()));
					}
				}
				if (entity instanceof Player _player)
					_player.getFoodData().setFoodLevel((int) NewaydbModVariables.NewayDBPlayerSaveJsonObj.get("satisfaction-level").getAsDouble());
				if (entity instanceof Player _player)
					_player.getFoodData().setSaturation((float) NewaydbModVariables.NewayDBPlayerSaveJsonObj.get("saturation-level").getAsDouble());
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), ("tp @s " + NewaydbModVariables.NewayDBPlayerSaveJsonObj.get("coordinates").getAsJsonArray()));
					}
				}
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), ("spawnpoint @s " + NewaydbModVariables.NewayDBPlayerSaveJsonObj.get("respawn-coordinates").getAsJsonArray()));
					}
				}
				PlayerRemoveLogicProcedure.execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

