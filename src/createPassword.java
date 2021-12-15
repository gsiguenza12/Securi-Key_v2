// SecondFrame.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class createPassword extends JFrame {
	private JButton btn1  = new JButton("Exit");
	private JButton btn2 = new JButton("Add");
	private JButton btn3 = new JButton("Modify");
  
	//private JTextField txtA = new JTextField();
	//private JTextField txtB = new JTextField();
	//private JTextField txtC = new JTextField();
  
	//private JLabel lblA = new JLabel(" :");
	private JLabel lblB = new JLabel("Select an option");
	//private JLabel lblC = new JLabel("Edit Password :");
	
	public createPassword() {
	
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
	
		//txtA.setBounds(125,55,100,20);
		//txtB.setBounds(200,35,100,20);
		//txtC.setBounds(200,65,100,20);
	
		lblB.setBounds(75,10,500,20);
		//lblA.setBounds(20,55,100,20);
	
		//lblC.setBounds(20,65,100,20);
	
		add(btn3);
		add(btn1);
		add(btn2);
	
		//add(lblA);
		add(lblB);
		//add(lblC);
	
		//add(txtA);
		//add(txtB);
		//add(txtC);
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
	
		/*btn2.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			btn2Click(e);
		  }
		}); */
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				new MyFrame();
			}
		});
	  }
	  
	  
	  private void btn1Click(ActionEvent evt){
		System.exit(0);
	  }	
}
