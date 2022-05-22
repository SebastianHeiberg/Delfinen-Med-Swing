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

public class Formand {

  JFrame frameFormand;
  JButton buttonVisMedlemmer;
  JButton buttonTilføjMedlem;
  JButton buttonSøgEfterMedlem;
  JButton buttonSletMedlem;
  JButton buttonRedigerMedlem;
  JButton buttonExit;
  JPanel jPanelKnapper;
  JPanel jPanelStoreOmråde;
  JScrollPane jScrollPanevisMembers;
  JComboBox jComboBoxSvømmetype;
  JTextField jTextFieldNavn;
  JTextField jTextFieldEmail;
  JTextField jTextFieldAlder;
  JComboBox jComboBoxKontingent;
  ButtonGroup buttonGroupPassivAktiv;
  JRadioButton jradioButtonAktiv;
  JRadioButton jradioButtonPassiv;
  ButtonGroup buttonGroupKøn;
  JRadioButton jRadioButtonMand;
  JRadioButton jRadioButtonKvinde;
  JComboBox jComboBoxdisciplin;
  JTextField textFieldSøgUdFra;
  JTextField textFieldSøgMedlemsnummer;
  JButton buttonNavn;
  JTextArea textAreaFremsøgteMembers;
  JComboBox jComboBoxsøgeKriterie;


  private MemberList memberList = new MemberList();
  private FileHandle fileHandle = new FileHandle();
  UI ui = new UI();


  public Formand() {
    frameFormand = new JFrame();
    frameFormand.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameFormand.setVisible(true);
    frameFormand.setSize(800, 650);
    frameFormand.setLocationRelativeTo(null);
    frameFormand.setLayout(null);

    buttonVisMedlemmer = new JButton("Vis alle medlemmer");
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
    buttonSletMedlem.setBounds(15, 340, 180, 60);
    buttonExit.setBounds(15, 420, 180, 60);

    //knappernes funktion
    buttonExit.addActionListener(alExitandSave);
    buttonVisMedlemmer.addActionListener(alShowAllMembers);
    buttonTilføjMedlem.addActionListener(alNytMedlem);
    buttonSøgEfterMedlem.addActionListener(alSøgMedlem);

    //Det store område
    jPanelStoreOmråde = new JPanel(null);
    jPanelStoreOmråde.setSize(590, 800);
    frameFormand.add(jPanelStoreOmråde);
    jPanelStoreOmråde.setBounds(210, 5, 590, 800);


  }

