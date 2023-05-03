package com;
import com.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping("/messages")
    public void addMessage(@RequestBody Message message) {
        messageService.addMessage(message);
    }

    @PutMapping("/messages/{id}")
    public void updateMessage(@PathVariable int id, @RequestBody Message message) {
        messageService.updateMessage(id, message);
    }

    @DeleteMapping("/messages/{id}")
    public void deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
    }
}
