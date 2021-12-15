import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SecondFrame extends JFrame {
	private JButton btn1  = new JButton("Exit");
	private JButton btn2 = new JButton("Add");
	private JButton btn3 = new JButton("Modify");
  
	private JLabel lblB = new JLabel("Select an option");
	//private JLabel lblC = new JLabel("Edit Password :");
	
	public SecondFrame() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,200);
		setVisible(true);
		getContentPane().setBackground(Color.GRAY);
		setTitle("Securi-Key");
		setLocation(new Point(300,200));
		setLayout(null);
		setResizable(false);   
		initComponent();    
		initEvent();

	}

	private void initComponent(){
		btn1.setBounds(300,130, 80,25);
		btn2.setBounds(300,100, 80,25);
		btn3.setBounds(300,70, 80,25);
	
		lblB.setBounds(75,10,500,20);
	
		add(btn3);
		add(btn1);
		add(btn2);
	
		add(lblB);
	  }

	  private void initEvent(){
		this.addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent e){
		   System.exit(1);
		  }
		});
	
		btn1.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			btn1Click(e);
		  }
		});
	
		btn2.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
		  {
				dispose();
				new createPassword();
		  }
		}); 
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				new editPassword();
			}
		});
	  }
	  
	  
	  private void btn1Click(ActionEvent evt){
		System.exit(0);
	  }	

}
