package Member;

public class BestResultCompetition {

  private Integer personalBestCompetitionTimeMinutes;
  private Integer personalBestCompetitionTimeSeconds;
  private Integer personalBestCompetitionMonth;
  private Integer personalBestCompetitionYear;

  public BestResultCompetition(Integer personalBestCompetitionTimeMinutes, Integer personalBestCompetitionTimeSeconds, Integer personalBestCompetitionMonth, Integer personalBestCompetitionYear) {
    this.personalBestCompetitionTimeMinutes = personalBestCompetitionTimeMinutes;
    this.personalBestCompetitionTimeSeconds = personalBestCompetitionTimeSeconds;
    this.personalBestCompetitionMonth = personalBestCompetitionMonth;
    this.personalBestCompetitionYear = personalBestCompetitionYear;
  }

  public Integer getPersonalBestCompetitionTimeMinutes() {
    return personalBestCompetitionTimeMinutes;
  }

  public Integer getPersonalBestCompetitionTimeSeconds() {
    return personalBestCompetitionTimeSeconds;
  }

  public Integer getPersonalBestCompetitionMonth() {
    return personalBestCompetitionMonth;
  }

  public Integer getPersonalBestCompetitionYear() {
    return personalBestCompetitionYear;
  }

  public void setPersonalBestCompetitionTimeMinutes(Integer personalBestCompetitionTimeMinutes) {
    this.personalBestCompetitionTimeMinutes = personalBestCompetitionTimeMinutes;
  }

  public void setPersonalBestCompetitionTimeSeconds(Integer personalBestCompetitionTimeSeconds) {
    this.personalBestCompetitionTimeSeconds = personalBestCompetitionTimeSeconds;
  }

  public void setPersonalBestCompetitionMonth(Integer personalBestCompetitionMonth) {
    this.personalBestCompetitionMonth = personalBestCompetitionMonth;
  }

  public void setPersonalBestCompetitionYear(Integer personalBestCompetitionYear) {
    this.personalBestCompetitionYear = personalBestCompetitionYear;
  }
}
