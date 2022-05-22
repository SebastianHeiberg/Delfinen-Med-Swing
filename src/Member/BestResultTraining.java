package Member;

public class BestResultTraining {

  private Integer personalBestTrainingTimeMinutes;
  private Integer personalBestTrainingTimeSeconds;
  private Integer personalBestTrainingMonth;
  private Integer personalBestTrainingYear;

  public BestResultTraining(Integer personalBestTrainingTimeMinutes, Integer personalBestTrainingTimeSeconds, Integer personalBestTrainingMonth, Integer personalBestTrainingYear) {
    this.personalBestTrainingTimeMinutes = personalBestTrainingTimeMinutes;
    this.personalBestTrainingTimeSeconds = personalBestTrainingTimeSeconds;
    this.personalBestTrainingMonth = personalBestTrainingMonth;
    this.personalBestTrainingYear = personalBestTrainingYear;
  }

  public Integer getPersonalBestTrainingTimeMinutes() {
    return personalBestTrainingTimeMinutes;
  }

  public Integer getPersonalBestTrainingTimeSeconds() {
    return personalBestTrainingTimeSeconds;
  }

  public Integer getPersonalBestTrainingMonth() {
    return personalBestTrainingMonth;
  }

  public Integer getPersonalBestTrainingYear() {
    return personalBestTrainingYear;
  }

  public void setPersonalBestTrainingTimeMinutes(Integer personalBestTrainingTimeMinutes) {
    this.personalBestTrainingTimeMinutes = personalBestTrainingTimeMinutes;
  }

  public void setPersonalBestTrainingTimeSeconds(Integer personalBestTrainingTimeSeconds) {
    this.personalBestTrainingTimeSeconds = personalBestTrainingTimeSeconds;
  }

  public void setPersonalBestTrainingMonth(Integer personalBestTrainingMonth) {
    this.personalBestTrainingMonth = personalBestTrainingMonth;
  }

  public void setPersonalBestTrainingYear(Integer personalBestTrainingYear) {
    this.personalBestTrainingYear = personalBestTrainingYear;
  }
}
