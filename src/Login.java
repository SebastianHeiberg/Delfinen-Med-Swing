import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Login.LoginCheck;

public class Login {

  private UI ui = new UI();
  public FileHandle fileHandle = new FileHandle();
  private LoginCheck logins = new LoginCheck();
  JPasswordField jPasswordField;
  JFrame frameMain;
  JLabel jLabelFunktion;
  JLabel jLabelPassword;
  JLabel jlabelUsername;
  JTextField jTextFieldUsername;
  JComboBox jComboBoxOptions;
  String[] muligheder = {"Formand", "Kasserer", "Træner"};
  JButton exit;
  JButton login;

  public Login() {
    frameMain = new JFrame("Login");
    frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameMain.setVisible(true);
    frameMain.setLayout(new GridLayout(4, 2));
    jLabelFunktion = new JLabel("Funktion i klubben ");
    jComboBoxOptions = new JComboBox(muligheder);
    jTextFieldUsername = new JTextField("", 15);
    jLabelPassword = new JLabel("Kodeord ");
    jlabelUsername = new JLabel("Brugernavn ");
    jPasswordField = new JPasswordField("", 15);
    login = new JButton("Login");
    exit = new JButton("Exit");

    //actions
    exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frameMain.dispose();
      }
    });

    login.addActionListener(this::attemptLoginuser);

    //alle knapperne
    frameMain.add(jLabelFunktion);
    jLabelFunktion.setHorizontalAlignment(JLabel.CENTER);
    frameMain.add(jComboBoxOptions);
    frameMain.add(jlabelUsername);
    jlabelUsername.setHorizontalAlignment(JLabel.CENTER);
    frameMain.add(jTextFieldUsername);
    frameMain.add(jLabelPassword);
    jLabelPassword.setHorizontalAlignment(JLabel.CENTER);
    frameMain.add(jPasswordField);
    frameMain.add(exit);
    frameMain.add(login);

    //laveBorder til alle felterne
    Border blackline = BorderFactory.createLineBorder(Color.black);
    jLabelFunktion.setBorder(blackline);
    jLabelPassword.setBorder(blackline);
    jlabelUsername.setBorder(blackline);
    jComboBoxOptions.setBorder(blackline);
    jTextFieldUsername.setBorder(blackline);
    jPasswordField.setBorder(blackline);


    frameMain.setSize(400, 200);
    frameMain.setLocationRelativeTo(null);
  }

  private void attemptLoginuser(java.awt.event.ActionEvent evt) {

    String funktion = jComboBoxOptions.getItemAt(jComboBoxOptions.getSelectedIndex()).toString();
    String password = String.valueOf(jPasswordField.getPassword());
    String username = jTextFieldUsername.getText();

    if (funktion.equals("Formand")) {
      if (logins.checkAccesChairman(username, password)) {
        frameMain.dispose();
        Formand formand = new Formand();
      } else {
        ui.showErrorLogin(frameMain);
      }
    }

    if (funktion.equals("Kasserer")) {
      if (logins.checkAccesTreasurer(username, password)) {
        frameMain.dispose();
        Kasserer kasserer = new Kasserer();
      } else {
        ui.showErrorLogin(frameMain);
      }
    }

    if (funktion.equals("Træner")) {
      if (logins.checkAccesCoach(username, password)) {
        frameMain.dispose();
        Træner træner = new Træner();
      } else {
        ui.showErrorLogin(frameMain);
      }
    }
  }

  public void run() {

    logins.setAllUsers(fileHandle.loadLogins());
  }

  public static void main(String[] args) throws IOException {
    new Login().run();
  }


}
