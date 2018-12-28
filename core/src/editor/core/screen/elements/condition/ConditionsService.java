package editor.core.screen.elements.condition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Condition;

public class ConditionsService {

    public static final Set<String> selectableConditionTypes = new HashSet<>();
    public static final Map<String, ConditionMetadata> conditionMetadatas = new HashMap<>();

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
}