  ActionListener alExitandSave = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      fileHandle.saveAllNonCompetitorsToFile(memberList.getAllNonCompetitors());
      fileHandle.saveAllCompetitorsToFile(memberList.getAllCompetitors());
      frameFormand.dispose();
      new Funktioner.Login().run();
    }
  };

  ActionListener alShowAllMembers = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelStoreOmråde.removeAll();
      jPanelStoreOmråde.repaint();
      jPanelStoreOmråde.setLayout(new BorderLayout());
      JTextArea textAreavisMedlemmerPanel = new JTextArea();
      jScrollPanevisMembers = new JScrollPane(textAreavisMedlemmerPanel);
      jPanelStoreOmråde.add(jScrollPanevisMembers);
      jScrollPanevisMembers.setSize(575, 605);
      jScrollPanevisMembers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPanevisMembers.setEnabled(false);

      ui.printAllMembers(memberList.getAllNonCompetitors(), memberList.getAllCompetitors(), textAreavisMedlemmerPanel);

    }
  };

  ActionListener alNytMedlem = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelStoreOmråde.removeAll();
      jPanelStoreOmråde.repaint();
      jPanelStoreOmråde.setLayout(null);

      String[] kontingentBetalt = {"Ja", "Nej"};
      String[] typer = {"Motion", "Konkurrence"};
      String[] svømmetyper = {"Crawl", "Bryst", "Rygcrawl", "Butterfly"};
      JLabel jLabelMedlemstype = new JLabel("Medlemstype", JLabel.CENTER);
      JLabel jlabelNavnPåSvommer = new JLabel("Navn på svømmer", JLabel.CENTER);
      JLabel jLabelEmailPåSvommer = new JLabel("E-mail på svømmer", JLabel.CENTER);
      JLabel jlabelAlderPåSvommer = new JLabel("Alder på svømmer", JLabel.CENTER);
      JLabel jlabelKontigentBetalt = new JLabel("Er kontingent betalt", JLabel.CENTER);
      JLabel jLabelKunForMotionister = new JLabel("Kun relevant for motionister");
      JLabel jLabelPassivSvømmer = new JLabel("Kontingent type", JLabel.CENTER);
      JLabel jLabelKunForKonkurrencesvommere = new JLabel("Kun relevant for konkurrencesvømmere");
      JLabel jLabelKøn = new JLabel("Køn", JLabel.CENTER);
      JLabel jLabelSvømmeDisciplin = new JLabel("Svømmedisciplin", JLabel.CENTER);
      jComboBoxSvømmetype = new JComboBox<>(typer);
      jTextFieldNavn = new JTextField("");
      jTextFieldEmail = new JTextField("");
      jTextFieldAlder = new JTextField("");
      jComboBoxKontingent = new JComboBox<>(kontingentBetalt);
      jradioButtonAktiv = new JRadioButton("aktiv");
      jradioButtonPassiv = new JRadioButton("passiv");
      jRadioButtonKvinde = new JRadioButton("K");
      jRadioButtonMand = new JRadioButton("M");
      buttonGroupPassivAktiv = new ButtonGroup();
      buttonGroupPassivAktiv.add(jradioButtonPassiv);
      buttonGroupPassivAktiv.add(jradioButtonAktiv);
      buttonGroupKøn = new ButtonGroup();
      jComboBoxdisciplin = new JComboBox<>(svømmetyper);
      JButton jButtonOpretNytMedlem = new JButton("Opret nyt medlem");
      jButtonOpretNytMedlem.addActionListener(alLavNytMedlem);


      buttonGroupKøn.add(jRadioButtonMand);
      buttonGroupKøn.add(jRadioButtonKvinde);
      jPanelStoreOmråde.add(jLabelMedlemstype);
      jPanelStoreOmråde.add(jlabelNavnPåSvommer);
      jPanelStoreOmråde.add(jlabelAlderPåSvommer);
      jPanelStoreOmråde.add(jLabelEmailPåSvommer);
      jPanelStoreOmråde.add(jlabelKontigentBetalt);
      jPanelStoreOmråde.add(jLabelKunForKonkurrencesvommere);
      jPanelStoreOmråde.add(jLabelKunForMotionister);
      jPanelStoreOmråde.add(jLabelKøn);
      jPanelStoreOmråde.add(jLabelSvømmeDisciplin);
      jPanelStoreOmråde.add(jLabelPassivSvømmer);
      jPanelStoreOmråde.add(jComboBoxSvømmetype);
      jPanelStoreOmråde.add(jTextFieldNavn);
      jPanelStoreOmråde.add(jTextFieldEmail);
      jPanelStoreOmråde.add(jTextFieldAlder);
      jPanelStoreOmråde.add(jComboBoxKontingent);
      jPanelStoreOmråde.add(jradioButtonAktiv);
      jPanelStoreOmråde.add(jradioButtonPassiv);
      jPanelStoreOmråde.add(jRadioButtonMand);
      jPanelStoreOmråde.add(jRadioButtonKvinde);
      jPanelStoreOmråde.add(jComboBoxdisciplin);
      jPanelStoreOmråde.add(jButtonOpretNytMedlem);


      jLabelMedlemstype.setBounds(50, 50, 170, 30);
      jlabelNavnPåSvommer.setBounds(50, 100, 170, 30);
      jLabelEmailPåSvommer.setBounds(50, 150, 170, 30);
      jlabelAlderPåSvommer.setBounds(50, 200, 170, 30);
      jlabelKontigentBetalt.setBounds(50, 250, 170, 30);
      jLabelKunForMotionister.setBounds(150, 300, 200, 30);
      jLabelPassivSvømmer.setBounds(50, 350, 170, 30);
      jLabelKunForKonkurrencesvommere.setBounds(125, 400, 250, 30);
      jLabelKøn.setBounds(50, 450, 170, 30);
      jLabelSvømmeDisciplin.setBounds(50, 500, 170, 30);

      jComboBoxSvømmetype.setBounds(250, 50, 170, 30);
      jTextFieldNavn.setBounds(250, 100, 170, 30);
      jTextFieldEmail.setBounds(250, 150, 170, 30);
      jTextFieldAlder.setBounds(250, 200, 170, 30);
      jComboBoxKontingent.setBounds(250, 250, 170, 30);
      jradioButtonPassiv.setBounds(250, 350, 100, 30);
      jradioButtonAktiv.setBounds(350, 350, 100, 30);
      jRadioButtonMand.setBounds(250, 450, 100, 30);
      jRadioButtonKvinde.setBounds(350, 450, 100, 30);
      jComboBoxdisciplin.setBounds(250, 500, 170, 30);
      jButtonOpretNytMedlem.setBounds(150, 550, 170, 30);

      DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
      listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
      jComboBoxKontingent.setRenderer(listRenderer);
      jComboBoxSvømmetype.setRenderer(listRenderer);
      jComboBoxdisciplin.setRenderer(listRenderer);

      Border blackline = BorderFactory.createLineBorder(Color.black);
      jLabelMedlemstype.setBorder(blackline);
      jlabelNavnPåSvommer.setBorder(blackline);
      jlabelAlderPåSvommer.setBorder(blackline);
      jLabelEmailPåSvommer.setBorder(blackline);
      jlabelKontigentBetalt.setBorder(blackline);
      jLabelPassivSvømmer.setBorder(blackline);
      jLabelKøn.setBorder(blackline);
      jLabelSvømmeDisciplin.setBorder(blackline);

      jLabelMedlemstype.setOpaque(true);
      jlabelNavnPåSvommer.setOpaque(true);
      jlabelAlderPåSvommer.setOpaque(true);
      jLabelEmailPåSvommer.setOpaque(true);
      jlabelKontigentBetalt.setOpaque(true);
      jLabelPassivSvømmer.setOpaque(true);
      jLabelKøn.setOpaque(true);
      jLabelSvømmeDisciplin.setOpaque(true);
      jComboBoxKontingent.setOpaque(true);
      jComboBoxSvømmetype.setOpaque(true);
      jComboBoxdisciplin.setOpaque(true);

      jLabelMedlemstype.setBackground(Color.WHITE);
      jlabelNavnPåSvommer.setBackground(Color.WHITE);
      jlabelAlderPåSvommer.setBackground(Color.WHITE);
      jLabelEmailPåSvommer.setBackground(Color.WHITE);
      jlabelKontigentBetalt.setBackground(Color.WHITE);
      jLabelPassivSvømmer.setBackground(Color.WHITE);
      ;
      jLabelKøn.setBackground(Color.WHITE);
      jLabelSvømmeDisciplin.setBackground(Color.WHITE);
      jComboBoxKontingent.setBackground(Color.WHITE);
      jComboBoxSvømmetype.setBackground(Color.WHITE);
      jComboBoxdisciplin.setBackground(Color.WHITE);

    }
  };

  ActionListener alSøgMedlem = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelStoreOmråde.removeAll();
      jPanelStoreOmråde.repaint();
      jPanelStoreOmråde.setLayout(null);

      textFieldSøgMedlemsnummer = new JTextField();
      JLabel jLabelInputText = new JLabel("Input søgningord", JLabel.CENTER);
      JLabel jLabelSøgGennem = new JLabel("Søg efter ", JLabel.CENTER);
      buttonNavn = new JButton("Søg");
      textAreaFremsøgteMembers = new JTextArea();
      String[] muligheder = {"E-mail", "Navn", "Medlemsnummer"};
      jComboBoxsøgeKriterie = new JComboBox<>(muligheder);
      textFieldSøgUdFra = new JTextField();
      textFieldSøgUdFra.setHorizontalAlignment(JTextField.CENTER);

      jPanelStoreOmråde.add(jLabelInputText);
      jPanelStoreOmråde.add(jLabelSøgGennem);
      jPanelStoreOmråde.add(buttonNavn);
      jPanelStoreOmråde.add(textAreaFremsøgteMembers);
      jPanelStoreOmråde.add(jComboBoxsøgeKriterie);
      jPanelStoreOmråde.add(textFieldSøgUdFra);

      jLabelInputText.setBounds(100, 150, 170, 30);
      jLabelSøgGennem.setBounds(100, 100, 170, 30);
      jComboBoxsøgeKriterie.setBounds(300, 100, 170, 30);
      textFieldSøgUdFra.setBounds(300, 150, 170, 30);
      buttonNavn.setBounds(200, 200, 170, 30);
      textAreaFremsøgteMembers.setBounds(25, 250, 525, 340);

      Border blackline = BorderFactory.createLineBorder(Color.black);
      jLabelInputText.setBorder(blackline);
      jLabelSøgGennem.setBorder(blackline);
      jComboBoxsøgeKriterie.setBorder(blackline);
      textFieldSøgMedlemsnummer.setBorder(blackline);
      textFieldSøgUdFra.setBorder(blackline);

      DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
      listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
      jComboBoxsøgeKriterie.setRenderer(listRenderer);

      buttonNavn.addActionListener(alSøgMedlemFraKriterie);
    }
  };

  ActionListener alLavNytMedlem = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

      String medlemstype = jComboBoxSvømmetype.getItemAt(jComboBoxSvømmetype.getSelectedIndex()).toString();
      String kontingentBetalt = jComboBoxSvømmetype.getItemAt(jComboBoxSvømmetype.getSelectedIndex()).toString();
      String navn = jTextFieldNavn.getText();
      String email = jTextFieldEmail.getText();
      Integer alder = -1;
      String textalder = jTextFieldAlder.getText();
      try {
        alder = Integer.parseInt(textalder);
      } catch (NumberFormatException nfe) {
        ui.showErrorNumbersOnlyAge(frameFormand);
      }

      boolean betalt = false;
      if (kontingentBetalt.equals("Ja")) {
        betalt = true;
      }

      if (medlemstype.equals("Motion") && !navn.isEmpty() && alder > 0 && !email.isEmpty()) {

        String selectedAktivPassiv = getSelectedButtonText(buttonGroupPassivAktiv);

        boolean aktivPasiv = true;
        if (selectedAktivPassiv.equals("Passiv")) {
          aktivPasiv = false;
        }
        //TODO få bragt memberNumber i spil.
        try {
          NonCompetitor member = new NonCompetitor(navn, 0, alder, email, betalt, aktivPasiv);
          memberList.getAllNonCompetitors().add(member);
        } catch (NullPointerException nullPointerException) {
          System.out.println("FEJL!");
        }
      } else if (medlemstype.equals("Konkurrence") && !navn.isEmpty() && alder > 0 && !email.isEmpty()) {

        String selectedMK = getSelectedButtonText(buttonGroupKøn);
        SwimmingDisciplins swimmingDisciplins = SwimmingDisciplins.valueOf(jComboBoxdisciplin.getItemAt(jComboBoxdisciplin.getSelectedIndex()).toString().toUpperCase(Locale.ROOT));
        BestResultTraining bestResultTraining = new BestResultTraining(0, 0, 0, 0);
        BestResultCompetition bestResultCompetition = new BestResultCompetition(0, 0, 0, 0);

        try {
          Competitor member = new Competitor(navn, 0, alder, email, betalt, selectedMK, swimmingDisciplins, bestResultTraining, bestResultCompetition);
          memberList.getAllCompetitors().add(member);
        } catch (NullPointerException nullPointerException) {
          System.out.println("FEJL!");
        }

      } else {
        ui.showErrorCreatemember(frameFormand);
      }


    }
  };

  ActionListener alSøgMedlemFraKriterie = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      String valgAfsøgning = jComboBoxsøgeKriterie.getItemAt(jComboBoxsøgeKriterie.getSelectedIndex()).toString();
      String tekst = textFieldSøgUdFra.getText();
     textAreaFremsøgteMembers.selectAll();
     textAreaFremsøgteMembers.setText("");
//
//      jPanelStoreOmråde.revalidate();
//      jPanelStoreOmråde.repaint();

      if (!tekst.isEmpty()) {
        if (valgAfsøgning.equals("E-mail")) {
          ArrayList<Member> members = memberList.findSpecifikMembersByEmail(tekst);
          ui.printFoundMembersBySearch(members, textAreaFremsøgteMembers);

        } else if (valgAfsøgning.equals("Navn")) {
          ArrayList<Member> members = memberList.findSpecifikMembersByName(tekst);
          ui.printFoundMembersBySearch(members, textAreaFremsøgteMembers);

        } else {
          //medlemsnummer
          int nummer = 0;
          try {
            nummer = Integer.parseInt(tekst);
          } catch (NumberFormatException numberFormatException) {
            ui.showErrorNumbersOnlyMember(frameFormand);
          }
          Member member = memberList.findSpecifikMemberByMemberNumber(nummer);
          ui.printMemberFoundByMembernumber(member, textAreaFremsøgteMembers);
        }

      } else {
        ui.showErrorSearchwordMissing(frameFormand);
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

  public void run() {
    memberList.setAllNonCompetitors(fileHandle.loadNonCompetitors());
    memberList.setAllCompetitors(fileHandle.loadCompetitors());
  }
}
