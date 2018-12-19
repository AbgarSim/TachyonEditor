package editor.core.screen.elements.condition;

import java.util.ArrayList;

import editor.core.enums.ConditionType;

public class SectorTypePresentCond extends SectorCondition {

	public ArrayList<Integer> types = new ArrayList<>();
	
	public SectorTypePresentCond() {
		super();
		type = ConditionType.SECTOR_TYPE_PRESENT;
	}

	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("sector_type")){
			types.add(Integer.parseInt(value));
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return !types.isEmpty();
	}

	@Override
	public String getCode() {
		String code = super.getCode();
		for (Integer t : types) {
			code+="sector_type="+t+";";
		}
		return code;
	}

}
