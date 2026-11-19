import java.util.ArrayList;
import java.util.Date;

public class VoucherObj 
{
	private String voucherId;
	private Date voucherDate;
	private TimeSlotObj bteTimeSlot;
	private ArrayList<ItemObj> itemAry;
	private long voucherTotal;
	public VoucherObj() 
	{
		super();
	}
	public VoucherObj(String voucherId, Date voucherDate, TimeSlotObj voucherTSC, ArrayList<ItemObj> saleItems,
			long voucherTotal)
	{
		super();
		this.voucherId = voucherId;
		this.voucherDate = voucherDate;
		this.bteTimeSlot = voucherTSC;
		this.itemAry = saleItems;
		this.voucherTotal = voucherTotal;
	}
	public String getVoucherId() 
	{
		return voucherId;
	}
	public void setVoucherId(String voucherId) 
	{
		this.voucherId = voucherId;
	}
	public Date getVoucherDate() {
		return voucherDate;
	}
	public void setVoucherDate(Date voucherDate) 
	{
		this.voucherDate = voucherDate;
	}
	public TimeSlotObj getVoucherTSC() {
		return bteTimeSlot;
	}
	public void setVoucherTSC(TimeSlotObj voucherTSC) 
	{
		this.bteTimeSlot = voucherTSC;
	}
	public ArrayList<ItemObj> getSaleItems()
	{
		return itemAry;
	}
	
	public long getVoucherTotal() {
		return voucherTotal;
	}
	public void setVoucherTotal(long voucherTotal)
	{
		this.voucherTotal = voucherTotal;
	}
	@Override
	public String toString() 
	{
		return "VoucherObj [voucherId=" + voucherId + ", voucherDate=" + voucherDate + ", voucherTSC=" + bteTimeSlot
				+ ", saleItems=" + itemAry + ", voucherTotal=" + voucherTotal + "]";
	}
	
	
}
