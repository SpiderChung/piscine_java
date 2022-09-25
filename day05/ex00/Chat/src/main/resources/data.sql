INSERT INTO chat.users (login, password) VALUES ('Jack', 'j');
INSERT INTO chat.users (login, password) VALUES ('Tim', 't');
INSERT INTO chat.users (login, password) VALUES ('Tom', '123');
INSERT INTO chat.users (login, password) VALUES ('John', '123qweASD');
INSERT INTO chat.users (login, password) VALUES ('Ben', 'b');

INSERT INTO chat.rooms (name, owner) VALUES ('cars', 1);
INSERT INTO chat.rooms (name, owner) VALUES ('hobby', 2);
INSERT INTO chat.rooms (name, owner) VALUES ('sport', 3);
INSERT INTO chat.rooms (name, owner) VALUES ('anime', 4);
INSERT INTO chat.rooms (name, owner) VALUES ('animals', 5);


INSERT INTO chat.message (author, chatroom, text, time) VALUES (1, 1, 'Where is my car, dude?', current_timestamp);
INSERT INTO chat.message (author, chatroom, text, time) VALUES (2, 2, 'Where is my mind', current_timestamp);
INSERT INTO chat.message (author, chatroom, text, time) VALUES (3, 3, 'Lova-lova', current_timestamp);
INSERT INTO chat.message (author, chatroom, text, time) VALUES (4, 4, 'Its coming', current_timestamp);
INSERT INTO chat.message (author, chatroom, text, time) VALUES (5, 5, 'I need somebody to love', current_timestamp);
INSERT INTO chat.message (author, chatroom, text, time) VALUES (5, 5, 'Everybody get up singing', current_timestamp);