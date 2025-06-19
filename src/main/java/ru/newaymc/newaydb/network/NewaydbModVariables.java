package ru.newaymc.newaydb.network;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.io.File;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NewaydbModVariables {
	public static File NewayDBSettingsFile = new File("");
	public static com.google.gson.JsonObject NewayDBSettingsJsonObj = new com.google.gson.JsonObject();
	public static File NewayDBServerFile = new File("");
	public static com.google.gson.JsonObject NewayDBServerJsonObj = new com.google.gson.JsonObject();
	public static File NewayDBPlayerSaveFile = new File("");
	public static com.google.gson.JsonObject NewayDBPlayerSaveJsonObj = new com.google.gson.JsonObject();
	public static com.google.gson.JsonArray NewayDBPlayerCoordinatesJsonArray = new com.google.gson.JsonArray();
	public static File NewayDBLangFile = new File("");
	public static com.google.gson.JsonObject NewayDBLangJsonObj = new com.google.gson.JsonObject();
	public static com.google.gson.JsonArray NewayDBPlayerSpawnCoordinatesJsonArray = new com.google.gson.JsonArray();
	public static com.google.gson.JsonArray NewayDBPlayerSaveJsonArray = new com.google.gson.JsonArray();

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
	}
}
