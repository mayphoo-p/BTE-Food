

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;



public class TestDatePicker extends JFrame{
	
	private JLabel lblSDate = new JLabel("Start Date : ");
	private JLabel lblEDate = new JLabel("End Date : ");
	private JButton btnOK = new JButton("OK");
	 UtilDateModel model = new UtilDateModel();
     JDatePanelImpl datePanel = new JDatePanelImpl(model);
     DateLabelFormatter formatter = new DateLabelFormatter();
     JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,formatter);
     
     UtilDateModel model1 = new UtilDateModel();
     JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
     DateLabelFormatter formatter1 = new DateLabelFormatter();
     JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1,formatter1);
     
	
	public TestDatePicker() {
		setVisible(true);
		setSize(350,350);
		setTitle("Date Picker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(lblSDate);
		add(datePicker);
		add(lblEDate);
		add(datePicker1);
		add(btnOK);
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Date sDate = (Date) datePicker.getModel().getValue();
				Date eDate = (Date) datePicker1.getModel().getValue();
				BTEReport report = new BTEReport();
				
				//Report By FoodID
				/*ArrayList<Voucher> voucherList =report.getVoucherByFoodID("FD001", sDate, eDate);
				String[]tscAry= {"T0910","T1011","T1112","T1201","T1314","T1415","T1516","T1617","T1718","T1819","T1920","T2021"};
				int[]qtyAry=new int[tscAry.length];
				for(Voucher v : voucherList)
				{
					int index=-1;
					String tsc = v.getVoucherTSC().getTimeSlotCode();
					for(int i =0;i<tscAry.length;i++) {
						if(tsc.equals(tscAry[i]))
							index=i;
					}
					ArrayList<Item>itemList = v.getSaleItems();
					int qty=0;
					for(int i=0;i<itemList.size();i++) {
						if("FD001".equals(itemList.get(i).getFood().getFoodID()))
							qty=itemList.get(i).getQty();
					}
					if(index!=-1) {
						qtyAry[index]+=qty;
					}
					
				}
				
				
				//show report -> table
				for(int i=0;i<tscAry.length;i++) {
					System.out.println(tscAry[i]+"\t"+qtyAry[i]);
				}
				*/

				//Report By TSC
				ArrayList<VoucherObj> voucherList =report.getVoucherByTSC("T0910", sDate, eDate);
				String[]foodIDAry= {"FD001","FD002","FD003","FD004","FD005","FD006","FD007","FD008","FD009","FD010"};
				int[]qtyAry=new int[foodIDAry.length];
				for(VoucherObj v : voucherList)
				{
					ArrayList<ItemObj> itemList = v.getSaleItems();
					for(int i=0;i<itemList.size();i++) 
					{
						String itemFoodID=itemList.get(i).getFood().getFoodID();
						for(int j=0;j<foodIDAry.length;j++) 
						{
							if(itemFoodID.equals(foodIDAry[j]))
								qtyAry[j]+=itemList.get(i).getQty();
						}
					}
				}
				
				for(int i=0;i<foodIDAry.length;i++)
					System.out.println(foodIDAry[i]+"\t"+qtyAry[i]);
			}
		});
	}
	
	public static void main(String[] args) {
		new TestDatePicker();
	}

}
