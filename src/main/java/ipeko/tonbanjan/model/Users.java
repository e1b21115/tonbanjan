package ipeko.tonbanjan.model;

public class Users {
  int roomId;
  String userName;

  // Thymeleafでフィールドを扱うためにはgetter/setterが必ず必要
  // vscodeのソースコード右クリック->ソースアクションでsetter/getterを簡単に追加できる

  public int getRoomId() {
    return roomId;
  }

  public void setUserId(int roomId) {
    this.roomId = roomId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
