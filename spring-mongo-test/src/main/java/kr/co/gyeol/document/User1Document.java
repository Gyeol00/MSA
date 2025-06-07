package kr.co.gyeol.document;

import kr.co.gyeol.dto.User1DTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(value = "user1")
public class User1Document {

    @Id
    private String _id;
    private String uid;
    private String name;
    private int age;
    private String addr;

    // 귀찮으면 DTO 만들지 말고 controller에서 document 반화하면 됨.
    public User1DTO toDTO() {
        return User1DTO.builder()
                .uid(uid)
                .name(name)
                .age(age)
                .addr(addr)
                .build();
    }

}
