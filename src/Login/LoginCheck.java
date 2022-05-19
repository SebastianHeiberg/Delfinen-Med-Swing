package Login;

import java.util.ArrayList;

public class LoginCheck {
  ArrayList<Login> allUsers;

  public LoginCheck() {
    this.allUsers = new ArrayList<>();
  }

  public void setAllUsers(ArrayList<Login> allUsers) {
    this.allUsers = allUsers;
  }

  public boolean checkAccesChairman(String username, String password) {
    for (Login login : allUsers) {
      if (login.getUsername().equals(username) && login.getPassword().equals(password) && login.getFunctionInDelfinen().equals(LoginFunction.CHAIRMAN)) {
        return true;
      }
    }
    return false;
  }

  public boolean checkAccesTreasurer(String username, String password) {
    for (Login login : allUsers) {
      if (login.getUsername().equals(username) && login.getPassword().equals(password) && login.getFunctionInDelfinen().equals(LoginFunction.TREASURER)) {
        return true;
      }
    }
    return false;
  }

  public boolean checkAccesCoach(String username, String password) {
    for (Login login : allUsers) {
      if (login.getUsername().equals(username) && login.getPassword().equals(password) && login.getFunctionInDelfinen().equals(LoginFunction.COACH)) {
        return true;
      }
    }
    return false;
  }
}