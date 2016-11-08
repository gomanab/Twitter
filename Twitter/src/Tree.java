import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.tree.*;

public class Tree {
	private static JTree twitTree;
	private static Tree INSTANCE = null;
	private static DefaultTreeModel model;
	private static DefaultMutableTreeNode root;
	private static DefaultMutableTreeNode currentNode;
	private static int numberOfGroups = 0;
	private static int numberOfUsers = 0;
	
	protected Tree() {super();}
	
	public static Tree getInstance() {
		if (INSTANCE != null) {
			INSTANCE = new Tree();
		}
		
		return INSTANCE;
	}
	
	public static JTree getTree() {
		return twitTree;
	}
	
	public static void setCurrentNode(DefaultMutableTreeNode node) {
		currentNode = node;
	}
	
	public static void addRoot(String rootName) {
		twitTree = new JTree(new DefaultMutableTreeNode(rootName));
		
		model = (DefaultTreeModel) twitTree.getModel();
		root = (DefaultMutableTreeNode) model.getRoot();
		currentNode = root;
	}
	
	public static void addStudent (Student newStudent) {
    	root.add(new DefaultMutableTreeNode(newStudent));
    	((DefaultTreeModel) twitTree.getModel()).nodesWereInserted(root, new int[]{root.getChildCount()-1});
    	
    	numberOfUsers++;
	}
	
	public static void addGroup (Group newGroup) {
		try {
			Profile profile = (Profile) currentNode.getUserObject();
			
			if (!profile.isGroup()) {
				currentNode = root;
			}
		}
		catch (Exception ex) {
			currentNode = root;
		}
		
		currentNode.add(new DefaultMutableTreeNode(newGroup));
		((DefaultTreeModel) twitTree.getModel()).nodesWereInserted(currentNode, new int[]{currentNode.getChildCount()-1});
		
		numberOfGroups++;
	}
	
	public static String getNode() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) model.getChild(root, 0);
		Student student = (Student) node.getUserObject();
		return student.toString();	
	}
	
	public static int getNumberOfGroups () {
		return numberOfGroups;
	}
	
	public static int getNumberOfUsers () {
		return numberOfUsers;
	}
	
	public static int getSize() {
		return getNumberOfNodes(model, root);
	}
	
	private static int getNumberOfNodes (TreeModel currentModel, Object node) {
		int count = 1;
	    int nChildren = currentModel.getChildCount(node);  
	    for (int i = 0; i < nChildren; i++)  
	    {  
	        count += getNumberOfNodes(currentModel, currentModel.getChild(node, i));  
	    }  
	    return count;
	}
}
