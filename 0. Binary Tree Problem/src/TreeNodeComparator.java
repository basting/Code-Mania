import java.util.Comparator;

public class TreeNodeComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		Load load1 = (Load)arg0;
		Load load2 = (Load)arg1;
		if(load1.pattern.length() > load2.pattern.length()){
			return 1;
		}else if (load1.pattern.length() < load2.pattern.length()){
			return -1;
		}
		return 0;
	}

}
