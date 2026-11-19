import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class ReadJSONFood

{
	ArrayList<FoodObj> foodList = new ArrayList<>();
	

    public ReadJSONFood() 
    {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        
        try 
        {
        	reader = new FileReader("bte_foods.json"); 
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray foodListInJSON = (JSONArray) obj;
           // System.out.println(employeeListInJSON);
            
            for(Object jsonObj : foodListInJSON) {
            	JSONObject foodInJSon = (JSONObject)jsonObj;
            	foodList.add(parseFoodObject(foodInJSon));
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private FoodObj parseFoodObject(JSONObject food) 
    {
        //Get emp object within list
        JSONObject obj = (JSONObject) food.get("food");
         
        //Get emp name
        String foodID = (String) obj.get("foodID");    
         
        String foodName = (String) obj.get("foodName");
        
        int foodPrice = Integer.parseInt((String)obj.get("foodPrice"));  
        
        //Get emp major
       
        
        FoodObj foodObject = new FoodObj(foodID,foodName,foodPrice);
        return foodObject;
    }
    
    public ArrayList<FoodObj> getFoodList(){
    	return foodList;
    }
//   public static void main(String[]args) {
//    	ReadJSONFood reader = new ReadJSONFood();
//    	ArrayList<FoodObj> foodList = reader.getFoodList();
//    	for(FoodObj food : foodList)
//    		System.out.println(food);
//    }
}