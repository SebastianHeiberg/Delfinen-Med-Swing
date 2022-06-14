package Funktioner;

import Member.MemberList;
import Persistence.FileHandle;
import UI.UI;

import javax.swing.*;
import java.awt.*;

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

  }

  public void run() {
    memberList.setAllNonCompetitors(fileHandle.loadNonCompetitors());
    memberList.setAllCompetitors(fileHandle.loadCompetitors());
  }



}
