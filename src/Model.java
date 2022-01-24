import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.swing.JFileChooser;

public class Model {
	private View view;
	private LinkedList<MainNode> mainLkd= new LinkedList<MainNode>();
	private LinkedList<MainNode> inverLkd= new LinkedList<MainNode>();
	Model (View view){
		this.view = view;
	}
	
	//Open files and create first data structure
	public void open() {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		if (chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			File[] files = chooser.getSelectedFiles();
			createMainLkd(files);
			view.state.setText("Files opened");
		}	
	}
	
	//Create inverte data structure
	public void invert() {
		//Check if first struct has been created
		if (mainLkd.isEmpty()) {
			view.state.setText("You must select files");
			return;
		}
		
		//Iterate over first struct to create the inverted struct
		Iterator mainIter=mainLkd.iterator();
		while(mainIter.hasNext()) {
			MainNode mainNode = (MainNode) mainIter.next();
			Iterator subIter=mainNode.subList.iterator();
			while(subIter.hasNext()) {
				SubNode subNode = (SubNode) subIter.next();
				addNodeMain(inverLkd,subNode,mainNode.text);
			}
		}
		Collections.sort(inverLkd);
		view.state.setText("Inverted structure created");
	}
	
	//Display the two data structures
	public void display() {
		if (mainLkd.isEmpty()) {
			view.state.setText("You must select files");
			return;
		}
		if (inverLkd.isEmpty()) {
			view.state.setText("You must created inverted structure");
			return;
		}
		view.struct.setText(toStringMain(mainLkd)+"\n"+toStringMain(inverLkd));
	}
	
	//Create linked list from file array
	private void createMainLkd(File[] files) {
		for(int i = 0; i<files.length; i++) {
			mainLkd.add(new MainNode(files[i].getName(), getWordLkd(files[i])));
		}
		Collections.sort(mainLkd);
		System.out.println(toStringMain(mainLkd));
	}
	
	//Create sub linked list from a file
	private LinkedList<SubNode> getWordLkd(File file) {
		LinkedList<SubNode> list = new LinkedList<SubNode>();
		FileReader reader;
		String word = "";
		int data;
		try {
			reader = new FileReader(file);
			data = reader.read();
			while (data!=-1) {
				if (Character.isLetter((char) data)) {
					word+= (char)data;
				} else if (word.length()>0) {
					addNode(list,word);
					word="";
				}
				data=reader.read();
				if (data==-1 && word.length()>0){
					addNode(list,word);
				}
			}
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(list);
		return list;
		
		
	}
	
	//add node to sub linked list
	private void addNode(LinkedList<SubNode> list, String word) {
		if (list.isEmpty()){
			list.addFirst(new SubNode(word,1));
			return;
		}
		SubNode node;
		Iterator currNode=list.iterator();
		while (currNode.hasNext()) {
			node = (SubNode) currNode.next();
			if ( node.text.equalsIgnoreCase(word)) {
				node.incr();
				return;
			}
		}
		list.add(new SubNode(word,1));
	}
	
	//add node to main linked list
	private void addNodeMain(LinkedList<MainNode> list, SubNode node, String docName) {
		if (list.isEmpty()) {
			list.addFirst(new MainNode(node.text, new LinkedList<SubNode>() )  );
			list.getFirst().subList.add(new SubNode(docName, node.freq));
			return;
		}
		Iterator iter = list.iterator();
		while(iter.hasNext()) {
			MainNode currNode = (MainNode) iter.next();
			if (currNode.text.equalsIgnoreCase(node.text)) {
				currNode.subList.add(new SubNode(docName, node.freq));
				return;
			}
		}
		//word first encounter case
		LinkedList<SubNode> subList= new LinkedList<SubNode>();
		subList.add(new SubNode(docName, node.freq));
		list.add(new MainNode(node.text,subList));
	}
	
	//Convert sub linked list to String (for display)
	public String toString(LinkedList<SubNode> list) {
		String ans="";
		Iterator currNode=list.iterator();
		while(currNode.hasNext()) {
			SubNode node = (SubNode) currNode.next();
			ans+="("+node.text+","+node.freq+");";
		}
		return ans;
	}
	
	//Convert main linked list to String (for display)
	public String toStringMain(LinkedList<MainNode> list) {
		String ans="";
		Iterator currNode=list.iterator();
		while(currNode.hasNext()) {
			MainNode node = (MainNode) currNode.next();
			ans+=node.text+"\n"+toString(node.subList)+"\n";
		}
		return ans;
		
	}
	
	//Search operation
	public void search() {
		
		//created the two data structures cond
		if (mainLkd.isEmpty()) {
			view.state.setText("You must select files");
			return;
		}
		if (inverLkd.isEmpty()) {
			view.state.setText("You must create inverted structure");
			return;
		}
		String[] wordsToSearch = view.text.getText().split(" ");
		LinkedList<SubNode> ans = new LinkedList<SubNode>();
		boolean notExist;
		
		//init
		Iterator iter = inverLkd.iterator();
		while(iter.hasNext()) {
			MainNode currNode = (MainNode) iter.next();
			if (currNode.text.equalsIgnoreCase(wordsToSearch[0])) {
				ans= (LinkedList<SubNode>) currNode.subList.clone();
			}
		}
		//Check if first word exists in database
		if (ans.isEmpty()) {
			view.state.setText("Search done");
			view.searchRes.setText("Doesn't exist");;
			return;
		}
		
		//handle other words
		for (int i = 1; i<wordsToSearch.length; i++) {
			notExist = true;
			iter = inverLkd.iterator();
			while(iter.hasNext()) {
				MainNode currNode = (MainNode) iter.next(); 
				if (currNode.text.equalsIgnoreCase(wordsToSearch[i])) {
					notExist = false;
					ans=intersect(ans,currNode.subList);
				}
			}
			if (notExist) {
				view.state.setText("Search done");
				view.searchRes.setText("Doesn't exist");;
				return;
			}
		}
		
		if (ans.isEmpty()) {
			view.state.setText("Search done");
			view.searchRes.setText("These words don't exist in the same file");
			return;
		}
		//Sort answers by frequency
		Collections.sort(ans, new AnsComparator());
		view.state.setText("Search done");
		view.searchRes.setText("Result (doc,fréq): "+toString(ans));
	}

	//returns common nodes between two linked lists
	private LinkedList<SubNode> intersect(LinkedList<SubNode> l1, LinkedList<SubNode> l2) {
		LinkedList<SubNode> ans = new LinkedList<SubNode>();
		Iterator iter1 = l1.iterator();
		Iterator iter2 = l2.iterator();
		SubNode node1 = (SubNode) iter1.next();
		SubNode node2 = (SubNode) iter2.next();
		
		while (true) {
			try {
				if (node1.text.equalsIgnoreCase(node2.text)) {
					ans.add(new SubNode(node1.text,node1.freq+node2.freq));
					node1 = (SubNode) iter1.next();
					node2 = (SubNode) iter2.next();
				} else if (node1.text.compareToIgnoreCase(node2.text)<0) {
					node1 = (SubNode) iter1.next();
				} else {
					node2 = (SubNode) iter2.next();
				}
			} catch (NoSuchElementException e) {
				//reached the end of one of the lists
				break;
			}
		}
			
		return ans;
	}
}
