CREATE TABLE questions (
  questionId IDENTITY,
  roomId INT NOT NULL,
  q_content VARCHAR NOT NULL
);

CREATE TABLE answers (
  answerId IDENTITY,
  questionId INT NOT NULL,
  a_content VARCHAR NOT NULL
);

CREATE TABLE users (
  roomId INT NOT NULL,
  userName VARCHAR NOT NULL
);

CREATE TABLE attendance(
  attendId IDENTITY,
  roomId INT NOT NULL,
  userId INT NOT NULL,
  attend_times INT
);

CREATE TABLE class (
  classId IDENTITY,
  className VARCHAR NOT NULL
);

CREATE TABLE send_answer (
  sAnswerId IDENTITY,
  answerId INT,
  questionId INT NOT NULL
);
