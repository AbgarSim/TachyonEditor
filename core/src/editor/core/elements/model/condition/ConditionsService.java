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
    private static final Map<String, ConditionMetadata> conditionMetadatas = new HashMap<>();

    public static void loadConditions() {
        selectableConditionTypes.clear();
        conditionMetadatas.clear();
        Json json = new Json();
        FileHandle fh = Gdx.files.internal("data/conditions.json");
        System.out.println("Loading conditions metadata from " + fh.file().getAbsolutePath());
        ArrayList<JsonValue> list = json.fromJson(ArrayList.class, fh);
        for (JsonValue v : list) {
            ConditionMetadata cm = json.readValue(ConditionMetadata.class, v);
            conditionMetadatas.put(cm.type.toUpperCase(), cm);
            if (!cm.isAbstract) {
                selectableConditionTypes.add(cm.type.toUpperCase());
            }
        }
    }

    public static ConditionMetadata getConditionMetadata(String type){
        return conditionMetadatas.get(type);
    }

    public static Set<String> getSelectableConditionTypes(){
        return selectableConditionTypes;
    }

}
