# Payconiq

QA engineer assignment

# What's tested
In assignment I focused on CRUD operations as was requested.
In general when testing API I have encountered two slightly different contexts.
- When API is created particularly for one feature and is utilized by UI team which is creating the UI interface for it. In this case it's important not to divide API and UI when creating a test strategy, still means that automated API tests should be added, but how to construct test oracles should depend on the feature as a whole (it is also very important for avoiding miscommunication between different teams on later stages).
- When designing an open API which is going to be used by different teams/companies/entities to develop their own applications. In this case is very important to have very comprehensive and detailed documentation of the API endpoints. Test strategy should aim to cover it or at least most of it.If in the previous scenario engineers might find out during the development some misunderstanding and change API on the fly so to say here it can be used by many different entities.

In CRUD operations usually response body of GET requests is very important as usually it provides necessary data for applications utilizing it. More emphasis should go to make detailed assertions of important GET requests' responses compared to POST and PUT.

In this assignment I've added simple tests for Creating, Getting, Deleting and Updating gists.
I created some assertions for files and also added JSON schema assertion, but as mentioned above it could be beneficial to add more assertions and potentially each element in JSON response can be checked.
I have added also few negative scenarios that are targeting some edge cases. Another interesting test could have been for Truncation using Equivalence Partitioning (check gists for files smaller for truncation, exactly on the edge and for big data when truncation is actually happens).

# What's used
- Java maven
- JSON libraries (for creating and manipulating test data)
- Junit 5 (running tests)
- RestAssured (I came across this library couple of times but haven't really used it, was a good occasion to try it ou for real)

#Run
- To run the tests open the project using an IDE, e.g. Intellij and run tests in Gists.api package.
- Run "mvn clean test" from command line