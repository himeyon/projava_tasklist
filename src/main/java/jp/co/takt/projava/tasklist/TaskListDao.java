package jp.co.takt.projava.tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import jp.co.takt.projava.tasklist.HomeController.TaskItem;

@Service
public class TaskListDao {
    private final JdbcTemplate jdbcTemplate;

    /**
     * コンストラクタ
     * @param jdbcTemplate JDBCテンプレート
     */
    @Autowired
    TaskListDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 登録処理.
     * @param taskItem タスク
     */
    public void add(final TaskItem taskItem) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(taskItem);
        var insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName("tasklist");
        insert.execute(param);
    }

    /**
     * 検索処理
     * @return タスクリスト
     */
    public List<TaskItem> findAll() {
        final String query = "SELECT * FROM tasklist";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(query);
        return result.stream()
            .map((Map<String, Object> row) -> new TaskItem(
                row.get("id").toString(),
                row.get("task").toString(),
                row.get("deadline").toString(),
                (Boolean) row.get("done"))).toList();
    }

    /**
     * 削除処理
     * @param id タスクID
     * @return 件数
     */
    public int delete(final String id) {
        return this.jdbcTemplate.update("DELETE FROM tasklist WHERE id = ?", id);
    }
}
