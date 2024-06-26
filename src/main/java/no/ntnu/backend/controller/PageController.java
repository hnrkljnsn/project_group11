package no.ntnu.backend.controller;

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

    @GetMapping("/login.html")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/flightdetails.html")
    public String getFlightDetails() {
        return "flightdetails";
    }

    @GetMapping("/createaccount.html")
    public String getCreateAccount(){return "createaccount";}

    @GetMapping("/favorites.html")
    public String getFavorites(){return "favorites";}

    @GetMapping("/account.html")
    public String getAccount(){return "account";}


}
