package Funktioner;

import Member.*;
import Persistence.FileHandle;
import UI.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Coach {

  private final JFrame frameCoach;
  private final JPanel jPanelButtons;
  private final JPanel jPanelLargeArea;
  //højre menu
  private final JButton jButtonTop5;
  private final JButton jButtonRegisterNewTime;
  private final JButton jButtonKonvertSwimmer;
  private final JButton jButtonExit;
  //vis top 5
  private JComboBox jComboBoxGender;
  private JComboBox jComboBoxDisciplin;
  private JComboBox jComboBoxAgeGroup;
  private JScrollPane jScrollPaneShowTop5;
  private JButton showTop5People;
  //Ny træningstid
  private JComboBox jComboBoxLocation;
  private JComboBox jComboBoxMonth;
  private JComboBox jComboBoxYear;
  private JComboBox jComboBoxMin;
  private JComboBox jComboBoxSec;
  private JTextField jTextFieldIndtastMedlemsnummer;
  private JLabel jLabelShowMember;
  private JLabel jLabelShowMemberTrainingTime;
  private JLabel jLabelShowMemberCompetivieTime;
  private JButton jButtonConfirmMember;
  private JButton jButtonConfirmTime;

  // generelt brugt
  private final DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
  private final Border blackline = BorderFactory.createLineBorder(Color.black);
  private final MemberList memberList = new MemberList();
  private final FileHandle fileHandle = new FileHandle();
  private final UI ui = new UI();
  private Competitor competitorChoosen = null;

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
    jButtonRegisterNewTime = new JButton("Registrer ny svømmetid");
    jButtonKonvertSwimmer = new JButton("Konverter svømmer");
    jButtonExit = new JButton("Gem og til hovedmenu");

    jPanelButtons.add(jButtonTop5);
    jPanelButtons.add(jButtonRegisterNewTime);
    jPanelButtons.add(jButtonKonvertSwimmer);
    jPanelButtons.add(jButtonExit);

    //knapper
    jButtonTop5.setBounds(15, 20, 180, 60);
    jButtonRegisterNewTime.setBounds(15, 100, 180, 60);
    jButtonKonvertSwimmer.setBounds(15, 180, 180, 60);
    jButtonExit.setBounds(15, 260, 180, 60);

    jButtonExit.addActionListener(alMenuOptionExitAndSave);
    jButtonTop5.addActionListener(alMenuOptionShowTop5);
    jButtonKonvertSwimmer.addActionListener(alMenuOptionConverMember);
    jButtonRegisterNewTime.addActionListener(alMenuOptionNewTrainingTime);

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
      jScrollPaneShowTop5.setBounds(5, 200, 0, 0);
      jScrollPaneShowTop5.setSize(570, 250);
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
      competitorChoosen = null;

      String[] location = {"Konkurrence", "Træning"};
      jComboBoxLocation = new JComboBox(location);
      String[] month = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
      jComboBoxMonth = new JComboBox(month);
      String[] year = {"2022"};
      jComboBoxYear = new JComboBox(year);
      String[] sec = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
      jComboBoxMin = new JComboBox(sec);
      jComboBoxSec = new JComboBox(sec);
      JLabel jlabelMonth = new JLabel("Måned", JLabel.CENTER);
      JLabel jlabelYear = new JLabel("Årstal", JLabel.CENTER);
      JLabel jLabelChooseMember = new JLabel("1. Indtast medlemsnummer", JLabel.CENTER);
      jTextFieldIndtastMedlemsnummer = new JTextField("", 15);
      jButtonConfirmMember = new JButton("2. Vælg");
      jLabelShowMember = new JLabel("Valgt medlem: ");
      JLabel jLabelMinuts = new JLabel("Minut", JLabel.CENTER);
      JLabel jLabelSeconds = new JLabel("Sekund", JLabel.CENTER);
      JLabel jLabelLocation = new JLabel("Lokalitet", JLabel.CENTER);
      jButtonConfirmTime = new JButton("3. Registerer ny tid");
      jLabelShowMemberTrainingTime = new JLabel("Nuværende træningstid:");
      jLabelShowMemberCompetivieTime = new JLabel("Nuværende konkurrencetid: ");

      //lav indhold
      jPanelLargeArea.add(jLabelChooseMember);
      jPanelLargeArea.add(jTextFieldIndtastMedlemsnummer);
      jPanelLargeArea.add(jButtonConfirmMember);
      jPanelLargeArea.add(jLabelShowMember);
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
      jPanelLargeArea.add(jLabelShowMemberTrainingTime);
      jPanelLargeArea.add(jLabelShowMemberCompetivieTime);

      jLabelChooseMember.setBounds(50, 75, 170, 30);
      jTextFieldIndtastMedlemsnummer.setBounds(240, 75, 170, 30);
      jButtonConfirmMember.setBounds(430, 75, 100, 30);
      jLabelShowMember.setBounds(50, 125, 480, 30);
      jLabelShowMemberTrainingTime.setBounds(50, 165, 480, 30);
      jLabelShowMemberCompetivieTime.setBounds(50, 205, 480, 30);
      jLabelLocation.setBounds(50, 250, 60, 30);
      jComboBoxLocation.setBounds(120, 250, 100, 30);
      jlabelMonth.setBounds(50, 290, 60, 30);
      jComboBoxMonth.setBounds(120, 290, 100, 30);
      jlabelYear.setBounds(50, 330, 60, 30);
      jComboBoxYear.setBounds(120, 330, 100, 30);
      jLabelMinuts.setBounds(50, 370, 60, 30);
      jComboBoxMin.setBounds(120, 370, 100, 30);
      jLabelSeconds.setBounds(50, 410, 60, 30);
      jComboBoxSec.setBounds(120, 410, 100, 30);
      jButtonConfirmTime.setBounds(50, 450, 170, 30);

      jLabelShowMemberTrainingTime.setOpaque(true);
      jLabelShowMemberCompetivieTime.setOpaque(true);
      jComboBoxMin.setOpaque(true);
      jComboBoxSec.setOpaque(true);
      jLabelMinuts.setOpaque(true);
      jLabelSeconds.setOpaque(true);
      jLabelChooseMember.setOpaque(true);
      jTextFieldIndtastMedlemsnummer.setOpaque(true);
      jLabelShowMember.setOpaque(true);
      jlabelMonth.setOpaque(true);
      jlabelYear.setOpaque(true);
      jComboBoxYear.setOpaque(true);
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
      jlabelMonth.setBackground(Color.WHITE);
      jlabelYear.setBackground(Color.WHITE);
      jComboBoxYear.setBackground(Color.WHITE);
      jComboBoxMonth.setBackground(Color.WHITE);
      jLabelMinuts.setBackground(Color.WHITE);
      jLabelSeconds.setBackground(Color.WHITE);
      jLabelShowMemberTrainingTime.setBackground(Color.WHITE);
      jLabelShowMemberCompetivieTime.setBackground(Color.WHITE);

      jComboBoxMin.setBorder(blackline);
      jComboBoxSec.setBorder(blackline);
      jLabelChooseMember.setBorder(blackline);
      jTextFieldIndtastMedlemsnummer.setBorder(blackline);
      jLabelShowMember.setBorder(blackline);
      jlabelMonth.setBorder(blackline);
      jlabelYear.setBorder(blackline);
      jComboBoxYear.setBorder(blackline);
      jComboBoxMonth.setBorder(blackline);
      jLabelMinuts.setBorder(blackline);
      jLabelSeconds.setBorder(blackline);
      jLabelLocation.setBorder(blackline);
      jLabelShowMemberTrainingTime.setBorder(blackline);
      jLabelShowMemberCompetivieTime.setBorder(blackline);

      listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
      jTextFieldIndtastMedlemsnummer.setHorizontalAlignment(JTextField.CENTER);
      jLabelChooseMember.setHorizontalAlignment(JLabel.CENTER);

      jButtonConfirmMember.addActionListener(alChooseMember);
      jButtonConfirmTime.addActionListener(alAddTime);
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
      Competitor member;
      {
      }
      try {
        member = memberList.findSpecifikCompetitorByMemberNumber(Integer.parseInt(jTextFieldIndtastMedlemsnummer.getText()));
      } catch (NumberFormatException nfe) {
        member = null;
        ui.showErrorfindMember(frameCoach);
      }
      if (member == null) {
        ui.showErrorMemberNull(frameCoach);
        jLabelShowMember.setText("Valgt medlem: ");
        jLabelShowMemberTrainingTime.setText("Nuværende træningstid: ");
        jLabelShowMemberCompetivieTime.setText("Nuværende konkurrencetid: ");
        competitorChoosen = null;
      } else {
        jLabelShowMember.setText("Valgt medlem: " + ui.printMemberName(member));
        jLabelShowMemberTrainingTime.setText(ui.printMemberTrainingTime(member));
        jLabelShowMemberCompetivieTime.setText(ui.printMemberCompetitiveTime(member));
        competitorChoosen = member;
      }
    }
  };

  ActionListener alAddTime = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    String location = jComboBoxLocation.getItemAt(jComboBoxLocation.getSelectedIndex()).toString();
    int min = Integer.parseInt(jComboBoxMin.getItemAt(jComboBoxMin.getSelectedIndex()).toString());
    int sec = Integer.parseInt(jComboBoxSec.getItemAt(jComboBoxSec.getSelectedIndex()).toString());
    int month = Integer.parseInt(jComboBoxMonth.getItemAt(jComboBoxMonth.getSelectedIndex()).toString());
    int year = Integer.parseInt(jComboBoxYear.getItemAt(jComboBoxYear.getSelectedIndex()).toString());

      if (competitorChoosen == null) {
        ui.showErrorFindMemberFirst(frameCoach);
      } else if (location.equals("Træning")){

        BestResultTraining training = competitorChoosen.getBestResultTraining();
        training.setPersonalBestTrainingMonth(month);
        training.setPersonalBestTrainingYear(year);
        training.setPersonalBestTrainingTimeMinutes(min);
        training.setPersonalBestTrainingTimeSeconds(sec);
        jLabelShowMember.setText("Valgt medlem: " + ui.printMemberName(competitorChoosen));
        jLabelShowMemberTrainingTime.setText("OPDATERET: " + ui.printMemberTrainingTime(competitorChoosen));
        jLabelShowMemberCompetivieTime.setText(ui.printMemberCompetitiveTime(competitorChoosen));

      } else {
        BestResultCompetition competition = competitorChoosen.getBestResultCompetition();
        competition.setPersonalBestCompetitionMonth(month);
        competition.setPersonalBestCompetitionYear(year);
        competition.setPersonalBestCompetitionTimeMinutes(min);
        competition.setPersonalBestCompetitionTimeSeconds(sec);
        jLabelShowMember.setText("Valgt medlem: " + ui.printMemberName(competitorChoosen));
        jLabelShowMemberTrainingTime.setText(ui.printMemberTrainingTime(competitorChoosen));
        jLabelShowMemberCompetivieTime.setText("OPDATERET: " + ui.printMemberCompetitiveTime(competitorChoosen));
      }

    }
  };

}