package ru.newaymc.newaydb.procedures;

import ru.newaymc.newaydb.network.NewaydbModVariables;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.Minecraft;

import java.util.Calendar;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class PlayerSaveLogicProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		NewaydbModVariables.NewayDBPlayerSaveFile = new File((FMLPaths.GAMEDIR.get().toString() + "/NewayMC/NewayDB/PlayerData/"), File.separator + (entity.getDisplayName().getString() + ".json"));
		if (!NewaydbModVariables.NewayDBPlayerSaveFile.exists()) {
			try {
				NewaydbModVariables.NewayDBPlayerSaveFile.getParentFile().mkdirs();
				NewaydbModVariables.NewayDBPlayerSaveFile.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("nickname", (entity.getDisplayName().getString()));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("uuid", (entity.getStringUUID()));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("save-time", Calendar.getInstance().getTime().toString());
		if (new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
				} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SURVIVAL;
				}
				return false;
			}
		}.checkGamemode(entity)) {
			NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("gamemode", "survival");
		} else if (new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
				} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
				}
				return false;
			}
		}.checkGamemode(entity)) {
			NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("gamemode", "creative");
		} else if (new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.ADVENTURE;
				} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.ADVENTURE;
				}
				return false;
			}
		}.checkGamemode(entity)) {
			NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("gamemode", "adventure");
		} else if (new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
				} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SPECTATOR;
				}
				return false;
			}
		}.checkGamemode(entity)) {
			NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("gamemode", "spectator");
		}
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("coordinates-x", (entity.getX()));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("coordinates-y", (entity.getY()));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("coordinates-z", (entity.getZ()));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("respawn-coordinates-x",
				((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
						? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getX() : _player.level().getLevelData().getXSpawn())
						: 0));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("respawn-coordinates-y",
				((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
						? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getY() : _player.level().getLevelData().getYSpawn())
						: 0));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("respawn-coordinates-z",
				((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
						? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getZ() : _player.level().getLevelData().getZSpawn())
						: 0));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("exp-level", (entity instanceof Player _plr ? _plr.experienceLevel : 0));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("health-level", (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("max-health-level", (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("satisfaction-level", (entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0));
		NewaydbModVariables.NewayDBPlayerSaveJsonObj.addProperty("saturation-level", (entity instanceof Player _plr ? _plr.getFoodData().getSaturationLevel() : 0));
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
