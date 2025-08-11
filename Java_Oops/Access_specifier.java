package Java_Oops;

public class Access_specifier {
    public static void main(String[] args) {
        Bankaccount account = new Bankaccount();
        account.username = "Tanya";
        // account.password = "12345"; // This line would cause an error because password is private
        System.out.println("Account holder name: " + account.username);
        // System.out.println("Account password: " + account.password); // This line would also cause an error
        account.setPassword("12345"); // Correct way to set the password
        System.out.println("Password set successfully.");
    }
}

class Bankaccount{
    public String username;
    private String password;

    public void setPassword(String newPassword) {
        password = newPassword;
    }
}
