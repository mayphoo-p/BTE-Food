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


public class ReportByTSC extends JFrame
{
	private JPanel panel1 = new JPanel();

	private JLabel lblSD = new JLabel("Start Date : ");
	private JLabel lblED = new JLabel("End Date : ");
	private JLabel lblTSC = new JLabel("Time Slot : ");
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

	private JComboBox<String> TSCComboBox = new JComboBox<String>();
	private ReadJSONTimeSlot timeSlotReader = new ReadJSONTimeSlot();
	private ArrayList<TimeSlotObj> timeSlotList = timeSlotReader.getTimeSlotList();
	
	ReadJSONFood reader1 =new ReadJSONFood();
	ArrayList<FoodObj> foodList = reader1.getFoodList();
	
	private String[] cols = {"Food ID","Qty"};
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
	
	Border border1 = BorderFactory.createLineBorder(panelcolor2, 1);
	Border border2 = BorderFactory.createLineBorder(panelcolor2, 2);
	
	private BTEFood bteFrame = null;
	
	public ReportByTSC(BTEFood bteFrame)
	{
		this.bteFrame=bteFrame;
		setVisible(true);
		setSize(600, 500);
		setLocation((int)(scrWidth / 2 - this.getWidth() / 2), (int)(scrHeight / 2 - this.getHeight() / 2));
		setTitle("Report By Time Slot Code");
		getContentPane().setBackground(panelcolor1);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		lblSD.setFont(font1);
		lblED.setFont(font1);
		lblTSC.setFont(font1);
		lblOption.setFont(font1);
		btnShow.setFont(font1);
		rdoAscending.setFont(font1);
		rdoDecending.setFont(font1);
		btnBack.setFont(font1);
		
		lblSD.setBounds(20,15,100,30);
		datePicker1.setBounds(110,15,130,30);
		lblED.setBounds(20,50,100,30);
		datePicker2.setBounds(110,50,130,30);
		lblTSC.setBounds(20,85,120,30);
		TSCComboBox.setBounds(110,85,140,30);
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
		TSCComboBox.setBackground(panelcolor1);
		rdoAscending.setBackground(panelcolor1);
		rdoDecending.setBackground(panelcolor1);
		btnShow.setBackground(panelcolor3);
		btnBack.setBackground(panelcolor3);

		TSCComboBox.setBorder(border1);

		panel1.setLayout(null);
		panel1.setBackground(panelcolor1);
		panel1.setBounds(0,0,570,220);
		panel1.add(lblSD);
		panel1.add(lblED);
		panel1.add(datePicker1);
		panel1.add(datePicker2);
		panel1.add(lblTSC);
		panel1.add(TSCComboBox);
		panel1.add(lblOption);
		panel1.add(rdoAscending);
		panel1.add(rdoDecending);
		panel1.add(btnShow);
		
		add(panel1);
		add(scrollPane);
		add(btnBack);
		
		for(TimeSlotObj timeSlot : timeSlotList)
		{
			TSCComboBox.addItem(timeSlot.getTimeSlot());
		}
		
		btnShow.addActionListener(new MyActionListener());
		btnBack.addActionListener(new MyActionListener());
		
		}
	
	public void sedate() 
	{
		int day,month,year,day1,month1,year1;
		String time;
		time=(String) TSCComboBox.getSelectedItem();
		Date sDate = (Date) datePicker1.getModel().getValue();
		day = sDate.getDate();
		month = sDate.getMonth()+1;
		year = sDate.getYear()+1900;
		Date eDate = (Date) datePicker2.getModel().getValue();
		day1 = eDate.getDate();
		month1 = eDate.getMonth()+1;
		year1 = eDate.getYear()+1900;
		BTEReport report = new BTEReport();
		ArrayList<String> foodIDAry = new ArrayList<>();
		for(FoodObj food : foodList) 
		{
			foodIDAry.add(food.getFoodID());
		}
		model.setRowCount(foodIDAry.size());

		ArrayList<VoucherObj> voucherList =report.getVoucherByTSC(time, sDate, eDate);
		
		int[]qtyAry=new int[foodIDAry.size()];
		for(VoucherObj v : voucherList)
		{
			ArrayList<ItemObj> itemList = v.getSaleItems();
			for(int i=0;i<itemList.size();i++) 
			{
				String itemFoodID=itemList.get(i).getFood().getFoodID();
				for(int j=0;j<foodIDAry.size();j++) 
				{
					if(itemFoodID.equals(foodIDAry.get(j)))
						qtyAry[j]+=itemList.get(i).getQty();
				}
			}
		}
		if(rdoAscending.isSelected())
			sortByAsc(qtyAry, foodIDAry);
		else
			sortByDsc(qtyAry,foodIDAry);
		for(int i=0;i<foodIDAry.size();i++)
		{	
			//System.out.println(foodIDAry.get(i)+"\t"+qtyAry[i]);
			table.setValueAt(foodIDAry.get(i),i,0);
			table.setValueAt(qtyAry[i],i,1);
		}
	}
	
	public void sortByAsc(int [] qtyAry, ArrayList<String> foodIDAry)
	{
		//bubble sort
		for(int i=0;i<qtyAry.length;i++) {
			for(int j=0;j<qtyAry.length-1;j++) {
				if(qtyAry[j] > qtyAry[j+1]) {
					int qtyTemp;
					qtyTemp=qtyAry[j];
					qtyAry[j]=qtyAry[j+1];
					qtyAry[j+1]=qtyTemp;
					String foodTemp;
					foodTemp=foodIDAry.get(j);
					foodIDAry.set(j, foodIDAry.get(j+1));
					foodIDAry.set(j+1, foodTemp);
				}
			}
		}
	}
	public void sortByDsc(int [] qtyAry, ArrayList<String> foodIDAry)
	{
		//bubble sort
		for(int i=0;i<qtyAry.length;i++) {
			for(int j=0;j<qtyAry.length-1;j++) {
				if(qtyAry[j] < qtyAry[j+1]) {
					int qtyTemp;
					qtyTemp=qtyAry[j];
					qtyAry[j]=qtyAry[j+1];
					qtyAry[j+1]=qtyTemp;
					String foodTemp;
					foodTemp=foodIDAry.get(j);
					foodIDAry.set(j,foodIDAry.get(j+1));
                    foodIDAry.set(j+1, foodTemp);
				}
			}
		}
	}
	
	public void timeSlot() 
	{
		ReadJSONTimeSlot reader =new ReadJSONTimeSlot();
		ArrayList<TimeSlotObj> timeList = reader.getTimeSlotList();
		String time;
		time=(String) TSCComboBox.getSelectedItem();
		for(TimeSlotObj time5 : timeList)
		{
			if(time.equals(time5.getTimeSlotCode()))
		this.lblTSC.setText(time5.getTimeSlot());
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
				if(dob == null || dob1 == null || rdoGroup.isSelected(null) || TSCComboBox.equals(null)) 
				{
					JOptionPane.showMessageDialog(null, "You need to enter full Information!!!");
				}else 
				{
				sedate();
				timeSlot();
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
