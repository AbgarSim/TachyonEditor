package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class SectorNPCsPresentCond extends SectorCondition {

	public int qty;
	public String name;

	public SectorNPCsPresentCond() {
		super();
		type = ConditionType.SECTOR_NPCS_PRESENT;
	}
	
	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("qty")){
			qty = Integer.parseInt(value);
		}else if(key.equalsIgnoreCase("name")){
			name = value;
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return qty>0;
	}

	@Override
	public String getCode() {
		return super.getCode()+"qty="+qty+";"+"name="+name+";";
	}
	
}
