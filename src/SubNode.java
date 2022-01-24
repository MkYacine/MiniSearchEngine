//Sub node class
public class SubNode implements Comparable<SubNode>{
	String text;
	int freq = 0;
	SubNode(String t, int f){
		this.text=t;
		this.freq=f;
	}
	public void incr() {
		this.freq+=1;
	}
	@Override
	public int compareTo(SubNode o) {
		// TODO Auto-generated method stub
		return this.text.compareTo(o.text);
	}
}
