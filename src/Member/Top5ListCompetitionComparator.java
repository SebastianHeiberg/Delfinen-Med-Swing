package Member;

import java.util.Comparator;

public class Top5ListCompetitionComparator implements Comparator<Competitor> {

  @Override
  public int compare(Competitor o1, Competitor o2) {
    Integer minutes1 = o1.getBestResultCompetition().getPersonalBestCompetitionTimeMinutes();
    Integer seconds1 = o1.getBestResultCompetition().getPersonalBestCompetitionTimeSeconds();
    Integer minSec1 = minutes1 * 100 + seconds1;

    Integer minutes2 = o2.getBestResultCompetition().getPersonalBestCompetitionTimeMinutes();
    Integer seconds2 = o2.getBestResultCompetition().getPersonalBestCompetitionTimeSeconds();
    Integer minSec2 = minutes2 * 100 + seconds2;

    return minSec1.compareTo(minSec2);
  }

}
