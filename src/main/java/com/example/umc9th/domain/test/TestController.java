package com.example.umc9th.domain.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "테스트 API")
public class TestController {

    @GetMapping("/test")
    @Operation(
            summary = "테스트하는 API",
            description = "요청을 보내면 Hello World를 응답으로 보냅니다."
    )
    public String test(){
        return "Hello World";
    }
}
