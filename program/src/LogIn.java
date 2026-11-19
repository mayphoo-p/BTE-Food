import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn extends JFrame
{
	private FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
	
	private JLabel lblUserName = new JLabel("User Name");
	private JTextField txtUserName = new JTextField(20);
	private JLabel lblPassword = new JLabel("Password");
	private JPasswordField txtPassword = new JPasswordField(20);
	private JButton btnLogIn = new JButton("Log In");
	
	private Font font1 = new Font("Comic Sans MS",Font.PLAIN,15);

	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension scrDimension = tk.getScreenSize();
	private double scrWidth = scrDimension.getWidth();
	private double scrHeight = scrDimension.getHeight();
	
	private Color panelcolor1 = new Color(238,238,155);
	private Color panelcolor2 = new Color(147,202,237);
	
	public LogIn()
	{
		setVisible(true);
		setSize(300, 200);
		setLocation((int)(scrWidth / 2 - this.getWidth() / 2), (int)(scrHeight / 2 - this.getHeight() / 2));
		setTitle("Log In To BTE Food Court");
		getContentPane().setBackground(panelcolor1);
		setLayout(flowLayout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(panelcolor1);
		
		lblUserName.setFont(font1);
		lblPassword.setFont(font1);
		txtUserName.setFont(font1);
		txtPassword.setFont(font1);
		btnLogIn.setFont(font1);
		
		btnLogIn.setBackground(panelcolor2);
		
		add(lblUserName);
		add(txtUserName);
		add(lblPassword);
		add(txtPassword);
		add(btnLogIn);
		
		btnLogIn.addActionListener(new MyActionListener());
	}
	
	class MyActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try {
                if (txtUserName.getText().isEmpty() || txtPassword.getPassword().length == 0) 
                {
                    throw new BlankDataException("Please enter all the required information.");
                }

                else if (txtUserName.getText().equalsIgnoreCase("MIA") && new String(txtPassword.getPassword()).equalsIgnoreCase("123")) {
                    new BTEFood();
                    hideFrame();
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Log In Failed. Please check your informations.");
                    txtUserName.requestFocus();
                }
            } 
			catch (BlankDataException ex) 
			{
                JOptionPane.showMessageDialog(null, ex.getMessage());
                txtUserName.requestFocus();
            }
		}
		
		class BlankDataException extends Exception
		{
	        public BlankDataException()
	        {
	            super("Blank data error");
	        }

	        public BlankDataException(String message) 
	        {
	            super(message);
	        }
	    }
	}
	
	@SuppressWarnings("deprecation")
	public void hideFrame() {
		this.hide();
	}
	
	public static void main(String[] args)
	{
		new LogIn();
	}

}
