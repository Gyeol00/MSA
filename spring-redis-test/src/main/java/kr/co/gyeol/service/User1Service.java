package kr.co.gyeol.service;

import kr.co.gyeol.dto.User1DTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class User1Service { // redis CRUD

    private final static String KEY = "user1";
    private final RedisTemplate<String, User1DTO> redisTemplate;

    public void save(User1DTO user1DTO) {
        redisTemplate.opsForHash().put(KEY, user1DTO.getUid(), user1DTO); // KEY = redis KEY
    }

    @Cacheable(value = KEY, key = "#uid") // findByuid 메서드를 호출하면 return값을 cache 해줌.
    public User1DTO findByUid(String uid) {

        log.info("findByUid : " + uid + "!!!!!!!!!!!!!!!");

        return (User1DTO) redisTemplate.opsForHash().get(KEY, uid); // 메서드 리턴값을 캐싱, 이 값이 캐싱하고 그 캐싱값을 리턴
    }

    public Map<Object, Object> findAll(){
        return redisTemplate.opsForHash().entries(KEY); // entries가 반환값이 map
    }

    public void update(User1DTO user1DTO) {
        // save와 동일
        redisTemplate.opsForHash().put(KEY, user1DTO.getUid(), user1DTO);
    }

    public void delete(String uid){
        redisTemplate.opsForHash().delete(KEY, uid);
    }

}
