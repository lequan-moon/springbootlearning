package hello;
import hello.model.Blog;
import hello.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HelloController {
    @Autowired
    BlogRepository blogRepo;

    @RequestMapping("/")
    public String index(
            @RequestParam(name = "name", defaultValue = "World", required = false) String name,
            Model indexModel) {
        indexModel.addAttribute("name", name);

        List<Blog> blogs = blogRepo.findAll();
        indexModel.addAttribute("blogs", blogs);
        return "index";
    }
}
