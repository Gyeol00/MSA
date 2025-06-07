package kr.co.gyeol.service;

import kr.co.gyeol.document.User1Document;
import kr.co.gyeol.dto.User1DTO;
import kr.co.gyeol.repository.User1Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class User1Service {

    private final User1Repository user1Repository;

    public void save(User1DTO user1DTO) {

        User1Document doc = user1DTO.toDocument();
        user1Repository.save(doc);

    }

    public List<User1DTO> findAll() {

        List<User1Document> user1List = user1Repository.findAll();
        List<User1DTO> user1DTOList =  user1List
                                        .stream()
                                        .map((document) -> {
                                            User1DTO dto = document.toDTO();
                                            return dto;
        }).toList();

        return user1DTOList;

    }

    public User1DTO findById(String uid) {
        Optional<User1Document> user1Document = user1Repository.findByUid(uid);

        if(user1Document.isPresent()) {
            User1DTO dto = user1Document.get().toDTO();
            return dto;
        }

        return null;
     }

    public void modify(User1DTO user1DTO) {

        String uid = user1DTO.getUid();

        Optional<User1Document> optUser1 = user1Repository.findByUid(uid);

        if(optUser1.isPresent()) {
            // 수정

            /*
            setter 안쓰는 방법
            User1Document user1Document = optUser1.get();
            User1Document modifiedUser1 = User1Document.builder()
                                                        ._id(user1Document.get_id())
                                                        .name(user1DTO.getName())
                                                        .age(user1DTO.getAge())
                                                        .addr(user1DTO.getAddr())
                                                        .build();

             */


            User1Document modifiedUser1 = user1DTO.toDocument();
            modifiedUser1.set_id(optUser1.get().get_id());

            user1Repository.save(modifiedUser1);

        }

    }

    public void delete(String uid) {

        user1Repository.deleteByUid(uid);
    }

}
