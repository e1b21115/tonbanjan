INSERT INTO
  answers (questionId, a_content)
VALUES
  (1, '回答1');

INSERT INTO
  answers (questionId, a_content)
VALUES
  (1, '回答2');

INSERT INTO
  answers (questionId, a_content)
VALUES
  (1, '回答3');

INSERT INTO
  answers (questionId, a_content)
VALUES
  (1, '回答4');

INSERT INTO
  questions (roomId, q_content)
VALUES
  (1, '数学好き？');

INSERT INTO
  questions (roomId, q_content)
VALUES
  (2, '国語好き？');

INSERT INTO
  questions (roomId, q_content)
VALUES
  (3, '社会好き？');

INSERT INTO
  questions (roomId, q_content)
VALUES
  (4, '理科好き？');

INSERT INTO
  questions (roomId, q_content)
VALUES
  (5, '英語好き？');

INSERT INTO
  users (userName, password)
VALUES
  ('user1', 'password1');

INSERT INTO
  attendance (roomId, userId, attend_times)
VALUES
  (1, 1, 1);

INSERT INTO
  class (className)
VALUES
  ('数学');

INSERT INTO
  class (className)
VALUES
  ('国語');

INSERT INTO
  class (className)
VALUES
  ('社会');

INSERT INTO
  class (className)
VALUES
  ('理科');

INSERT INTO
  class (className)
VALUES
  ('英語');

INSERT INTO
  send_answer (answerId,questionId)
VALUES
  (1, 1);
