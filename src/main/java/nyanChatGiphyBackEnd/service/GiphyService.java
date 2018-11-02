package nyanChatGiphyBackEnd.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nyanChatGiphyBackEnd.model.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GiphyService {


    public List<ResponseData> getImagesFromAPI() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> giphy = restTemplate.getForEntity(
                "http://api.giphy.com/v1/gifs/search?q=ryan+gosling&api_key=fjtnCi7UwhEb04yzyHE8b0N3n9KlzAiA", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(giphy.getBody());
        JsonNode data = root.path("data");
        List<ResponseData> imageList = new ArrayList<>();
        for (JsonNode node : data) {
            JsonNode images = node.get("images");
            JsonNode originalStill = images.get("original_still");
            ResponseData image = new ResponseData();
            image.setUrl(originalStill.get("url").textValue());
            image.setWidth(originalStill.get("width").textValue());
            image.setHeight(originalStill.get("height").textValue());
            imageList.add(image);
        }
        return imageList;
    }

}
