/**
 * 
 */
package universe.data.cargo;

public enum MineralType {
	ORE("Ore"),
	GAS("Gas"),
	WATER("Water"),
	RARE("Rare Metals");
	
	private String name;
	
	private MineralType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}