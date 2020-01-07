package core.game_engine;

import processing.data.JSONObject;

public interface Serializable {
    public JSONObject serializeToJSON();
    public void loadJSONObject(JSONObject json);
}
