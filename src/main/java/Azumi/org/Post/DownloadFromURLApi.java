package Azumi.org.Post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.criterion.Example;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownloadFromURLApi {

    //method responsible for update DB from URL adress
    public StringBuffer updateDB() {
        StringBuffer sb = new StringBuffer();
        try {
            URL u = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection url = (HttpURLConnection) u.openConnection();
            String line;
            if (url.getResponseCode() == 200) {
                InputStream im = url.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(im));

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sb;
    }
}

