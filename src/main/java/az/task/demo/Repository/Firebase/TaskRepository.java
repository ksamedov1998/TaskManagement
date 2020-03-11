package az.task.demo.Repository.Firebase;

import az.task.demo.Domains.Firebase.FirebaseTask;
import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository("firebaseTaskRepository")
public class TaskRepository {

    private FirebaseDatabase database;

    private DatabaseReference reference;



    public void createTask(FirebaseTask task){
        reference=database.getReference("task");
            reference.push().setValue(task,null);
    }

    public void getAllTasks() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
