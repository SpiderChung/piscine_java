INSERT INTO chat.user(name, password) VALUES
('Billy','1'), ('Willy','12'), ('Dilly','123'), ('Chuck','qwe'), ('Ben','ASD'), ('Mike','111'), ('Adolf', 'adik'),
('Samuel', 'sam'), ('Henderson','111'), ('Mohhamed', '111'), ('Roberto', 'robo'), ('Gini', 'lo'), ('Jennifer', '123')
ON CONFLICT DO NOTHING;

INSERT INTO chat.chatroom(title, owner) VALUES
('Chat1', (SELECT id FROM chat.user WHERE name = 'Billy')),
('Chat2', (SELECT id FROM chat.user WHERE name = 'Willy')),
('Chat3', (SELECT id FROM chat.user WHERE name = 'Dilly')),
('Chat4', (SELECT id FROM chat.user WHERE name = 'Chuck')),
('Chat5', (SELECT id FROM chat.user WHERE name = 'Ben')),
('Chat6', (SELECT id FROM chat.user WHERE name = 'Mike')),
('Chat7', (SELECT id FROM chat.user WHERE name = 'Adolf')),
('Chat8', (SELECT id FROM chat.user WHERE name = 'Samuel')),
('Chat9', (SELECT id FROM chat.user WHERE name = 'Henderson')),
('Chat10', (SELECT id FROM chat.user WHERE name = 'Mohhamed')),
('Chat11', (SELECT id FROM chat.user WHERE name = 'Roberto')),
('Chat12', (SELECT id FROM chat.user WHERE name = 'Gini')),
('Chat13', (SELECT id FROM chat.user WHERE name = 'Jennifer'))
ON CONFLICT DO NOTHING;

INSERT INTO chat.message (author, room, text) VALUES
((SELECT id FROM chat.user WHERE name = 'Billy'), (SELECT id FROM chat.chatroom WHERE title = 'Chat1'), 'Message1'),
((SELECT id FROM chat.user WHERE name = 'Willy'), (SELECT id FROM chat.chatroom WHERE title = 'Chat2'), 'Message2'),
((SELECT id FROM chat.user WHERE name = 'Dilly'), (SELECT id FROM chat.chatroom WHERE title = 'Chat3'), 'Message3'),
((SELECT id FROM chat.user WHERE name = 'Chuck'), (SELECT id FROM chat.chatroom WHERE title = 'Chat4'), 'Message4'),
((SELECT id FROM chat.user WHERE name = 'Ben'), (SELECT id FROM chat.chatroom WHERE title = 'Chat5'), 'Message5'),
((SELECT id FROM chat.user WHERE name = 'Mike'), (SELECT id FROM chat.chatroom WHERE title = 'Chat6'), 'Message6'),
((SELECT id FROM chat.user WHERE name = 'Adolf'), (SELECT id FROM chat.chatroom WHERE title = 'Chat7'), 'Message7'),
((SELECT id FROM chat.user WHERE name = 'Samuel'), (SELECT id FROM chat.chatroom WHERE title = 'Chat8'), 'Message8'),
((SELECT id FROM chat.user WHERE name = 'Henderson'), (SELECT id FROM chat.chatroom WHERE title = 'Chat9'), 'Message9'),
((SELECT id FROM chat.user WHERE name = 'Mohhamed'), (SELECT id FROM chat.chatroom WHERE title = 'Chat10'), 'Message10'),
((SELECT id FROM chat.user WHERE name = 'Roberto'), (SELECT id FROM chat.chatroom WHERE title = 'Chat11'), 'Message11'),
((SELECT id FROM chat.user WHERE name = 'Gini'), (SELECT id FROM chat.chatroom WHERE title = 'Chat12'), 'Message12'),
((SELECT id FROM chat.user WHERE name = 'Jennifer'), (SELECT id FROM chat.chatroom WHERE title = 'Chat13'), 'Message13')
ON CONFLICT DO NOTHING;

INSERT INTO chat.user_chatroom (user_id, chat_id) VALUES
((SELECT id FROM chat.user WHERE name = 'Billy'), (SELECT id FROM chat.chatroom WHERE title = 'Chat1')),
((SELECT id FROM chat.user WHERE name = 'Billy'), (SELECT id FROM chat.chatroom WHERE title = 'Chat2')),
((SELECT id FROM chat.user WHERE name = 'Willy'), (SELECT id FROM chat.chatroom WHERE title = 'Chat3')),
((SELECT id FROM chat.user WHERE name = 'Willy'), (SELECT id FROM chat.chatroom WHERE title = 'Chat4')),
((SELECT id FROM chat.user WHERE name = 'Dilly'), (SELECT id FROM chat.chatroom WHERE title = 'Chat5')),
((SELECT id FROM chat.user WHERE name = 'Dilly'), (SELECT id FROM chat.chatroom WHERE title = 'Chat6')),
((SELECT id FROM chat.user WHERE name = 'Chuck'), (SELECT id FROM chat.chatroom WHERE title = 'Chat7')),
((SELECT id FROM chat.user WHERE name = 'Chuck'), (SELECT id FROM chat.chatroom WHERE title = 'Chat8')),
((SELECT id FROM chat.user WHERE name = 'Ben'), (SELECT id FROM chat.chatroom WHERE title = 'Chat9')),
((SELECT id FROM chat.user WHERE name = 'Ben'), (SELECT id FROM chat.chatroom WHERE title = 'Chat10')),
((SELECT id FROM chat.user WHERE name = 'Mike'), (SELECT id FROM chat.chatroom WHERE title = 'Chat11')),
((SELECT id FROM chat.user WHERE name = 'Mike'), (SELECT id FROM chat.chatroom WHERE title = 'Chat12')),
((SELECT id FROM chat.user WHERE name = 'Adolf'), (SELECT id FROM chat.chatroom WHERE title = 'Chat13')),
((SELECT id FROM chat.user WHERE name = 'Adolf'), (SELECT id FROM chat.chatroom WHERE title = 'Chat1')),
((SELECT id FROM chat.user WHERE name = 'Samuel'), (SELECT id FROM chat.chatroom WHERE title = 'Chat2')),
((SELECT id FROM chat.user WHERE name = 'Samuel'), (SELECT id FROM chat.chatroom WHERE title = 'Chat3')),
((SELECT id FROM chat.user WHERE name = 'Henderson'), (SELECT id FROM chat.chatroom WHERE title = 'Chat4')),
((SELECT id FROM chat.user WHERE name = 'Henderson'), (SELECT id FROM chat.chatroom WHERE title = 'Chat5')),
((SELECT id FROM chat.user WHERE name = 'Mohhamed'), (SELECT id FROM chat.chatroom WHERE title = 'Chat6')),
((SELECT id FROM chat.user WHERE name = 'Mohhamed'), (SELECT id FROM chat.chatroom WHERE title = 'Chat7')),
((SELECT id FROM chat.user WHERE name = 'Roberto'), (SELECT id FROM chat.chatroom WHERE title = 'Chat8')),
((SELECT id FROM chat.user WHERE name = 'Roberto'), (SELECT id FROM chat.chatroom WHERE title = 'Chat9')),
((SELECT id FROM chat.user WHERE name = 'Jennifer'), (SELECT id FROM chat.chatroom WHERE title = 'Chat10')),
((SELECT id FROM chat.user WHERE name = 'Gini'), (SELECT id FROM chat.chatroom WHERE title = 'Chat11'))
ON CONFLICT DO NOTHING;