package pojo;

public class Authentication_Login_API
{
    //private declare fields - JSON key should be private
    private String username;
    private String password;

    //setter and getter methods
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}