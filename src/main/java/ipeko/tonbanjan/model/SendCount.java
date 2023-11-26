package ipeko.tonbanjan.model;

public class SendCount {
  public int questionId;
  public int answerId;
  public int amount;

  public SendCount(int que, int ans, int amo) {
    this.questionId = que;
    this.answerId = ans;
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

  public int getamount() {
    return amount;
  }

  public void setamount(int amount) {
    this.amount = amount;
  }
}
