package ipeko.tonbanjan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class TonbanjanAuthConfiguration {
  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後に / にリダイレクト
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(AntPathRequestMatcher.antMatcher("/waitingRoom/**"))
            .authenticated()
            .requestMatchers(AntPathRequestMatcher.antMatcher("/**"))
            .permitAll())// 上記以外は全員アクセス可能
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
  public InMemoryUserDetailsManager userDetailsService() {

    // ユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されているため，{bcrypt}とつける
    // ハッシュ化せずに平文でパスワードを指定する場合は{noop}をつける
    // ハッシュ化されたパスワードを得るには，この授業のbashターミナルで下記のように末尾にユーザ名とパスワードを指定すると良い(要VPN)
    // $ sshrun htpasswd -nbBC 10 user1 p@ss

    // $ sshrun htpasswd -nbBC 10 user1 p@ss
    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$10$RTBVuLU8cWogbgMrPoPItuPz.kZSNG8q.98jVKNJBFqx6v0G8UdQu").roles("USER").build();
    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$10$EHxZV90jvMPpdG93R39fOutiBp103qRsUSMswcY62mOVRTpPpBqE.").roles("USER").build();
    UserDetails user3 = User.withUsername("user3")
        .password("{bcrypt}$2y$10$M.bX1fpymkpz1X0tlZWPGem/ETREI5WaIVZJdpI9gJ9NXzNGWbzFG").roles("USER").build();
    UserDetails admin = User.withUsername("admin")
        .password("{bcrypt}$2y$10$dNYDu/FsSxtRYLwDu/coKOQKXH8EmyRTnyj/teQjItpJzGJTnEADu").roles("ADMIN").build();

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(user1, user2, user3, admin);
  }

}
