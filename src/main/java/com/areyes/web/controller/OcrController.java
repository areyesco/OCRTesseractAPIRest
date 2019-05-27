package com.areyes.web.controller;

import com.areyes.entity.User;
import com.areyes.service.OcrService;
import com.areyes.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/ocr")
@Slf4j
public class OcrController {

    private final OcrService ocrService;

    @Autowired
    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @GetMapping("")
    public String getVersion() {
        //log.info("process=get-users");
        return "Version de Ocr Api Rest 0.1";
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<User> getUser(@PathVariable Long id) {
    //     //log.info("process=get-user, user_id={}", id);
    //     Optional<User> user = ocrService.getUserById(id);
    //     return user.map( u -> ResponseEntity.ok(u))
    //                .orElse(ResponseEntity.notFound().build());
    // }

    // @PostMapping("")
    // @ResponseStatus(CREATED)
    // public User createUser(@RequestBody User user) {
    //     //log.info("process=create-user, user_email={}", user.getEmail());
    //     return ocrService.createUser(user);
    // }

    @GetMapping("/{imgPath}")
    public String executeOcr(@PathVariable String imgPath) {
        //log.info("process=update-user, user_id={}", id);
        //user.setId(1L);
        //user.setId(id);
        return ocrService.executeOcr(imgPath);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        //log.info("process=delete-user, user_id={}", id);
        //ocrService.deleteUser(id);
    }

}
