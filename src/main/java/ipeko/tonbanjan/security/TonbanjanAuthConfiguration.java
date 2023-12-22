package ipeko.tonbanjan.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class TonbanjanAuthConfiguration {
  @Value("${azure.ad.tenant-id}")
  private String tenantId;

  @Value("${azure.ad.client-id}")
  private String clientId;

  @Value("${azure.ad.client-secret}")
  private String clientSecret;

  @Value("${azure.ad.redirect-uri}")
  private String redirectUri;

  @Value("${azure.ad.logout-uri}")
  private String logoutUri;

  @Value("${azure.ad.authorization-uri}")
  private String authorization;

  @Value("${azure.ad.token-uri}")
  private String token;

  @Value("${azure.ad.user-info-uri}")
  private String userInfo;

  @Value("${azure.ad.jwk-set-uri}")
  private String jwkSetUri;

  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(AntPathRequestMatcher.antMatcher("/waitingRoom/**"))
            .authenticated()
            .requestMatchers(AntPathRequestMatcher.antMatcher("/**"))
            .permitAll())// 上記以外は全員アクセス可能
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後に / にリダイレクト
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/*")))// h2-console用にCSRF対策を無効化
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions
                .sameOrigin()));
    return http.build();
  }

  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public ClientRegistrationRepository clientRegistrationRepository() {
    return new InMemoryClientRegistrationRepository(clientRegistration());
  }

  private ClientRegistration clientRegistration() {
    return ClientRegistration.withRegistrationId("example-client")
        .clientId("your-client-id")
        .clientSecret("your-client-secret")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .redirectUri("your-redirect-uri")
        .scope("openid", "profile", "email")
        .authorizationUri("authorization-uri")
        .tokenUri("token-uri")
        .userInfoUri("user-info-uri")
        .userNameAttributeName(IdTokenClaimNames.SUB)
        .jwkSetUri("jwk-set-uri")
        .clientName("東盤雀")
        .build();
  }
}