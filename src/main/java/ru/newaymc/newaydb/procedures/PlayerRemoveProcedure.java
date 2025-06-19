package ru.newaymc.newaydb.procedures;

import ru.newaymc.newaydb.network.NewaydbModVariables;

import net.minecraftforge.server.ServerLifecycleHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.client.Minecraft;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class PlayerRemoveProcedure {
    public static void execute(LevelAccessor world) {
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
                    PlayerRemoveLogicProcedure.execute();
                } else {
                    if ((NewaydbModVariables.NewayDBSettingsJsonObj.get("world").getAsString())
                            .equals(world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName())) {
                        PlayerRemoveLogicProcedure.execute();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
