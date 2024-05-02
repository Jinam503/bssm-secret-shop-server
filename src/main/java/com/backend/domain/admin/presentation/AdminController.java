package com.backend.domain.admin.presentation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @Value("${admin.key}")
    private String key;

    @GetMapping
        public Boolean checkAdmin(HttpServletRequest request) {
        System.out.println(key);
        return request.getHeader("Authorization").equals(key);
    }
}
