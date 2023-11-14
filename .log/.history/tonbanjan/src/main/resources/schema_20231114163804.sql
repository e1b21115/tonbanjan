CREATE TABLE questions (
  questionId IDENTITY,
  roomId INT NOT NULL,
  q_content VARCHAR NOT NULL,
  q_amount INT
);

CREATE TABLE answers (
  answerId IDENTITY,
  questionId INT NOT NULL,
  a_content VARCHAR NOT NULL
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
  attend_times INT
);
