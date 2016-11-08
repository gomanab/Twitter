import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class GroupDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();
	JButton submitBtn = new JButton("Submit");
	JTextField idTxtBx = new JTextField(10);
	JTextField nameTxtBx = new JTextField(10);
	
	GroupDialog (JFrame frame) {
		super(frame, true);
		setSize(200, 200);
	    setLocationRelativeTo(null);
	    this.setTitle("Add Group");
	    
	    
	    panel.add(idTxtBx);
	    panel.add(nameTxtBx);
	    panel.add(submitBtn);
	    
	    submitBtn.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evnt) {
	        	Tree.addGroup(new Group(Integer.parseInt(idTxtBx.getText()), nameTxtBx.getText(), true));
	            dispose();
	        }
	    });
	    
	    this.add(panel);
	    
	    setVisible(true);
	}
}

