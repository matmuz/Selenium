package data;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Existing (already registered) user's data as constants read from json file
 */
public final class ExistingUser {

    private static final String PATH_TO_FILE = "src/test/resources/data/existingUser.json";
    private static final String ADDRESS_KEY = "my_address";

    public static final String FIRSTNAME;
    public static final String LASTNAME;
    public static final String EMAIL;
    public static final String PASSWORD;
    public static final String ADDRESS;
    public static final String POSTAL_CODE;
    public static final String CITY;

    static {
        String json = null;
        try {
            json = convertFileIntoString(PATH_TO_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert json != null;
        JSONObject jsonObject = new JSONObject(json);
        FIRSTNAME = jsonObject.getString("firstname");
        LASTNAME = jsonObject.getString("lastname");
        EMAIL = jsonObject.getString("email");
        PASSWORD = jsonObject.getString("password");
        ADDRESS = getAddressObject(jsonObject).getString("address");
        POSTAL_CODE = getAddressObject(jsonObject).getString("postal_code");
        CITY = getAddressObject(jsonObject).getString("city");
    }

    /**
     * Converts a json file into a String
     *
     * @param file path to a file
     * @return Json as a String
     * @throws IOException if a file is not found
     */
    public static String convertFileIntoString(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    /**
     * Gets full name by combining first and last name with space added between
     *
     * @return full name as String
     */
    public static String getFullName() {
        return FIRSTNAME + " " + LASTNAME;
    }

    private static JSONObject getAddressObject(JSONObject jsonObject) {
        return jsonObject.getJSONObject(ADDRESS_KEY);
    }
}