import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import Login.LoginCheck;
import com.sun.security.auth.module.JndiLoginModule;


public class Login {

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

  public Login() throws IOException {
    frameMain = new JFrame("Login");
    frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameMain.setVisible(true);
    frameMain.setLayout(new GridLayout(4, 2));
    jLabelFunktion = new JLabel("Funktion i klubben ");
    jComboBoxOptions = new JComboBox(muligheder);
    jTextFieldUsername = new JTextField("", 15);
    jLabelPassword = new JLabel("Kodeord ");
    jlabelUsername = new JLabel("Brugernavn ");
    jPasswordField = new JPasswordField("",15);
    login = new JButton("Login");
    exit = new JButton("Exit");

    exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frameMain.dispose();
      }
    });
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
    if (jComboBoxOptions.getItemAt(jComboBoxOptions.getSelectedIndex()).toString().equalsIgnoreCase("Formand") && logins.checkAccesChairman(jTextFieldUsername.getText(), String.valueOf(jPasswordField.getPassword()))) {
      frameMain.dispose();
      Formand formand = new Formand();
    }else if (jComboBoxOptions.getItemAt(jComboBoxOptions.getSelectedIndex()).toString().equalsIgnoreCase("Kasserer") && logins.checkAccesTreasurer(jTextFieldUsername.getText(), String.valueOf(jPasswordField.getPassword()))) {
      frameMain.dispose();
      Formand formand = new Formand();
    } else if (jComboBoxOptions.getItemAt(jComboBoxOptions.getSelectedIndex()).toString().equalsIgnoreCase("Træner") && logins.checkAccesCoach(jTextFieldUsername.getText(), String.valueOf(jPasswordField.getPassword()))) {
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
