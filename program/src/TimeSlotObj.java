
public class TimeSlotObj
{
	private String timeSlotCode;
	private String timeSlot;

	public TimeSlotObj(String timeSlotCode, String timeSlot)
	{
		super();
		this.timeSlotCode = timeSlotCode;
		this.timeSlot = timeSlot;
	}

	public TimeSlotObj() 
	{
		super();
	}

	public String getTimeSlotCode() 
	{
		return timeSlotCode;
	}

	public void setTimeSlotCode(String timeSlotCode)
	{
		this.timeSlotCode = timeSlotCode;
	}

	public String getTimeSlot() 
	{
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot)
	{
		this.timeSlot = timeSlot;
	}

	@Override
	public String toString()
	{
		return "TimeSlotObj [timeSlotCode=" + timeSlotCode + ", timeSlot=" + timeSlot + "]";
	}
	
	
}
