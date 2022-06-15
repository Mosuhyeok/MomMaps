package com.ssafy.mvc.config;

import com.ssafy.mvc.filter.JwtAuthenticationFilter;
import com.ssafy.mvc.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity		// 시큐리티 활성화, 기본 스프링 필터체인에 등록
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
	      return new BCryptPasswordEncoder();
	   }

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		// 해당하는 cors 허용주소를 넣을 리스트
		List<String>corsList = new ArrayList<>();
		corsList.add("*");

		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(corsList);
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");


		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.cors().configurationSource(corsConfigurationSource());
		//http.httpBasic().disable(); // 일반적인 루트가 아닌 다른 방식으로 요청시 거절, header에 id, pw가 아닌 token(jwt)을 달고 간다. 그래서 basic이 아닌 bearer를 사용한다.
		http.httpBasic().disable()
				.authorizeRequests()// 요청에 대한 사용권한 체크
//				.antMatchers("/test").authenticated()
//				.antMatchers("/admin/**").hasRole("ADMIN")
//				.antMatchers("/user/**").hasRole("GUEST")
				.antMatchers("/**").permitAll()
				.anyRequest().permitAll()
				.and()
				.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
						UsernamePasswordAuthenticationFilter.class); // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다

		// + 토큰에 저장된 유저정보를 활용하여야 하기 때문에 CustomUserDetailService 클래스를 생성합니다.
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


	}
}
