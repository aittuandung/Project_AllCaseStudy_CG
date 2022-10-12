package Validate;

public class ValidateUserName {
    public final static String User_name="[\\w]{8,15}";
    public  boolean validate(String input) {
        return input.matches(User_name);
    }

}
