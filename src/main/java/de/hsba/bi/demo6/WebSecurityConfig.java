package de.hsba.bi.demo6;



import de.hsba.bi.demo6.user.UserAdapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import de.hsba.bi.demo6.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

// Legt die Zugriffsberechtigungen für die Rollen (USER & ADMIN) fest
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/users/registration").permitAll()
                .antMatchers("/users/**").hasRole(de.hsba.bi.demo6.user.User.ADMIN_ROLE)
                .antMatchers("/evaluationForms/{id}/delete").hasRole(User.ADMIN_ROLE)
                .antMatchers("/evaluationForms/{evaluationFormId}/deleteQuestion/**").hasRole(User.ADMIN_ROLE)
                .antMatchers("/evaluationForms/{evaluationFormId}/changeQuestion/**").hasRole(User.ADMIN_ROLE)
                .antMatchers("/evaluationForms/{id}/questions").hasRole(User.ADMIN_ROLE)
                .antMatchers("/lectures").hasRole(User.ADMIN_ROLE)
                .antMatchers("/lectures/").hasRole(User.ADMIN_ROLE)
                .antMatchers("/lectures/{lectureId}/delete").hasRole(User.ADMIN_ROLE)
                .antMatchers("/lectures/{lectureId}/changeName").hasRole(User.ADMIN_ROLE)
                .antMatchers("/lectures/{lectureId}/change").hasRole(User.ADMIN_ROLE)
                .antMatchers("/{evaluationFormId}/ratings").hasRole(User.USER_ROLE)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

// Datenbank (h2-console/) wird aktiv ausgeschlossen
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");

    }

    @Autowired
    private UserAdapterService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

// Verschlüsselt das Passwort für die Datenbank
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
