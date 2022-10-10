package cn.edu.cumt.ec.shop.config;


import cn.edu.cumt.ec.shop.security.JwtAuthenticationEntryPoint;
import cn.edu.cumt.ec.shop.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * 自定义Spring Security认证处理类的时候
 * 我们需要继承自WebSecurityConfigurerAdapter来完成，相关配置重写对应 方法即可。 
 * */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter{
	private final JwtAuthenticationEntryPoint unauthorizedHandler;
	private final AccessDeniedHandler accessDeniedHandler;
	private final UserDetailsService userService;
	private final JwtAuthenticationTokenFilter authenticationTokenFilter;

	@Autowired
	public WebConfig(JwtAuthenticationEntryPoint unauthorizedHandler,
					 @Qualifier("accessDeniedHandler") AccessDeniedHandler accessDeniedHandler,
					 @Qualifier("userService") UserDetailsService userService,
					 JwtAuthenticationTokenFilter authenticationTokenFilter) {
		this.unauthorizedHandler = unauthorizedHandler;
		this.accessDeniedHandler = accessDeniedHandler;
		this.userService = userService;
		this.authenticationTokenFilter = authenticationTokenFilter;
	}

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				//设置UserDetailsService
				.userDetailsService(this.userService)
				//使用BCrypt进行密码的hash
				.passwordEncoder(passwordEncoder());
	}

	/**
	 * 装载BCrypt密码编码器
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
				.and()
				//由于使用的是JWT，我们这里不需要csrf
				.csrf().disable()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				//基于token，所以不需要session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				//对于获取token的rest api要允许匿名访问
				.antMatchers("/auth/login", "/auth/sign","/webjars/**", "/error/**").permitAll()
				//放行浏览器的预检请求【option请求】
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				//除上面外的所有请求全部需要鉴权认证
				.anyRequest().authenticated();
		// 禁用缓存
		httpSecurity.headers().cacheControl();
		// 添加JWT filter
		httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers( "/doc.html",
				"/swagger-ui.html",
				"/swagger-ui/*",
				"/swagger-resources/**",
				"/v2/api-docs",
				"/v3/api-docs",
				"/webjars/**",
				"/favicon.ico",
				"/**/*.html",
				"/**/*.js",
				"/**.json",
				"/**/*.css",
				"/**/*.jpg",
				"/**/*.png");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
