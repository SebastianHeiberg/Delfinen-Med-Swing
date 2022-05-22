package Member;

public class Competitor extends Member {

  private String gender; //TODO: Det findes kun 2 konkurrenceklasser: M / F. Fejlmelding
  private SwimmingDisciplins swimmingDisciplin;
  private BestResultCompetition bestResultCompetition;
  private BestResultTraining bestResultTraining;


/*  public Competitor(String name, Integer memberNumber, Integer age, String email, boolean isMembershipPaid, String gender, SwimmingDisciplins swimmingDisciplin,
                    Integer personalBestTrainingTimeMinutes, Integer personalBestTrainingTimeSeconds, Integer personalBestTrainingMonth, Integer personalBestTrainingYear,
                    Integer personalBestCompetitionTimeMinutes, Integer personalBestCompetitionTimeSeconds, Integer personalBestMonthCompetition, Integer personalBestYearCompetition) {
    super(name, memberNumber, age, email, isMembershipPaid);
    this.gender = gender;
    this.swimmingDisciplin = swimmingDisciplin;
    this.personalBestTrainingTimeMinutes = personalBestTrainingTimeMinutes;
    this.personalBestTrainingTimeSeconds = personalBestTrainingTimeSeconds;
    this.personalBestTrainingMonth = personalBestTrainingMonth;
    this.personalBestTrainingYear = personalBestTrainingYear;
    this.personalBestCompetitionTimeMinutes = personalBestCompetitionTimeMinutes;
    this.personalBestCompetitionTimeSeconds = personalBestCompetitionTimeSeconds;
    this.personalBestCompetitionMonth = personalBestMonthCompetition;
    this.personalBestCompetitionYear = personalBestYearCompetition;
  }*/
//
//  public Competitor(String name, Integer memberNumber, Integer age, String email, boolean isMembershipPaid, String gender, SwimmingDisciplins swimmingDisciplin){
//    super(name, memberNumber, age, email, isMembershipPaid);
//    this.gender = gender;
//    this.swimmingDisciplin = swimmingDisciplin;
//  }

  public Competitor(String name, Integer memberNumber, Integer age, String email, boolean isMembershipPaid, String gender, SwimmingDisciplins swimmingDisciplin, BestResultTraining bestResultTraining, BestResultCompetition bestResultCompetition){
    super(name, memberNumber, age, email, isMembershipPaid);
    this.gender = gender;
    this.swimmingDisciplin = swimmingDisciplin;
    this.bestResultCompetition = bestResultCompetition;
    this.bestResultTraining = bestResultTraining;
  }

  public BestResultCompetition getBestResultCompetition() {
    return bestResultCompetition;
  }

  public BestResultTraining getBestResultTraining() {
    return bestResultTraining;
  }

  public SwimmingDisciplins getSwimmingDisciplin() {
    return swimmingDisciplin;
  }

  public void setSwimmingDisciplin(SwimmingDisciplins swimmingDisciplin) {
    this.swimmingDisciplin = swimmingDisciplin;
  }


  public String getGender() {
    return gender;
  }
}