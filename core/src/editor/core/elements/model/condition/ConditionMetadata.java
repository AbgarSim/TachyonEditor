package editor.core.elements.model.condition;

import java.util.HashMap;

public class ConditionMetadata extends Condition {

    public boolean isAbstract;
    public String extendsCondition;

    public HashMap<String, String> getParameters(){
        HashMap<String,String> retval = new HashMap<>();
        retval.putAll(parameters);
        if(extendsCondition!=null && ConditionsService.getConditionMetadata(extendsCondition)!=null){
            ConditionMetadata parent = ConditionsService.getConditionMetadata(extendsCondition);
            retval.putAll(parent.getParameters());
        }
        return retval;
    }


}
