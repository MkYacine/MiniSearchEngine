import java.util.Comparator;

//Class comparator to sort linked lists by frequency
public class AnsComparator implements Comparator<SubNode> {

	@Override
	public int compare(SubNode o1, SubNode o2) {
		// TODO Auto-generated method stub
		return o2.freq-o1.freq;
	}

}
