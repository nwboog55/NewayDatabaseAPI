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
		NewaydbModVariables.NewayDBSettingsJsonObj.addProperty("toggle-backup", true);
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
	}
}
