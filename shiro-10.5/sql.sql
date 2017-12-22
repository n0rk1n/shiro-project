CREATE TABLE t_session
(
  id           VARCHAR(64),
  session_data TEXT
);
ALTER TABLE t_session
  COMMENT = '会话数据持久化表';