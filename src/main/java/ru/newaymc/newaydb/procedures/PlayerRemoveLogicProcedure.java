package ru.newaymc.newaydb.procedures;

import ru.newaymc.newaydb.network.NewaydbModVariables;

import java.io.IOException;
import java.io.FileWriter;

public class PlayerRemoveLogicProcedure {
    public static void execute() {
        NewaydbModVariables.NewayDBPlayerCoordinatesJsonArray = new com.google.gson.JsonArray();
        NewaydbModVariables.NewayDBPlayerSpawnCoordinatesJsonArray = new com.google.gson.JsonArray();
        NewaydbModVariables.NewayDBPlayerSaveJsonObj.remove("nickname");
        NewaydbModVariables.NewayDBPlayerSaveJsonObj.remove("uuid");
        NewaydbModVariables.NewayDBPlayerSaveJsonObj.remove("save-time");
        NewaydbModVariables.NewayDBPlayerSaveJsonObj.remove("coordinates");
        NewaydbModVariables.NewayDBPlayerSaveJsonObj.remove("respawn-coordinates");
        NewaydbModVariables.NewayDBPlayerSaveJsonObj.remove("exp-level");
        NewaydbModVariables.NewayDBPlayerSaveJsonObj.remove("health-level");
        NewaydbModVariables.NewayDBPlayerSaveJsonObj.remove("max-health-level");
        NewaydbModVariables.NewayDBPlayerSaveJsonObj.remove("satisfaction-level");
        NewaydbModVariables.NewayDBPlayerSaveJsonObj.remove("saturation-level");
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
