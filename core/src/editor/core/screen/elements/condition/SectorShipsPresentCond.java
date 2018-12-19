package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;
import editor.core.enums.ShipClass;

public class SectorShipsPresentCond extends SectorCondition {

	public int qty;
	public String location;
	public String shipName;
	public String owner;
	public String crew;
	public String race;
	public ShipClass shipClass;
	public String posAreaStart;
	public String posAreaEnd;
	public ShipClass ignoreClass;
	
	public SectorShipsPresentCond() {
		super();
		type = ConditionType.SECTOR_SHIPS_PRESENT;
	}

	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("qty")){
			qty = Integer.parseInt(value);
		}else if(key.equalsIgnoreCase("sector")){
			location = value;
		}else if(key.equalsIgnoreCase("ship_name")){
			shipName = value;
		}else if(key.equalsIgnoreCase("owner")){
			owner = value;
		}else if(key.equalsIgnoreCase("crew")){
			crew = value;
		}else if(key.equalsIgnoreCase("race")){
			race = value;
		}else if(key.equalsIgnoreCase("ship_class")){
			shipClass = ShipClass.valueOf(value);
		}else if(key.equalsIgnoreCase("ignore_class")){
			ignoreClass = ShipClass.valueOf(value);
		} else if (key.equalsIgnoreCase("pos_area_start")) {
			posAreaStart = value;
		} else if (key.equalsIgnoreCase("pos_area_end")) {
			posAreaEnd = value;
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
		String code = super.getCode();
		code+="qty="+qty+";";
		code+=(location==null?"":("sector="+location+";"));
		code+=(shipName==null?"":("ship_name="+shipName+";"));
		code+=(owner==null?"":("owner="+owner+";"));
		code+=(crew==null?"":("crew="+owner+";"));
		code+=(race==null?"":("race="+race+";"));
		code+=(shipClass==null?"":("ship_class="+shipClass+";"));
		code+=(posAreaStart==null || posAreaEnd==null?"":("pos_area_start="+posAreaStart+";"+"pos_area_end="+posAreaEnd+";"));
		return code;
	}
	
}
