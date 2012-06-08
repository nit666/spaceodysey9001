package universe.data.cargo;

import universe.data.unit.Constructable;

public abstract class ContructableCargo extends DefaultCargo implements Constructable {

	boolean underConstruction;
	
	public boolean isUnderConstruction() {
		return underConstruction;
	}

	public void setUnderConstruction(boolean underConstruction) {
		this.underConstruction = underConstruction;
	}
}
