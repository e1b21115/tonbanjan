CREATE TABLE questions (
  questionId IDENTITY,
  roomId INT NOT NULL,
  q_content VARCHAR NOT NULL,
  ForeignKey(roomId) REFERENCES room(roomId)
);

CREATE TABLE answers (
  answerId IDENTITY,
  questionId INT NOT NULL,
  a_content VARCHAR NOT NULL,
  ForeignKey(questionId) REFERENCES questions(questionId)
);

CREATE TABLE users (
  userId IDENTITY,
  userName VARCHAR NOT NULL,
  password VARCHAR NOT NULL
);

CREATE TABLE attendance(
  attendId IDENTITY,
  roomId INT NOT NULL,
  userId INT NOT NULL,
  attend_times INT,
  ForeignKey(roomId) REFERENCES room(roomId),
  ForeignKey(userId) REFERENCES users(userId)
);
