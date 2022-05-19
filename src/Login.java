import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.FileHandler;

import Login.LoginCheck;


public class Login {

  public FileHandle fileHandle = new FileHandle();
  private LoginCheck logins = new LoginCheck();
  JFrame frameMain;
  JLabel jLabelFunktion;
  JLabel jLabelPassword;
  JLabel username;
  JTextField jTextFieldPassword;
  JTextField jTextFieldUsername;
  JComboBox jComboBoxOptions;
  String[] muligheder = {"Formand", "Kasserer", "Træner"};
  JButton exit;
  JButton login;

  public Login() throws IOException {
    frameMain = new JFrame("Login");
    frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameMain.setVisible(true);
    frameMain.setLayout(new GridLayout(4, 2));
    jLabelFunktion = new JLabel("Funktion i klubben ");
    jComboBoxOptions = new JComboBox(muligheder);
    jTextFieldUsername = new JTextField("", 15);
    jTextFieldPassword = new JTextField("", 15);
    jLabelPassword = new JLabel("Kodeord ");
    username = new JLabel("Brugernavn ");
    login = new JButton("Login");
    exit = new JButton("Exit");

    frameMain.add(jLabelFunktion);
    jLabelFunktion.setHorizontalAlignment(JLabel.CENTER);
    frameMain.add(jComboBoxOptions);
    frameMain.add(username);
    username.setHorizontalAlignment(JLabel.CENTER);
    frameMain.add(jTextFieldUsername);
    frameMain.add(jLabelPassword);
    jLabelPassword.setHorizontalAlignment(JLabel.CENTER);
    frameMain.add(jTextFieldPassword);
    frameMain.add(exit);
    frameMain.add(login);

    frameMain.setSize(400, 200);
    frameMain.setLocationRelativeTo(null);
  }

  private void attemptLoginuser(java.awt.event.ActionEvent evt) {
    if (jComboBoxOptions.getItemAt(jComboBoxOptions.getSelectedIndex()).toString().equalsIgnoreCase("Formand") && logins.checkAccesChairman(jTextFieldUsername.getText(), jTextFieldPassword.getText())) {
      frameMain.dispose();
      Formand formand = new Formand();
    }
  }


  public void run() {
    login.addActionListener(this::attemptLoginuser);
    logins.setAllUsers(fileHandle.loadLogins());
  }

  public static void main(String[] args) throws IOException {
    new Login().run();
  }


}
