
public class Sergeant extends classGrunt {

	/**Sergeant is the head of the party. He orders his troops to move around
	 * Sergeant do not have a lot of armor, but grunts protect him
	 * Assassins are lethal to Sergeant - that's what they are there for
	 * 
	 * Their special abilities:
	 * Call Retreat: When the battle does not favor them all troops retreat without
	 * any consequences
	 * Focus Attack: Double damage to all grunts for a while, but afterwards all the
	 * grunts become more vulnerable
	 * Veteran: General is a seasoned veteran, doubling all experience he might receive
	 * Death Cry: If General gets killed, all grunts receive some f-ing overpowered buff
	 * but if the match doesn't end soon the grunt will become near useless.
	 *
	 * 
	 */
	
	//data?
	static boolean callRetreat;
	static int focusAttack;
	static double battleExperience;
	
	//more data
	String type = null; 
	boolean alive = true;
	int lifePoints = 1; 
	int attackPoints = 1; 
	int defensePoints = 1; 
	
	public class checkUnitLifePoints {
		
	}

}
