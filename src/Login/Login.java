package Login;

public class Login {

  private String username;
  private String password;
  private LoginFunction functionInDelfinen;

  public Login (String username, String password, LoginFunction functionInDelfinen) {
    this.password = password;
    this.username = username;
    this.functionInDelfinen = functionInDelfinen;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public LoginFunction getFunctionInDelfinen() {
    return functionInDelfinen;
  }
}
