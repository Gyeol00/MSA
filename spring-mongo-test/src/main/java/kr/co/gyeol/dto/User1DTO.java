package kr.co.gyeol.dto;

import kr.co.gyeol.document.User1Document;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User1DTO {

    // entity 식별값이므로 dto는 없음
    // private String _id;
    private String uid;
    private String name;
    private int age;
    private String addr;

    public User1Document toDocument() {
        return User1Document.builder()
                            .uid(uid)
                            .name(name)
                            .age(age)
                            .addr(addr)
                            .build();
    }
}
