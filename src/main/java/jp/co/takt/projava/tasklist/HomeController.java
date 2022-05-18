package jp.co.takt.projava.tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class HomeController {

    private final TaskListDao taskListDao;

    /**
     * コンストラクタ
     * @param dao タスクリストDAO
     */
    @Autowired
    HomeController(final TaskListDao dao) {
        this.taskListDao = dao;
    }

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

    /**
     * タスクの一覧表示.
     * @param model モデル
     * @return タスク一覧
     */
    @GetMapping("/list")
    String listItems(final Model model) {
        model.addAttribute("taskList", taskListDao.findAll());
        return "home";
    }

    /**
     * タスク追加処理.
     * @param task タスク名
     * @param deadline タスクの期限
     * @return タスク一覧(リダイレクト)
     */
    @GetMapping("/add")
    String addItem(@RequestParam("task") String task, @RequestParam("deadline") String deadline) {
        final String id = UUID.randomUUID().toString().substring(0, 8);
        final var item = new TaskItem(id, task, deadline, false);
        taskListDao.add(item);
        return "redirect:/list";
    }
}
