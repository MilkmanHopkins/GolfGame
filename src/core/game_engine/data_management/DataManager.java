package core.game_engine.data_management;

import core.game_engine.Sprite;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class DataManager {
    PApplet parent;
    private String load_game_file = "hole1.json";
    private String data_folder = "src/core/game/data_folder/";
    public JSONObject game_data;
    public DataManager(PApplet p){
        this.parent = p;
    }
    public void load(){
        this.game_data = parent.loadJSONObject(data_folder+load_game_file);
    }
    public void save(ArrayList<Sprite> json_list, String nameOfList, Sprite player){
        // list of objects with a name
        JSONArray new_list = new JSONArray();

        for(Serializable serialJson : json_list){
            // add object to list
            new_list.append(serialJson.serializeToJSON());
        }
        //Player always added
        new_list.append(player.serializeToJSON());

        this.game_data.setJSONArray(nameOfList, new_list);
        parent.saveJSONObject(this.game_data, data_folder+load_game_file);
    }
    public JSONArray get_json_array(String arrayName){
        if(this.game_data.hasKey(arrayName)){
            return this.game_data.getJSONArray(arrayName);
        }
        return null;
    }
    // Select between different JSON files
    public void setLoad_game_file(int load_game_file) {
        this.load_game_file = "hole"+ load_game_file + ".json";
    }
}
