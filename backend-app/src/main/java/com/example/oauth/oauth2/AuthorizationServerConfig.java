package com.example.oauth.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
  private String clientid = "tutorialspoint";
  private String clientSecret = "my-secret-key";
  private String privateKey = "-----BEGIN RSA PRIVATE KEY-----" +
      "MIIEowIBAAKCAQEAtOpRDyp5+kHOyL168By1bOj3Nse/S6DG5+HUrbgkQa0uLDDX" +
      "zvIGbw72F2urtL7UzDNAGvgPgqpUSRWRstZGOSZ/LTRY+8dZMGMniKOuUMQEUnd8" +
      "w2QiO72LqU/l0utUmnkpGkVJZYnL4zWc2XHegtgFDYvMfcXLSBRWP1+YpxGzSDQK" +
      "pIDJ084W8EucsfYl3XnRj5YIYKkKMGNmalpmayRS+nz5mkkJvCDiSRrBXozn4HT8" +
      "a1KYRdnyrvoXKMRQehLRFDEm28QRqaIz6YO31E/Rz4rQDPRWgqneZxuQnRh0hd2t" +
      "yO4R7AmnFk+EhulsV6zCWevA4cdvEzGdIs8hVwIDAQABAoIBAGZpekd2iSQ0moUi" +
      "VfSUm9MyMbeN7Wk3c0GSol1Qrx2KlmenopLgiOnXc1GgvsuLd1S88l4R7F3QY/QX" +
      "RKoTCB+T/kwtpO2qVH/CsX91ANlxjOzN/Q9yoLpmuye2NehnFTZ4vd7EOB9FmOhe" +
      "0hJ5ljdFZ97aM4FDNabw9D3dtMFlnQk8RMfHvQFokaF+KIsiBmWd940vNycLbJzV" +
      "eRboakQ33Hk47jk3SIwbABxNrbmgUloePW7WrmNoeO03mItOJS/bdpk9UxKBde8f" +
      "fEi6vloys5JHRZjQ4n8RtB2YHwdrAi1Vdag1QdKBrCrhyLoVuKJEmdjIDUFEUqG5" +
      "5KuLFmECgYEA2R6sp632ippbAtZgZ7Eo8ZMPG8lMEFMt3u7lsSgYGf+eNMZ122k7" +
      "zPMz78i64qQ2kSBMyRWUR39QV8KOIKXqqVe9LF9famzy1E86N60H0RoslCRlUzlM" +
      "MMyCC8ynP1MuwUwCFMxVT7plIth+KCaZpECPg6oOkgg8DvH7pxBYjzECgYEA1U/v" +
      "R5v7VUb6M/XRKhr1EIVtUThXXghe7YI+jHe02EmybY0OAGM1Jrw30pd3Ac7E6XOF" +
      "/Nr0t5d6NTvQlIL0swKBUfOCrOawOY+OuL3OI+16B44ooM/ix0BkGMyzrFfp5Wdc" +
      "pmWGj9YFmfCIddGwn+GkF8CAbwkefK00H3qO5wcCgYEAxd1zgRhDnwqOOGa/0p+b" +
      "7ZOUaEEphZMYMZtLTgh49nCCcBwQ2hTr/5n/0dz/m34FkHp8H6D/0ayPvnq6Zbxu" +
      "v0cLSj3cSbPiFGPjbUvN3NzalWMZyB9mdO5a8X/fRxF6gYPQPb39XTxD4N6lEdgj" +
      "mpGPeHDo+GJywTf7m8OEwXECgYAeAZ+UQDHrvg9GLecdeCtPBqXpDfbkSGMqH+8y" +
      "jUhIQJ2z4Wq9zTbV2xYzLpFUFMUIXfLULezMwqczQ9XEfmNzpv+QCqQwNEdH4ZTf" +
      "Efwke6x10Z6PeI7bHoi2IF2J+dfyKEcVNSTnqOCi1f4sLLIe23vcx3yJogvC2A6D" +
      "+xaNEwKBgBKhGLkBLEVeApeX2TWnCmVt3W7dx+wo+8+B+0whBAkIpjZj2Dq28Zk0" +
      "hVQKVzCl/YIfd0tiymIUmijyEb0UZTvvCvq9OKXb8JpVmjku2Efz8aQh2AF9qQeg" +
      "EKwM+m7XNDk7oaWDx4018I0qbuUHAVBDhYQhX9FmKJR3mB0j6wI5" +
      "-----END RSA PRIVATE KEY-----";
  private String publicKey = "-----BEGIN PUBLIC KEY-----" +
      "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtOpRDyp5+kHOyL168By1" +
      "bOj3Nse/S6DG5+HUrbgkQa0uLDDXzvIGbw72F2urtL7UzDNAGvgPgqpUSRWRstZG" +
      "OSZ/LTRY+8dZMGMniKOuUMQEUnd8w2QiO72LqU/l0utUmnkpGkVJZYnL4zWc2XHe" +
      "gtgFDYvMfcXLSBRWP1+YpxGzSDQKpIDJ084W8EucsfYl3XnRj5YIYKkKMGNmalpm" +
      "ayRS+nz5mkkJvCDiSRrBXozn4HT8a1KYRdnyrvoXKMRQehLRFDEm28QRqaIz6YO3" +
      "1E/Rz4rQDPRWgqneZxuQnRh0hd2tyO4R7AmnFk+EhulsV6zCWevA4cdvEzGdIs8h" +
      "VwIDAQAB" +
      "-----END PUBLIC KEY-----";

  @Autowired
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Bean
  public JwtAccessTokenConverter tokenEnhancer() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey(privateKey);
    converter.setVerifierKey(publicKey);
    return converter;
  }

  @Bean
  public JwtTokenStore tokenStore() {
    return new JwtTokenStore(tokenEnhancer());
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
        .accessTokenConverter(tokenEnhancer());
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    //security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    //Below one used to prevent basic Authorisation
    security.allowFormAuthenticationForClients();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory().withClient(clientid).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
        .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
        .refreshTokenValiditySeconds(20000);
  }
}
