package UI;

import Member.Member;

import javax.swing.*;

public class UI {


  public void showErrorLogin (JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Systemet kan ikke tilgås med det indtastede.");
  }

  public void showErrorfindMember (JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Kun tal i medlemsoplysninger.");
  }

  public void showErrorMemberNull (JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Kan ikke finde medlem.");
  }

  public String printMembersInDebt(String name, double debt, int membernumber) {
    return "\nMedlemsnummer: "+ membernumber + ", Navn: " + name + " , Restance: " + debt;
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

}
