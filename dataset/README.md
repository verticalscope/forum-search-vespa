# DIY Data setup instructions
- Use the commands in the file [mysql_setup.sql](mysql_setup.sql) to create the database and the required tables. 
- Download the data from: https://archive.org/download/stackexchange/diy.stackexchange.com.7z
- Unzip the downloaded file
- For now, we're just going to be using the Posts and Comments section of the downloaded data.
- Login into the mysql instance using the user and password created in the setup instructions. 
  ```
  mysql --host=127.0.0.1 --user=root -p
  ```
- Run the commands:
  ```
  load xml local infile '<path_to_diy_dataset>/Posts.xml' into table Posts rows identified by '<row>';
  load xml local infile '<path_to_diy_dataset>/Comments.xml' into table Comments rows identified by '<row>';
  ```