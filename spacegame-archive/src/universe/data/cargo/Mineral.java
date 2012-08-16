package universe.data.cargo;

import java.util.HashMap;
import java.util.Map;

public class Mineral extends DefaultCargo {

	static Map<MineralType, Mineral> minerals;
	
	static {
		minerals = new HashMap<MineralType, Mineral>();
		minerals.put(MineralType.GAS, new Mineral(MineralType.GAS));
		minerals.put(MineralType.ORE, new Mineral(MineralType.ORE));
		minerals.put(MineralType.WATER, new Mineral(MineralType.WATER));
		minerals.put(MineralType.RARE, new Mineral(MineralType.RARE));
	}
	
	MineralType type;
	
	public Mineral(MineralType type) {
		this.type = type;
	}
	
	@Override
	public String getName() {
		return type.getName();
	}

	public static Mineral getMineralOfType(MineralType type) {
		return minerals.get(type);
	}
	
	@Override
	public int hashCode() {
		return getName().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Mineral)) {
			return false;
		}
		if (((Mineral)o).getName().equals(this.getName())) {
			return true;
		}
		return false;
	}
}
