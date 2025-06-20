package ru.newaymc.newaydb.procedures;

import ru.newaymc.newaydb.network.NewaydbModVariables;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlayerSetupProcedure {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		NewaydbModVariables.NewayDBSettingsFile = new File((FMLPaths.GAMEDIR.get().toString() + "/NewayMC/NewayDB/"), File.separator + "settings.json");
		if (!NewaydbModVariables.NewayDBSettingsFile.exists()) {
			try {
				NewaydbModVariables.NewayDBSettingsFile.getParentFile().mkdirs();
				NewaydbModVariables.NewayDBSettingsFile.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		NewaydbModVariables.NewayDBSettingsJsonObj.addProperty("force-world-save", true);
		NewaydbModVariables.NewayDBSettingsJsonObj.addProperty("world", "");
		{
			com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter fileWriter = new FileWriter(NewaydbModVariables.NewayDBSettingsFile);
				fileWriter.write(mainGSONBuilderVariable.toJson(NewaydbModVariables.NewayDBSettingsJsonObj));
				fileWriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		NewaydbModVariables.NewayDBLangFile = new File((FMLPaths.GAMEDIR.get().toString() + "/NewayMC/NewayDB/Language/"), File.separator + "lang.json");
		if (!NewaydbModVariables.NewayDBLangFile.exists()) {
			try {
				NewaydbModVariables.NewayDBLangFile.getParentFile().mkdirs();
				NewaydbModVariables.NewayDBLangFile.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		NewaydbModVariables.NewayDBLangJsonObj.addProperty("newaydb-server-connect", "[NewayDB] The connection to the server has been successfully established.");
		NewaydbModVariables.NewayDBLangJsonObj.addProperty("newaydb-server-fail", "[NewayDB] The connection to the server was not established. Error code:");
		NewaydbModVariables.NewayDBLangJsonObj.addProperty("newaydb-server-update", "[NewayDB] You have an outdated version of the mod installed. Please update to the latest version of the mod.");
		{
			com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter fileWriter = new FileWriter(NewaydbModVariables.NewayDBLangFile);
				fileWriter.write(mainGSONBuilderVariable.toJson(NewaydbModVariables.NewayDBLangJsonObj));
				fileWriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}
