import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONTimeSlot {
ArrayList<TimeSlotObj> timeSlotList = new ArrayList<>();
	
    public ReadJSONTimeSlot() 
    {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        
        try 
        {
        	reader = new FileReader("bte_tsc.json"); 

        	Object obj = jsonParser.parse(reader);
 
            JSONArray timeSlotListInJSON = (JSONArray) obj;
            
            for(Object jsonObj : timeSlotListInJSON) {
            	JSONObject timeSlotInJSon = (JSONObject)jsonObj;
            	timeSlotList.add(parseTimeSlotObject(timeSlotInJSon));
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
        
    }
        private TimeSlotObj parseTimeSlotObject(JSONObject btetsc) 
        {
            
            JSONObject obj = (JSONObject) btetsc.get("btetsc");
             
            String timeSlotCode = (String) obj.get("timeSlotCode");    
             
            String timeSlot = (String)obj.get("timeSlot");  
            
            
            TimeSlotObj timeSlotObject = new TimeSlotObj(timeSlotCode,timeSlot);
            return timeSlotObject;
        }
        
        public ArrayList<TimeSlotObj> getTimeSlotList(){
        	return timeSlotList;
        }
//        public static void main(String[]args) {
//        	ReadJSONTimeSlot reader = new ReadJSONTimeSlot();
//        	ArrayList<TimeSlotObj> TimeSlotList = reader.getTimeSlotList();
//        	for(TimeSlotObj tsc : TimeSlotList)
//        		System.out.println(tsc);
//        }
	
}
