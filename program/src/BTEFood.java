import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class BTEFood extends JFrame
{
private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();

	
	private JRadioButton rdoVoucher = new JRadioButton("Show Voucher");
	private JRadioButton rdoFood = new JRadioButton("Report By Food");
	private JRadioButton rdoTSC = new JRadioButton("Report By Time Slot Code");
	private ButtonGroup rdoGroup = new ButtonGroup();
	
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension scrDimension = tk.getScreenSize();
	private double scrWidth = scrDimension.getWidth();
	private double scrHeight = scrDimension.getHeight();
	
	private Font font1 = new Font("Comic Sans MS",Font.PLAIN,20);
	
	private Color panelcolor1 = new Color(238,238,155);
	private JLabel lblImg = new JLabel(new ImageIcon("food1.jpg"));
	
	public BTEFood()
	{
		setVisible(true);
		setSize(600,500);      
		setLocation((int)(scrWidth / 2 - this.getWidth() / 2), (int)(scrHeight / 2 - this.getHeight() / 2));
		setTitle("BTE Food");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		rdoVoucher.setFont(font1);
		rdoFood.setFont(font1);
		rdoTSC.setFont(font1);
		
		rdoVoucher.setBackground(panelcolor1);
		rdoFood.setBackground(panelcolor1);
		rdoTSC.setBackground(panelcolor1);
		
		rdoGroup.add(rdoVoucher);
		rdoGroup.add(rdoFood);
		rdoGroup.add(rdoTSC);
		
		panel1.setLayout(new GridLayout(8,1,10,10));
		panel1.add(rdoVoucher);
		panel1.add(rdoFood);
		panel1.add(rdoTSC);
		panel1.setBorder(new TitledBorder("Choose Option: "));
		panel1.setBackground(panelcolor1);
		
		panel2.setBackground(panelcolor1);
		
		//panel3.setLayout(new GridLayout(1,1,10,10));
		panel3.add(lblImg);
		panel3.setBackground(panelcolor1);
		
		panel4.setBackground(panelcolor1);
		
		add(panel1,BorderLayout.WEST);
		add(panel2,BorderLayout.SOUTH);
		add(panel3,BorderLayout.EAST);
		add(panel3,BorderLayout.CENTER);
		
		rdoVoucher.addActionListener(new MyActionListener());
		rdoFood.addActionListener(new MyActionListener());
		rdoTSC.addActionListener(new MyActionListener());

	}
	
	@SuppressWarnings("deprecation")
	public void hideFrame() {
		this.hide();
	}
	
	public static void main(String[] args) 
	{
		new BTEFood();
	}
	
	public void showVoucherFrame() {
		new ShowVoucher(this);
	}
	
	public void showFoodFrame() {
		new ReportByFood(this);
	}
	
	public void showTSCFrame() {
		new ReportByTSC(this);
	}
	
	class MyActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			
			Object source = e.getSource();
			if(source==rdoVoucher)
			{ 
				showVoucherFrame();
				hideFrame();
			}
			else if(source==rdoFood)
			{
				showFoodFrame();
				hideFrame();
			}
			else
				showTSCFrame();
				hideFrame();
			
		}
	}

}
