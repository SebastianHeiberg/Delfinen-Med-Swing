package UI;

import Member.Member;
import Member.NonCompetitor;
import Member.Competitor;

import javax.swing.*;
import java.util.ArrayList;


public class UI {


  public void showErrorLogin(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Systemet kan ikke tilgås med det indtastede.");
  }

  public void showErrorfindMember(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Kun tal i medlemsoplysninger.");
  }

  public void showErrorMemberNull(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Kan ikke finde medlem.");
  }

  public void showErrorNumbersOnly(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Kun tal i aldersfeltet.");
  }

  public void showErrorFindMemberFirst(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Indtast medlemsnummer og vælg medlem først.");
  }

  public String printMembersInDebt(String name, double debt, int membernumber) {
    return "\nMedlemsnummer: " + membernumber + ", Navn: " + name + " , Restance: " + debt;
  }

  public String printMembersInDebtHeader() {
    return "\nMedlemmer med restance:\n";
  }

  public String printMemberKassereÆndreRestance(Member member) {
    return "Navn: " + member.getName() + " , Email: " + member.getEmail() + " , I restance: " + member.isMembershipPaid();
  }

  public String printExpectedAnnualSum(double expectedSum, int members) {
    return "\nSamlet antal medlemmer: " + members +
        "\nForventet årligt indkomst: " + expectedSum + " kr.";
  }

  public void printAllMembers(ArrayList<NonCompetitor> memberListNonCompetitor, ArrayList<Competitor> memberListCompetitor, JTextArea textAreavisMedlemmerPanel) {
    textAreavisMedlemmerPanel.append("\nKlubbens ikke-konkurrencemedlemmer:\n");
    for (Member member : memberListNonCompetitor) {
      textAreavisMedlemmerPanel.append(printMember(member));
    }
    textAreavisMedlemmerPanel.append("\nKlubben konkurrencemedlemmer:\n");
    for (Competitor competitor : memberListCompetitor) {
      textAreavisMedlemmerPanel.append(printMember(competitor));
    }
  }

  public String printMember(Member member) {

    if (member instanceof Competitor competitor) {
      return String.format("""
          Medlemsnummer: %d  Navn: %s   Køn: %s  Email: %s  Alder:  %d år  Restance: %s  Svømmedisciplin: %s
          """, member.getMemberNumber(), member.getName(), competitor.getGender(), member.getEmail(), member.getAge(), member.isMembershipPaid(), competitor.getSwimmingDisciplin());
    } else {
      return String.format("""
          Medlemsnummer: %d  Navn: %s   Email: %s  Alder:  %d år  restance: %s
          """, member.getMemberNumber(), member.getName(), member.getEmail(), member.getAge(), member.isMembershipPaid());
    }
  }
}
