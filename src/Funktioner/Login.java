package Funktioner;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Persistence.FileHandle;
import UI.UI;
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
  JLabel jLabelDelfinen;

  public Login() {
    frameMain = new JFrame("Login");
    frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameMain.setVisible(true);
    frameMain.setLayout(null);

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

    //billede og overskrift
    ImageIcon icon = new ImageIcon("delfin.png");
    JLabel contentPane = new JLabel();
    contentPane.setIcon( icon );
    contentPane.setLayout( new BorderLayout() );
    frameMain.add(contentPane);
    contentPane.setBounds(30,50,300,300);
    jLabelDelfinen = new JLabel("Svømmeklubben Delfinen");
    frameMain.add(jLabelDelfinen);
    jLabelDelfinen.setBounds(350,20,400,40);
    jLabelDelfinen.setFont(new Font("Serif", Font.BOLD, 30));

    //set bounds forsøg // hvor lang mod højre, hvor lavt
    jLabelFunktion.setBounds(300,100,200,20);
    jComboBoxOptions.setBounds(520,100,200,20);
    jlabelUsername.setBounds(300,140,200,20);
    jTextFieldUsername.setBounds(520,140,200,20);
    jLabelPassword.setBounds(300, 180,200,20);
    jPasswordField.setBounds(520,180,200,20);
    login.setBounds(520,220,200,60);
    exit.setBounds(300,220,200,60);

    //laveBorder til alle felterne
    Border blackline = BorderFactory.createLineBorder(Color.black);
    jLabelFunktion.setBorder(blackline);
    jLabelPassword.setBorder(blackline);
    jlabelUsername.setBorder(blackline);
    jComboBoxOptions.setBorder(blackline);
    jTextFieldUsername.setBorder(blackline);
    jPasswordField.setBorder(blackline);

    //centrer svar
    jTextFieldUsername.setHorizontalAlignment(JTextField.CENTER);
    jPasswordField.setHorizontalAlignment(JPasswordField.CENTER);


    frameMain.setSize(800, 400);
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
        kasserer.getMemberList().setAllNonCompetitors(fileHandle.loadNonCompetitors());
        kasserer.getMemberList().setAllCompetitors(fileHandle.loadCompetitors());
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
//    new Funktioner.Login().run();
    Kasserer kas = new Kasserer();
  }
}
