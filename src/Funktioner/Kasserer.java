package Funktioner;

import Member.MemberList;
import Persistence.FileHandle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Kasserer {
  JFrame frameKasserer;
  JButton buttonVisMedlemmerIRestance;
  JButton buttonBudget;
  JButton buttonÆndreRestance;
  JButton buttonExit;
  JPanel jPanelKnapper;
  JPanel jPanelStoreOmråde;
  JScrollPane jScrollPanevisMembers;
  private MemberList memberList = new MemberList();
  private FileHandle fileHandle = new FileHandle();


  public Kasserer() {
    frameKasserer = new JFrame();
    frameKasserer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameKasserer.setVisible(true);
    frameKasserer.setSize(800, 650);
    frameKasserer.setLocationRelativeTo(null);
    frameKasserer.setLayout(null);
    buttonBudget = new JButton("Vis budget");
    buttonVisMedlemmerIRestance = new JButton("Vis medlemmer i restance");
    buttonÆndreRestance = new JButton("Ændre restance");
    buttonExit = new JButton("Gem og til hovedmenu");

    //panel med knapperne
    jPanelKnapper = new JPanel();
    jPanelKnapper.setLayout(null);
    jPanelKnapper.setBackground(Color.BLUE);
    jPanelKnapper.setSize(800, 210);
    frameKasserer.add(jPanelKnapper);
    jPanelKnapper.setBounds(0, 0, 210, 800);

    jPanelKnapper.add(buttonBudget);
    jPanelKnapper.add(buttonVisMedlemmerIRestance);
    jPanelKnapper.add(buttonÆndreRestance);
    jPanelKnapper.add(buttonExit);

    buttonBudget.setBounds(15, 20, 180, 60);
    buttonVisMedlemmerIRestance.setBounds(15, 100, 180, 60);
    buttonÆndreRestance.setBounds(15, 180, 180, 60);
    buttonExit.setBounds(15, 260, 180, 60);

    //Det store område
    jPanelStoreOmråde = new JPanel(null);
    jPanelStoreOmråde.setSize(590, 800);
    frameKasserer.add(jPanelStoreOmråde);
    jPanelStoreOmråde.setBounds(210, 5, 590, 800);


    //knappernes funktion
    buttonVisMedlemmerIRestance.addActionListener(alShowMembers);
    buttonExit.addActionListener(alExit);
    buttonBudget.addActionListener(alShowBudget);
    buttonÆndreRestance.addActionListener(alÆndreRestance);


  }

  ActionListener alShowMembers = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelStoreOmråde.removeAll();
//      jPanelStoreOmråde.revalidate();
      jPanelStoreOmråde.repaint();
      jPanelStoreOmråde.setLayout(new BorderLayout());
      JTextArea textAreavisMedlemmerPanel = new JTextArea();
      jScrollPanevisMembers = new JScrollPane(textAreavisMedlemmerPanel);
      jPanelStoreOmråde.add(jScrollPanevisMembers);
      jScrollPanevisMembers.setSize(575, 605);
      jScrollPanevisMembers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPanevisMembers.setEnabled(false);


      //TODO Nu mangler der kun nogle members!
    }
  };

  ActionListener alExit = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      //TODO noget med at gemme oplysningerne!
      frameKasserer.dispose();
//      fileHandle.saveAllNonCompetitorsToFile(memberList.getAllNonCompetitors());
//      fileHandle.saveAllCompetitorsToFile(memberList.getAllCompetitors());
      new Funktioner.Login().run();
    }
  };

  ActionListener alÆndreRestance = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelStoreOmråde.removeAll();
      jPanelStoreOmråde.repaint();

      JPanel jPanelÆndreRestance = new JPanel(null);
      jPanelÆndreRestance.setSize(575, 605);
      JLabel jLabelVælgMedlem = new JLabel("1. Indtast medlemsNummer");
      JTextField jTextFieldIndtastMedlemsnummer = new JTextField("", 15);
      JButton jButtonbekræftMedlem = new JButton("2. Vælg");
      JLabel jLabelVisMedlem = new JLabel("");
      JLabel jLabelRestancetitel = new JLabel("3. Ændre restance til");
      String[] muligheder = {"I Restance", "Ikke i Restance"};
      JComboBox valg = new JComboBox(muligheder);
      JButton jButtonVælgFinal = new JButton("4. bekæft");
      jPanelÆndreRestance.setBackground(Color.WHITE);

      //lav indhold
      jPanelStoreOmråde.add(jPanelÆndreRestance);
      jPanelÆndreRestance.add(jLabelVælgMedlem);
      jPanelÆndreRestance.add(jTextFieldIndtastMedlemsnummer);
      jPanelÆndreRestance.add(jButtonbekræftMedlem);
      jPanelÆndreRestance.add(jLabelVisMedlem);
      jPanelÆndreRestance.add(jLabelRestancetitel);
      jPanelÆndreRestance.add(valg);
      jPanelÆndreRestance.add(jButtonVælgFinal);

      jLabelVælgMedlem.setBounds(50, 150, 170, 30);
      jTextFieldIndtastMedlemsnummer.setBounds(240, 150, 170, 30);
      jButtonbekræftMedlem.setBounds(430, 150, 100, 30);
      jLabelVisMedlem.setBounds(50, 200, 480, 30);
      jLabelRestancetitel.setBounds(50, 250, 170, 30);
      valg.setBounds(240, 250, 170, 30);
      jButtonVælgFinal.setBounds(430, 250, 100, 30);


      Border blackline = BorderFactory.createLineBorder(Color.black);
      jLabelVælgMedlem.setBorder(blackline);
      jTextFieldIndtastMedlemsnummer.setBorder(blackline);
      jLabelVisMedlem.setBorder(blackline);
      jLabelRestancetitel.setBorder(blackline);

      jTextFieldIndtastMedlemsnummer.setHorizontalAlignment(JTextField.CENTER);
      jLabelVælgMedlem.setHorizontalAlignment(JLabel.CENTER);
      jLabelRestancetitel.setHorizontalAlignment(JLabel.CENTER);

      jPanelÆndreRestance.repaint();
    }
  };

  ActionListener alShowBudget = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelStoreOmråde.removeAll();
//      jPanelStoreOmråde.revalidate();
      jPanelStoreOmråde.repaint();
      jPanelStoreOmråde.setLayout(new BorderLayout());
      JTextArea textAreavisBudget = new JTextArea();
      jScrollPanevisMembers = new JScrollPane(textAreavisBudget);
      jPanelStoreOmråde.add(jScrollPanevisMembers);
      jScrollPanevisMembers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPanevisMembers.setEnabled(false);
      jScrollPanevisMembers.setSize(575, 605);

      //TODO indsætte budgettet
      textAreavisBudget.append(String.format("""
                    
           Klubbens økonomi
           Antal juniorer  : 30
           Antal seniorer : 45
           Antal passive  : 12
           Antal medlemmer: 82

           Samlet indtægt : 50000
          """));


    }
  };

  public MemberList getMemberList() {
    return memberList;
  }
}
