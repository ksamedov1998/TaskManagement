package az.task.demo.Controller;

import az.task.demo.Domains.Comment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    /*  add, delete , get, "update" */


    @PostMapping("/post/{taskId}")
    public void addComment(@PathVariable("taskId") int taskId,
                           @RequestAttribute Comment comment){


    }


}
