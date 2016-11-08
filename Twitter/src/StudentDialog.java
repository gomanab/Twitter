import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class StudentDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();
	JButton submitBtn = new JButton("Submit");
	JTextField idTxtBx = new JTextField(10);
	JTextField nameTxtBx = new JTextField(10);
	
	StudentDialog (JFrame frame) {
		super(frame, true);
		setSize(200, 200);
	    setLocationRelativeTo(null);
	    this.setTitle("Add Student");
	    
	    
	    panel.add(idTxtBx);
	    panel.add(nameTxtBx);
	    panel.add(submitBtn);
	    
	    submitBtn.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evnt) {
	        	Tree.addStudent(new Student(Integer.parseInt(idTxtBx.getText()), nameTxtBx.getText(), false));
	            dispose();
	        }
	    });
	    
	    this.add(panel);
	    
	    setVisible(true);
	}
}

