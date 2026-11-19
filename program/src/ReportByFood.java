import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


public class ReportByFood extends JFrame
{
	private JPanel panel1 = new JPanel();

	private JLabel lblSD = new JLabel("Start Date : ");
	private JLabel lblED = new JLabel("End Date : ");
	private JLabel lblFoodName = new JLabel("Food Name : ");
	private JLabel lblOption = new JLabel("Order by :");
	
	UtilDateModel model1 = new UtilDateModel();
	JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
	DateLabelFormatter formatter1 = new DateLabelFormatter();
	JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1,formatter1);
	
	UtilDateModel model2 = new UtilDateModel();
	JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
	DateLabelFormatter formatter2 = new DateLabelFormatter();
	JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2,formatter2);

	private JButton btnShow = new JButton("Show");
	private JButton btnBack = new JButton("Back");
	
	private JRadioButton rdoAscending = new JRadioButton("Ascending");
	private JRadioButton rdoDecending = new JRadioButton("Decending");
	private ButtonGroup rdoGroup = new ButtonGroup();

	private JComboBox<String> FoodIDComboBox = new JComboBox<String>();
	private ReadJSONFood foodReader = new ReadJSONFood();
	private ArrayList<FoodObj> foodList = foodReader.getFoodList();
	
	private ReadJSONTimeSlot reader1 =new ReadJSONTimeSlot();
	private ArrayList<TimeSlotObj> timeSlotList = reader1.getTimeSlotList();
	
	private String[] cols = {"Time Slot Code","Qty"};
	private DefaultTableModel model=new DefaultTableModel(cols,0);
	private JTable table =new JTable(model);
	private JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	private Font font1 = new Font("Comic Sans MS",Font.PLAIN,15);
	
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension scrDimension = tk.getScreenSize();
	private double scrWidth = scrDimension.getWidth();
	private double scrHeight = scrDimension.getHeight();
	
	private Color panelcolor1 = new Color(238,238,155);
	private Color panelcolor2 = new Color(0,0,0);
	private Color panelcolor3 = new Color(147,202,237);
	//private Color panelcolor4 = new Color(255,255,255);
	
	Border border1 = BorderFactory.createLineBorder(panelcolor2, 1);
	Border border2 = BorderFactory.createLineBorder(panelcolor2, 2);
	
	private BTEFood bteFrame= null;
	
	public ReportByFood(BTEFood bteFrame)
	{
		this.bteFrame=bteFrame;
		setVisible(true);
		setSize(600, 500);
		setLocation((int)(scrWidth / 2 - this.getWidth() / 2), (int)(scrHeight / 2 - this.getHeight() / 2));
		setTitle("Report By Food");
		getContentPane().setBackground(panelcolor1);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblSD.setFont(font1);
		lblED.setFont(font1);
		lblFoodName.setFont(font1);
		lblOption.setFont(font1);
		btnShow.setFont(font1);
		rdoAscending.setFont(font1);
		rdoDecending.setFont(font1);
		btnBack.setFont(font1);
		
		lblSD.setBounds(20,15,100,30);
		datePicker1.setBounds(110,15,130,30);
		lblED.setBounds(20,50,100,30);
		datePicker2.setBounds(110,50,130,30);
		lblFoodName.setBounds(20,85,100,30);
		FoodIDComboBox.setBounds(110,85,180,30);
		lblOption.setBounds(20, 120, 100, 30);
		rdoAscending.setBounds(110,120,130,30);
		rdoDecending.setBounds(110,155,130,30);
		btnShow.setBounds(220,190,80,30);
		scrollPane.setBounds(20, 240, 540, 160);
		btnBack.setBounds(490,420,70,30);

		
		rdoGroup.add(rdoAscending);
		rdoGroup.add(rdoDecending);
		
		datePicker1.setBackground(null);
		datePicker2.setBackground(null);
		FoodIDComboBox.setBackground(panelcolor1);
		rdoAscending.setBackground(panelcolor1);
		rdoDecending.setBackground(panelcolor1);
		btnShow.setBackground(panelcolor3);
		btnBack.setBackground(panelcolor3);

		FoodIDComboBox.setBorder(border1);

		panel1.setLayout(null);
		panel1.setBackground(panelcolor1);
		panel1.setBounds(0,0,570,220);
		panel1.add(lblSD);
		panel1.add(lblED);
		panel1.add(datePicker1);
		panel1.add(datePicker2);
		panel1.add(lblFoodName);
		panel1.add(FoodIDComboBox);
		panel1.add(lblOption);
		panel1.add(rdoAscending);
		panel1.add(rdoDecending);
		panel1.add(btnShow);
		
		add(panel1);
		add(scrollPane);
		add(btnBack);
		
		for(FoodObj food : foodList)
		{
			FoodIDComboBox.addItem(food.getFoodName());
		}
		
		btnShow.addActionListener(new MyActionListener());
		btnBack.addActionListener(new MyActionListener());
		
	}
	
	public void sedate() 
	{
		int day,month,year,day1,month1,year1;
		Date sDate = (Date) datePicker1.getModel().getValue();
		day = sDate.getDate();
		month = sDate.getMonth()+1;
		year = sDate.getYear()+1900;
		Date eDate = (Date) datePicker2.getModel().getValue();
		day1 = eDate.getDate();
		month1 = eDate.getMonth()+1;
		year1 = eDate.getYear()+1900;
		String food;
		food=(String) FoodIDComboBox.getSelectedItem();
		BTEReport report = new BTEReport();
		ArrayList<String> timeAry = new ArrayList<>();
		for(TimeSlotObj time : timeSlotList) 
		{
			timeAry.add(time.getTimeSlotCode());
		}
		model.setRowCount(timeAry.size());
		
		ArrayList<VoucherObj> voucherList =report.getVoucherByFoodID(food, sDate, eDate);
		int[]qtyAry=new int[timeAry.size()];
		for(VoucherObj v : voucherList)
		{
			int index=-1;
			String tsc = v.getVoucherTSC().getTimeSlotCode();
			for(int i =0;i<timeAry.size();i++) 
			{
				if(tsc.equals(timeAry.get(i)))
					index=i;
			}
			ArrayList<ItemObj>itemList = v.getSaleItems();
			int qty=0;
			for(int i=0;i<itemList.size();i++) 
			{
				if(food.equals(itemList.get(i).getFood().getFoodName()))
					qty=itemList.get(i).getQty();
			}
			if(index!=-1) {
				qtyAry[index]+=qty;
			}
		}
		
		if(rdoAscending.isSelected())
			sortByAsc(qtyAry, timeAry);
		else
			sortByDsc(qtyAry,timeAry);

		for(int i=0;i<timeAry.size();i++) 
		{ 
			table.setValueAt(timeAry.get(i),i,0);
			table.setValueAt(qtyAry[i],i,1);

		}
	}
	
	public void sortByAsc(int [] qtyAry, ArrayList<String> timeAry)
	{
		//bubble sort
		for(int i=0;i<qtyAry.length;i++) {
			for(int j=0;j<qtyAry.length-1;j++) {
				if(qtyAry[j] > qtyAry[j+1]) {
					int qtyTemp;
					qtyTemp=qtyAry[j];
					qtyAry[j]=qtyAry[j+1];
					qtyAry[j+1]=qtyTemp;
					String timeTemp;
					timeTemp=timeAry.get(j);
					timeAry.set(j,timeAry.get(j+1));
                    timeAry.set(j+1, timeTemp);
				}
			}
		}
	}
	
	public void sortByDsc(int [] qtyAry, ArrayList<String> timeAry)
	{
		//bubble sort
		for(int i=0;i<qtyAry.length;i++) {
			for(int j=0;j<qtyAry.length-1;j++) {
				if(qtyAry[j] < qtyAry[j+1]) {
					int qtyTemp;
					qtyTemp=qtyAry[j];
					qtyAry[j]=qtyAry[j+1];
					qtyAry[j+1]=qtyTemp;
					String timeTemp;
					timeTemp=timeAry.get(j);
					timeAry.set(j,timeAry.get(j+1));
                    timeAry.set(j+1, timeTemp);
				}
			}
		}
	}
	
	public void foodId() 
	{
		ReadJSONFood reader =new ReadJSONFood();
		ArrayList<FoodObj> foodList = reader.getFoodList();
		String foodID;
		foodID=(String) FoodIDComboBox.getSelectedItem();
		for(FoodObj food : foodList) 
		{
			if (foodID.equals(food.getFoodName()))
				lblFoodName.setText("Food Name :");
		}
	}
	
	class MyActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == btnShow) {
				Date dob = (Date)datePicker1.getModel().getValue();
				Date dob1 = (Date)datePicker2.getModel().getValue();
				if(dob == null || dob1 == null || rdoGroup.isSelected(null) || FoodIDComboBox.equals(null)) 
				{
					JOptionPane.showMessageDialog(null, "You need to enter full Information!!!");
				}else 
				{
					sedate();
					foodId();
				}
			}
			else {
				hideFrame();
				bteFrame.show();
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void hideFrame() {
		this.hide();
	}

}
