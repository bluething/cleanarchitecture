### Implementing a Use Case

Start with [domain entity](https://github.com/bluething/cleanarchitecture/blob/main/hexagonalarchitecture/codekata/buckpal/application/src/main/java/io/github/bluething/cleanarchitecture/hexagonalarchitecture/buckpal/application/domain/Account.java) then build a use cases around it.

The use case is "sending money from one account to another", the steps is  
1. Takes input  
2. Validates business rules  
3. Manipulates the model state  
4. Returns output

Where is input validation (for example the transfer amount must be greater than zero)? Outside of domain entity, in input model (`SendMoneyCommand` class).  
The use case is, however, responsible for validating business rules (for example "the source account must not be overdrawn"). It shares this responsibility with the domain entities.

Builder pattern vs constructor for many field?  
Prefer to use constructor. Remember to create immutable object.  
With builder pattern what happen if we forgot to add new field to the code that calls the builder?

Don't share input model!  
Use different input models for different use cases.

Don't share output model!

Where is the "services" class? For each use case there are one service class  
![ervice class](https://github.com/bluething/cleanarchitecture/blob/main/images/buckpaldomaindiagram.png?raw=true)

Rich vs [anemic](https://martinfowler.com/bliki/AnemicDomainModel.html) domain model?  
- In a rich domain model, as much of the domain logic as possible is implemented within the entities at the core of the application. The use case class serves as an entry point to the domain model.
- In an "anemic" domain model, the entities themselves are very thin, separate logic with data. The domain logic is implemented in use case classes.

What about read-only use cases?  
In this example it's not considered as a use case. It's only a simple query for data.  
This way, read-only queries are clearly distinguishable from modifying use cases (or "commands") in the codebase. This plays nicely with concepts such as Command-Query Separation (CQS) and Command-Query Responsibility Segregation (CQRS).

### Implementing a Web Adapter

All communication with the outside world goes through adapters  
![dip adapter](https://github.com/bluething/cleanarchitecture/blob/main/images/dipadapter.png?raw=true)

The application layer should not do HTTP, so we should make sure not to leak HTTP details. This makes the web adapter replaceable by another adapter should the need arise.

Why we need a layer between adapter and use cases?  
For maintenance purpose, we know exactly what communication with the outside world takes place.

How about sending data back to outside?  
![output port](https://github.com/bluething/cleanarchitecture/blob/main/images/outputport.png?raw=true)  
There is no reason that the same adapter cannot be both at the same time.

Responsibilities of web adapter:  
- Maps HTTP requests to Java objects.  
- Performs authorization checks.  
- Validates input.  
- Maps input to the input model of the use case.  
- Calls the use case.  
- Maps the output of the use case back to HTTP, also translate the exception thrown by the use cases.  
- Returns an HTTP response.

What validation is done by the adapter, we already have one in use case layer?  
Validate that we can transform the input model of the web adapter into the input model of the use cases.

Are we just going to make one controller?  
It's better to create a separate controller, potentially in a separate package, for each operation.  
Why?  
- Using one controller will only result in a class with a large number of rows, hard to maintain or read. Usually this class will violate SRP.  
- The same goes for the test code.  
- If using one controller we will usually use the same data structure, share between the api. It didn't work, each api need different data structure.

### Implementing a Persistence Adapter

![high level persistence adapter dip](https://github.com/bluething/cleanarchitecture/blob/main/images/highleveldippersistenceadapter.png?raw=true)  
The persistence adapter is a "driven" or "outgoing" adapter, because it's called by the application and not the other way around.  
Naturally, at runtime, we still have a dependency from our application core to the persistence adapter.

Responsibilities of persistence adapter:  
1. Takes input  
2. Maps input into a database format  
3. Sends input to the database  
4. Maps database output into an application format  
5. Returns output

The input model may be a domain entity, or an object dedicated to a specific database operation, as specified by the interface.  
The input model lies within the application core and not within the persistence adapter.  
It then _maps_ the input model to a format it can work with to modify or query the database.

Will we only have a single repository? No, remember ISP.  
We can create repository for each domain or per bounded context.  
![slice repository](https://github.com/bluething/cleanarchitecture/blob/main/images/slicerepoperaggregate.png?raw=true)

Example for slicing by bounded context  
![slice repo by bounded context](https://github.com/bluething/cleanarchitecture/blob/main/images/slicerepohardboundaries.png?raw=true)

What about database transactions, the persistence adapter doesn't know which other database operations are part of the same use case?  
Put this responsibility to application (inside) part, using AOP or Spring `@Transactional` annotation.

### How about testing?

![testing pyramid](https://github.com/bluething/cleanarchitecture/blob/main/images/testingpyramid.png?raw=true)  
The basic statement is that we should have high coverage of fine-grained tests that are cheap to build, easy to maintain, fast-running, and stable.  
Once tests combine multiple units and cross-unit boundaries, architectural boundaries, or even system boundaries, they tend to become more expensive to build, slower to run, and more brittle (failing due to some configuration error instead of a functional error).

- Unit test  
  A unit test usually instantiates a single class and tests its functionality through its interface.
- Integration test  
  The integration tests will cross the boundaries, so the objects graph is not complete or must work against mocks at some point.
- System test  
In system test we have complete object graph. We verify whether a certain use case works as expected through all the layers of the application.

How to create unit test for stateless class, for example use case class?  
We can't verify the state in "then" section, so we verify the interaction with certain methods on its (mocked) dependencies.  
Because this kind of test is vulnerable to changes in the structure of the code under test and not only its behavior, think about most important interaction that the class have.  
No need to test all interactions.