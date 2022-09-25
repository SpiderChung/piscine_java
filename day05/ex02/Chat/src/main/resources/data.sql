INSERT INTO chat.user(name, password) VALUES
('Billy','1'), ('Willy','12'), ('Dilly','123'), ('Chuck','qwe'), ('Ben','ASD'), ('Mike','111')
ON CONFLICT DO NOTHING;

INSERT INTO chat.chatroom(title, owner) VALUES
('Chat1', (SELECT id FROM chat.user WHERE name = 'Billy')),
('Chat2', (SELECT id FROM chat.user WHERE name = 'Willy')),
('Chat3', (SELECT id FROM chat.user WHERE name = 'Dilly')),
('Chat4', (SELECT id FROM chat.user WHERE name = 'Chuck')),
('Chat5', (SELECT id FROM chat.user WHERE name = 'Ben')),
('Chat6', (SELECT id FROM chat.user WHERE name = 'Mike'))
ON CONFLICT DO NOTHING;

INSERT INTO chat.message (author, room, text) VALUES
((SELECT id FROM chat.user WHERE name = 'Billy'), (SELECT id FROM chat.chatroom WHERE title = 'Chat1'), 'Message1'),
((SELECT id FROM chat.user WHERE name = 'Willy'), (SELECT id FROM chat.chatroom WHERE title = 'Chat2'), 'Message2'),
((SELECT id FROM chat.user WHERE name = 'Dilly'), (SELECT id FROM chat.chatroom WHERE title = 'Chat3'), 'Message3'),
((SELECT id FROM chat.user WHERE name = 'Chuck'), (SELECT id FROM chat.chatroom WHERE title = 'Chat4'), 'Message4'),
((SELECT id FROM chat.user WHERE name = 'Ben'), (SELECT id FROM chat.chatroom WHERE title = 'Chat5'), 'Message5'),
((SELECT id FROM chat.user WHERE name = 'Mike'), (SELECT id FROM chat.chatroom WHERE title = 'Chat6'), 'Message6')
ON CONFLICT DO NOTHING;

INSERT INTO chat.user_chatroom (user_id, chat_id) VALUES
((SELECT id FROM chat.user WHERE name = 'Billy'), (SELECT id FROM chat.chatroom WHERE title = 'Chat1')),
((SELECT id FROM chat.user WHERE name = 'Willy'), (SELECT id FROM chat.chatroom WHERE title = 'Chat2')),
((SELECT id FROM chat.user WHERE name = 'Dilly'), (SELECT id FROM chat.chatroom WHERE title = 'Chat3')),
((SELECT id FROM chat.user WHERE name = 'Chuck'), (SELECT id FROM chat.chatroom WHERE title = 'Chat4')),
((SELECT id FROM chat.user WHERE name = 'Ben'), (SELECT id FROM chat.chatroom WHERE title = 'Chat5')),
((SELECT id FROM chat.user WHERE name = 'Mike'), (SELECT id FROM chat.chatroom WHERE title = 'Chat6'))
ON CONFLICT DO NOTHING;