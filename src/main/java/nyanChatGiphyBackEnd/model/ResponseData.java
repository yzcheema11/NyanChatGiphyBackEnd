package nyanChatGiphyBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseData {

    private String url;
    private String width;
    private String height;

    List<String> originalStills = new ArrayList<>();

    public ResponseData(String url, String width, String height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public ResponseData() {

    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "url='" + url + '\'' +
                '}';
    }
}
