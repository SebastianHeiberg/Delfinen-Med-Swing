import Login.Login;
import Login.LoginFunction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileHandle {

  public ArrayList<Login> loadLogins() {
    ArrayList<Login> logins = new ArrayList<>();

    try {
      Scanner fileInput = new Scanner(new File("Logins.csv"));

      while (fileInput.hasNextLine()) {

        String line = fileInput.nextLine();
        Scanner lineInput = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
        String username = lineInput.next();
        String password = lineInput.next();
        LoginFunction loginFunction = LoginFunction.valueOf(lineInput.next());

        Login login = new Login(username, password, loginFunction);
        logins.add(login);
      }
    } catch (FileNotFoundException fnfe) {
      System.err.println("File not found.");
    }
    return logins;
  }
}
