-- don't ctrl + alt + L here!
CREATE TABLE oauth_client_details (client_id VARCHAR(256) PRIMARY KEY, resource_ids VARCHAR(256), client_secret VARCHAR(256), scope VARCHAR(256), authorized_grant_types VARCHAR(256), web_server_redirect_uri VARCHAR(256), authorities  VARCHAR(256), access_token_validity INTEGER, refresh_token_validity INTEGER, additional_information VARCHAR(4096), autoapprove VARCHAR(256));

CREATE TABLE oauth_client_token (token_id          VARCHAR(256),token  BYTEA,authentication_id VARCHAR(256) PRIMARY KEY,user_name         VARCHAR(256),client_id         VARCHAR(256));

CREATE TABLE oauth_access_token (token_id          VARCHAR(256),token  BYTEA,authentication_id VARCHAR(256) PRIMARY KEY,user_name         VARCHAR(256),client_id         VARCHAR(256),authentication    BYTEA,refresh_token     VARCHAR(256));

CREATE TABLE oauth_refresh_token (token_id       VARCHAR(256),token          BYTEA,authentication BYTEA);

CREATE TABLE oauth_code (code VARCHAR(256),authentication BYTEA);

CREATE TABLE oauth_approvals (userId         VARCHAR(256),clientId       VARCHAR(256),scope          VARCHAR(256),status         VARCHAR(10),expiresAt      TIMESTAMP,lastModifiedAt TIMESTAMP);

CREATE TABLE ClientDetails (appId       VARCHAR(256) PRIMARY KEY,resourceIds VARCHAR(256),appSecret   VARCHAR(256),scope       VARCHAR(256),grantTypes  VARCHAR(256),redirectUrl VARCHAR(256),authorities VARCHAR(256),access_token_validity  INTEGER,refresh_token_validity INTEGER,additionalInformation  VARCHAR(4096),autoApproveScopes      VARCHAR(256));
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('clientapp', 'findandplay_api', 'db', 'read,write', 'password,refresh_token', '/success', 'USER,GUEST,ADMIN', 10000, 100000, null, null);
INSERT INTO clientdetails (appid, resourceids, appsecret, scope, granttypes, redirecturl, authorities, access_token_validity, refresh_token_validity, additionalinformation, autoapprovescopes) VALUES ('clientapp', 'findandplay_api', 'db', 'read,write', 'password,refresh_token', '/success', 'USER, GUEST, ADMIN', 10000, 100000, null , null);

