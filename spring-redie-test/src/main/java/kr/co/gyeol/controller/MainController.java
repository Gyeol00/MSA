package kr.co.gyeol.controller;

import kr.co.gyeol.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final MainService mainService;

    @PostMapping("/redis/string")
    public void setValue(String key, String value) {
        mainService.setValue(key, value);
    }

    @GetMapping("/redis/string/{key}")
    public ResponseEntity getValue(@PathVariable("key") String key) {

        String value = mainService.getValue(key);

        return ResponseEntity.ok().body(value);
    }

    // 리스트에 데이터 추가
    @PostMapping("/redis/addToListFromRight")
    public void addToListFromRight(String key, String value) {

        mainService.addToListFromRight(key, value);

    }

    @PostMapping("/redis/addToListFromLeft")
    public void addToListFromLeft(String key, String value) {
        mainService.addToListFromLeft(key, value);
    }

    @GetMapping("/redis/getFromList")
    public void getFromList(String key, int index) {
        mainService.getFromList(key, index);

    }

    @GetMapping("/redis/getRangeFromList")
    public void getRangeFromList(String key, int start, int end) {
        mainService.getRangeFromList(key, start, end);
    }

    @PostMapping("/redis/set")
    public void addToSet(String key, String[] value) {

        mainService.addToSet(key, value);
    }

    @GetMapping("/redis/getFromSet")
    public void getFromSet(String key) {
        mainService.getFromSet(key);
    }

//    @GetMapping("/redis/set/{key}")
//    public ResponseEntity<Set<String>> getFromSet(@PathVariable("key") String key){
//        Set<String> resultSet = mainService.getFromSet(key);
//        return ResponseEntity.ok().body(resultSet);
//    }

}
