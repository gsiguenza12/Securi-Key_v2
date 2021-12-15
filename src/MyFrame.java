import java.io.File; 	// import file class
import java.io.FileNotFoundException;
import java.io.FileWriter; // import file writer class
import java.io.IOException; // import IOExceptions error handling
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyFrame extends JFrame {


  private JButton btnTutup  = new JButton("Exit");
  private JButton btnTambah = new JButton("Add");
  //private JFrame f = new JFrame("Login");
	private JButton bok = new JButton("Login");

  private JTextField txtA = new JTextField();
  private JTextField txtB = new JTextField();
  private JTextField txtC = new JTextField();

  private JLabel lblA = new JLabel("Input Password :");
  private JLabel lblB = new JLabel("Welcome to Securi-Key Please sign in!");
  //private JLabel lblC = new JLabel("Create Password :");

  public MyFrame(){
    firstTimeUser();
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().add(bok);

    getContentPane().setBackground(Color.GRAY);
    setTitle("Securi-Key");
    setSize(400,200);
    setLocation(new Point(300,200));
    setLayout(null);    
    setResizable(false);
    initComponent();    
    initEvent();    
  }

  private void initComponent(){
    btnTutup.setBounds(300,130, 80,25);
    //btnTambah.setBounds(300,100, 80,25);
    bok.setBounds(300,100, 80,25);

    txtA.setBounds(125,55,100,20);
    //txtB.setBounds(200,35,100,20);
    //txtC.setBounds(200,65,100,20);

    lblB.setBounds(75,10,500,20);
    lblA.setBounds(20,55,100,20);

    //lblC.setBounds(20,65,100,20);

    add(bok);
    add(btnTutup);
    //add(btnTambah);

    add(lblA);
    add(lblB);
    //add(lblC);

    add(txtA);
    add(txtB);
    add(txtC);
  }

  private void initEvent(){

    bok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
        String x,y,z;
        int checkPass, userPass;
        try{
          x = (txtA.getText());
    
          txtC.setText(x);
          userPass = x.hashCode();
          checkPass = readFile("User.txt");
          if (userPass == checkPass)
          {
            System.out.println("Acess granted");
            	dispose();
				      new SecondFrame();
          }
          else 
          {
            System.out.println("Acess Denied");
            lblB.setText("Acess Denied");
          }
    
        }catch(Exception e){
          System.out.println(e);
          JOptionPane.showMessageDialog(null, 
              e.toString(),
              "Error", 
              JOptionPane.ERROR_MESSAGE);
        }



			}
		});

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
       System.exit(1);
      }
    });

    btnTutup.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnTutupClick(e);
      }
    });

    btnTambah.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnTambahClick(e);
      }
    }); 
  }
  
  
  private void btnTutupClick(ActionEvent evt){
    System.exit(0);
  }
  
  private void btnTambahClick(ActionEvent evt){
    String x,y,z;
    int checkPass, userPass;
    try{
      x = (txtA.getText());

      userPass = x.hashCode();
      checkPass = readFile("User.txt");
      if (userPass == checkPass)
      {
        System.out.println("Acess granted");
      }
      else 
      {
        System.out.println("Acess Denied");
      }

    }catch(Exception e){
      System.out.println(e);
      JOptionPane.showMessageDialog(null, 
          e.toString(),
          "Error", 
          JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void checkUser(){
		int checkPass, userPass; 

		Scanner user = new Scanner(System.in);
		System.out.println("Please enter your password.");

		String pass = user.nextLine();
		userPass = pass.hashCode();
		checkPass = readFile("User.txt");
		if (userPass == checkPass)
		{
			System.out.println("Acess granted");
		}
		else 
		{
			System.out.println("Acess Denied");
		}

	}

  public static boolean doesFileExist(String info)
	{
		try {
			File myObj = new File(info);
			if (myObj.createNewFile()) {  
				return false;
			} else {
				return true;
			}
		} catch(IOException e) {
				errorHandle();
				e.printStackTrace();
			}	
		return false;
  }

  public static void makeFile(String info)
	{
		try {
			File myObj = new File(info);
			if (myObj.createNewFile()) {  
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists");
			}
		} catch(IOException e) {
				errorHandle();
				e.printStackTrace();
			}	
	}

  public static void WriteToFile(String info, String data)
	{
		try {
			FileWriter myWriter = new FileWriter(info);
			myWriter.write(data);
			myWriter.close();
			
		} catch (IOException e)
		{
			errorHandle();
			e.printStackTrace();
		}
	}

  public static void firstTimeUser(){

		if (doesFileExist("User.txt") != true){

    //JOptionPane.showMessageDialog(null, "Please create a password.");
    String pass = JOptionPane.showInputDialog("Please create a password.");
		Scanner user = new Scanner(System.in);
		System.out.println("Please create your password.");

		//String pass = user.nextLine();
		pass = Integer.toString(pass.hashCode());
		makeFile("User.txt");
		WriteToFile("User.txt", pass);
    }
	}

  public static void errorHandle()
	{
		System.out.println("An error has occured.");		
	}


  public static int readFile(String info)
	{
		int data;
		try {
			File myObj = new File(info);
			Scanner myReader = new Scanner(myObj);
			while(myReader.hasNextLine()){
				return data = Integer.parseInt(myReader.nextLine());
			}
			myReader.close();

			
		} catch (FileNotFoundException e)
		{
			System.out.println("An error has occured.");
			errorHandle();
			e.printStackTrace();
		}
		return 0;
	}


  
}