INSERT INTO users (id, avatar_high, avatar_low, avatar_medium, city, confirmed, user_created, email, verification_key, user_last_action, msisdn, user_name, password, user_surname) VALUES (73, null, null, null, 'KAZAN', true, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E0F2D16E3600078', 'hajrullinbulat@gmail.com', null, null, '79991697612', 'Булат', '$2a$04$mAtCce6W2wAHKLw/XBjf.u2u1EKaFXzgjbjmnqyuEzYyECxQt/Y0q', 'Хайруллин');
INSERT INTO users (id, avatar_high, avatar_low, avatar_medium, city, confirmed, user_created, email, verification_key, user_last_action, msisdn, user_name, password, user_surname) VALUES (101, null, null, null, 'KAZAN', true, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E0F2D16E3600078', 'dima.kuzin@dz.ru', null, null, '79991697611', 'Дима', '$2a$04$mAtCce6W2wAHKLw/XBjf.u2u1EKaFXzgjbjmnqyuEzYyECxQt/Y0q', 'Кузин');
INSERT INTO users (id, avatar_high, avatar_low, avatar_medium, city, confirmed, user_created, email, verification_key, user_last_action, msisdn, user_name, password, user_surname) VALUES (79, null, null, null, 'KAZAN', true, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E0F2D16E3600078', 'aydar@gmail.com', null, null, '79991697613', 'Айдар', '$2a$04$mAtCce6W2wAHKLw/XBjf.u2u1EKaFXzgjbjmnqyuEzYyECxQt/Y0q', 'Фаррахов');
INSERT INTO users (id, avatar_high, avatar_low, avatar_medium, city, confirmed, user_created, email, verification_key, user_last_action, msisdn, user_name, password, user_surname) VALUES (100, null, null, null, 'KAZAN', true, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E0F2D16E3600078', 'pavel@gmail.com', null, null, '79991697614', 'Павел', '$2a$04$mAtCce6W2wAHKLw/XBjf.u2u1EKaFXzgjbjmnqyuEzYyECxQt/Y0q', 'Боробов');
INSERT INTO users (id, avatar_high, avatar_low, avatar_medium, city, confirmed, user_created, email, verification_key, user_last_action, msisdn, user_name, password, user_surname) VALUES (102, null, null, null, 'KAZAN', true, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E0F2D16E3600078', 'ildar@gmail.com', null, null, '79991697615', 'Ильдар', '$2a$04$mAtCce6W2wAHKLw/XBjf.u2u1EKaFXzgjbjmnqyuEzYyECxQt/Y0q', 'Назмеев');
INSERT INTO users (id, avatar_high, avatar_low, avatar_medium, city, confirmed, user_created, email, verification_key, user_last_action, msisdn, user_name, password, user_surname) VALUES (103, null, null, null, 'KAZAN', true, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E0F2D16E3600078', 'matvey@gmail.com', null, null, '79991697616', 'Матвей', '$2a$04$mAtCce6W2wAHKLw/XBjf.u2u1EKaFXzgjbjmnqyuEzYyECxQt/Y0q', 'Кример');

INSERT INTO roles (id, role_name) VALUES (1, 'USER');
INSERT INTO roles (id, role_name) VALUES (2, 'GUEST');
INSERT INTO roles (id, role_name) VALUES (3, 'ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (73, 1);

INSERT INTO sports (id, sport_name) VALUES (75, 'FOOTBALL');
INSERT INTO sports (id, sport_name) VALUES (76, 'VOLLEYBALL');

INSERT INTO sections (id, section_address, section_created, section_info, section_status, section_author_id, section_sport_id) VALUES (1, 'Нурсултана Назарбаева 70', null, 'Информация о секции волейбола', null, 73, 76);
INSERT INTO sections (id, section_address, section_created, section_info, section_status, section_author_id, section_sport_id) VALUES (2, 'Нурсултана Назарбаева 71', null, 'Информация о секции футбола', null, 101, 75);

INSERT INTO checked_sections (id, checked_sections_date, checked_sections_status, section_id, user_id) VALUES (1, null, 'APPROVED', 1, 102);
INSERT INTO checked_sections (id, checked_sections_date, checked_sections_status, section_id, user_id) VALUES (2, null, 'WAITING', 1, 101);
INSERT INTO checked_sections (id, checked_sections_date, checked_sections_status, section_id, user_id) VALUES (3, null, 'CANCELED', 2, 73);
INSERT INTO checked_sections (id, checked_sections_date, checked_sections_status, section_id, user_id) VALUES (4, null, 'APPROVED', 2, 100);

INSERT INTO adverts (id, advert_persons_count, advert_created, advert_info, advert_min_level, advert_status, advert_author_id, advert_sport_id) VALUES (74, 1, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E0F2D1833118078', 'Информация по адверту 74', 'AMATEUR', 'OPEN', 73, 75);
INSERT INTO adverts (id, advert_persons_count, advert_created, advert_info, advert_min_level, advert_status, advert_author_id, advert_sport_id) VALUES (75, 1, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E0F2D1833118078', 'Информация по адверту 75', 'SEMIPRO', 'CLOSED', 73, 75);

INSERT INTO checked_adverts (id, checked_adverts_date, checked_adverts_status, advert_id, user_id) VALUES (76, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E34353722614078', 'WAITING', 74, 79);
INSERT INTO checked_adverts (id, checked_adverts_date, checked_adverts_status, advert_id, user_id) VALUES (77, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E34353722614078', 'APPROVED', 74, 100);
INSERT INTO checked_adverts (id, checked_adverts_date, checked_adverts_status, advert_id, user_id) VALUES (78, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E34353722614078', 'CANCELED', 74, 101);
INSERT INTO checked_adverts (id, checked_adverts_date, checked_adverts_status, advert_id, user_id) VALUES (79, E'\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E1040C0E34353722614078', 'CANCELED', 74, 73);