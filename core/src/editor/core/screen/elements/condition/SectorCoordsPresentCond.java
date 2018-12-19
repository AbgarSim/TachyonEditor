package editor.core.screen.elements.condition;

import java.util.ArrayList;

import editor.core.enums.ConditionType;

public class SectorCoordsPresentCond extends SectorCondition {

	public ArrayList<String> coords= new ArrayList<>();

	public SectorCoordsPresentCond() {
		super();
		type = ConditionType.SECTOR_COORDS_PRESENT;
	}
	
	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("coords")){
			coords.add(value);
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return !coords.isEmpty();
	}

	@Override
	public String getCode() {
		String code = super.getCode();
		for (String c : coords) {
			code+="coords="+c+";";
		}
		return code;
	}
	
}
