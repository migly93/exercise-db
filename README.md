## MariaDB container with data for Eurovision Services Backend exercise

### Prerequisites:

You must have installed Docker in your OS.

If you don't have it, please visit the [Docker install guide](https://docs.docker.com/v17.09/engine/installation/).

### Running instructions:

Execute th docker-compose-full.yml file with the following command:
 `docker compose -f docker-compose-full.yml up`

It will start the entire application.

To run the application visit: `http://localhost:4200/`
To check the api documentation visit: `http://localhost:8080/swagger-ui/index.html`

You can also: 
   -  run every container separately with the `docker-compose up` command in backend and frontend folders
   -  run backend and frontend from your preferred IDE.

## Info

For further informations contact me at `giuseppe.migliaccio93@gmail.com`