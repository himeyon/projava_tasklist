package jp.co.takt.projava.tasklist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class HomeRestController {
    @RequestMapping(value = "/resthello")
    String hello() {
        return """
            Hello.
            It works!
            現在時刻は%sです。
        """.formatted(LocalDateTime.now());
    }

    record TaskItem(String id, String task, String deadline, boolean done) {}
    private List<TaskItem> taskItems = new ArrayList<>();

    /**
     * タスクを追加する.
     * @param task タスク名
     * @param deadline タスクの期限
     * @return タスク追加メッセージ
     */
    @GetMapping("/restadd")
    String addItem(@RequestParam("task") String task, @RequestParam("deadline") String deadline) {
        final String id = UUID.randomUUID().toString().substring(0, 8);
        taskItems.add(new TaskItem(id, task, deadline, false));
        return "タスクを追加しました。";
    }

    /**
     * タスクを一覧表示する.
     * @return タスク一覧
     */
    @GetMapping("/restlist")
    String listItems() {
        return taskItems.stream()
            .map(TaskItem::toString)
            .collect(Collectors.joining(", "));
    }
}
