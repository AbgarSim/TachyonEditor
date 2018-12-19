package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class SectorAreaPresentCond extends SectorCondition {

	public String areaStart;
	public String areaEnd;

	public SectorAreaPresentCond() {
		super();
		type = ConditionType.SECTOR_AREA_PRESENT;
	}
	
	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("area_start")){
			areaStart = value;
		}else if(key.equalsIgnoreCase("area_end")){
			areaEnd = value;
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return areaStart!=null && areaEnd!=null;
	}

	@Override
	public String getCode() {
		String code = super.getCode();
		code+="area_start="+areaStart+";"+"area_end="+areaEnd+";";
		return code;
	}
	
}
