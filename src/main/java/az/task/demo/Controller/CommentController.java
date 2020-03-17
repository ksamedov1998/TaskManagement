package az.task.demo.Controller;

import az.task.demo.Domains.Comment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {


    @PostMapping("/post/{taskId}")
    public void addComment(@PathVariable("taskId") int taskId,
                           @RequestAttribute Comment comment){


    }

    @GetMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable("commentId") int commentId){


    }

    @GetMapping("/update/{commentId}")
    public void updateComment(@PathVariable("commentId") int commentId){


    }

}
