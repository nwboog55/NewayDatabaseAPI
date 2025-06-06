package ru.newaymc.newaydb.procedures;

import ru.newaymc.newaydb.NewaydbMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class UpdaterProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (new Object() {
			public boolean isSuccessStatus(java.net.HttpURLConnection connection) {
				try {
					if (connection != null) {
						int status = connection.getResponseCode();
						// Check if status is in 2xx range (success)
						return status >= 200 && status < 300;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			}
		}.isSuccessStatus((new Object() {
			public java.net.HttpURLConnection createGetRequest(String url) {
				try {
					java.net.URL requestUrl = new java.net.URL(url);
					java.net.HttpURLConnection connection = (java.net.HttpURLConnection) requestUrl.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					return connection;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}.createGetRequest("https://nwboog55.github.io/")))) {
			NewaydbMod.LOGGER.info((Component.translatable("newaydb.server.connect").getString()));
			if (!(new Object() {
				public boolean isSuccessStatus(java.net.HttpURLConnection connection) {
					try {
						if (connection != null) {
							int status = connection.getResponseCode();
							// Check if status is in 2xx range (success)
							return status >= 200 && status < 300;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return false;
				}
			}.isSuccessStatus((new Object() {
				public java.net.HttpURLConnection createGetRequest(String url) {
					try {
						java.net.URL requestUrl = new java.net.URL(url);
						java.net.HttpURLConnection connection = (java.net.HttpURLConnection) requestUrl.openConnection();
						connection.setRequestMethod("GET");
						connection.setConnectTimeout(5000);
						connection.setReadTimeout(5000);
						return connection;
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			}.createGetRequest("https://nwboog55.github.io/newaydb/api/1.1.json"))))) {
				NewaydbMod.LOGGER.warn((Component.translatable("newaydb.server.update").getString()));
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("newaydb.server.update"), false);
			}
		} else {
			NewaydbMod.LOGGER.error((Component.translatable(("newaydb.server.fail" + (new Object() {
				public int getStatusCode(java.net.HttpURLConnection connection) {
					try {
						if (connection != null) {
							return connection.getResponseCode();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return -1;
				}
			}.getStatusCode((new Object() {
				public java.net.HttpURLConnection createGetRequest(String url) {
					try {
						java.net.URL requestUrl = new java.net.URL(url);
						java.net.HttpURLConnection connection = (java.net.HttpURLConnection) requestUrl.openConnection();
						connection.setRequestMethod("GET");
						connection.setConnectTimeout(5000);
						connection.setReadTimeout(5000);
						return connection;
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			}.createGetRequest("https://nwboog55.github.io/")))))).getString()));
		}
	}
}
