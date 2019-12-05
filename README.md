## MariaDB and API container with data for Eurovision Services Backend exercise

### Prerequisites:

You must have installed Docker in your OS.

If you don't have it, please visit the [Docker install guide](https://docs.docker.com/v17.09/engine/installation/).

### Running instructions:

Execute the docker-compose file. It will start the database and the API (_**the API is only needed if you're going to do the front-end exercise**_)

After running the docker-compose the following endpoints can be reached:

- Database: localhost:3306
- API: localhost:1111/cities/queryByPage?page={page_number}&size={items_by_page}


All the info needed to connect to the DB is included in the exercise PDF provided. 