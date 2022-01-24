import java.util.LinkedList;

//Main node class
public class MainNode implements Comparable<MainNode> {
	String text;
	LinkedList<SubNode> subList;
	
	MainNode(String t, LinkedList<SubNode> l){
		this.text=t;
		this.subList=l;
	}
	
	@Override
	public int compareTo(MainNode o) {
		// TODO Auto-generated method stub
		return this.text.compareTo(o.text);
	}
}
