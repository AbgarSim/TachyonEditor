package editor.core.elements.model.condition;

import java.util.HashMap;

public class Condition {

    private String type;
    private HashMap<String, String> parameters = new HashMap<>();
    public boolean isAbstract;
    public String extendsCondition;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    public void addParameter(String paramName, String paramValue){
        parameters.put(paramName,paramValue);
    }

    public HashMap<String, String> getParameters() {
        HashMap<String, String> retval = new HashMap<>();
        retval.putAll(parameters);
        if (extendsCondition != null && ConditionsService.getConditionMetadataByType(extendsCondition) != null) {
            Condition parent = ConditionsService.getConditionMetadataByType(extendsCondition);
            retval.putAll(parent.getParameters());
        }
        return retval;
    }
}
