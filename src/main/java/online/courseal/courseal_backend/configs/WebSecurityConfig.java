package online.courseal.courseal_backend.configs;

import jakarta.servlet.Filter;
import online.courseal.courseal_backend.configs.jwt.AccessDeniedHandler;
import online.courseal.courseal_backend.configs.jwt.AuthEntryPointJwt;
import online.courseal.courseal_backend.configs.jwt.AuthTokenFilter;
import online.courseal.courseal_backend.configs.jwt.HttpRequestEndpointChecker;
import online.courseal.courseal_backend.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableJpaRepositories(basePackages = {"online.courseal.courseal_backend"})
@ComponentScan("online.courseal.courseal_backend.controllers")
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DispatcherServlet dispatcherServlet;

    @Autowired
    private HttpRequestEndpointChecker endpointChecker;

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public Filter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOriginPatterns(List.of("*"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/static/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/user-management/register").permitAll()
                        .requestMatchers("/api/courseal-info").permitAll()
                        .requestMatchers("/api/user/**").permitAll()
                        .requestMatchers("/api/course/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(c -> c.authenticationEntryPoint(new AuthEntryPointJwt(endpointChecker))
                        .accessDeniedHandler(new AccessDeniedHandler(endpointChecker)))
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public HttpRequestEndpointChecker endpointChecker() {
        return new HttpRequestEndpointChecker(dispatcherServlet);
    }
}
