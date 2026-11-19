
public class ItemObj
{
	private int no;
	private FoodObj food;
	private int qty;
	private int total;
	
	public ItemObj() 
	{
		super();
	}

	public ItemObj(int no, FoodObj food, int qty, int total) 
	{
		super();
		this.no = no;
		this.food = food;
		this.qty = qty;
		this.total = total;
	}

	public int getNo() 
	{
		return no;
	}

	public void setNo(int no) 
	{
		this.no = no;
	}

	public FoodObj getFood()
	{
		return food;
	}

	public void setFood(FoodObj food)
	{
		this.food = food;
	}

	public int getQty()
	{
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
		
	}

	public int getTotal() 
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	@Override
	public String toString() 
	{
		return "ItemObj [no=" + no + ", food=" + food + ", qty=" + qty + ", total=" + total + "]";
	}

	
	
	
}
