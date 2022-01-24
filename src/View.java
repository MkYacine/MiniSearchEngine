import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class View extends JFrame{
	private Model model;
	private Controller controller;
	
	JPanel panel = (JPanel) this.getContentPane();
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File"); 	 
	JMenu graphMenu = new JMenu("Graph");
	JMenuItem openItem = new JMenuItem("Open");
	JMenuItem inverItem = new JMenuItem("Invert");
	JMenuItem structItem = new JMenuItem("Display");
	JLabel label = new JLabel("Word to search :");
	JTextArea text = new JTextArea();
	JButton searchB = new JButton("Search");
	JLabel state = new JLabel("");
	JTextArea searchRes = new JTextArea("");
	JTextArea struct = new JTextArea();
	JScrollPane pane;
	
	View (){
		model = new Model(this);
		controller = new Controller(model);
		
		fileMenu.add(openItem);
		fileMenu.add(inverItem);
		graphMenu.add(structItem);
		menuBar.add(fileMenu);
		menuBar.add(graphMenu);
		
		openItem.addActionListener(controller.new OpenClass());
		inverItem.addActionListener(controller.new InverClass());
		structItem.addActionListener(controller.new DisplayClass());
		
		text.setPreferredSize(new Dimension(200,18));
		
		searchB.addActionListener(controller.new SearchClass());
		
		state.setPreferredSize(new Dimension(450,18));
		searchRes.setPreferredSize(new Dimension(450,36));
		searchRes.setLineWrap(true);
		searchRes.setFocusable(false);
		struct.setFocusable(false);
		
		pane = new JScrollPane(struct);
		pane.setPreferredSize(new Dimension(480,400));
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel.add(menuBar);
		panel.add(state);
		panel.add(label);
		panel.add(text);
		panel.add(searchB);
		panel.add(searchRes);
		panel.add(pane);
		
		this.setJMenuBar(menuBar);
		this.setTitle("Search Engine");
		this.setSize(500,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		this.setVisible(true);
		
	}	
}
