package hello;
import hello.model.Blog;
import hello.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;
import java.util.Optional;

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


    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String newBlog(Model model) {
        model.addAttribute("pageTitle", "New Blog");
        model.addAttribute("givenAction", "/blog");
        model.addAttribute("givenTitle", "");
        model.addAttribute("givenContent", "");
        return "blog";
    }

    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    public String addBlog(@RequestParam String title, @RequestParam String content) {
        Blog newBlog = new Blog(title, content);
        blogRepo.save(newBlog);
        return "redirect:/";
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    public String viewBlog(@PathVariable(value = "id") int id, Model model) {
        Blog blog = blogRepo.findById(id).get();
        model.addAttribute("pageTitle", "Blog detail");
        model.addAttribute("givenAction", "/blog");
        model.addAttribute("givenTitle", blog.getTitle());
        model.addAttribute("givenContent", blog.getContent());
        return "blog";
    }

    @RequestMapping(value = "/blog/delete/{id}", method = RequestMethod.GET)
    public String deleteBlog(@PathVariable(value = "id") int id) {
        blogRepo.deleteById(id);
        return "redirect:/";
    }
}
