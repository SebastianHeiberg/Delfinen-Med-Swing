package Funktioner;

import Member.Member;
import Member.MemberList;
import Persistence.FileHandle;
import Member.NonCompetitor;
import UI.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Kasserer {
  JFrame frameKasserer;
  JButton buttonVisMedlemmerIRestance;
  JButton buttonBudget;
  JButton buttonÆndreRestance;
  JButton buttonExit;
  JPanel jPanelKnapper;
  JPanel jPanelStoreOmråde;
  JScrollPane jScrollPanevisMembers;
  JPanel jPanelÆndreRestance;
  JLabel jLabelVælgMedlem;
  JTextField jTextFieldIndtastMedlemsnummer;
  JButton jButtonbekræftMedlem;
  JLabel jLabelVisMedlem;
  JLabel jLabelRestancetitel;
  String[] comboBowValmuligheder = {"I Restance", "Ikke i Restance"};
  JComboBox jComboBoxvalg = new JComboBox(comboBowValmuligheder);
  JButton jButtonVælgFinal;
  private MemberList memberList = new MemberList();
  private FileHandle fileHandle = new FileHandle();
  UI ui = new UI();


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

  public void run() {
    memberList.setAllNonCompetitors(fileHandle.loadNonCompetitors());
    memberList.setAllCompetitors(fileHandle.loadCompetitors());
  }

  ActionListener alShowMembers = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelStoreOmråde.removeAll();
//      jPanelLargeArea.revalidate();
      jPanelStoreOmråde.repaint();
      jPanelStoreOmråde.setLayout(new BorderLayout());
      JTextArea textAreavisMedlemmerPanel = new JTextArea();
      jScrollPanevisMembers = new JScrollPane(textAreavisMedlemmerPanel);
      jPanelStoreOmråde.add(jScrollPanevisMembers);
      jScrollPanevisMembers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPanevisMembers.setEnabled(false);
      jScrollPanevisMembers.setSize(575, 605);

      textAreavisMedlemmerPanel.append(ui.printMembersInDebtHeader());

      for (Member member : memberList.getAllNonCompetitors()) {
        if (!member.isMembershipPaid()) {
          textAreavisMedlemmerPanel.append(ui.printMembersInDebt(member.getName(), calculateMembershipCost(member),member.getMemberNumber()));
        }
      }

      for (Member member : memberList.getAllCompetitors()) {
        if (!member.isMembershipPaid()) {
          textAreavisMedlemmerPanel.append(ui.printMembersInDebt(member.getName(), calculateMembershipCost(member),member.getMemberNumber()));
        }
      }
    }
  };

  ActionListener alExit = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      frameKasserer.dispose();
      fileHandle.saveAllNonCompetitorsToFile(memberList.getAllNonCompetitors());
      fileHandle.saveAllCompetitorsToFile(memberList.getAllCompetitors());
      new Funktioner.Login().run();
    }
  };

  ActionListener alÆndreRestance = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelStoreOmråde.removeAll();
      jPanelStoreOmråde.repaint();

      jPanelÆndreRestance = new JPanel(null);
      jPanelÆndreRestance.setSize(575, 605);
      jLabelVælgMedlem = new JLabel("1. Indtast medlemsNummer");
      jTextFieldIndtastMedlemsnummer = new JTextField("", 15);
      jButtonbekræftMedlem = new JButton("2. Vælg");
      jLabelVisMedlem = new JLabel("");
      jLabelRestancetitel = new JLabel("3. Ændre restance til");
      jComboBoxvalg = new JComboBox(comboBowValmuligheder);
      jButtonVælgFinal = new JButton("4. bekæft");

      //lav indhold
      jPanelStoreOmråde.add(jPanelÆndreRestance);
      jPanelÆndreRestance.add(jLabelVælgMedlem);
      jPanelÆndreRestance.add(jTextFieldIndtastMedlemsnummer);
      jPanelÆndreRestance.add(jButtonbekræftMedlem);
      jPanelÆndreRestance.add(jLabelVisMedlem);
      jPanelÆndreRestance.add(jLabelRestancetitel);
      jPanelÆndreRestance.add(jComboBoxvalg);
      jPanelÆndreRestance.add(jButtonVælgFinal);

      jLabelVælgMedlem.setBounds(50, 150, 170, 30);
      jTextFieldIndtastMedlemsnummer.setBounds(240, 150, 170, 30);
      jButtonbekræftMedlem.setBounds(430, 150, 100, 30);
      jLabelVisMedlem.setBounds(50, 200, 480, 30);
      jLabelRestancetitel.setBounds(50, 250, 170, 30);
      jComboBoxvalg.setBounds(240, 250, 170, 30);
      jButtonVælgFinal.setBounds(430, 250, 100, 30);

      jLabelVælgMedlem.setOpaque(true);
      jTextFieldIndtastMedlemsnummer.setOpaque(true);
      jLabelVisMedlem.setOpaque(true);
      jLabelRestancetitel.setOpaque(true);
      jComboBoxvalg.setOpaque(true);
      jLabelVælgMedlem.setBackground(Color.WHITE);
      jTextFieldIndtastMedlemsnummer.setBackground(Color.WHITE);
      jLabelVisMedlem.setBackground(Color.WHITE);
      jLabelRestancetitel.setBackground(Color.WHITE);
      jComboBoxvalg.setBackground(Color.WHITE);

      DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
      listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
      jComboBoxvalg.setRenderer(listRenderer);


      Border blackline = BorderFactory.createLineBorder(Color.black);
      jLabelVælgMedlem.setBorder(blackline);
      jTextFieldIndtastMedlemsnummer.setBorder(blackline);
      jLabelVisMedlem.setBorder(blackline);
      jLabelRestancetitel.setBorder(blackline);

      jTextFieldIndtastMedlemsnummer.setHorizontalAlignment(JTextField.CENTER);
      jLabelVælgMedlem.setHorizontalAlignment(JLabel.CENTER);
      jLabelRestancetitel.setHorizontalAlignment(JLabel.CENTER);

      jButtonbekræftMedlem.addActionListener(alVælgMember);
      jButtonVælgFinal.addActionListener(alBekræftValg);

      jPanelÆndreRestance.repaint();
    }
  };

  ActionListener alShowBudget = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      jPanelStoreOmråde.removeAll();
