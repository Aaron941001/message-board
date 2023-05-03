package com;
import com.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Implement the methods for interacting with the database

    public List<Message> getAllMessages() {
        String sql = "SELECT * FROM messages";
        RowMapper<Message> rowMapper = (rs, rowNum) -> {
            Message message = new Message();
            message.setId(rs.getInt("id"));
            message.setContent(rs.getString("content"));
            return message;
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void addMessage(Message message) {
        String sql = "INSERT INTO messages (content) VALUES (?)";
        jdbcTemplate.update(sql, message.getContent());
    }

    public void updateMessage(int id, Message message) {
        String sql = "UPDATE messages SET content = ? WHERE id = ?";
        jdbcTemplate.update(sql, message.getContent(), id);
    }

    public void deleteMessage(int id) {
        String sql = "DELETE FROM messages WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
