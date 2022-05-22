package Funktioner;

import Member.MemberList;
import Persistence.FileHandle;
import UI.UI;

import javax.swing.*;
import javax.swing.border.Border;
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
  JPanel jPanelStoreOmråde;
  JScrollPane jScrollPanevisMembers;
  JLabel jLabelMedlemstype;
  JLabel jlabelNavnPåSvommer;
  JLabel jLabelEmailPåSvommer;
  JLabel jlabelAlderPåSvommer;
  JLabel jlabelKontigentBetalt;
  JLabel jLabelKunForMotionister;
  JLabel jLabelPassivSvømmer;
  JLabel jLabelKunForKonkurrencesvommere;
  JLabel jLabelKøn;
  JLabel jLabelSvømmeDisciplin;
  String [] typer = {"Motion","Konkurrence"};
  JComboBox jComboBoxSvømmetype;
  JTextField jTextFieldNavn;
  JTextField jTextFieldEmail;
  JTextField jTextFieldAlder;
  String [] kontingentBetalt = {"Ja","Nej"};
  JComboBox jComboBoxKontingent;
  ButtonGroup buttonGroupPassivAktiv;
  JRadioButton jradioButtonAktiv;
  JRadioButton jradioButtonPassiv;
  ButtonGroup buttonGroupKøn;
  JRadioButton jRadioButtonMand;
  JRadioButton jRadioButtonKvinde;
  JComboBox jComboBoxdisciplin;
  String [] svømmetyper = {"Crawl","Bryst","Rygcrawl","Butterfly"};
  JButton jButtonOpretNytMedlem;








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
    buttonSletMedlem.setBounds(15,340,180,60);
    buttonExit.setBounds(15,420,180,60);

    //knappernes funktion
    buttonExit.addActionListener(alExitandSave);
    buttonVisMedlemmer.addActionListener(alShowAllMembers);
    buttonTilføjMedlem.addActionListener(alNytMedlem);

    //Det store område
    jPanelStoreOmråde = new JPanel(null);
    jPanelStoreOmråde.setSize(590, 800);
    frameFormand.add(jPanelStoreOmråde);
    jPanelStoreOmråde.setBounds(210, 5, 590, 800);


  }

  ActionListener alExitandSave = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      frameFormand.dispose();
      fileHandle.saveAllNonCompetitorsToFile(memberList.getAllNonCompetitors());
      fileHandle.saveAllCompetitorsToFile(memberList.getAllCompetitors());
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

      ui.printAllMembers(memberList.getAllNonCompetitors(),memberList.getAllCompetitors(),textAreavisMedlemmerPanel);

    }
  };

  ActionListener alNytMedlem = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelStoreOmråde.removeAll();
      jPanelStoreOmråde.repaint();
      jPanelStoreOmråde.setLayout(null);

      jLabelMedlemstype = new JLabel("Medlemstype",JLabel.CENTER);
      jlabelNavnPåSvommer = new JLabel("Navn på svømmer",JLabel.CENTER);
      jLabelEmailPåSvommer = new JLabel("E-mail på svømmer",JLabel.CENTER);
      jlabelAlderPåSvommer = new JLabel("Alder på svømmer",JLabel.CENTER);
      jlabelKontigentBetalt = new JLabel("Er kontingent betalt",JLabel.CENTER);
      jLabelKunForMotionister = new JLabel("Kun relevant for motionister");
      jLabelPassivSvømmer = new JLabel("Passiv eller aktiv svømmer",JLabel.CENTER);
      jLabelKunForKonkurrencesvommere = new JLabel("Kun relevant for konkurrencesvømmere");
      jLabelKøn = new JLabel("Køn",JLabel.CENTER);
      jLabelSvømmeDisciplin = new JLabel("Svømmedisciplin",JLabel.CENTER);
      jComboBoxSvømmetype = new JComboBox<>(typer);
      jTextFieldNavn = new JTextField("",JTextField.CENTER);
      jTextFieldEmail = new JTextField("",JTextField.CENTER);
      jTextFieldAlder = new JTextField("",JTextField.CENTER);
      jComboBoxKontingent = new JComboBox<>(kontingentBetalt);
      jradioButtonAktiv = new JRadioButton("aktiv");
      jradioButtonPassiv = new JRadioButton("passiv");
      jRadioButtonKvinde = new JRadioButton("Kvinde");
      jRadioButtonMand = new JRadioButton("Mand");
      buttonGroupPassivAktiv = new ButtonGroup();
      buttonGroupPassivAktiv.add(jradioButtonPassiv);
      buttonGroupPassivAktiv.add(jradioButtonAktiv);
      buttonGroupKøn = new ButtonGroup();
      jComboBoxdisciplin = new JComboBox<>(svømmetyper);
      jButtonOpretNytMedlem = new JButton("Opret nyt medlem");


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


      jLabelMedlemstype.setBounds(50,50,170,30);
      jlabelNavnPåSvommer.setBounds(50,100,170,30);
      jlabelAlderPåSvommer.setBounds(50,150,170,30);
      jLabelEmailPåSvommer.setBounds(50,200,170,30);
      jlabelKontigentBetalt.setBounds(50,250,170,30);
      jLabelKunForMotionister.setBounds(150,300,200,30);
      jLabelPassivSvømmer.setBounds(50,350,170,30);
      jLabelKunForKonkurrencesvommere.setBounds(125,400,250,30);
      jLabelKøn.setBounds(50,450,170,30);
      jLabelSvømmeDisciplin.setBounds(50,500,170,30);

      jComboBoxSvømmetype.setBounds(250,50,170,30);
      jTextFieldNavn.setBounds(250,100,170,30);
      jTextFieldEmail.setBounds(250,150,170,30);
      jTextFieldAlder.setBounds(250,200,170,30);
      jComboBoxKontingent.setBounds(250,250,170,30);
      jradioButtonPassiv.setBounds(250,350,100,30);
      jradioButtonAktiv.setBounds(350,350,100,30);
      jRadioButtonMand.setBounds(250,450,100,30);
      jRadioButtonKvinde.setBounds(350,450,100,30);
      jComboBoxdisciplin.setBounds(250,500,170,30);

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
    }
  };

  public void run() {
    memberList.setAllNonCompetitors(fileHandle.loadNonCompetitors());
    memberList.setAllCompetitors(fileHandle.loadCompetitors());
  }
}
