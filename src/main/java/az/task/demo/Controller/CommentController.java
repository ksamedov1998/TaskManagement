package az.task.demo.Controller;

import az.task.demo.Domains.Comment;
import az.task.demo.Repository.HibernateRepository;
import az.task.demo.Service.CommentService;
import az.task.demo.Util.DynamicQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HibernateRepository hibernateRepository;

    @PostMapping("/post/{taskId}")
    public void addComment(@PathVariable("taskId") int taskId,
                           @RequestBody Comment comment){
        commentService.addComment(taskId,comment);
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable("commentId") int commentId){
            commentService.deleteComment(commentId);
    }

    @PatchMapping("/update/{commentId}")
    public void updateComment(@PathVariable("commentId") int commentId,
                                Comment comment){
       commentService.updateComment(commentId,comment);
    }

}
