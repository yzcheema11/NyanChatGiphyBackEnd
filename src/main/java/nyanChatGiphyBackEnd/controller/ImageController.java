package nyanChatGiphyBackEnd.controller;

import nyanChatGiphyBackEnd.service.GiphyService;
import nyanChatGiphyBackEnd.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ImageController {
    @Autowired
    private GiphyService giphyService;

    @GetMapping("/")
    public ResponseEntity<List<ResponseData>> getImageStills(){
        List<ResponseData> response;
        try{
            response = giphyService.getImagesFromAPI();
        }catch (IOException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
