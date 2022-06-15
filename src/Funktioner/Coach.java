package Funktioner;

import Member.MemberList;
import Persistence.FileHandle;
import UI.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Coach {

  JFrame frameCoach;
  JPanel jPanelButtons;
  JPanel jPanelLargeArea;
  //højre menu
  JButton jButtonTop5;
  JButton jButtonNewTrainingTime;
  JButton jButtonNewCompetitionTime;
  JButton jButtonKonvertSwimmer;
  JButton jButtonExit;
  //vis top 5
  JLabel jLabelGender;
  JLabel jLabelDisciplin;
  JLabel jLabelAgeGroup;
  JComboBox jComboBoxGender;
  JComboBox jComboBoxDisciplin;
  JComboBox jComboBoxAgeGroup;
  JScrollPane jScrollPaneShowTop5;
  JButton showTop5People;

  DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
  Border blackline = BorderFactory.createLineBorder(Color.black);
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

    //Det store område
    jPanelLargeArea = new JPanel(null);
    jPanelLargeArea.setSize(590, 800);
    frameCoach.add(jPanelLargeArea);
    jPanelLargeArea.setBounds(210, 1, 590, 800);

    jButtonTop5 = new JButton("Vis top 5");
    jButtonNewTrainingTime = new JButton("Ny træningstid");
    jButtonNewCompetitionTime = new JButton("Ny konkurrencetid");
    jButtonKonvertSwimmer = new JButton("Konverter svømmer");
    jButtonExit = new JButton("Gem og til hovedmenu");

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
    jButtonTop5.addActionListener(alMenuOptionShowTop5);
  }

  public void run() {
    memberList.setAllNonCompetitors(fileHandle.loadNonCompetitors());
    memberList.setAllCompetitors(fileHandle.loadCompetitors());
  }

  ActionListener alMenuOptionShowTop5 = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelLargeArea.removeAll();
      jPanelLargeArea.repaint();
      jPanelLargeArea.setLayout(null);
      JTextArea textAreavisMedlemmerPanel = new JTextArea();
      jScrollPaneShowTop5 = new JScrollPane(textAreavisMedlemmerPanel);
      jPanelLargeArea.add(jScrollPaneShowTop5);
      jScrollPaneShowTop5.setBounds(1,250,0,0);
      jScrollPaneShowTop5.setSize(575, 360);
      jScrollPaneShowTop5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPaneShowTop5.setEnabled(false);

      jLabelGender = new JLabel("Køn",JLabel.CENTER);
      jLabelDisciplin = new JLabel("Svømmedisciplin",JLabel.CENTER);
      jLabelAgeGroup = new JLabel("Aldersgruppe",JLabel.CENTER);
      String [] gender = {"Mand","Kvinde"};
      jComboBoxGender = new JComboBox(gender);
      String[] swimmingType = {"Crawl", "Bryst", "Rygcrawl", "Butterfly"};
      jComboBoxDisciplin = new JComboBox(swimmingType);
      String [] ageGroup = {"Senior","Junior"};
      jComboBoxAgeGroup = new JComboBox(ageGroup);
      showTop5People = new JButton("Vis top 5");


      //alt med de tre felter og menuerne
      jPanelLargeArea.add(jLabelGender);
      jPanelLargeArea.add(jLabelAgeGroup);
      jPanelLargeArea.add(jLabelDisciplin);
      jPanelLargeArea.add(jComboBoxAgeGroup);
      jPanelLargeArea.add(jComboBoxGender);
      jPanelLargeArea.add(jComboBoxDisciplin);
      jPanelLargeArea.add(showTop5People);

      jLabelGender.setBounds(25,50,170,30);
      jComboBoxGender.setBounds(230,50,170,30);
      jLabelDisciplin.setBounds(25, 100,170,30);
      jComboBoxDisciplin.setBounds(230,100,170,30);
      jLabelAgeGroup.setBounds(25,150,170,30);
      jComboBoxAgeGroup.setBounds(230,150,170,30);
      showTop5People.setBounds(450, 87, 100,50);

      jLabelGender.setBorder(blackline);
      jLabelDisciplin.setBorder(blackline);
      jLabelAgeGroup.setBorder(blackline);
      jComboBoxAgeGroup.setBorder(blackline);
      jComboBoxDisciplin.setBorder(blackline);
      jComboBoxGender.setBorder(blackline);

      jLabelGender.setOpaque(true);
      jLabelDisciplin.setOpaque(true);
      jLabelAgeGroup.setOpaque(true);
      jComboBoxAgeGroup.setOpaque(true);
      jComboBoxDisciplin.setOpaque(true);
      jComboBoxGender.setOpaque(true);

      jComboBoxAgeGroup.setBackground(Color.WHITE);
      jComboBoxDisciplin.setBackground(Color.WHITE);
      jComboBoxGender.setBackground(Color.WHITE);
      jLabelGender.setBackground(Color.WHITE);
      jLabelDisciplin.setBackground(Color.WHITE);
      jLabelAgeGroup.setBackground(Color.WHITE);

      listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
      jComboBoxAgeGroup.setRenderer(listRenderer);
      jComboBoxDisciplin.setRenderer(listRenderer);
      jComboBoxGender.setRenderer(listRenderer);


    }
  };

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
