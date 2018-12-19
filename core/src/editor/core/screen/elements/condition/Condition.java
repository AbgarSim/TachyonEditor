package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public abstract class Condition {
	
	public static final String COND_SEPARATOR = "&&&";
	public ConditionType type;
	public boolean targetPlayer; //Used only in dialogues

	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("target_player")){
			targetPlayer = "1".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
		}
	}

	public String getCode(){
		String code = type+";"+(targetPlayer?"target_player=1;":"");
		return code;
	}

	public void loadFromCode(String[] parts){
		String[] kv;
		for (String part : parts) {
			kv = part.split("=");
			if(kv.length==2){
				setParam(kv[0], kv[1]);
			}else if(kv.length>2){
				System.out.println("Server: Error code 53");
			}
		}
	}

	public abstract boolean conditionValid();

}
