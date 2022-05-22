package Funktioner;

import Member.MemberList;
import Persistence.FileHandle;
import UI.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formand {

  JFrame frameFormand;
  JButton buttonVisMedlemmer;
  JButton buttonTilføjMedlem;
  JButton buttonSøgEfterMedlem;
  JButton buttonSletMedlem;
  JButton buttonRedigerMedlem;
  JButton buttonExit;
  JPanel jPanelKnapper;
  private MemberList memberList = new MemberList();
  private FileHandle fileHandle = new FileHandle();
  UI ui = new UI();

  public Formand(){
    frameFormand = new JFrame();
    frameFormand.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameFormand.setVisible(true);
    frameFormand.setSize(800, 650);
    frameFormand.setLocationRelativeTo(null);
    frameFormand.setLayout(null);

    buttonVisMedlemmer = new JButton("Vis medlemmer");
    buttonTilføjMedlem = new JButton("Tilføj medlem");
    buttonSøgEfterMedlem = new JButton("Søg efter medlem");
    buttonSletMedlem = new JButton("Slet medlem");
    buttonRedigerMedlem = new JButton("Rediger medlem");
    buttonExit = new JButton("Gem og til hovedmenu");

    jPanelKnapper = new JPanel();
    jPanelKnapper.setLayout(null);
    jPanelKnapper.setBackground(Color.BLUE);
    jPanelKnapper.setSize(800, 210);
    frameFormand.add(jPanelKnapper);
    jPanelKnapper.setBounds(0, 0, 210, 800);
    jPanelKnapper.add(buttonTilføjMedlem);
    jPanelKnapper.add(buttonSøgEfterMedlem);
    jPanelKnapper.add(buttonRedigerMedlem);
    jPanelKnapper.add(buttonVisMedlemmer);
    jPanelKnapper.add(buttonSletMedlem);
    jPanelKnapper.add(buttonExit);

    //knapper
    buttonTilføjMedlem.setBounds(15, 20, 180, 60);
    buttonSøgEfterMedlem.setBounds(15, 100, 180, 60);
    buttonRedigerMedlem.setBounds(15, 180, 180, 60);
    buttonVisMedlemmer.setBounds(15, 260, 180, 60);
    buttonSletMedlem.setBounds(15,340,180,60);
    buttonExit.setBounds(15,420,180,60);

    //knappernes funktion
    buttonExit.addActionListener(alExit);

  }

  ActionListener alExit = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      //TODO noget med at gemme oplysningerne!
      frameFormand.dispose();
      fileHandle.saveAllNonCompetitorsToFile(memberList.getAllNonCompetitors());
      fileHandle.saveAllCompetitorsToFile(memberList.getAllCompetitors());
      new Funktioner.Login().run();
    }
  };

  public void run() {
    memberList.setAllNonCompetitors(fileHandle.loadNonCompetitors());
    memberList.setAllCompetitors(fileHandle.loadCompetitors());
  }
}
