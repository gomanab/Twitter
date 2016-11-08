import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class userViewFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	Border border = BorderFactory.createLineBorder(Color.BLACK);
	
	private SpringLayout layout = new SpringLayout();
	//Buttons
	private JButton followUserBtn = new JButton("Follow User");
	private JButton postBtn = new JButton("Post");
	private JButton refreshBtn = new JButton("Refresh View");
	
	//TextFields
	private JTextField userIdTxBx = new JTextField(10);
	private JTextField msgTxBx = new JTextField(12);
	
	
	//TextArea
	private JTextArea newsFeed = new JTextArea(5, 20);
	private JTextArea listView = new JTextArea(5, 20);
	
	//
	private Student student;
	
	public userViewFrame(Student s) {
		student = s;
		
		addObjectToPanel();
		addActionListeners();
		setInfo();
		
		this.setVisible(true);
		this.add(panel);
		this.setSize(275,350);
		this.setTitle(s.toString());
	}
	
	private void addObjectToPanel() {
		panel.setLayout(layout);
		
		//userIdTxBx
		layout.putConstraint(SpringLayout.WEST, userIdTxBx, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, userIdTxBx, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.SOUTH, userIdTxBx, 0, SpringLayout.SOUTH, followUserBtn);
		
		//followUserBtn
		layout.putConstraint(SpringLayout.WEST, followUserBtn, 5, SpringLayout.EAST, userIdTxBx);
		layout.putConstraint(SpringLayout.NORTH, followUserBtn, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, followUserBtn, -10, SpringLayout.EAST, panel);
		
		//listView
		layout.putConstraint(SpringLayout.WEST, listView, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, listView, 5, SpringLayout.SOUTH, userIdTxBx);
		layout.putConstraint(SpringLayout.EAST, listView, -10, SpringLayout.EAST, panel);
		listView.setBorder(border);
		
		//msgTxBx
		layout.putConstraint(SpringLayout.WEST, msgTxBx, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, msgTxBx, 5, SpringLayout.SOUTH, listView);
		layout.putConstraint(SpringLayout.SOUTH, msgTxBx, 0, SpringLayout.SOUTH, postBtn);
		
		//postBtn
		layout.putConstraint(SpringLayout.WEST, postBtn, 5, SpringLayout.EAST, msgTxBx);
		layout.putConstraint(SpringLayout.NORTH, postBtn, 5, SpringLayout.SOUTH, listView);
		layout.putConstraint(SpringLayout.EAST, postBtn, -10, SpringLayout.EAST, panel);
		
		//newsFeed
		layout.putConstraint(SpringLayout.WEST, newsFeed, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, newsFeed, 5, SpringLayout.SOUTH, msgTxBx);
		layout.putConstraint(SpringLayout.EAST, newsFeed, -10, SpringLayout.EAST, panel);
		newsFeed.setBorder(border);
		
		//refreshBtn
		layout.putConstraint(SpringLayout.WEST, refreshBtn, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, refreshBtn, 5, SpringLayout.SOUTH, newsFeed);
		layout.putConstraint(SpringLayout.EAST, refreshBtn, -10, SpringLayout.EAST, panel);
		newsFeed.setBorder(border);
		
		panel.add(userIdTxBx);
		panel.add(followUserBtn);
		panel.add(listView);
		panel.add(msgTxBx);
		panel.add(postBtn);
		panel.add(newsFeed);
		panel.add(refreshBtn);
	}
	
	private void addActionListeners() {
		followUserBtn.addActionListener(this);
		postBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
	}
	
	public void setInfo () {
		userIdTxBx.setText("User ID - " + student.getId());
		listView.setText(student.getFollowerList());
		newsFeed.setText(student.getNewsFeed());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Student st = (Student)adminControlFrame.getSelectedUser();
		
		if (e.getActionCommand() == "Follow User") {
			
			if (st != student) {
				//Register new user
				st = (Student)adminControlFrame.getSelectedUser();
				st.register(student);
				student.addFollowing(st);
				
				JOptionPane.showMessageDialog(this, "Now Following " + st.toString());
			}
			else {
				JOptionPane.showMessageDialog(this, "Cannot follow yourself.");
			}
		}
		else if (e.getActionCommand() == "Post") {
			student.addComment(msgTxBx.getText());
			student.notifyObserver();
			msgTxBx.setText("");
		}
		
		setInfo();
	}
}
