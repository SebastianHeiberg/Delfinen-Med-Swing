package UI;

import javax.swing.*;

public class UI {


  public void showErrorLogin (JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Systemet kan ikke tilgås med det indtastede.");
  }

  public String printMembersInDebt(String name, double debt, int membernumber) {
    return "\nMedlemsnummer: "+ membernumber + ", Navn: " + name + " , Restance: " + debt;
  }

  public String printMembersInDebtHeader() {
    return "\nMedlemmer med restance:\n";
  }

  public String printExpectedAnnualSum(double expectedSum, int members) {
        return "\nSamlet antal medlemmer: " + members +
            "\nForventet årligt indkomst: " + expectedSum + " kr.";
  }

}
