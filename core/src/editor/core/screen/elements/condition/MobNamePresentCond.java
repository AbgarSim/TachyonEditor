package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class MobNamePresentCond extends MobCondition {

	public String name; 

	public MobNamePresentCond() {
		super();
		type = ConditionType.MOB_NAME_PRESENT;
	}
	
	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("mob_name")){
			name = value;
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return name!=null && !name.isEmpty();
	}

	@Override
	public String getCode() {
		return super.getCode()+"mob_name="+name+";";
	}
	
}
