# SimilarWebService
`SimilarWebService` is a Java client for the SimilarWeb API. This service allows you to fetch data about website metrics such as segments, total traffic and engagement, bounce rates, average visit durations, page views, and unique visitors from both desktop and mobile web.

## Design
This project contains a `SimilarWebService` class that functions as a thread-safe singleton. By calling `SimilarWebService.getInstance()`, you can instantiate or retrieve the single instance of the service.

The `SimilarWebService` is implemented with an instance of the `SWApi` client which makes HTTP requests to the SimilarWeb API. Methods in `SimilarWebService` are provided for each endpoint in the API, allowing you to call them directly from the service.

## Using SimilarWebService

### Instantiation

Fetch the singleton instance of `SimilarWebService`: <br>
```java
SimilarWebService service = SimilarWebService.getInstance();
```

### Fetching Data

To fetch segment data, use:
```java
SwResponse response = service.getSegmentData(request);
```

Replace 'request' with an instance of `SwSegmentRequest` containing your specifications.

## Environment
The project is written in Java and uses Maven as a build tool.

## Building
To build the project, navigate to the project folder in your terminal and run:

```bash
mvn clean install
```

This will fetch necessary dependencies, run any tests (if available), and install the `SimilarWebService` package.

## Contributing
We welcome contributions to this project. Create a new branch, implement your feature or bug fix, and open a pull request.

Remember to always run the build and tests before opening a PR.