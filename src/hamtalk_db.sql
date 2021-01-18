--유저정보 테이블 생성
CREATE TABLE kakaouser (
    user_num NUMBER(4) PRIMARY KEY,
    phonenum VARCHAR2(11) NOT NULL UNIQUE,
    NAME VARCHAR2(20) NOT NULL,
    PASSWORD VARCHAR2(20) NOT NULL
    );
--유저정보테이블 번호부여 시퀀스
CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;

--확인용 유저 정보 추가
INSERT INTO kakaouser VALUES (user_seq.NEXTVAL, '01012341234', '고희광', '1234');
INSERT INTO kakaouser VALUES (user_seq.NEXTVAL, '01011111111', '김민재', '1234');
INSERT INTO kakaouser VALUES (user_seq.NEXTVAL, '01022222222', '홍아현', '1234');
--kakaouser테이블 확인

SELECT * FROM KAKAOUSER ;

--채팅룸 테이블 생성
CREATE TABLE chatroom (
   room_num NUMBER(4) PRIMARY KEY,
   user1_num number(4) NOT NULL,
   user2_num number(4) NOT NULL,
   lastLogOn_user1 DATE,
   lastLogOn_user2 DATE,
   CONSTRAINT charromm_user_UK UNIQUE (user1_num, user2_num), --유저 둘이 들어온 방은 유일하다
   CONSTRAINT chatroom_user1_num_FK FOREIGN KEY (user1_num) REFERENCES kakaouser(user_num),
   CONSTRAINT chatroom_user2_num_FK FOREIGN KEY (user2_num) REFERENCES kakaouser(user_num)
);
--채팅룸 방번호시퀀스 생성
CREATE SEQUENCE chatroom_seq START WITH 1 INCREMENT BY 1;

SELECT * FROM chatroom;

--메세지 내역 저장 테이블
CREATE TABLE chatMessage(
   message_num number(20) PRIMARY KEY, --메세지번호
   message varchar2(4000) NOT NULL, --메세지 내용
   message_time DATE DEFAULT sysdate, --메세지 보낸 시간
   user_num number(4) NOT NULL, --보낸사람 번호
   room_num number(4) NOT NULL, --방번호
   CONSTRAINT chatMessage_user_num_FK FOREIGN KEY (user_num) REFERENCES kakaouser(USER_NUM),
   CONSTRAINT chatMessage_room_num_FK FOREIGN KEY (room_num) REFERENCES chatroom(ROOM_NUM)
);
--메세지 내역 저장 테이블
CREATE SEQUENCE chatMessage_seq START WITH 1 INCREMENT BY 1;
SELECT * FROM CHATMESSAGE ;


SELECT message_num, message, to_char(MESSAGE_TIME, 'YYYYMMDDHH24MI') time, user_num, ROOM_NUM 
FROM (SELECT * FROM CHATMESSAGE ORDER BY MESSAGE_TIME DESC)
WHERE rownum <= 10 and ROOM_NUM =1 ORDER BY  MESSAGE_time ASC;

SELECT * FROM chatmessage;

CREATE TABLE user_data(
   user_num NUMBER PRIMARY KEY,
   user_status varchar2(60) DEFAULT '',
   user_image blob DEFAULT empty_blob(),
   CONSTRAINT user_data_FK FOREIGN KEY (user_num) REFERENCES kakaouser(user_num)
);

SELECT * FROM user_data;

COMMIT;
