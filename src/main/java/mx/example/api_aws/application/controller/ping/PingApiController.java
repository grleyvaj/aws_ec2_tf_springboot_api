package mx.example.api_aws.application.controller.ping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PingApiController {

    @GetMapping(path = "/ping")
    public ResponseEntity<String> ping() {
        Map<String, String> pong = new HashMap<>();
        return ResponseEntity.ok("Hello, World!");
    }

}
