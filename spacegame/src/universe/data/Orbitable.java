package universe.data;

public interface Orbitable extends Location {
	double getPeriod();
	Planet getParent();
}
