package ipeko.tonbanjan.model;

public class Send {
    public int answerId, questionId, sAnswerId;
    
    public int getsendId() {
        return answerId;
      }

      public void setsendId(int answerId) {
        this.answerId = answerId;
      }

      public int getQuestionId() {
        return questionId;
      }

      public void setQuestionId(int questionId) {
        this.questionId = questionId;
      }
}
