package kr.ac.jejunu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestController {
    private final UserDao userDao;

    @GetMapping("/{id}")
    public @ResponseBody User get(@PathVariable("id") Integer id) {
        return userDao.findById(id).get();
    }

    @PostMapping
    public @ResponseBody User create(@RequestBody User user) {
        userDao.save(user);
        return user;
    }
}
