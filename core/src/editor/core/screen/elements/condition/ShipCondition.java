package editor.core.screen.elements.condition;

public abstract class ShipCondition extends Condition {

	public boolean allMobShips = true; // used only when checking condition and only mob is provided
	public boolean checkTargetShip;
	public boolean checkShooterShip;

	@Override
	public void setParam(String key, String value){
		if (key.equalsIgnoreCase("all_mob_ships")) {
			allMobShips = "1".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
		}else if (key.equalsIgnoreCase("check_target_ship")) {
			checkTargetShip = "1".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
		}else if (key.equalsIgnoreCase("check_shooter_ship")) {
			checkShooterShip = "1".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
		}else{
			super.setParam(key, value);
		}
	}

}
