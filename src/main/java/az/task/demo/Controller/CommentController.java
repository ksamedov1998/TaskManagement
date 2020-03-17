package az.task.demo.Controller;

import az.task.demo.Domains.Comment;
import az.task.demo.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{taskId}")
    public void addComment(@PathVariable("taskId") int taskId,
                           @RequestBody Comment comment){
        System.out.println("smth");
        commentService.addComment(taskId,comment);
    }

    @GetMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable("commentId") int commentId){
            commentService.deleteComment(commentId);
    }

    @GetMapping("/update/{commentId}")
    public void updateComment(@PathVariable("commentId") int commentId,
                                Comment comment){
        commentService.updateComment(commentId,comment);
    }

}
