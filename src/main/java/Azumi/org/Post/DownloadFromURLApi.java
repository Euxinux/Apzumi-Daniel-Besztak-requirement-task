package Azumi.org.Post;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

@Component
@Slf4j
@Log
public class DownloadFromURLApi {

    @Value( "${URL.API}" )
    private String URL;
    //method responsible for update DB from URL address
    public StringBuffer updateDB() {
        StringBuffer sb = new StringBuffer();
        try {
            URL u = new URL(URL);
            HttpURLConnection url = (HttpURLConnection) u.openConnection();
            String line;
            if (url.getResponseCode() == 200) {
                InputStream im = url.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(im));

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                im.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return sb;
    }
}

