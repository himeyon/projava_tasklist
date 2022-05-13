package jp.co.takt.projava.tasklist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    record TaskItem(String id, String task, String deadline, boolean done) {}
    private List<TaskItem> taskItems = new ArrayList<>();

    /**
     * タスクの一覧表示.
     * @param model モデル
     * @return タスク一覧
     */
    @GetMapping("/list")
    String listItems(final Model model) {
        model.addAttribute("taskList", taskItems);
        return "home";
    }
}
