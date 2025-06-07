package kr.co.gyeol.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User1DTO implements Serializable { // Serializable 직렬화. DTO를 redis에 저장.

    private String uid;
    private String name;
    private int age;
    private String addr;

}
