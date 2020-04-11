package az.task.demo.UT.controller;

import az.task.demo.Controller.CommentController;
import az.task.demo.Domains.Comment;
import az.task.demo.Service.CommentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentControllerTest {
    @InjectMocks
    CommentController commentController;

    @Mock
    CommentService service;

    Comment comment;


    @BeforeAll
    public void initComment(){
        comment = new Comment();
        comment.setCommentText("asdsa");
        comment.setStatus(1);
        comment.setId(1);
    }

    @Test
    public void test(){
        when(service.addComment(1,comment)).thenReturn(comment);
        assertThat(commentController.addComment(1,comment)).isEqualTo(comment);
    }
}
