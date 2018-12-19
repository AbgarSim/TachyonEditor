package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;
import editor.core.enums.ItemType;

public class ShipCargoPresentCond extends ShipCondition {

	public ItemType cargoType;
	public ShipSystemSuperType systemType;
	public String systemModel;
	public int qty; 
	
	public ShipCargoPresentCond() {
		super();
		type = ConditionType.SHIP_CARGO_PRESENT;
	}
	
	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("cargo_type")){
			cargoType = ItemType.valueOf(value);
		}else if(key.equalsIgnoreCase("system_type")){
			systemType = ShipSystemSuperType.valueOf(value);
		}else if(key.equalsIgnoreCase("system_model")){
			systemModel = value;
		}else if(key.equalsIgnoreCase("qty")){
			qty = Integer.parseInt(value);
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return cargoType!=null && qty>0 && (systemType!=null || (systemModel!=null && !systemModel.isEmpty()));
	}

	@Override
	public String getCode() {
		String code = super.getCode();
		code+="cargo_type="+cargoType.name()+";"+getSystemTypeCode()+getSystemModelCode()+"qty="+qty+";";
		return code;
	}

	protected String getSystemTypeCode(){
		String retval = "";
		if(systemType!=null){
			retval = "system_type="+systemType.name()+";";
		}
		return retval;
	}

	protected String getSystemModelCode(){
		String retval = "";
		if(systemModel!=null){
			retval = "system_model="+systemModel+";";
		}
		return retval;
	}
	
}
