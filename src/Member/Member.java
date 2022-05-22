package Member;

public abstract class Member {

  protected String name;
  protected Integer memberNumber;
  protected Integer age; //TODO fødselsdato eller fødselsår? reverse til format 850115?
  protected String email;
  protected boolean isMembershipPaid;


  public Member (String name, Integer memberNumber, Integer age, String email, boolean isMembershipPaid){
    this.name = name;
    this.memberNumber = memberNumber;
    this.age = age;
    this.email = email;
    this.isMembershipPaid = isMembershipPaid;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public Integer getMemberNumber() {
    return memberNumber;
  }

  public Integer getAge() {
    return age;
  }

  public boolean isMembershipPaid() {
    return isMembershipPaid;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setMembershipPaid(boolean membershipPaid) {
    isMembershipPaid = membershipPaid;
  }
}
