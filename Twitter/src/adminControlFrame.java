import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class adminControlFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static DefaultMutableTreeNode selectedNode;
	JTree tree;
	
	private JScrollPane treeScroll;
	private JPanel panel = new JPanel();
	
	//Layout
	private SpringLayout layout = new SpringLayout();
	
	//Buttons
	private JButton addUserBtn = new JButton("Add User");
	private JButton addGroupBtn = new JButton("Add Group");
	private JButton userViewBtn = new JButton("Open User View");
	private JButton userTotalBtn = new JButton("Show User Total");
	private JButton groupTotalBtn = new JButton("Show Group Total");
	private JButton messageTotalBtn = new JButton("Show Mesages Total");
	private JButton positivePercentageBtn = new JButton("Show Positive Percentage");
	
	//TextFields
	JTextField userIdTextField = new JTextField(15);
	JTextField groupIdTextField = new JTextField(15);
	
	public adminControlFrame() {
		//Add root to tree
		addObjectToPanel();
		addActionListeners();
		tree = Tree.getTree();
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.add(panel);
		this.setSize(600,400);
		this.setTitle("Twitter");
	}
	
	private void addObjectToPanel() {
		panel.setLayout(layout);
		
		//Construct Tree
		Tree.addRoot("Root");
		treeScroll = new JScrollPane(Tree.getTree());
		
		//Set treeScroll constraints
		layout.putConstraint(SpringLayout.WEST, treeScroll, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, treeScroll, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.SOUTH, treeScroll, -10, SpringLayout.SOUTH, panel);
		layout.putConstraint(SpringLayout.EAST, treeScroll, -350, SpringLayout.EAST, panel);
		
		//userIDTextField 
		layout.putConstraint(SpringLayout.WEST, userIdTextField, 5, SpringLayout.EAST, treeScroll);
		layout.putConstraint(SpringLayout.NORTH, userIdTextField, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.SOUTH, userIdTextField, 0, SpringLayout.SOUTH, addUserBtn);
		
		//addUserBtn
		layout.putConstraint(SpringLayout.WEST, addUserBtn, 5, SpringLayout.EAST, userIdTextField);
		layout.putConstraint(SpringLayout.NORTH, addUserBtn, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, addUserBtn, -10, SpringLayout.EAST, panel);
		
		//groupIdTextField
		layout.putConstraint(SpringLayout.WEST, groupIdTextField, 5, SpringLayout.EAST, treeScroll);
		layout.putConstraint(SpringLayout.NORTH, groupIdTextField, 5, SpringLayout.SOUTH, userIdTextField);
		layout.putConstraint(SpringLayout.SOUTH, groupIdTextField, 0, SpringLayout.SOUTH, addGroupBtn);
		
		//addGroupBtn
		layout.putConstraint(SpringLayout.WEST, addGroupBtn, 5, SpringLayout.EAST, groupIdTextField);
		layout.putConstraint(SpringLayout.NORTH, addGroupBtn, 5, SpringLayout.SOUTH, addUserBtn);
		layout.putConstraint(SpringLayout.EAST, addGroupBtn, -10, SpringLayout.EAST, panel);
		
		//userViewBtn
		layout.putConstraint(SpringLayout.WEST, userViewBtn, 5, SpringLayout.EAST, treeScroll);
		layout.putConstraint(SpringLayout.NORTH, userViewBtn, 5, SpringLayout.SOUTH, addGroupBtn);
		layout.putConstraint(SpringLayout.EAST, userViewBtn, -10, SpringLayout.EAST, panel);
		
		//messageTotalBtn
		layout.putConstraint(SpringLayout.WEST, messageTotalBtn, 5, SpringLayout.EAST, treeScroll);
		layout.putConstraint(SpringLayout.SOUTH, messageTotalBtn, -10, SpringLayout.SOUTH, panel);
		layout.putConstraint(SpringLayout.EAST, messageTotalBtn, 0, SpringLayout.EAST, groupIdTextField);
		
		//positivePercentageBtn
		layout.putConstraint(SpringLayout.WEST, positivePercentageBtn, 5, SpringLayout.EAST, messageTotalBtn);
		layout.putConstraint(SpringLayout.SOUTH, positivePercentageBtn, -10, SpringLayout.SOUTH, panel);
		layout.putConstraint(SpringLayout.EAST, positivePercentageBtn, -10, SpringLayout.EAST, panel);
		
		//userTotalBtn
		layout.putConstraint(SpringLayout.WEST, userTotalBtn, 5, SpringLayout.EAST, treeScroll);
		layout.putConstraint(SpringLayout.SOUTH, userTotalBtn, -5, SpringLayout.NORTH, messageTotalBtn);
		layout.putConstraint(SpringLayout.EAST, userTotalBtn, 0, SpringLayout.EAST, groupIdTextField);
		
		//groupTotalBtn
		layout.putConstraint(SpringLayout.WEST, groupTotalBtn, 5, SpringLayout.EAST, userTotalBtn);
		layout.putConstraint(SpringLayout.SOUTH, groupTotalBtn, -5, SpringLayout.NORTH, positivePercentageBtn);
		layout.putConstraint(SpringLayout.EAST, groupTotalBtn, -10, SpringLayout.EAST, panel);
		
		panel.add(treeScroll);
		panel.add(addUserBtn);
		panel.add(addGroupBtn);
		panel.add(userIdTextField);
		panel.add(groupIdTextField);
		panel.add(userViewBtn);
		panel.add(messageTotalBtn);
		panel.add(positivePercentageBtn);
		panel.add(userTotalBtn);
		panel.add(groupTotalBtn);
	}
	
	public static Profile getSelectedUser () {
		return (Profile) selectedNode.getUserObject();
	}
	
	private void addActionListeners() {
		addUserBtn.addActionListener(this);
		addGroupBtn.addActionListener(this);
		userViewBtn.addActionListener(this);
		messageTotalBtn.addActionListener(this);
		groupTotalBtn.addActionListener(this);
		userTotalBtn.addActionListener(this);
		
		
		JTree tree = Tree.getTree();
		
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
            	try {
	                selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	                
	                //Set current selected Node
	                Tree.setCurrentNode(selectedNode);
	                
	                Profile profile = (Profile)selectedNode.getUserObject();
	                
	                if (!profile.isGroup()) {
	                	userIdTextField.setText("User ID - " + profile.getId());
	                	groupIdTextField.setText("");
	                }
	                else {
	                	groupIdTextField.setText("Group ID - " + profile.getId());
	                	userIdTextField.setText("");
	                }
            	}
            	catch (ClassCastException ex) {
            		userIdTextField.setText("Root - No ID");
            	}
            }
        });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Profile profile = null;
		
		try {
			profile = (Profile)selectedNode.getUserObject();
			selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		}
		catch (Exception ex) {
			
		}
		
		if (e.getActionCommand() == "Add User") {
			new StudentDialog(this);
		}
		else if (e.getActionCommand() == "Add Group") {
			new GroupDialog(this);
		}
		else if (e.getActionCommand() == "Open User View") {
            //Set current selected Node
            Tree.setCurrentNode(selectedNode);
            
            if (!profile.isGroup()) {
            	new userViewFrame((Student)profile);
			}
		}
		else if (e.getActionCommand() == "Show Mesages Total") {
			try {
				JOptionPane.showMessageDialog(this, profile.toString() + " total messages: " + ((Student)profile).getMessagesTotal());
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Select a user to show total.");
			}
		}
		else if (e.getActionCommand() == "Show Group Total") {
			JOptionPane.showMessageDialog(this, "Total Number of Groups: " + Tree.getNumberOfGroups());
		}
		else if (e.getActionCommand() == "Show User Total") {
			JOptionPane.showMessageDialog(this, "Total Number of Users: " + Tree.getNumberOfUsers());
		}
		
	}
}
