package ru.newaymc.newaydb.procedures;

import ru.newaymc.newaydb.network.NewaydbModVariables;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.Calendar;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class PlayerSaveLogicProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		NewaydbModVariables.NewayDBPlayerSaveFile = new File((FMLPaths.GAMEDIR.get().toString() + "/NewayMC/NewayDB/Local"), File.separator + (entity.getDisplayName().getString() + ".json"));
		if (!NewaydbModVariables.NewayDBPlayerSaveFile.exists()) {
			try {
				NewaydbModVariables.NewayDBPlayerSaveFile.getParentFile().mkdirs();
				NewaydbModVariables.NewayDBPlayerSaveFile.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		NewaydbModVariables.NewayDBPlayerSaveJsonArray.add((entity.getX()));
		NewaydbModVariables.NewayDBPlayerSaveJsonArray.add((entity.getY()));
		NewaydbModVariables.NewayDBPlayerSaveJsonArray.add((entity.getZ()));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("nickname", (entity.getDisplayName().getString()));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("uuid", (entity.getStringUUID()));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("save-time", Calendar.getInstance().getTime().toString());
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.add("coordinates", NewaydbModVariables.NewayDBPlayerSaveJsonArray);
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("exp-level", (entity instanceof Player _plr ? _plr.experienceLevel : 0));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("health-level", (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("satisfaction-level", (entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0));
		{
			com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter fileWriter = new FileWriter(NewaydbModVariables.NewayDBPlayerSaveFile);
				fileWriter.write(mainGSONBuilderVariable.toJson(NewaydbModVariables.NewayDBPlayerSaveJsonObj));
				fileWriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}
