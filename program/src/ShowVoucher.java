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

public class ShowVoucher extends JFrame {
	
	private JPanel panel1 = new JPanel();
	
	private JLabel lblDate = new JLabel("Voucher Date : ");
	private JLabel lblTSC = new JLabel("Time Slot Code : ");
	private JLabel lblTotal = new JLabel("Total  : ");
	private JButton btnBack = new JButton("Back");
	private JLabel lblVoucherID = new JLabel("Voucher ID : ");
	private JComboBox<String> voucherIDComboBox = new JComboBox<String>();
	private ReadJSONVoucher voucherReader = new ReadJSONVoucher();
	private ArrayList<VoucherObj> voucherList = voucherReader.getVoucherList();
	
	private JTextField txtDate = new JTextField(20);
	private JTextField txtTSC = new JTextField(20);
	private JTextField txtTotal = new JTextField(20);

	private String[] cols = {"No","Food ID","Qty","Price","Sub Total"};
	private String[][]rowData = new String[20][cols.length];
	private JTable table = new JTable(rowData, cols);
	
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
	
	private BTEFood bteFrame=null;
	
	public ShowVoucher(BTEFood bteFrame)
	{
		this.bteFrame=bteFrame;
		setVisible(true);
		setSize(600, 500);
		setLocation((int)(scrWidth / 2 - this.getWidth() / 2), (int)(scrHeight / 2 - this.getHeight() / 2));
		setTitle("Voucher");
		getContentPane().setBackground(panelcolor1);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel1.setLayout(null);
		panel1.setBounds(20,65, 540, 330);
		panel1.setBackground(panelcolor1);
		//panel1.setBorder(border2);
		
		btnBack.setFont(font1);
		lblVoucherID.setFont(font1);
		lblDate.setFont(font1);
		lblTSC.setFont(font1);
		lblTotal.setFont(font1);
		voucherIDComboBox.setFont(font1);
		txtDate.setFont(font1);
		txtTSC.setFont(font1);
		txtTotal.setFont(font1);
		
		btnBack.setBounds(490,415,70,30);
		btnBack.setBackground(panelcolor3);
		
		lblVoucherID.setBounds(35,15,100,30);
		
		voucherIDComboBox.setBounds(135,15,130,30);
		voucherIDComboBox.setBorder(border1);
		voucherIDComboBox.setBackground(panelcolor1);
		
		scrollPane.setBounds(9,100,523,160);
		panel1.add(scrollPane);
		
		lblDate.setBounds(15,10,150,30);
		panel1.add(lblDate);
		
		txtDate.setBounds(145,10,150,30);
		txtDate.setBackground(null);
		txtDate.setBorder(border1);
		panel1.add(txtDate);
		
		lblTSC.setBounds(15,53,160,30);
		panel1.add(lblTSC);
		
		txtTSC.setBounds(145,55,150,30);
		txtTSC.setBackground(null);
		txtTSC.setBorder(border1);
		panel1.add(txtTSC);
		
		lblTotal.setBounds(320,280,150,30);
		panel1.add(lblTotal);
		
		txtTotal.setBounds(380,280,150,30);
		txtTotal.setBackground(null);
		txtTotal.setBorder(border1);
		panel1.add(txtTotal);
		txtDate.setEditable(false);
		txtTSC.setEditable(false);
		txtTotal.setEditable(false);
		
		add(panel1);
		add(btnBack);
		add(lblVoucherID);
		add(voucherIDComboBox);
		
		for(VoucherObj voucher : voucherList)
		{
			voucherIDComboBox.addItem(voucher.getVoucherId());
		}
		
		btnBack.addActionListener(new MyActionListener());
		voucherIDComboBox.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ReadJSONVoucher reader = new ReadJSONVoucher();
				String voucherId;
		        voucherId = (String) voucherIDComboBox.getSelectedItem();
		        int index = reader.getVoucherIndex(voucherId);
		        VoucherObj vObj = voucherList.get(index);
		        Date date = vObj.getVoucherDate();
		        int day = date.getDate();
		        int month = date.getMonth()+1;
		        int year = date.getYear()+1900;
		        txtDate.setText(day+"."+month+"."+year);
		        String tsc = vObj.getVoucherTSC().getTimeSlot();
		        txtTSC.setText(tsc);
		        ArrayList<ItemObj> itemList = vObj.getSaleItems();
		        for (int i=0; i<itemList.size();i++)
		        {
		          ItemObj itemObj = itemList.get(i);
		          table.setValueAt(Integer.toString(itemObj.getNo()), i ,0);
		          table.setValueAt(itemObj.getFood().getFoodID(), i, 1);
		          table.setValueAt(Integer.toString(itemObj.getFood().getFoodPrice()), i, 2);
		          table.setValueAt(Integer.toString(itemObj.getQty()), i, 3);
		          table.setValueAt(Integer.toString(itemObj.getTotal()), i, 4);
		        }
		        txtTotal.setText(Long.toString(vObj.getVoucherTotal()));
				
			}
		});
		
	}
	
	
	
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			//new FoodCourtGUI();
			hideFrame();
			bteFrame.show();
		}
		
	}
	
	
	public void hideFrame() {
		this.hide();
	}

	

}
