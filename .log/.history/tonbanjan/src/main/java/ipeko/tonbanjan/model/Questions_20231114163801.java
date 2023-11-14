package ipeko.tonbanjan.model;

public class Questions {
  int questionId;
  int roomId;
  int q_amount;
  String q_content;

  // Thymeleafでフィールドを扱うためにはgetter/setterが必ず必要
  // vscodeのソースコード右クリック->ソースアクションでsetter/getterを簡単に追加できる

  public int getQuestionId() {
    return questionId;
  }

  public void setQuestionId(int questionId) {
    this.questionId = questionId;
  }

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  public String getQ_content() {
    return q_content;
  }

  public void setQ_content(String q_content) {
    this.q_content = q_content;
  }

  public void setQuestionAmount(int q_amount) {
    this.q_amount= q_amount;
  }

  public int getQuestionAmount() {
    return q_amount;
  }

}
