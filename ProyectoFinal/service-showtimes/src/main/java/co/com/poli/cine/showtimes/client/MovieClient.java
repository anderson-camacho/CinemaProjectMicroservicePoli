package co.com.poli.cine.showtimes.client;

import co.com.poli.cine.showtimes.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-movie")
public interface MovieClient {
    @GetMapping("/movie/{id}")
    Response findById(@PathVariable("id") Long id);
}
