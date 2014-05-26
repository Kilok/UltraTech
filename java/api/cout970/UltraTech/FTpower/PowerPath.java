package api.cout970.UltraTech.FTpower;

/**
 * 
 * @author Cout970
 *
 */
public class PowerPath {

	public IPowerConductor a;
	public IPowerConductor b;

	public PowerPath(IPowerConductor from, IPowerConductor to) {
		a = from;
		b = to;
	}

	public boolean contains(IPowerConductor from, IPowerConductor to) {
		if(a == from && b == to)return true;
		if(b == from && a == to)return true;
		return false;
	}


}
