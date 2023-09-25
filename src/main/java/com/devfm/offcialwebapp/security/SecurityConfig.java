package com.devfm.offcialwebapp.security;
import com.devfm.offcialwebapp.security.filter.CustomAuthenticationFilter;
import com.devfm.offcialwebapp.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@SuppressWarnings(value = "deprecation")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Configure authentication manager
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    // Configure HTTP security settings
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Create a custom authentication filter
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");

        // Configure security settings
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);

        // Allow unauthenticated access to login and token refresh endpoints
        http.authorizeRequests().antMatchers("/api/login/**", "/user/refresh-token?**").permitAll();

        // Add role-based authorization for specific endpoints
        addPatternsToRole(http, GET, "/user/get-user/**", "ROLE_USER");
        http.authorizeRequests().antMatchers(POST, "/add-user/**").hasAnyAuthority("ROLE_ADMIN");

        // Require authentication for all other endpoints
        http.authorizeRequests().anyRequest().authenticated();

        // Add custom authentication and authorization filters
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // Define a custom authentication manager bean
    @Bean
    public AuthenticationManager authenticationManageran() throws Exception {
        return super.authenticationManagerBean();
    }

    // Helper method to add role-based authorization patterns
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry addPatternsToRole(HttpSecurity http,
                                                                                                               HttpMethod HttpMethod,
                                                                                                               String path,
                                                                                                               String role) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers(HttpMethod, path)
                .hasAnyAuthority(role);
    }
}
