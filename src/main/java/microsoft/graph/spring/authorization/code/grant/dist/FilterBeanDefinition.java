package microsoft.graph.spring.authorization.code.grant.dist;

import microsoft.graph.spring.authorization.code.grant.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The {@code FilterBeanDefinition} contains a {@link FilterRegistrationBean}
 * with the {@link AuthenticationFilter}, which intercepts calls to protected
 * resources.
 */
@Configuration
public class FilterBeanDefinition {
    @Autowired
    private ClientProperties clientProperties;

    @Bean
    public FilterRegistrationBean createFilterRegistrationBean() {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthenticationFilter());
        filterRegistrationBean.addUrlPatterns("/protected/*");
        filterRegistrationBean.addInitParameter("clientId", this.clientProperties.getClientId());
        filterRegistrationBean.addInitParameter("redirectUrl", this.clientProperties.getRedirectUrlHost() +
                                                               this.clientProperties.getRedirectUrlEndpoint());

        return filterRegistrationBean;
    }
}
