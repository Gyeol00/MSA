package kr.co.gyeol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

// 레퍼지토리 없이 서비스에서 처리함
@RequiredArgsConstructor
@Service
public class MainService {

    // 설정에서 빈등록한 stringRedisTemplate 주입
    private final StringRedisTemplate stringRedisTemplate;

    public void setValue(String key, String value) {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        valueOps.set(key, value);
    }

    public String getValue(String key){
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        String value = valueOps.get(key);
        return value;
    }

    // 리스트 rpush
    public void addToListFromRight(String key, String value) {
        ListOperations<String, String> listOps = stringRedisTemplate.opsForList();
        listOps.rightPush(key, value);
    }
    public void addToListFromLeft(String key, String value){
        ListOperations<String, String> listOps = stringRedisTemplate.opsForList();
        listOps.leftPush(key, value);
    }

    // index 데이터 값
    public String getFromList(String key, int index){
        ListOperations<String, String> listOps = stringRedisTemplate.opsForList();
        return listOps.index(key, index);
    }
    public List<String> getRangeFromList(String key, int start, int end){
        ListOperations<String, String> listOps = stringRedisTemplate.opsForList();
        return listOps.range(key, start, end);
    }

    public void addToSet(String key, String... values) { // String... values = 배열
        SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
        setOps.add(key, values);
    }
    public Set<String> getFromSet(String key){
        SetOperations<String, String> setOps = stringRedisTemplate.opsForSet(); // 제네릭 선언
        return setOps.members(key);
    }

    public void addToHash(String key, String hashKey, String value) {
        HashOperations<String, String, String> hashOps = stringRedisTemplate.opsForHash();
        hashOps.put(key, hashKey, value);
    }

    public String getFromHash(String key, String hashKey){
        HashOperations<String, String, String> hashOps = stringRedisTemplate.opsForHash();
        return hashOps.get(key, hashKey);
    }
}