//      jPanelLargeArea.revalidate();
      jPanelStoreOmråde.repaint();
      jPanelStoreOmråde.setLayout(new BorderLayout());
      JTextArea textAreavisBudget = new JTextArea();
      jScrollPanevisMembers = new JScrollPane(textAreavisBudget);
      jPanelStoreOmråde.add(jScrollPanevisMembers);
      jScrollPanevisMembers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPanevisMembers.setEnabled(false);
      jScrollPanevisMembers.setSize(575, 605);


      double expectedSum = 0;
      int antalmembers = 0;
      for (Member member : memberList.getAllNonCompetitors()) {
        expectedSum += calculateMembershipCost(member);
        antalmembers ++;
      }

      for (Member member : memberList.getAllCompetitors()) {
        expectedSum += calculateMembershipCost(member);
        antalmembers ++;
      }

      textAreavisBudget.append(String.format(ui.printExpectedAnnualSum(expectedSum, antalmembers)));
    }
  };

  ActionListener alVælgMember = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    Member member; {
    }
      try {
      member = memberList.findSpecifikMemberByMemberNumber(Integer.parseInt(jTextFieldIndtastMedlemsnummer.getText()));
    } catch (NumberFormatException nfe){
      member = null;
      ui.showErrorfindMember(frameKasserer);
    }
    if (member == null){
      ui.showErrorMemberNull(frameKasserer);
    } else {
      jLabelVisMedlem.setText(ui.printMemberKassereÆndreRestance(member));
    }
    }
  };

  ActionListener alBekræftValg = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      Member member;

      try {
        member = memberList.findSpecifikMemberByMemberNumber(Integer.parseInt(jTextFieldIndtastMedlemsnummer.getText()));

        if (member == null){
          ui.showErrorMemberNull(frameKasserer);
        } else {

          String nyStatus = jComboBoxvalg.getItemAt(jComboBoxvalg.getSelectedIndex()).toString();

          if (nyStatus.equals("I Restance")) {
            member.setMembershipPaid(true);
          } else {
            member.setMembershipPaid(false);
          }
          jLabelVisMedlem.setText(ui.printMemberKassereÆndreRestance(member));
        }
      } catch (NumberFormatException nfe){
        ui.showErrorfindMember(frameKasserer);
      }
    }
  };

  protected double calculateMembershipCost(Member member) {
    double adultMembershipCost = 1600;
    double seniorDiscount = 0.75;
    int age = member.getAge();

    if (member instanceof NonCompetitor) {
      if (!((NonCompetitor) member).isActive()) {
        return 500;
      }
    }

    if (age < 18) {
      return 1000;
    } else if (age < 65) {
      return adultMembershipCost;
    } else {
      return adultMembershipCost * seniorDiscount;
    }
  }

}
