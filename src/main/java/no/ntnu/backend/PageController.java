package no.ntnu.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/home.html")
    public String getHome() {
        return "home";
    }

    @GetMapping("/about.html")
    public String getAbout() {
        return "about";
    }

    @GetMapping("/search.html")
    public String getSearch() {
        return "search";
    }


}
