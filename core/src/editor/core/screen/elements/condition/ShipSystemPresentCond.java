package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipSystemPresentCond extends ShipCondition {

	public ShipSystemSuperType systemType;
	public String systemModel;
	public boolean activeSystems;
	public int qty; 
	
	public ShipSystemPresentCond() {
		super();
		type = ConditionType.SHIP_SYSTEM_PRESENT;
	}

	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("system_type")){
			systemType = ShipSystemSuperType.valueOf(value);
		}else if(key.equalsIgnoreCase("system_model")){
			systemModel = value;
		}else if(key.equalsIgnoreCase("qty")){
			qty = Integer.parseInt(value);
		}else if(key.equalsIgnoreCase("active_system")){
			activeSystems = "1".equalsIgnoreCase(value);
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return qty>0 && (systemType!=null || (systemModel!=null && !systemModel.isEmpty()));
	}

	@Override
	public String getCode() {
		String code = super.getCode();
		code+="system_type="+systemType.name()+";"+"system_model="+systemModel+";"+"qty="+qty+";"+"active_system="+(activeSystems?"1":"0")+";";
		return code;
	}

}
