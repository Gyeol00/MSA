package kr.co.gyeol.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "User")
public class User {

    @Id
    private String uid;
    private String pass;
    private String name;
    private String birth;
    private String role;

    // OAuth 인증 업체 정보
    private String provider;

}