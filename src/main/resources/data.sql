INSERT INTO answers (questionId, a_content) VALUES (1, '2');
INSERT INTO answers (questionId, a_content) VALUES (1, '4');
INSERT INTO answers (questionId, a_content) VALUES (1, '6');
INSERT INTO answers (questionId, a_content) VALUES (1, '8');

INSERT INTO answers (questionId, a_content) VALUES (2, '回答1');
INSERT INTO answers (questionId, a_content) VALUES (2, '回答2');
INSERT INTO answers (questionId, a_content) VALUES (2, '回答3');
INSERT INTO answers (questionId, a_content) VALUES (2, '回答4');
INSERT INTO answers (questionId, a_content) VALUES (3, '回答1');
INSERT INTO answers (questionId, a_content) VALUES (3, '回答2');
INSERT INTO answers (questionId, a_content) VALUES (3, '回答3');
INSERT INTO answers (questionId, a_content) VALUES (3, '回答4');
INSERT INTO answers (questionId, a_content) VALUES (4, '回答1');
INSERT INTO answers (questionId, a_content) VALUES (4, '回答2');
INSERT INTO answers (questionId, a_content) VALUES (4, '回答3');
INSERT INTO answers (questionId, a_content) VALUES (4, '回答4');
INSERT INTO answers (questionId, a_content) VALUES (5, '回答1');
INSERT INTO answers (questionId, a_content) VALUES (5, '回答2');
INSERT INTO answers (questionId, a_content) VALUES (5, '回答3');
INSERT INTO answers (questionId, a_content) VALUES (5, '回答4');

INSERT INTO questions (roomId, q_content) VALUES (1, '2*3=');

INSERT INTO questions (roomId, q_content) VALUES (2, '国語好き？');
INSERT INTO questions (roomId, q_content) VALUES (3, '社会好き？');
INSERT INTO questions (roomId, q_content) VALUES (4, '理科好き？');
INSERT INTO questions (roomId, q_content) VALUES (5, '英語好き？');

INSERT INTO
  users (roomId, userName)
VALUES
  (0, 'user1');

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

INSERT INTO send_answer (answerId,questionId) VALUES (1, 2);
INSERT INTO send_answer (answerId,questionId) VALUES (2, 3);
INSERT INTO send_answer (answerId,questionId) VALUES (3, 4);
INSERT INTO send_answer (answerId,questionId) VALUES (4, 5);

INSERT INTO send_answer (answerId,questionId) VALUES (1, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (1, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (1, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (1, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (1, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (2, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (2, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (2, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (2, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (2, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (2, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (3, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (3, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (3, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (3, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (3, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (3, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (3, 1);
INSERT INTO send_answer (answerId,questionId) VALUES (4, 1);
