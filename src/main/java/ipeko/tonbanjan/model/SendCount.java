package ipeko.tonbanjan.model;

public class SendCount {
  public int questionId;
  public int answerId;
  public String a_content;
  public int amount;

  public SendCount(int que, int ans, String acon, int amo) {
    this.questionId = que;
    this.answerId = ans;
    this.a_content = acon;
    this.amount = amo;
  }

  public int getquestionId() {
    return questionId;
  }

  public void setquestionId(int questionId) {
    this.questionId = questionId;
  }

  public int getanswerId() {
    return answerId;
  }

  public void setanswerId(int answerId) {
    this.answerId = answerId;
  }

  public String geta_content() {
    return a_content;
  }

  public void seta_content(String a_content){
    this.a_content = a_content;
  }

  public int getamount() {
    return amount;
  }

  public void setamount(int amount) {
    this.amount = amount;
  }
}
