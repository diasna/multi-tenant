package id.dias.tenant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig
        extends AuthorizationServerConfigurerAdapter {

    private final AccessTokenConverter accessTokenConverter;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenStore tokenStore;
    private final TokenEnhancer tokenEnhancer;
    private final UserDetailsService userDetailsService;

    public AuthorizationServerConfig(
            AuthenticationManager authenticationManager,
            AccessTokenConverter accessTokenConverter,
            PasswordEncoder passwordEncoder, TokenStore tokenStore,
            TokenEnhancer tokenEnhancer,
            UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.accessTokenConverter = accessTokenConverter;
        this.passwordEncoder = passwordEncoder;
        this.tokenStore = tokenStore;
        this.tokenEnhancer = tokenEnhancer;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        clients.inMemory()
                .withClient("default")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes("password", "authorization_code",
                        "refresh_token")
                .scopes("read", "write")
                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer)
            throws Exception {
        oauthServer
                .passwordEncoder(passwordEncoder)
                .allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(
            final AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(tokenEnhancer)
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
    }
}
