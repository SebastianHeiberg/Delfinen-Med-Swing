package UI;

import Member.Member;
import Member.Competitor;
import Member.NonCompetitor;

import javax.swing.*;
import java.util.ArrayList;


public class UI {


  public void showErrorLogin(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Systemet kan ikke tilgås med det indtastede. Brug bruger: Test - adgang: 1234");
  }

  public void showErrorCreatemember(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Fejl i det indtastede.");

  }

  public void showErrorfindMember(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Kun tal i medlemsoplysninger.");
  }

  public void showErrorMemberNull(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Kan ikke finde medlem.");
  }

  public void showErrorNumbersOnlyAge(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Kun tal i aldersfeltet.");
  }

  public void showErrorNumbersOnlyMember(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Kun tal til medlemsnummeret.");
  }

  public void showErrorSearchwordMissing(JFrame frame) {
    JOptionPane.showMessageDialog(frame, "Der skal være noget i søgefeltet.");
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
          Medlemsnummer: %d  Navn: %s   Køn: %s  Email: %s  Alder:  %d år
          """, member.getMemberNumber(), member.getName(), competitor.getGender(), member.getEmail(), member.getAge(), member.isMembershipPaid(), competitor.getSwimmingDisciplin());
    } else {
      return String.format("""
          Medlemsnummer: %d  Navn: %s   Email: %s  Alder:  %d år
          """, member.getMemberNumber(), member.getName(), member.getEmail(), member.getAge(), member.isMembershipPaid());
    }
  }

  public void printFoundMembersBySearch(ArrayList<Member> members, JTextArea jTextArea) {

    if (members.isEmpty()) {
      jTextArea.append("Ingen medlemmer fundet ud fra søgekriterier.");
    } else {
      jTextArea.append("Fundne medlemmer ud fra søgekriterier:\n");

      for (Member member : members) {

        if (member instanceof Competitor competitor) {
          jTextArea.append(String.format("""
              Navn: %s  Medlemsnummer: %d Køn: %s  Email: %s  Alder:  %d år  restance: %s
              """, competitor.getName(), competitor.getMemberNumber(), competitor.getGender(), competitor.getEmail(), competitor.getAge(), competitor.isMembershipPaid()));
        } else {
          jTextArea.append(String.format("""
              Navn: %s  Medlemsnummer: %d  Email: %s  Alder:  %d år  restance: %s
              """, member.getName(), member.getMemberNumber(), member.getEmail(), member.getAge(), member.isMembershipPaid()));
        }
      }
    }
  }

  public void printMemberFoundByMembernumber(Member member, JTextArea jTextArea) {
    if (member == null) {
      jTextArea.append("Intet medlemmer fundet ud fra søgekriteriet.\n");
    } else {
      jTextArea.append("fundet medlem ud fra søgekriteriet:\n");

      if (member instanceof Competitor competitor) {
        jTextArea.append(String.format("""
            Navn: %s  Medlemsnummer: %d Køn: %s  Email: %s  Alder:  %d år  Restance: %s  Svømmedisciplin: %s 
            """, competitor.getName(), competitor.getMemberNumber(), competitor.getGender(), competitor.getEmail(), competitor.getAge(), competitor.isMembershipPaid(), competitor.getSwimmingDisciplin()));
      } else {
        jTextArea.append(String.format("""
            Navn: %s  Medlemsnummer: %d  Email: %s  Alder:  %d år  Restance: %s
            """, member.getName(), member.getMemberNumber(), member.getEmail(), member.getAge(), member.isMembershipPaid()));
      }
    }
  }

  public String printSlettet() {
    return "Medlem Slettet";
  }

  public void printTop5List(ArrayList<Competitor> allTrainingTimes, ArrayList<Competitor> allCompetitionTimes, JTextArea jTextArea) {
    jTextArea.append("\nTop 5 træningstider:\n");
    for (int i = 0; i < allTrainingTimes.size(); i++) {
      Competitor competitor = allTrainingTimes.get(i);
      Integer minutes = competitor.getBestResultTraining().getPersonalBestTrainingTimeMinutes();
      Integer seconds = competitor.getBestResultTraining().getPersonalBestTrainingTimeSeconds();
      Integer month = competitor.getBestResultTraining().getPersonalBestTrainingMonth();
      Integer year = competitor.getBestResultTraining().getPersonalBestTrainingYear();

      jTextArea.append(String.format("""
          %d. Medlemsnummer: %d, %s, Tid: %d:%d, Dato:%d/%d
          """, i + 1, competitor.getMemberNumber(), competitor.getName(), minutes, seconds, month, year));
    }

    jTextArea.append("\nTop 5 konkurrencetider:\n");
    for (int i = 0; i < allCompetitionTimes.size(); i++) {
      Competitor competitor = allCompetitionTimes.get(i);
      Integer minutes = competitor.getBestResultCompetition().getPersonalBestCompetitionTimeMinutes();
      Integer seconds = competitor.getBestResultCompetition().getPersonalBestCompetitionTimeSeconds();
      Integer month = competitor.getBestResultCompetition().getPersonalBestCompetitionMonth();
      Integer year = competitor.getBestResultCompetition().getPersonalBestCompetitionYear();

      jTextArea.append(String.format("""
          %d. Medlemsnummer: %d, %s, Tid: %d:%d, Dato:%d/%d
          """, i + 1, competitor.getMemberNumber(), competitor.getName(), minutes, seconds, month, year));
    }

  }
}
