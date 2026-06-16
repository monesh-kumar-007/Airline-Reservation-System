package util;

public class InputValidator{
    //Validate email
    public static boolean isValidEmail(String email){
        return email != null && email.matches("^[a-zA-Z0-9+_.-]+@(.+)$");
    }

    //Validate password
    public static boolean isValidPassword(String password){
        return password != null && password.length() >= 6;
    }

    //Validate name
    public static boolean isValidName(String name){
        return name != null && name.matches("^[a-zA-Z\\s.-]{2,}$");
    }

    //Validate positive integer
    public static boolean isPositiveInteger(int num){
        return num > 0;
    }

    //Validate string is not empty
    public static boolean isNotEmpty(String str){
        return str != null && !str.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "InputValidator []";
    }
}