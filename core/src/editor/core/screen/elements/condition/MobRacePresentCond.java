package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class MobRacePresentCond extends MobCondition {

	public String race;
	
	public MobRacePresentCond() {
		super();
		type = ConditionType.MOB_RACE_PRESENT;
	}
	
	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("race")){
			race = value;
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return race!=null;
	}

	@Override
	public String getCode() {
		return super.getCode()+"race="+race+";";
	}
	
}
