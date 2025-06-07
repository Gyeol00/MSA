package kr.co.gyeol.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(value = "user2")
public class User2Document {

    @Id
    private String _id;
    private String uid;
    private String name;
    private String hp;
    private int age;

}
