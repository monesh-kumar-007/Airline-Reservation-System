package model;

public class User {
    private String username;
    private String password;
    private String role;
    private int id;
    private String email;

    public User(String username, String email,String password, String role, int id) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.id = id;
    }

    public void setUsername(String username){
        this.username =  username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRole(String role){
        this.role = role;
    }


    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
