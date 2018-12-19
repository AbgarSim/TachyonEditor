package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class SectorThreatPresentCond extends SectorCondition {

	public String threatStart;
	public String threatEnd;

	public SectorThreatPresentCond() {
		super();
		type = ConditionType.SECTOR_THREAT_PRESENT;
	}
	
	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("threat_start")){
			threatStart = value;
		}else if(key.equalsIgnoreCase("threat_end")){
			threatEnd = value;
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return threatStart!=null && threatEnd!=null;
	}

	@Override
	public String getCode() {
		String code = super.getCode();
		code+="threat_start="+threatStart+";"+"threat_end="+threatEnd+";";
		return code;
	}
	
}
