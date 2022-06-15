package Member;

import java.util.Comparator;

public class Top5ListTrainingComparator implements Comparator<Competitor> {

  @Override
  public int compare(Competitor o1, Competitor o2) {
    Integer minutes1 = o1.getBestResultTraining().getPersonalBestTrainingTimeMinutes();
    Integer seconds1 = o1.getBestResultTraining().getPersonalBestTrainingTimeSeconds();
    Integer minSec1 = minutes1 * 100 + seconds1;

    Integer minutes2 = o2.getBestResultTraining().getPersonalBestTrainingTimeMinutes();
    Integer seconds2 = o2.getBestResultTraining().getPersonalBestTrainingTimeSeconds();
    Integer minSec2 = minutes2 * 100 + seconds2;

    return minSec1.compareTo(minSec2);
  }


}
