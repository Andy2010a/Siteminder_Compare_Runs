import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;





public class Main {
    public static TreeMap<String, String> hashmap = new TreeMap<String, String>();// tree map which alphabetises the attributes
  public  static JSONObject finalJsonObj = new JSONObject();




    public static void main(String[] args) {
        //System.out.println("Hello World!");
        JSONParser jsonParser = new JSONParser();// JsonParser for reading the Json file

        try {

            FileReader reader1 = new FileReader("Run1.json");//reader declaration for Run1.json
            JSONObject jsonObj1 = (JSONObject) jsonParser.parse(reader1);//parsing the json file

            readJson(jsonObj1,"Run1"); //reading the Run1.json file

            FileReader reader2 = new FileReader("Run2.json");//reader declaration for Run2.json
            JSONObject jsonObj2 = (JSONObject) jsonParser.parse(reader2); //parsing the json file

            readJson(jsonObj2,"Run2");//reading the Run1.json file

            finalJsonObj.putAll(hashmap); // Putting all the data in the tree map

            //Write JSON file
            try {
                    FileWriter file = new FileWriter("CombinedResults.json");

                file.write(finalJsonObj.toJSONString());// writing the json file
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

        public static void readJson(JSONObject jsonObj,String run) {

            for (Object key : jsonObj.keySet()) {  // looping through all keys

                JSONArray jsonArray = new JSONArray();
                JSONObject arrayjsonObject = new JSONObject();// declaring jsonobject and json array
                String keyStr = (String) key; //casting
                JSONArray keyvalue = (JSONArray) jsonObj.get(keyStr);// Grabbing the Jsonarray from Json object
                String value = ((JSONObject) keyvalue.get(0)).get("score").toString();//Getting score

                String attr = ((JSONObject) keyvalue.get(0)).get("attribute").toString(); //Getting the Attribute

                hashmap.put(attr + "-" + run, "Score:" + value); //setting and adding the tree map

            }


        }

}
