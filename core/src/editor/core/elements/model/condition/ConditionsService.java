package editor.core.elements.model.condition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConditionsService {

    private static final Set<String> selectableConditionTypes = new HashSet<>();
    private static final Map<String, Condition> conditionMetadatas = new HashMap<>();

    public static void loadConditions() {

        selectableConditionTypes.clear();
        conditionMetadatas.clear();


        Json json = new Json();
        FileHandle fileHandle = Gdx.files.internal("data/conditions.json");
        System.out.println("Loading conditions metadata from " + fileHandle.file().getAbsolutePath());
        ArrayList<JsonValue> list = json.fromJson(ArrayList.class, fileHandle);

        for (JsonValue jsonValue : list) {

            Condition condition = json.readValue(Condition.class, jsonValue);
            conditionMetadatas.put(condition.getType().toUpperCase(), condition);

            if (!condition.isAbstract) {
                selectableConditionTypes.add(condition.getType().toUpperCase());
            }
        }
    }

    public static Condition getConditionMetadataByType(String type){
        return conditionMetadatas.get(type);
    }

    public static Set<String> getSelectableConditionTypes(){
        return selectableConditionTypes;
    }

    public static Map<String, Condition> getConditionMetadatas(){
        return new HashMap<String, Condition>(conditionMetadatas);
    }
}
