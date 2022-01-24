import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	private Model model;
	
	Controller(Model model){
		this.model=model;
	}
	
	public class OpenClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.open();
			
		}
		
	}
	public class InverClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.invert();
			
		}
		
	}
	public class SearchClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.search();
			
		}
		
	}
	
	public class DisplayClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.display();
			
		}
		
	}
}
