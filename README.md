# CinemaPlatform

The connection to the database is established in Main.getConnection(). 
For the UserApp, CinemaGoer, Award, and Review objects, we have created CRUD operations (create, read, update, delete) which are included in methods in MainService. 
The application has an audit service (AuditService) that writes to an audit file (data/audit.csv, data/cinemagoer.csv, etc.).
