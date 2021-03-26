CREATE SCHEMA `forum_data` ;
USE forum_data;

CREATE TABLE Posts (
    Id INT NOT NULL PRIMARY KEY,
    PostTypeId TINYINT NOT NULL ,
    AcceptedAnswerId INT,
    ParentId INT,
    CreationDate DATETIME NOT NULL,
    DeletionDate DATETIME,
    Score INT NULL,
    ViewCount INT NULL,
    Body text NULL,
    OwnerUserId INT,
    OwnerDisplayName varchar(256),
    LastEditorUserId INT,
    LastEditorDisplayName VARCHAR(40),
    LastEditDate DATETIME,
    LastActivityDate DATETIME,
    Title varchar(256),
    Tags VARCHAR(256),
    AnswerCount INT DEFAULT 0,
    CommentCount INT DEFAULT 0,
    FavoriteCount INT DEFAULT 0,
    ClosedDate DATETIME,
    CommunityOwnedDate DATETIME,
    ContentLicense varchar(12));

CREATE TABLE Comments (
    Id INT NOT NULL PRIMARY KEY,
    PostId INT NOT NULL,
    Score INT NOT NULL DEFAULT 0,
    Text TEXT,
    CreationDate DATETIME,
    UserDisplayName VARCHAR(30),
    UserId INT,
    ContentLicense varchar(12)
);