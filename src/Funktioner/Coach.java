package Funktioner;

import Member.Competitor;
import Member.Member;
import Member.MemberList;
import Member.SwimmingDisciplin;
import Persistence.FileHandle;
import UI.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
  JComboBox jComboBoxGender;
  JComboBox jComboBoxDisciplin;
  JComboBox jComboBoxAgeGroup;
  JScrollPane jScrollPaneShowTop5;
  JButton showTop5People;
  //Ny træningstid
  JButton registerNewTime;
  JComboBox jComboBoxLocation;
  JComboBox jComboBoxMonth;
  JComboBox jComboBoxDay;
  JComboBox jComboBoxYear;
  JComboBox jComboBoxMin;
  JComboBox jComboBoxSec;
  JTextField jTextFieldInputMemberNumber;
  JTextField jTextFieldDisplayMember;
  JTextArea jTextAreaFoundMember;
  JTextField jTextFieldIndtastMedlemsnummer;
  JButton jButtonConfirmMember;
  JLabel jLabelShowMember;
  JButton jButtonConfirmTime;


  DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
  Border blackline = BorderFactory.createLineBorder(Color.black);
  private MemberList memberList = new MemberList();
  private FileHandle fileHandle = new FileHandle();
  UI ui = new UI();

  public Coach() {
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
    jButtonNewTrainingTime = new JButton("Registrer ny svømmetid");
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
    jButtonKonvertSwimmer.addActionListener(alMenuOptionConverMember);
    jButtonNewCompetitionTime.addActionListener(alMenuOptionNewCompetitionTime);
    jButtonNewTrainingTime.addActionListener(alMenuOptionNewTrainingTime);

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
      jScrollPaneShowTop5.setBounds(1, 250, 0, 0);
      jScrollPaneShowTop5.setSize(575, 360);
      jScrollPaneShowTop5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPaneShowTop5.setEnabled(false);

      JLabel jLabelGender = new JLabel("Køn", JLabel.CENTER);
      JLabel jLabelDisciplin = new JLabel("Svømmedisciplin", JLabel.CENTER);
      JLabel jLabelAgeGroup = new JLabel("Aldersgruppe", JLabel.CENTER);
      String[] gender = {"Mand", "Kvinde"};
      jComboBoxGender = new JComboBox(gender);
      String[] swimmingType = {"Crawl", "Bryst", "Rygcrawl", "Butterfly"};
      jComboBoxDisciplin = new JComboBox(swimmingType);
      String[] ageGroup = {"Senior", "Junior"};
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

      jLabelGender.setBounds(25, 50, 170, 30);
      jComboBoxGender.setBounds(230, 50, 170, 30);
      jLabelDisciplin.setBounds(25, 100, 170, 30);
      jComboBoxDisciplin.setBounds(230, 100, 170, 30);
      jLabelAgeGroup.setBounds(25, 150, 170, 30);
      jComboBoxAgeGroup.setBounds(230, 150, 170, 30);
      showTop5People.setBounds(435, 87, 100, 50);

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


      ActionListener displayTop5 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          textAreavisMedlemmerPanel.setText("");
          String swimmerType = jComboBoxDisciplin.getItemAt(jComboBoxDisciplin.getSelectedIndex()).toString().toUpperCase();
          String swimmerGender = jComboBoxGender.getItemAt(jComboBoxGender.getSelectedIndex()).toString();
          String swimmerAge = jComboBoxAgeGroup.getItemAt(jComboBoxAgeGroup.getSelectedIndex()).toString();

          SwimmingDisciplin swimmingDisciplin = SwimmingDisciplin.valueOf(swimmerType);
          String memberGender;

          if (swimmerGender.equals("Mand")) {
            memberGender = "M";
          } else {
            memberGender = "K";
          }

          int age;
          if (swimmerAge.equals("Senior")) {
            age = 19;
          } else {
            age = 17;
          }

          ArrayList<Competitor> top5Competition = memberList.createTop5ListCompetition(memberGender, swimmingDisciplin, age);
          ArrayList<Competitor> top5Training = memberList.createTop5ListTraining(memberGender, swimmingDisciplin, age);
          ui.printTop5List(top5Training, top5Competition, textAreavisMedlemmerPanel);
        }
      };

      showTop5People.addActionListener(displayTop5);

    }
  };

  ActionListener alMenuOptionConverMember = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelLargeArea.removeAll();
      jPanelLargeArea.repaint();
      jPanelLargeArea.setLayout(null);

    }
  };

  ActionListener alMenuOptionNewTrainingTime = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelLargeArea.removeAll();
      jPanelLargeArea.repaint();
      jPanelLargeArea.setLayout(null);

      JButton registerNewTime = new JButton("Registrer tid");
      String[] location = {"Stævne", "Træning"};
      jComboBoxLocation = new JComboBox(location);
      String[] month = {"Januar", "Februar", "Marts", "April", "Maj", "Juni", "Juli", "August", "September", "Oktober", "November", "December"};
      jComboBoxMonth = new JComboBox(month);
      String[] day = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
      jComboBoxDay = new JComboBox(day);
      String[] year = {"2022"};
      jComboBoxYear = new JComboBox(year);
      String[] sec = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
      jComboBoxMin = new JComboBox(sec);
      jComboBoxSec = new JComboBox(sec);
      jTextFieldInputMemberNumber = new JTextField();
      JLabel jlabelMonth = new JLabel("Måned",JLabel.CENTER);
      JLabel jlabelDay = new JLabel("Dag",JLabel.CENTER);
      JLabel jlabelYear = new JLabel("Årstal",JLabel.CENTER);
      jTextAreaFoundMember = new JTextArea();
      jTextFieldDisplayMember = new JTextField();
      JLabel jLabelChooseMember = new JLabel("1. Indtast medlemsnummer",JLabel.CENTER);
      jTextFieldIndtastMedlemsnummer = new JTextField("", 15);
      jButtonConfirmMember = new JButton("2. Vælg");
      jLabelShowMember = new JLabel("");
      JLabel jLabelMinuts = new JLabel("Minutter",JLabel.CENTER);
      JLabel jLabelSeconds = new JLabel("Sekunder",JLabel.CENTER);
      JLabel jLabelLocation = new JLabel("Lokalitet",JLabel.CENTER);
      jButtonConfirmTime = new JButton("3. Registerer ny tid");

      //lav indhold
      jPanelLargeArea.add(jLabelChooseMember);
      jPanelLargeArea.add(jTextFieldIndtastMedlemsnummer);
      jPanelLargeArea.add(jButtonConfirmMember);
      jPanelLargeArea.add(jLabelShowMember);
      jPanelLargeArea.add(jlabelDay);
      jPanelLargeArea.add(jComboBoxDay);
      jPanelLargeArea.add(jlabelMonth);
      jPanelLargeArea.add(jComboBoxMonth);
      jPanelLargeArea.add(jlabelYear);
      jPanelLargeArea.add(jComboBoxYear);
      jPanelLargeArea.add(jLabelMinuts);
      jPanelLargeArea.add(jLabelSeconds);
      jPanelLargeArea.add(jComboBoxSec);
      jPanelLargeArea.add(jComboBoxMin);
      jPanelLargeArea.add(jComboBoxLocation);
      jPanelLargeArea.add(jLabelLocation);
      jPanelLargeArea.add(jButtonConfirmTime);


      jLabelChooseMember.setBounds(50, 75, 170, 30);
      jTextFieldIndtastMedlemsnummer.setBounds(240, 75, 170, 30);
      jButtonConfirmMember.setBounds(430, 75, 100, 30);
      jLabelShowMember.setBounds(50, 125, 480, 30);
      jlabelDay.setBounds(50, 170, 60, 30);
      jComboBoxDay.setBounds(120, 170, 100, 30);
      jlabelMonth.setBounds(50, 210, 60, 30);
      jComboBoxMonth.setBounds(120,210,100,30);
      jlabelYear.setBounds(50, 250, 60, 30);
      jComboBoxYear.setBounds(120,250,100,30);
      jLabelLocation.setBounds(50,290,60,30);
      jComboBoxLocation.setBounds(120,290,100,30);
      jLabelMinuts.setBounds(50,330,60,30);
      jLabelSeconds.setBounds(50,370,60,30);
      jComboBoxMin.setBounds(120,330,100,30);
      jComboBoxSec.setBounds(120,370,100,30);
      jButtonConfirmTime.setBounds(50,410,170,30);

      jComboBoxMin.setOpaque(true);
      jComboBoxSec.setOpaque(true);
      jLabelMinuts.setOpaque(true);
      jLabelSeconds.setOpaque(true);
      jLabelChooseMember.setOpaque(true);
      jTextFieldIndtastMedlemsnummer.setOpaque(true);
      jLabelShowMember.setOpaque(true);
      jlabelDay.setOpaque(true);
      jlabelMonth.setOpaque(true);
      jlabelYear.setOpaque(true);
      jComboBoxYear.setOpaque(true);
      jComboBoxDay.setOpaque(true);
      jComboBoxMonth.setOpaque(true);
      jLabelLocation.setOpaque(true);
      jComboBoxLocation.setOpaque(true);

      jComboBoxLocation.setBackground(Color.WHITE);
      jComboBoxMin.setBackground(Color.WHITE);
      jComboBoxSec.setBackground(Color.WHITE);
      jLabelLocation.setBackground(Color.WHITE);
      jLabelChooseMember.setBackground(Color.WHITE);
      jTextFieldIndtastMedlemsnummer.setBackground(Color.WHITE);
      jLabelShowMember.setBackground(Color.WHITE);
      jlabelDay.setBackground(Color.WHITE);
      jlabelMonth.setBackground(Color.WHITE);
      jlabelYear.setBackground(Color.WHITE);
      jComboBoxYear.setBackground(Color.WHITE);
      jComboBoxDay.setBackground(Color.WHITE);
      jComboBoxMonth.setBackground(Color.WHITE);
      jLabelMinuts.setBackground(Color.WHITE);
      jLabelSeconds.setBackground(Color.WHITE);

      jComboBoxMin.setBorder(blackline);
      jComboBoxSec.setBorder(blackline);
      jLabelChooseMember.setBorder(blackline);
      jTextFieldIndtastMedlemsnummer.setBorder(blackline);
      jLabelShowMember.setBorder(blackline);
      jlabelDay.setBorder(blackline);
      jlabelMonth.setBorder(blackline);
      jlabelYear.setBorder(blackline);
      jComboBoxYear.setBorder(blackline);
      jComboBoxDay.setBorder(blackline);
      jComboBoxMonth.setBorder(blackline);
      jLabelMinuts.setBorder(blackline);
      jLabelSeconds.setBorder(blackline);
      jLabelLocation.setBorder(blackline);

      listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);


      jTextFieldIndtastMedlemsnummer.setHorizontalAlignment(JTextField.CENTER);
      jLabelChooseMember.setHorizontalAlignment(JLabel.CENTER);

      jButtonConfirmMember.addActionListener(alChooseMember);

    }
  };

  ActionListener alMenuOptionNewCompetitionTime = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelLargeArea.removeAll();
      jPanelLargeArea.repaint();
      jPanelLargeArea.setLayout(null);

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

  ActionListener alChooseMember = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      Competitor member; {
      }
      try {
        member = memberList.findSpecifikCompetitorByMemberNumber(Integer.parseInt(jTextFieldIndtastMedlemsnummer.getText()));
      } catch (NumberFormatException nfe){
        member = null;
        ui.showErrorfindMember(frameCoach);
      }
      if (member == null){
        ui.showErrorMemberNull(frameCoach);
      } else {
        jLabelShowMember.setText(ui.printMemberName(member));
      }
    }
  };

}