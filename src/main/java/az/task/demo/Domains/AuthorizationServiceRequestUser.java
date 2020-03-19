package az.task.demo.Domains;

public class AuthorizationServiceRequestUser {
    private int id;
    private int role;

    public AuthorizationServiceRequestUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
