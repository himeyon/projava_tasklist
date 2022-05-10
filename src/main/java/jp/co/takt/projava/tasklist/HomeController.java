package jp.co.takt.projava.tasklist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;

@Controller
public class HomeController {

    /**
     * hello.
     * @param model モデル
     * @return htmlテンプレート
     */
    @RequestMapping(value = "/hello")
    String hello(final Model model) {
        model.addAttribute("time", LocalDateTime.now());
        return "hello";
    }
}
