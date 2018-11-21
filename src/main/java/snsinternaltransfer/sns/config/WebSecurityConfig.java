package snsinternaltransfer.sns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails NAN59 =
                User.withDefaultPasswordEncoder()
                .username("nansens")
                .password("nan59")
                .roles("USER")
                .build();

        UserDetails HELL =
                User.withDefaultPasswordEncoder()
                        .username("hell")
                        .password("hellerup")
                        .roles("USER")
                        .build();

        UserDetails ØST =
                User.withDefaultPasswordEncoder()
                        .username("øst")
                        .password("østerf")
                        .roles("USER")
                        .build();

        UserDetails ISTE =
                User.withDefaultPasswordEncoder()
                        .username("istedgade")
                        .password("kongAnders")
                        .roles("USER")
                        .build();

        UserDetails GLK =
                User.withDefaultPasswordEncoder()
                        .username("glk")
                        .password("glk120")
                        .roles("USER")
                        .build();

        UserDetails VALBY =
                User.withDefaultPasswordEncoder()
                        .username("valby")
                        .password("kruse")
                        .roles("USER")
                        .build();

        UserDetails LYNGBY =
                User.withDefaultPasswordEncoder()
                        .username("lyngby")
                        .password("jacobjo")
                        .roles("USER")
                        .build();

        UserDetails TIVH =
                User.withDefaultPasswordEncoder()
                        .username("hotel")
                        .password("tivolihotel")
                        .roles("USER")
                        .build();

        UserDetails RUNG =
                User.withDefaultPasswordEncoder()
                        .username("rungsted")
                        .password("havn")
                        .roles("USER")
                        .build();

        UserDetails BORG =
                User.withDefaultPasswordEncoder()
                        .username("borgergade")
                        .password("burger")
                        .roles("USER")
                        .build();

        UserDetails KRUDT =
                User.withDefaultPasswordEncoder()
                        .username("krudthuset")
                        .password("pruthuset")
                        .roles("USER")
                        .build();

        UserDetails TIVG =
                User.withDefaultPasswordEncoder()
                        .username("garden")
                        .password("tivoligarden")
                        .roles("USER")
                        .build();

        UserDetails FINANS =
                User.withDefaultPasswordEncoder()
                        .username("finans")
                        .password("JonJon")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(GLK, NAN59, HELL, ØST, ISTE, VALBY, LYNGBY, TIVH, RUNG, BORG, KRUDT, TIVG, FINANS);
    }
}
