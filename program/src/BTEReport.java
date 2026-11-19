import java.util.ArrayList;
import java.util.Date;

public class BTEReport {
	
	private ArrayList<VoucherObj> voucherList;
	private ReadJSONVoucher voucherReader;
	
	public BTEReport() {
		voucherList=new ArrayList<VoucherObj>();
		voucherReader = new ReadJSONVoucher();
		voucherList=voucherReader.getVoucherList();//Sale Vouchers
	}

	public ArrayList<VoucherObj> getVoucherByFoodID(String foodName,Date startDate,Date endDate){
		ArrayList<VoucherObj> selectedVoucher = new ArrayList<VoucherObj>();
		for(VoucherObj vObj : voucherList)
		{
			Date vDate = vObj.getVoucherDate();
			System.out.println("Is in date range : "+isInDateRange(vDate, startDate, endDate));
			if(isInDateRange(vDate, startDate, endDate))
			{
				
				ArrayList<ItemObj>itemList = vObj.getSaleItems();
				for(ItemObj itemObj:itemList)
				{
					FoodObj foodObj=itemObj.getFood();
					if(foodName.equals(foodObj.getFoodName()))
					{
						selectedVoucher.add(vObj);
						break;
					}
				}
			}
		}
		return selectedVoucher;
	}
	
	public ArrayList<VoucherObj> getVoucherByTSC(String timeSlot,Date startDate,Date endDate){
		ArrayList<VoucherObj> selectedVoucher = new ArrayList<VoucherObj>();
		for(VoucherObj vObj : voucherList)
		{
			Date vDate = vObj.getVoucherDate();
			if(isInDateRange(vDate, startDate, endDate))
			{
				if(timeSlot.equals(vObj.getVoucherTSC().getTimeSlot()))
					selectedVoucher.add(vObj);
			}
		}
		return selectedVoucher;
	}
	
	
	
	public boolean isInDateRange(Date d,Date fromDate,Date toDate) {
		boolean isInRange=false;
		if(d.getYear()==fromDate.getYear() && d.getYear()==toDate.getYear()) {
			if(d.getMonth()==fromDate.getMonth() && d.getMonth()==toDate.getMonth()) {
				if(d.getDate()>=fromDate.getDate() && d.getDate()<=toDate.getDate())
					isInRange=true;
			}
		}
		return isInRange;
	}
	
}
