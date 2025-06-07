package kr.co.gyeol.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDTO {
    private String uid;
    private String pass;
    private String name;
    private String birth;
    private String role;

}