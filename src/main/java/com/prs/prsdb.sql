DROP DATABASE IF EXISTS prs;
CREATE DATABASE prs;
USE prs;

CREATE TABLE User	(
ID				int				primary key auto_increment not null,
UserName		varchar(20)		not null,
Password		varchar(20)		not null,
FirstName		varchar(20)		not null,
LastName		varchar(20)		not null,
PhoneNumber		varchar(12)		not null,
Email			varchar(75)		not null,
IsReviewer		tinyint(1)		not null,
IsAdmin			tinyint(1)		not null,
IsActive		tinyint(1)		default 1 not null,
DateCreated		datetime		default current_timestamp not null,
DateUpdated		datetime		default current_timestamp on update current_timestamp not null,
UpdatedByUser	int				default 1 not null,

CONSTRAINT Uname UNIQUE (UserName)
);

CREATE TABLE Vendor (
ID				int				primary key auto_increment not null,
Code			varchar(10)		not null,
Name			varchar(255)	not null,
Address			varchar(255)	not null,
City			varchar(255)	not null,
State			varchar(2)		not null,
Zip				varchar(5)		not null,
PhoneNumber		varchar(12)		not null,
Email			varchar(100)	not null,
IsPreApproved	tinyint(1)		not null default 1,
IsActive		tinyint(1)		not null default 1,
DateCreated		datetime		not null default current_timestamp,
Dateupdated		datetime		not null default current_timestamp on update current_timestamp,
UpdatedByUser	int				not null default 1,

CONSTRAINT vcode UNIQUE (Code)
);

CREATE TABLE PurchaseRequest (
ID					int				primary key not null auto_increment,   
UserID				int				not null,
Description			varchar(100)	not null,
Justification		varchar(255)	not null,
DateNeeded			date			not null,
DeliveryMode		varchar(25)		not null,
Status				varchar(25)		not null,
Total				decimal(10,2)	not null,
SubmittedDate		datetime		not null,
ReasonForRejection	varchar(100)	not null default 1		,
IsActive			tinyint(1)			not null default 1,
DateCreated			datetime			not null default current_timestamp,
Dateupdated			datetime			not null default current_timestamp on update current_timestamp,
UpdatedByUser		int					not null default 1,

FOREIGN KEY (UserID) REFERENCES User(ID)
);

CREATE TABLE Product (
ID					int				primary key not null auto_increment,  
VendorID			int				not null,
PartNumber			varchar(50)		not null,
Name				varchar(150)	not null,
Price				decimal(10,2)	not null,
Unit				varchar(255)			,
PhotoPath			varchar(255)			,
IsActive			TinyInt(1)				,
DateCreated			datetime				,
DateUpdated			datetime				,
UpdatedByUser		int						,

FOREIGN KEY	(VendorID) REFERENCES Vendor(ID),
CONSTRAINT vendor_part UNIQUE (VendorID, PartNumber)
 );


CREATE TABLE PurchaseRequestLineItem (
ID					int					primary key auto_increment not null,
PurchaseRequestID	int					not null,
ProductID			int					not null,
Quantity			int					not null,
IsActive			tinyint(1)			not null default 1,
DateCreated			datetime			not null default current_timestamp,
Dateupdated			datetime			not null default current_timestamp on update current_timestamp,
UpdatedByUser		int					not null default 1,
CONSTRAINT req_pdt UNIQUE (PurchaseRequestID,ProductID)
);

INSERT INTO User (UserName, Password, FirstName, LastName, PhoneNumber, Email, IsReviewer, IsAdmin) 
VALUES		( '007', 'BondJamesBond', 'James', 'Bond', '999-999-9999', 'DoubleO@mi6.com', 1, 1),
			( 'JasonBourne', 'Dao4eva', 'David', 'Webb', '888-888-8888', 'BourneToDie@cia.com', 1, 1),
            ('OHBEHAVE', 'LoveFoxxy', 'Austin', 'Powers', '777-777-7777', 'ManOfMistery@ministryofdefense.com', 1, 1),
            ('SecretSpy', 'Password', 'Sterling', 'Archer', '666-666-6666', 'BestDamnSpy@isis.com', 1, 1);
            
INSERT INTO Vendor (Code, Name, Address, City, State, Zip, PhoneNumber, Email)
VALUES		('nin-001', 'Nintendo', '4600 150th Ave NE', 'Redmond', 'WA', '98052', '425-882-2040', 'support.nintendo.com'),
			('va-002', 'Valve', '10900 NE 4th street 500', 'Bellevue', 'WA', '98004', '425-889-9642', 'help.steampowered.com'),
            ('rs-003', 'Rockstar Games', '622 Broadway', 'New York', 'NY', '10012', '212-334-6633', 'support.rockstargames.com'), 
            ('es-004', 'Electronic Arts', '209 Redwood Shores Parkway', 'Redwood City', 'CA','94065', '658-628-1393', 'es@ea.com');
            
INSERT INTO Product (VendorID, PartNumber, Name, Price)
VALUES		(1, 'B01N5OKGLH', 'Super Smash Bros Ultimate', 49.68),
			(1, 'B01N1037CV', 'Mario kart 8 Deluxe', 59.99),
            (2, 'B003O6E3C8', 'Portal 2', 50.00),
            (2, 'B016KBVBCS', 'Steam Controller', 76.99),
            (3, 'B01M5DZ525', 'Red Dead Redeption 2', 59.99),
            (3, 'B00KVSQ848', 'Grand Theft Auto 5', 59.99),
            (4, 'B07QQ8W3HG', 'Star Wars Jedi: Fallen Order', 59.99),
            (4, 'B07DKY958Z', 'Anthem', 38.21);
            
	INSERT INTO PurchaseRequest (UserID, Description, Justification, DateNeeded, DeliveryMode, Status, Total, SubmittedDate, ReasonForRejection)
    VALUES		(3,'DescTest', 'JustTest', '2019-05-21', 'Carrier Pigeon', 'pending', 100, '2019-05-19', 'test'),
				(4,'DescTest', 'JustTest', '2019-05-22', 'Trained Hound', 'pending', 100, '2019-05-20', 'test'),
                (1,'DescTest', 'JustTest', '2019-05-23', 'Harambe', 'pending', 100, '2019-05-21', 'test'),
                (2,'DescTest', 'JustTest', '2019-05-24', 'Magic Geanie', 'pending', 100, '2019-05-22', 'test');
	
    INSERT INTO PurchaseRequestLineItem (PurchaseRequestID, ProductID, Quantity)
    VALUES		(1,2,10),
				(3,4,10);
            
       -- create a user and grant privileges to that user
       CREATE USER 'prs_user'@'localhost' IDENTIFIED BY 'sesame';
       GRANT SELECT, INSERT, DELETE, UPDATE ON prs.* TO 'prs_user'@'localhost';
	   FLUSH privileges;    
		
					



		





