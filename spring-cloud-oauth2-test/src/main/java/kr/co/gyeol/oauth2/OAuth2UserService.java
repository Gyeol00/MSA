package kr.co.gyeol.oauth2;

import kr.co.gyeol.entity.User;
import kr.co.gyeol.repository.UserRepository;
import kr.co.gyeol.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/*
    1. 사용자가 구글 로그인을 시도하면, 구글에서 사용자 정보를 받아옴.
    2. 그 정보를 가지고 DB에서 사용자가 있는지 확인.
    3. 사용자가 없으면 새로 생성해서 DB에 저장하고, 있으면 기존 사용자 정보를 사용.
    4. 사용자 정보를 MyUserDetails 객체에 담아서 리턴.

    이렇게 해서 나중에 Spring Security에서 인증을 할 때 MyUserDetails 객체가 사용됨.
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    // 사용자가 소셜 로그인을 시도할 때 실행되는 메서드
    // userRequest(사용자 정보) 여기로 로그인 한 회원 정보 들어옴
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // OAuth 인증 업체(구글, 네이버, 카카오 등등) 유저 정보 객체 반환
        log.info("userRequest : {}", userRequest);

        // 로그인 요청이 이루어진 후, 액세스 토큰 반환
        String accessToken = userRequest.getAccessToken().getTokenValue();
        log.info("accessToken : {}", accessToken);

        // 어떤 소셜로 로그인 했는지 알려주는 값
        String provider = userRequest.getClientRegistration().getRegistrationId();
        log.info("provider : {}", provider);

        // 구글에서 실제로 사용자의 정보를 받아오는 작업
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("oAuth2User : {}", oAuth2User);

        // 나의 애플리케이션에서 인증 처리, 구글에서 가져온 사용자 정보
        Map<String, Object> attrs = oAuth2User.getAttributes();

        String email = (String) attrs.get("email");
        String uid = email.substring(0, email.lastIndexOf("@"));
        String name = (String) attrs.get("name");

        // 회원 테이블에서 사용자 확인
        Optional<User> optUser = userRepository.findById(uid);

        // 반환값은 MyUserDetails에서 선언
        User user = null;
        if (optUser.isPresent()) {
            user = optUser.get();

        }else {
            // 회원이 존재하지 않으면 OAuth 회원 정보 저장
            user = User.builder()
                    .uid(uid)
                    .name(name)
                    .role("USER")
                    .provider(provider) // 구글에서 아이디를 받아오기 때문에 비번이 없음. 제공자에 대한 정보를 저장해야 됨.
                    .build();

            userRepository.save(user);
        }

        return MyUserDetails.builder()
                .user(user)
                .attributes(attrs)
                .accessToken(accessToken)
                .build();

    }
}
