package Funktioner;

import Member.MemberList;
import Persistence.FileHandle;
import UI.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Coach {

  JFrame frameCoach;
  JPanel jPanelButtons;
  //højre menu
  JButton jButtonTop5;
  JButton jButtonNewTrainingTime;
  JButton jButtonNewCompetitionTime;
  JButton jButtonKonvertSwimmer;
  JButton jButtonExit;

  DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
  private MemberList memberList = new MemberList();
  private FileHandle fileHandle = new FileHandle();
  UI ui = new UI();

  public Coach(){
    frameCoach = new JFrame();
    frameCoach.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameCoach.setVisible(true);
    frameCoach.setSize(800, 650);
    frameCoach.setLocationRelativeTo(null);
    frameCoach.setLayout(null);

    jPanelButtons = new JPanel();
    jPanelButtons.setLayout(null);
    jPanelButtons.setBackground(Color.BLUE);
    jPanelButtons.setSize(800, 210);
    frameCoach.add(jPanelButtons);
    jPanelButtons.setBounds(0, 0, 210, 800);

    jButtonTop5 = new JButton("Vis top 5");
    jButtonNewTrainingTime = new JButton("Ny træningstid");
    jButtonNewCompetitionTime = new JButton("Ny konkurrencetid");
    jButtonKonvertSwimmer = new JButton("Konverter svømmer");
    jButtonExit = new JButton("Exit");

    jPanelButtons.add(jButtonTop5);
    jPanelButtons.add(jButtonNewTrainingTime);
    jPanelButtons.add(jButtonNewCompetitionTime);
    jPanelButtons.add(jButtonKonvertSwimmer);
    jPanelButtons.add(jButtonExit);

    //knapper
    jButtonTop5.setBounds(15, 20, 180, 60);
    jButtonNewTrainingTime.setBounds(15, 100, 180, 60);
    jButtonNewCompetitionTime.setBounds(15, 180, 180, 60);
    jButtonKonvertSwimmer.setBounds(15, 260, 180, 60);
    jButtonExit.setBounds(15, 340, 180, 60);

    jButtonExit.addActionListener(alMenuOptionExitAndSave);
  }

  public void run() {
    memberList.setAllNonCompetitors(fileHandle.loadNonCompetitors());
    memberList.setAllCompetitors(fileHandle.loadCompetitors());
  }

  ActionListener alMenuOptionExitAndSave = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      fileHandle.saveAllNonCompetitorsToFile(memberList.getAllNonCompetitors());
      fileHandle.saveAllCompetitorsToFile(memberList.getAllCompetitors());
      frameCoach.dispose();
      new Funktioner.Login().run();
    }
  };

}
