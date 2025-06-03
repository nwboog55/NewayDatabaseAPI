package ru.newaymc.newaydb.procedures;

import ru.newaymc.newaydb.network.NewaydbModVariables;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

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
				if (entity instanceof Player _player)
					_player.getFoodData().setFoodLevel((int) NewaydbModVariables.NewayDBPlayerSaveJsonObj.get("satisfaction-level").getAsDouble());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
