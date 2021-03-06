package ua.com.foxminded.quickpoll.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("QuickPoll_Resources");
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers().antMatchers("/oauth2/v3/polls/**")
                .and()
                .authorizeRequests().antMatchers("/oauth2/v3/polls/**").authenticated()
                .and()
                .requestMatchers().antMatchers("/oauth2/v3/computeresult/**")
                .and()
                .authorizeRequests().antMatchers("/oauth2/v3/computeresult/**").authenticated();
    }
}
