package Member;

public class NonCompetitor extends Member {

  private boolean isActive;

  public NonCompetitor(String name, Integer memberNumber, Integer age, String email, boolean isMembershipPaid, boolean isActive) {
    super(name, memberNumber, age, email, isMembershipPaid);
    this.isActive = isActive;

  }

  public boolean isActive() {
    return isActive;
  }
}

