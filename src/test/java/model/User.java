package model;

public class User {

    private String email;
    private String password;
    private String dateOfBirth;


    public User(String email, String password, String dateOfBirth){
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail(){
        return email;
    }
}