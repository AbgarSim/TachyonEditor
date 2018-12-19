package editor.core.screen.elements.condition;

import java.util.ArrayList;
import java.util.List;

import editor.core.enums.AI_Type;
import editor.core.enums.ConditionType;

public class MobAiPresentCond extends MobCondition {

	public List<AI_Type> aiTypes = new ArrayList<>();
	public boolean allReq; 
	
	public MobAiPresentCond() {
		super();
		type = ConditionType.MOB_AI_PRESENT;
	}
	
	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("ai_type")){
			AI_Type t = AI_Type.valueOf(value);
			if(t!=null){
				aiTypes.add(t);
			}
		}else if(key.equalsIgnoreCase("all_req")){
			allReq = "1".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return !aiTypes.isEmpty();
	}

	@Override
	public String getCode() {
		return super.getCode()+"all_req="+allReq+";"+getTypesCode();
	}

	private String getTypesCode() {
		String str = "";
		if(!aiTypes.isEmpty()){
			for (AI_Type t : aiTypes) {
				str+="ai_type="+t+";";
			}
		}
		return str;
	}
	
}
