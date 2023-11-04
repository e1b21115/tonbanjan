package ipeko.tonbanjan.model;

public class Attendance {
  int attendId;
  int roomId;
  int userId;
  int attend_times;

  public int getAttendId() {
    return attendId;
  }

  public void setAttendId(int attendId) {
    this.attendId = attendId;
  }

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getAttend_times() {
    return attend_times;
  }

  public void setAttend_times(int attend_times) {
    this.attend_times = attend_times;
  }

}
