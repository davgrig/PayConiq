package testMethods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class gistData {

    public static JSONObject createGistJsonObject(String description, Boolean accessLevel, JSONObject files) {
        return new JSONObject()
            .put("description", description)
            .put("public", accessLevel)
            .put("files", files);
    }

    public static JSONObject createDefaultGistJsonObject() {
        try {
            String fileData = new String(Files.readAllBytes(Paths.get("src/test/resources/gist.json")));
            JSONObject files = new JSONObject(fileData);
            return createGistJsonObject("defaultTestGist", true, files);
        } catch (IOException e) {
            throw new RuntimeException("test data file is not found");
        }
    }

    public static JSONObject getUpdatedDefaultGistJsonObject() {
        JSONObject object = createDefaultGistJsonObject();
        object.put("description", "updated description");
        object.remove("public");
        object.getJSONObject("files").remove("hello_world.rb");
        object.getJSONObject("files").remove("hello_world_ruby.txt");
        object.getJSONObject("files").put("hello_world_python.txt", JSONObject.NULL);
        object.getJSONObject("files").getJSONObject("hello_world.py").put("content", "updated content");
        object.getJSONObject("files").getJSONObject("hello_world.py").put("filename", "hello_world.md");
        object.getJSONObject("files").put("new_file.txt", new JSONObject()
            .put("content", "new content"));
        return object;
    }

    public static JSONObject getJsonObjectWithoutFile() {
        JSONObject object = createDefaultGistJsonObject();
        object.remove("files");
        return object;
    }

    public static JSONObject getJsonObjectWithIncorrectFiles() {
        JSONObject object = createDefaultGistJsonObject();
        object.getJSONObject("files").put("public", "true");
        return object;
    }



}