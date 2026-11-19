

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
 
public class ReadJSONVoucher{
	
	ArrayList<VoucherObj> voucherList = new ArrayList<>();
	
    
    public ReadJSONVoucher(){
    	
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
     
         
        try{
        	FileReader reader = new FileReader("BTE_Vouchers.json");
        	
            //Read JSON file//
            Object obj = jsonParser.parse(reader);
            JSONArray foodListInJson = (JSONArray) obj;
//          System.out.println(foodListInJson);
     
           //OR
           //JSONArray foodListInJson = (JSONArray) jsonParser.parse(reader);
           //System.out.println(foodListInJson);
            
            for(Object jsonObj : foodListInJson) {
            	JSONObject voucherInJson = (JSONObject)jsonObj;
            	voucherList.add(parseVoucherObject(voucherInJson));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
 
    private VoucherObj parseVoucherObject(JSONObject voucher){
        //Get food object within list
        JSONObject voucherObject = (JSONObject) voucher.get("voucher");
         
        //Get voucher id
        String voucherID = (String) voucherObject.get("voucherID");    
         
        //Get voucher Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String d = (String) voucherObject.get("voucherDate");  
       Date voucherDate=null;
	try {
		voucherDate = dateFormat.parse(d);
	} catch (java.text.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        
       //Get TimeSlotCode
        Object strTsc = voucherObject.get("bteTimeSlot");
        TimeSlotObj tsc = new Gson().fromJson(strTsc.toString(), TimeSlotObj.class);
        //Get ItemArray
        ArrayList<ItemObj> itemAry=new ArrayList<ItemObj>();
        JSONArray arrays = (JSONArray) voucherObject.get("itemAry");
        for (Object object : arrays) {
        	//if(object instanceof Item)
        		itemAry.add(new Gson().fromJson(object.toString(), ItemObj.class));
        }
        //System.out.println("itemAry size is "+itemAry.size());
        //Get VoucherTotal
        long voucherTotal = (long) voucherObject.get("voucherTotal");
        
        VoucherObj vObj = new VoucherObj(voucherID, voucherDate, tsc, itemAry, voucherTotal);
        return vObj;
    }
    
    public ArrayList<VoucherObj> getVoucherList(){
    	return voucherList;
    }
    
//    public static void main(String[]args) {
//    	ReadJSONVoucher reader = new ReadJSONVoucher();
//    	ArrayList<VoucherObj> VoucherList = reader.getVoucherList();
//    	for(VoucherObj voucher : VoucherList)
//    		System.out.println(voucher);
//    }
    public int getVoucherIndex(String vID)
    {
    	int index=-1;
    	for (int i=0;i<voucherList.size();i++)
    	{
    		if (vID.equals(voucherList.get(i).getVoucherId()))
    			index=i;
    	}
		return index;
    	
    }
}