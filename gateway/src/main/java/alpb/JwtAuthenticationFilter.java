package alpb;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {
    private final JWTUtil jwtUtil;

    public static class Config {
        // 필터 구성을 위한 설정 클래스
    }

    public JwtAuthenticationFilter(JWTUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            return chain.filter(exchange);
        //     String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        //     return Mono.just(token)
        //         .flatMap(tkn -> {
        //             // jwtUtil.checkToken(tkn) 넣기
        //             if (1==1) {
        //                 return chain.filter(exchange); // 토큰 유효
        //             } else {
        //                 exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //                 return exchange.getResponse().setComplete(); // 토큰 무효
        //             }
        //         }).switchIfEmpty(Mono.defer(() -> {
        //             exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //             return exchange.getResponse().setComplete(); // 헤더 누락
        //         }));
        // };
    };
}
}
