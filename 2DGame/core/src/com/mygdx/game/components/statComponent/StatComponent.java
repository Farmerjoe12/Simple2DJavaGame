package com.mygdx.game.components.statComponent;

import com.mygdx.game.components.Component;

public abstract class StatComponent extends Component{

	int health = 100;
	int armor = 10;
	int strength = 5;
	int abilityPoints = 50;
	int level = 1;
	float experience = 100;
	float moveSpeed = 1f;
	float carryWeight = strength * 5; // some arbitrary value will be calculated based off of strength
	
	// getters
	public int getHealth() {return health;}
	public int getArmor() {return armor;}
	public int getStrength() {return strength;}
	public int getAP() {return abilityPoints;}
	public int getLevel() {return level;}
	public float getEXP() {return experience;}
	public float getMoveSpeed() {return moveSpeed;}
	// setters
	public int addHealth(int toAdd) {return health += toAdd;}	
	public int addArmor(int toAdd) {return armor += toAdd;}	
	public int addStrength(int toAdd) {return armor += toAdd;}	
	public int setAP(int toAdd) {return abilityPoints += toAdd;}	
	public int levelUP() {return level++;}	
	public float addEXP(float toAdd) {return experience += toAdd;}	
	public float setMoveSpeed(float newSpd) {return moveSpeed = newSpd;}	

}
