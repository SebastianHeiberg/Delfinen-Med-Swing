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
import java.util.Enumeration;
import java.util.Locale;

public class Chairman {

  JFrame frameChairman;
  //højreMenuen
  JButton buttonShowMembers;
  JButton buttonAddMember;
  JButton buttonSearchMember;
  JButton buttonDeleteMember;
  JButton buttonEditMember;
  JButton buttonExit;
  JPanel jPanelButtons;
  JPanel jPanelLargeArea;
  //tilføj medlem
  JScrollPane jScrollPaneShowMembers;
  JComboBox jComboBoxSwimmerType;
  JTextField jTextFieldName;
  JTextField jTextFieldEmail;
  JTextField jTextFieldAge;
  JComboBox jComboBoxPaymentStatus;
  ButtonGroup buttonGroupPassiveActive;
  JRadioButton jradioButtonActive;
  JRadioButton jradioButtonPassive;
  ButtonGroup buttonGroupGender;
  JRadioButton jRadioButtonMale;
  JRadioButton jRadioButtonFemale;
  JComboBox jComboBoxdisciplin;
  //søg medlem
  JTextField textFieldSearchCriteria;
  JTextField textFieldSearchMembernumber;
  JButton buttonName;
  JTextArea textAreaPotentialFoundMembers;
  JComboBox jComboBoxSearchCriteria;
  JScrollPane jScrollPaneFound;
  //slet medlem
  JTextField jTextFieldInputMembernumber;
  JButton jButtonConfirmDeletion;
  JButton jButtonChooseMember;
  JLabel jLabelShowMember;
  //rediger medlem
  JTextField jTextFieldEditname;
  JTextField jTextFieldEditEmail;
  JTextField jTextFieldEditAge;
  JComboBox jComboBoxEditPassiveActive;
  JButton jButtonEditname;
  JButton jButtonEditAge;
  JButton jButtonEditEmail;
  JButton jButtonEditPasAct;

  DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
  private MemberList memberList = new MemberList();
  private FileHandle fileHandle = new FileHandle();
  UI ui = new UI();


  public Chairman() {
    frameChairman = new JFrame();
    frameChairman.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameChairman.setVisible(true);
    frameChairman.setSize(800, 650);
    frameChairman.setLocationRelativeTo(null);
    frameChairman.setLayout(null);

    buttonShowMembers = new JButton("Vis alle medlemmer");
    buttonAddMember = new JButton("Tilføj medlem");
    buttonSearchMember = new JButton("Søg efter medlem");
    buttonDeleteMember = new JButton("Slet medlem");
    buttonEditMember = new JButton("Rediger medlem");
    buttonExit = new JButton("Gem og til hovedmenu");

    jPanelButtons = new JPanel();
    jPanelButtons.setLayout(null);
    jPanelButtons.setBackground(Color.BLUE);
    jPanelButtons.setSize(800, 210);
    frameChairman.add(jPanelButtons);
    jPanelButtons.setBounds(0, 0, 210, 800);
    jPanelButtons.add(buttonAddMember);
    jPanelButtons.add(buttonSearchMember);
    jPanelButtons.add(buttonEditMember);
    jPanelButtons.add(buttonShowMembers);
    jPanelButtons.add(buttonDeleteMember);
    jPanelButtons.add(buttonExit);

    //knapper
    buttonAddMember.setBounds(15, 20, 180, 60);
    buttonSearchMember.setBounds(15, 100, 180, 60);
    buttonEditMember.setBounds(15, 180, 180, 60);
    buttonShowMembers.setBounds(15, 260, 180, 60);
    buttonDeleteMember.setBounds(15, 340, 180, 60);
    buttonExit.setBounds(15, 420, 180, 60);

    //knappernes funktion
    buttonExit.addActionListener(alMenuOptionExitAndSave);
    buttonShowMembers.addActionListener(alMenuOptionShowAllMembers);
    buttonAddMember.addActionListener(alMenuOptionCreateNewMember);
    buttonSearchMember.addActionListener(alMenuOptionSearchMember);
    buttonDeleteMember.addActionListener(alMenuOptionDeleteMember);
    buttonEditMember.addActionListener(alMenuoptionEditMember);

    //Det store område
    jPanelLargeArea = new JPanel(null);
    jPanelLargeArea.setSize(590, 800);
    frameChairman.add(jPanelLargeArea);
    jPanelLargeArea.setBounds(210, 5, 590, 800);


  }

