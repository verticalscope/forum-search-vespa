# Vespa Indexer for indexing forum data to Vespa
This tool takes the forum data in SQL DB and indexes the content to Vespa to the relevant Vespa documents.

## How to run
- Specify the relevant values for parameters in the file `App.java` in the Predefined settings section
- Run the file `index_forum_data.sh`
- You might see a few warnings saying the data isn't properly loading for some posts and comments, which is okay given that some of the records in SQL were not consistent with the schema and had not loaded properly.