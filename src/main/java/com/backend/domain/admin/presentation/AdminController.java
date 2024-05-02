package com.backend.domain.admin.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

    @Value("${admin.key}")
    private String key;

    @GetMapping
        public Boolean checkAdmin(@RequestParam(value = "key", required = false) String requestKey) {
        System.out.println(key);
        return requestKey != null && requestKey.equals(key);
    }
}
