# KODO  Feed Project


## Steps to run project
- Clone the repo - `git clone https://github.com/malhotra1432/kodo-feed.git` 
- Build the project - `./go clean build`
- Run the project - `./go bootRun`
- Once the project starts. It will connect to H2 DB In Memory database. 
- Access the database by [url](http://localhost:8080/h2-console)
- Seed the  Data
  - via postman -  Import **kodo.postman_collection.json** to postman and it will have all the APIs available  with data. Try to hit post endpoint. It should seed the data to  H2 DB.
  - via Swagger - Go to swagger [url](http://localhost:8080/swagger-ui/index.html) and seed data via post [endpoint](http://localhost:8080/swagger-ui/index.html#/feed-controller/storeFeedDataUsingPOST). Copy the request data from [data.json](data.json)  file.
- Once the  data is seeded. Try get endpoints as per swagger or postman collections.
- 
## Commands
- `./go clean build`   - To clean and build project
- `./go bootRun`       - To run project
- `./go build`         - To build project
- `./go test`          - To run unit and integration tests
- `./go`              - To list out all commands

# Links 
- [Swagger Docs](http://localhost:8080/swagger-ui/index.html) 
- [H2 DB](http://localhost:8080/h2-console) 
- [Postman Collection](kodo.postman_collection.json)
- [Json Data](data.json)