  ActionListener alMenuOptionExitAndSave = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      fileHandle.saveAllNonCompetitorsToFile(memberList.getAllNonCompetitors());
      fileHandle.saveAllCompetitorsToFile(memberList.getAllCompetitors());
      frameChairman.dispose();
      new Funktioner.Login().run();
    }
  };

  ActionListener alMenuOptionShowAllMembers = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelLargeArea.removeAll();
      jPanelLargeArea.repaint();
      jPanelLargeArea.setLayout(new BorderLayout());
      JTextArea textAreavisMedlemmerPanel = new JTextArea();
      jScrollPaneShowMembers = new JScrollPane(textAreavisMedlemmerPanel);
      jPanelLargeArea.add(jScrollPaneShowMembers);
      jScrollPaneShowMembers.setSize(575, 605);
      jScrollPaneShowMembers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPaneShowMembers.setEnabled(false);

      ui.printAllMembers(memberList.getAllNonCompetitors(), memberList.getAllCompetitors(), textAreavisMedlemmerPanel);

    }
  };

  ActionListener alMenuOptionDeleteMember = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelLargeArea.removeAll();
      jPanelLargeArea.repaint();
      jPanelLargeArea.setLayout(null);

      jTextFieldInputMembernumber = new JTextField();
      jButtonConfirmDeletion = new JButton("Slet medlem");
      jLabelShowMember = new JLabel("");
      jButtonChooseMember = new JButton("Vælg");
      JLabel jLabelmembernumber = new JLabel("Indtast medlemsnummer", JLabel.CENTER);
      jButtonConfirmDeletion = new JButton("Bekræft sletning");

      jPanelLargeArea.add(jTextFieldInputMembernumber);
      jPanelLargeArea.add(jButtonConfirmDeletion);
      jPanelLargeArea.add(jLabelShowMember);
      jPanelLargeArea.add(jButtonChooseMember);
      jPanelLargeArea.add(jLabelmembernumber);

      jLabelmembernumber.setBounds(50, 150, 170, 30);
      jTextFieldInputMembernumber.setBounds(240, 150, 170, 30);
      jButtonChooseMember.setBounds(440, 150, 90, 30);
      jLabelShowMember.setBounds(50, 200, 480, 30);
      jButtonConfirmDeletion.setBounds(240, 250, 170, 30);

      jLabelmembernumber.setOpaque(true);
      jTextFieldInputMembernumber.setOpaque(true);
      jLabelShowMember.setOpaque(true);
      jLabelmembernumber.setBackground(Color.WHITE);
      jTextFieldInputMembernumber.setBackground(Color.WHITE);
      jLabelShowMember.setBackground(Color.WHITE);

      Border blackline = BorderFactory.createLineBorder(Color.black);
      jLabelmembernumber.setBorder(blackline);
      jTextFieldInputMembernumber.setBorder(blackline);
      jLabelShowMember.setBorder(blackline);

      jButtonChooseMember.addActionListener(alDisplayMemberForDeletion);
      jButtonConfirmDeletion.addActionListener(alDeleteSpecificMember);

    }
  };

  ActionListener alMenuOptionCreateNewMember = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelLargeArea.removeAll();
      jPanelLargeArea.repaint();
      jPanelLargeArea.setLayout(null);

      String[] paymentStatus = {"Ja", "Nej"};
      String[] types = {"Motion", "Konkurrence"};
      String[] swimmingType = {"Crawl", "Bryst", "Rygcrawl", "Butterfly"};
      JLabel jLabelMembertype = new JLabel("Medlemstype", JLabel.CENTER);
      JLabel jlabelNameOfSwimmer = new JLabel("Navn på svømmer", JLabel.CENTER);
      JLabel jLabelEmailOfSwimmer = new JLabel("E-mail på svømmer", JLabel.CENTER);
      JLabel jlabelAgeOfSwimmer = new JLabel("Alder på svømmer", JLabel.CENTER);
      JLabel jlabelIsMembershipPaid = new JLabel("Er kontingent betalt", JLabel.CENTER);
      JLabel jLabelOnlyNonCompetitor = new JLabel("Kun relevant for motionister");
      JLabel jLabelPassiveSwimmer = new JLabel("Kontingent type", JLabel.CENTER);
      JLabel jLabelOnlyCompetitiveSwimmers = new JLabel("Kun relevant for konkurrencesvømmere");
      JLabel jLabelGender = new JLabel("Køn", JLabel.CENTER);
      JLabel jLabelSwimmingDisciplin = new JLabel("Svømmedisciplin", JLabel.CENTER);
      jComboBoxSwimmerType = new JComboBox<>(types);
      jTextFieldName = new JTextField("");
      jTextFieldEmail = new JTextField("");
      jTextFieldAge = new JTextField("");
      jComboBoxPaymentStatus = new JComboBox<>(paymentStatus);
      jradioButtonActive = new JRadioButton("aktiv");
      jradioButtonPassive = new JRadioButton("passiv");
      jRadioButtonFemale = new JRadioButton("K");
      jRadioButtonMale = new JRadioButton("M");
      buttonGroupPassiveActive = new ButtonGroup();
      buttonGroupPassiveActive.add(jradioButtonPassive);
      buttonGroupPassiveActive.add(jradioButtonActive);
      buttonGroupGender = new ButtonGroup();
      jComboBoxdisciplin = new JComboBox<>(swimmingType);
      JButton jButtonCreateNewMember = new JButton("Opret nyt medlem");
      jButtonCreateNewMember.addActionListener(alMakeNewMember);


      buttonGroupGender.add(jRadioButtonMale);
      buttonGroupGender.add(jRadioButtonFemale);
      jPanelLargeArea.add(jLabelMembertype);
      jPanelLargeArea.add(jlabelNameOfSwimmer);
      jPanelLargeArea.add(jlabelAgeOfSwimmer);
      jPanelLargeArea.add(jLabelEmailOfSwimmer);
      jPanelLargeArea.add(jlabelIsMembershipPaid);
      jPanelLargeArea.add(jLabelOnlyCompetitiveSwimmers);
      jPanelLargeArea.add(jLabelOnlyNonCompetitor);
      jPanelLargeArea.add(jLabelGender);
      jPanelLargeArea.add(jLabelSwimmingDisciplin);
      jPanelLargeArea.add(jLabelPassiveSwimmer);
      jPanelLargeArea.add(jComboBoxSwimmerType);
      jPanelLargeArea.add(jTextFieldName);
      jPanelLargeArea.add(jTextFieldEmail);
      jPanelLargeArea.add(jTextFieldAge);
      jPanelLargeArea.add(jComboBoxPaymentStatus);
      jPanelLargeArea.add(jradioButtonActive);
      jPanelLargeArea.add(jradioButtonPassive);
      jPanelLargeArea.add(jRadioButtonMale);
      jPanelLargeArea.add(jRadioButtonFemale);
      jPanelLargeArea.add(jComboBoxdisciplin);
      jPanelLargeArea.add(jButtonCreateNewMember);


      jLabelMembertype.setBounds(50, 50, 170, 30);
      jlabelNameOfSwimmer.setBounds(50, 100, 170, 30);
      jLabelEmailOfSwimmer.setBounds(50, 150, 170, 30);
      jlabelAgeOfSwimmer.setBounds(50, 200, 170, 30);
      jlabelIsMembershipPaid.setBounds(50, 250, 170, 30);
      jLabelOnlyNonCompetitor.setBounds(150, 300, 200, 30);
      jLabelPassiveSwimmer.setBounds(50, 350, 170, 30);
      jLabelOnlyCompetitiveSwimmers.setBounds(125, 400, 250, 30);
      jLabelGender.setBounds(50, 450, 170, 30);
      jLabelSwimmingDisciplin.setBounds(50, 500, 170, 30);

      jComboBoxSwimmerType.setBounds(250, 50, 170, 30);
      jTextFieldName.setBounds(250, 100, 170, 30);
      jTextFieldEmail.setBounds(250, 150, 170, 30);
      jTextFieldAge.setBounds(250, 200, 170, 30);
      jComboBoxPaymentStatus.setBounds(250, 250, 170, 30);
      jradioButtonPassive.setBounds(250, 350, 100, 30);
      jradioButtonActive.setBounds(350, 350, 100, 30);
      jRadioButtonMale.setBounds(250, 450, 100, 30);
      jRadioButtonFemale.setBounds(350, 450, 100, 30);
      jComboBoxdisciplin.setBounds(250, 500, 170, 30);
      jButtonCreateNewMember.setBounds(150, 550, 170, 30);

      listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
      jComboBoxPaymentStatus.setRenderer(listRenderer);
      jComboBoxSwimmerType.setRenderer(listRenderer);
      jComboBoxdisciplin.setRenderer(listRenderer);

      Border blackline = BorderFactory.createLineBorder(Color.black);
      jLabelMembertype.setBorder(blackline);
      jlabelNameOfSwimmer.setBorder(blackline);
      jlabelAgeOfSwimmer.setBorder(blackline);
      jLabelEmailOfSwimmer.setBorder(blackline);
      jlabelIsMembershipPaid.setBorder(blackline);
      jLabelPassiveSwimmer.setBorder(blackline);
      jLabelGender.setBorder(blackline);
      jLabelSwimmingDisciplin.setBorder(blackline);

      jLabelMembertype.setOpaque(true);
      jlabelNameOfSwimmer.setOpaque(true);
      jlabelAgeOfSwimmer.setOpaque(true);
      jLabelEmailOfSwimmer.setOpaque(true);
      jlabelIsMembershipPaid.setOpaque(true);
      jLabelPassiveSwimmer.setOpaque(true);
      jLabelGender.setOpaque(true);
      jLabelSwimmingDisciplin.setOpaque(true);
      jComboBoxPaymentStatus.setOpaque(true);
      jComboBoxSwimmerType.setOpaque(true);
      jComboBoxdisciplin.setOpaque(true);

      jLabelMembertype.setBackground(Color.WHITE);
      jlabelNameOfSwimmer.setBackground(Color.WHITE);
      jlabelAgeOfSwimmer.setBackground(Color.WHITE);
      jLabelEmailOfSwimmer.setBackground(Color.WHITE);
      jlabelIsMembershipPaid.setBackground(Color.WHITE);
      jLabelPassiveSwimmer.setBackground(Color.WHITE);
      ;
      jLabelGender.setBackground(Color.WHITE);
      jLabelSwimmingDisciplin.setBackground(Color.WHITE);
      jComboBoxPaymentStatus.setBackground(Color.WHITE);
      jComboBoxSwimmerType.setBackground(Color.WHITE);
      jComboBoxdisciplin.setBackground(Color.WHITE);

    }
  };

  ActionListener alMenuOptionSearchMember = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelLargeArea.removeAll();
      jPanelLargeArea.repaint();
      jPanelLargeArea.setLayout(null);

      textAreaPotentialFoundMembers = new JTextArea();
      jScrollPaneFound = new JScrollPane(textAreaPotentialFoundMembers);
      jPanelLargeArea.add(jScrollPaneFound);
      jScrollPaneFound.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPaneFound.setEnabled(false);
      jScrollPaneFound.setBounds(25, 250, 525, 340);

      textFieldSearchMembernumber = new JTextField();
      JLabel jLabelInputText = new JLabel("Input søgningord", JLabel.CENTER);
      JLabel jLabelSearchAfter = new JLabel("Søg efter ", JLabel.CENTER);
      buttonName = new JButton("Søg");
      String[] searchOptions = {"E-mail", "Navn", "Medlemsnummer"};
      jComboBoxSearchCriteria = new JComboBox<>(searchOptions);
      textFieldSearchCriteria = new JTextField();
      textFieldSearchCriteria.setHorizontalAlignment(JTextField.CENTER);

      jPanelLargeArea.add(jLabelInputText);
      jPanelLargeArea.add(jLabelSearchAfter);
      jPanelLargeArea.add(buttonName);
      jPanelLargeArea.add(jComboBoxSearchCriteria);
      jPanelLargeArea.add(textFieldSearchCriteria);

      jLabelInputText.setOpaque(true);
      jLabelSearchAfter.setOpaque(true);
      jComboBoxSearchCriteria.setOpaque(true);
      jLabelInputText.setBackground(Color.WHITE);
      jLabelSearchAfter.setBackground(Color.WHITE);
      jComboBoxSearchCriteria.setBackground(Color.WHITE);


      jLabelInputText.setBounds(100, 150, 170, 30);
      jLabelSearchAfter.setBounds(100, 100, 170, 30);
      jComboBoxSearchCriteria.setBounds(300, 100, 170, 30);
      textFieldSearchCriteria.setBounds(300, 150, 170, 30);
      buttonName.setBounds(200, 200, 170, 30);

      Border blackline = BorderFactory.createLineBorder(Color.black);
      jLabelInputText.setBorder(blackline);
      jLabelSearchAfter.setBorder(blackline);
      jComboBoxSearchCriteria.setBorder(blackline);
      textFieldSearchMembernumber.setBorder(blackline);
      textFieldSearchCriteria.setBorder(blackline);

      DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
      listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
      jComboBoxSearchCriteria.setRenderer(listRenderer);


      buttonName.addActionListener(alSearchMemberFromCriteria);
    }
  };

  ActionListener alMenuoptionEditMember = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelLargeArea.removeAll();
      jPanelLargeArea.repaint();
      jPanelLargeArea.setLayout(null);

      jTextFieldInputMembernumber = new JTextField();
      jLabelShowMember = new JLabel("");
      jButtonChooseMember = new JButton("Vælg");
      JLabel jLabelmembernumber = new JLabel("Indtast medlemsnummer", JLabel.CENTER);
      JLabel jLabelChangeThis = new JLabel("Opdater oplysninger", JLabel.CENTER);
      JLabel jLabelName = new JLabel("Navn", JLabel.CENTER);
      JLabel jLabelEmail = new JLabel("Email", JLabel.CENTER);
      JLabel jLabelAge = new JLabel("Alder", JLabel.CENTER);
      JLabel jlabelPassiveActive = new JLabel("Passiv / aktiv", JLabel.CENTER);
      jTextFieldEditname = new JTextField();
      jTextFieldEditEmail = new JTextField();
      jTextFieldEditAge = new JTextField();
      String[] passiveActive = {"Passiv", "Aktiv"};
      jComboBoxEditPassiveActive = new JComboBox(passiveActive);
      JButton jButtonRedigerName = new JButton("Opdater");
      JButton jButtonRedigerAge = new JButton("Opdater");
      JButton jButtonRedigerEmail = new JButton("Opdater");
      JButton jButtonRedigerPasAct = new JButton("Opdater");

      jPanelLargeArea.add(jTextFieldInputMembernumber);
      jPanelLargeArea.add(jLabelShowMember);
      jPanelLargeArea.add(jButtonChooseMember);
      jPanelLargeArea.add(jLabelmembernumber);
      jPanelLargeArea.add(jLabelChangeThis);
      jPanelLargeArea.add(jLabelName);
      jPanelLargeArea.add(jLabelAge);
      jPanelLargeArea.add(jLabelEmail);
      jPanelLargeArea.add(jlabelPassiveActive);
      jPanelLargeArea.add(jTextFieldEditname);
      jPanelLargeArea.add(jTextFieldEditEmail);
      jPanelLargeArea.add(jTextFieldEditAge);
      jPanelLargeArea.add(jComboBoxEditPassiveActive);
      jPanelLargeArea.add(jButtonRedigerName);
      jPanelLargeArea.add(jButtonRedigerAge);
      jPanelLargeArea.add(jButtonRedigerEmail);
      jPanelLargeArea.add(jButtonRedigerPasAct);

      jLabelName.setOpaque(true);
      jLabelEmail.setOpaque(true);
      jLabelAge.setOpaque(true);
      jlabelPassiveActive.setOpaque(true);
      jLabelmembernumber.setOpaque(true);
      jTextFieldInputMembernumber.setOpaque(true);
      jLabelShowMember.setOpaque(true);
      jTextFieldEditname.setOpaque(true);
      jTextFieldEditEmail.setOpaque(true);
      jTextFieldEditAge.setOpaque(true);
      jComboBoxEditPassiveActive.setOpaque(true);

      jLabelName.setBackground(Color.WHITE);
      jLabelEmail.setBackground(Color.WHITE);
      jLabelAge.setBackground(Color.WHITE);
      jlabelPassiveActive.setBackground(Color.WHITE);
      jLabelmembernumber.setBackground(Color.WHITE);
      jTextFieldInputMembernumber.setBackground(Color.WHITE);
      jLabelShowMember.setBackground(Color.WHITE);
      jTextFieldEditname.setBackground(Color.WHITE);
      jTextFieldEditEmail.setBackground(Color.WHITE);
      jTextFieldEditAge.setBackground(Color.WHITE);
      jComboBoxEditPassiveActive.setBackground(Color.WHITE);

      jTextFieldEditname.setHorizontalAlignment(JTextField.CENTER);
      jTextFieldEditEmail.setHorizontalAlignment(JTextField.CENTER);
      jTextFieldEditAge.setHorizontalAlignment(JTextField.CENTER);

      jLabelmembernumber.setBounds(50, 150, 170, 30);
      jTextFieldInputMembernumber.setBounds(240, 150, 170, 30);
      jLabelShowMember.setBounds(50, 200, 480, 30);
      jButtonChooseMember.setBounds(440, 150, 90, 30);
      jLabelChangeThis.setBounds(240, 250, 170, 30);
      jLabelName.setBounds(50, 300, 170, 30);
      jLabelEmail.setBounds(50, 350, 170, 30);
      jLabelAge.setBounds(50, 400, 170, 30);
      jlabelPassiveActive.setBounds(50, 450, 170, 30);
      jTextFieldEditname.setBounds(240, 300, 170, 30);
      jTextFieldEditEmail.setBounds(240, 350, 170, 30);
      jTextFieldEditAge.setBounds(240, 400, 170, 30);
      jComboBoxEditPassiveActive.setBounds(240, 450, 170, 30);
      jButtonRedigerName.setBounds(440, 300, 90, 30);
      jButtonRedigerAge.setBounds(440, 400, 90, 30);
      jButtonRedigerEmail.setBounds(440, 350, 90, 30);
      jButtonRedigerPasAct.setBounds(440, 450, 90, 30);

      listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
      jComboBoxEditPassiveActive.setRenderer(listRenderer);

      Border blackline = BorderFactory.createLineBorder(Color.black);
      jLabelmembernumber.setBorder(blackline);
      jTextFieldInputMembernumber.setBorder(blackline);
      jLabelShowMember.setBorder(blackline);
      jLabelName.setBorder(blackline);
      jLabelEmail.setBorder(blackline);
      jLabelAge.setBorder(blackline);
      jlabelPassiveActive.setBorder(blackline);
      jTextFieldEditname.setBorder(blackline);
      jTextFieldEditEmail.setBorder(blackline);
      jTextFieldEditAge.setBorder(blackline);

      jButtonChooseMember.addActionListener(alDisplayMemberForDeletion);

    }
  };

  ActionListener alSearchMemberFromCriteria = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      String choosenSearch = jComboBoxSearchCriteria.getItemAt(jComboBoxSearchCriteria.getSelectedIndex()).toString();
      String text = textFieldSearchCriteria.getText();
      textAreaPotentialFoundMembers.selectAll();
      textAreaPotentialFoundMembers.setText("");


      if (!text.isEmpty()) {
        if (choosenSearch.equals("E-mail")) {
          ArrayList<Member> members = memberList.findSpecifikMembersByEmail(text);
          ui.printFoundMembersBySearch(members, textAreaPotentialFoundMembers);

        } else if (choosenSearch.equals("Navn")) {
          ArrayList<Member> members = memberList.findSpecifikMembersByName(text);
          ui.printFoundMembersBySearch(members, textAreaPotentialFoundMembers);

        } else {
          //medlemsnummer
          int number = 0;
          try {
            number = Integer.parseInt(text);
          } catch (NumberFormatException numberFormatException) {
            ui.showErrorNumbersOnlyMember(frameChairman);
          }
          Member member = memberList.findSpecifikMemberByMemberNumber(number);
          ui.printMemberFoundByMembernumber(member, textAreaPotentialFoundMembers);
        }

      } else {
        ui.showErrorSearchwordMissing(frameChairman);
      }
    }
  };

  ActionListener alMakeNewMember = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

      String membertype = jComboBoxSwimmerType.getItemAt(jComboBoxSwimmerType.getSelectedIndex()).toString();
      String paymentStatus = jComboBoxSwimmerType.getItemAt(jComboBoxSwimmerType.getSelectedIndex()).toString();
      String name = jTextFieldName.getText();
      String email = jTextFieldEmail.getText();
      Integer age = -1;
      String textAge = jTextFieldAge.getText();
      try {
        age = Integer.parseInt(textAge);
      } catch (NumberFormatException nfe) {
        ui.showErrorNumbersOnlyAge(frameChairman);
      }

      boolean paid = false;
      if (paymentStatus.equals("Ja")) {
        paid = true;
      }

      if (membertype.equals("Motion") && !name.isEmpty() && age > 0 && !email.isEmpty()) {

        String selectedActivePassive = getSelectedButtonText(buttonGroupPassiveActive);

        boolean activePassive = true;
        if (selectedActivePassive.equals("Passiv")) {
          activePassive = false;
        }
        //TODO få bragt memberNumber i spil.
        try {
          NonCompetitor member = new NonCompetitor(name, 0, age, email, paid, activePassive);
          memberList.getAllNonCompetitors().add(member);
          jTextFieldName.setText("");
          jTextFieldEmail.setText("");
          jTextFieldAge.setText("");
        } catch (NullPointerException nullPointerException) {
          System.out.println("FEJL!");
        }
      } else if (membertype.equals("Konkurrence") && !name.isEmpty() && age > 0 && !email.isEmpty()) {

        String selectedMF = getSelectedButtonText(buttonGroupGender);
        SwimmingDisciplin swimmingDisciplins = SwimmingDisciplin.valueOf(jComboBoxdisciplin.getItemAt(jComboBoxdisciplin.getSelectedIndex()).toString().toUpperCase(Locale.ROOT));
        BestResultTraining bestResultTraining = new BestResultTraining(0, 0, 0, 0);
        BestResultCompetition bestResultCompetition = new BestResultCompetition(0, 0, 0, 0);

        try {
          Competitor member = new Competitor(name, 0, age, email, paid, selectedMF, swimmingDisciplins, bestResultTraining, bestResultCompetition);
          memberList.getAllCompetitors().add(member);
          jTextFieldName.setText("");
          jTextFieldEmail.setText("");
          jTextFieldAge.setText("");
        } catch (NullPointerException nullPointerException) {
          System.out.println("FEJL!");
        }

      } else {
        ui.showErrorCreatemember(frameChairman);
      }


    }
  };

  public String getSelectedButtonText(ButtonGroup buttonGroup) {
    for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
      AbstractButton button = buttons.nextElement();

      if (button.isSelected()) {
        return button.getText();
      }
    }

    return null;
  }

  ActionListener alDisplayMemberForDeletion = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      Member member;
      {
      }
      try {
        member = memberList.findSpecifikMemberByMemberNumber(Integer.parseInt(jTextFieldInputMembernumber.getText()));
      } catch (NumberFormatException nfe) {
        member = null;
        ui.showErrorfindMember(frameChairman);
      }
      if (member == null) {
        ui.showErrorMemberNull(frameChairman);
      } else {
        jLabelShowMember.setText(ui.printMemberKassereÆndreRestance(member));
      }
    }
  };

  ActionListener alDeleteSpecificMember = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      Member member;
      {
      }
      try {
        member = memberList.findSpecifikMemberByMemberNumber(Integer.parseInt(jTextFieldInputMembernumber.getText()));
      } catch (NumberFormatException nfe) {
        member = null;
        ui.showErrorfindMember(frameChairman);
      }
      if (member == null) {
        ui.showErrorMemberNull(frameChairman);
      } else {
        memberList.removeMember(member);
        jLabelShowMember.setText(ui.printSlettet());
        jTextFieldInputMembernumber.setText("");
      }
    }
  };

  public void run() {
    memberList.setAllNonCompetitors(fileHandle.loadNonCompetitors());
    memberList.setAllCompetitors(fileHandle.loadCompetitors());
  }
}
