package com.dh.apigateway;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;



import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Component
public class LogFilter extends AbstractGatewayFilterFactory<LogFilter.Config> {

    private static Logger log = Logger.getLogger(LogFilter.class.getName());
    private static AtomicInteger COUNT_CALL_GATEWAY = new AtomicInteger(0);

    public LogFilter() {
        super(Config.class);
    }



    @Override
    public GatewayFilter apply(Config config){
        return (exchange, chain) -> {
            COUNT_CALL_GATEWAY.addAndGet(1);
            // --Filtro previo--
            log.info("Path requested: " + exchange.getRequest().getPath());
            log.info("Ejecutando la primera instancia..." + config.message);
            //--Contador de consultas--
            log.info("La cantidad de veces que se llamo fue: "+ COUNT_CALL_GATEWAY);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                //--Filtro posterior--
                log.info("Time response: " + Calendar.getInstance().getTime());
            }));
        };
    }


    public static class Config {
        //Put the configuration properties
        private String message;
        private String cookieName;
        private String cookieValue;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCookieName() {
            return cookieName;
        }

        public void setCookieName(String cookieName) {
            this.cookieName = cookieName;
        }

        public String getCookieValue() {
            return cookieValue;
        }

        public void setCookieValue(String cookieValue) {
            this.cookieValue = cookieValue;
        }
    }
}
