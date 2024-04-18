package no.ntnu.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/home1.html")
    public String getHome() {
        return "home1";
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
