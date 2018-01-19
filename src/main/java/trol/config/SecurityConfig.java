package trol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


/**
 * Configuration class for Spring Security.
 * Contains methods managing authentication method and user roles and permissions.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    /**
     * Manages users permissions to specific http requests.
     * Stylesheets and login page are available for all requests. Other requests have to be authorized.
     * @param http allows configuring web based security for specific http requests
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    /**
     *  Configures method of authentication. Application uses JDBC authentication.
     *  Database should contain tables for users and user roles (authorities).
     *  Sets queries for users and user roles.
     * @param auth allows for building different types of authentication
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
                "SELECT username,password,enabled FROM appusers WHERE username=?"
        ).authoritiesByUsernameQuery(
                "select username, role from authorities where username=?"
        );
    }
}
