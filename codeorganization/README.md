### How we design and organize our code?

#### Package by layer

We group the code based on what it does from technical perspective. Code is slice horizontally into layers, those layers should depend only on the next adjacent lower layer.

![package by layer](https://github.com/bluething/cleanarchitecture/blob/main/images/packagebylayer.jpg?raw=true)

The problem when we're slicing by functionality:  
1. Insufficient layers when code grows in scale and complexity.  
2. Layered architecture doesn't talk about business domain, they are buried in the package.

We can make a mistake when using this approach by introducing some undesirable dependencies, yet still create a nice, acyclic dependency graph.

![undesirable dependencies](https://github.com/bluething/cleanarchitecture/blob/main/images/problemwithpackagebylayer.jpg?raw=true)

Why this is bad? We can accidentally bypass the business logic layer.

What can we do to avoid this? We need a guideline, an architectural principle that says something like, "Web controllers should never access repositories directly."  
Is this enough? No, we have to monitor the implementation. One of them is by doing a code review.

#### Package by feature

We group the code based on related features, domain concepts or aggregate roots. This time our layer talk about business domain.

![package by feature](https://github.com/bluething/cleanarchitecture/blob/main/images/packagebyfeature.jpg?raw=true)

#### Ports and adapters

The aim for this approach is to create architectures where business/domain-focused code is independent and separate from the technical implementation details such as frameworks and databases.

![ports and adapters](https://github.com/bluething/cleanarchitecture/blob/main/images/portandadapter.jpg?raw=true)

The "inside" region contains all the domain concepts.  
The "outside" region contains the interactions with the outside world.  
The major rule here is that the "outside" depends on the "inside", never the other way around.

We know this approach as "hexagonal architecture".

![ports and adapters 2](https://github.com/bluething/cleanarchitecture/blob/main/images/portandadapter2.jpg?raw=true)

#### Package by component

What is a component? A bundle of business logic and persistence code.

Uncle Bob said "Components are the units of deployment. They are the smallest entities that can be deployed as part of a system. In Java, they are jar files."

In C4 model, a component define as "a grouping of related functionality encapsulated behind a well-defined interface, components are not separately deployable units."

![package by component](https://github.com/bluething/cleanarchitecture/blob/main/images/packagebycomponent.jpg?raw=true)

#### Enforce encapsulation

The evil is `public` access.  
If we mark all class as a public then the packages are simply an organization mechanism (a grouping, like folders), rather than being used for encapsulation. We can ignore the package because we can use the class from anywhere.  
The 4 approach above will look similar.

What the recommended use of `public` access.  
![public access](https://github.com/bluething/cleanarchitecture/blob/main/images/publicaccess.jpg?raw=true)  
The grayed-out types are where the access modifier can be made more restrictive

#### Other decoupling modes

1. OSGi.  
2. Java 9 module system.  
3. Decouple the dependencies at the source code level, by splitting code across different source code trees.

#### Additional reading

[The C4 model for visualising software architecture Context, Containers, Components, and Code](https://c4model.com/)  
[Introduction to OSGi](https://www.baeldung.com/osgi)  
[What is OSGi? A different approach to Java modularity](https://www.infoworld.com/article/3543072/what-is-osgi-java-modularity-with-the-open-service-gateway-initiative.html)  
[Java applications that comply with OSGi](https://www.ibm.com/docs/en/cics-ts/5.6?topic=java-applications-that-comply-osgi)  
[A Guide to Java 9 Modularity](https://www.baeldung.com/java-9-modularity)  
[Understanding Java 9 Modules](https://www.oracle.com/corporate/features/understanding-java-9-modules.html)  
[Java 9+ modularity: The difficulties and pitfalls of migrating from Java 8 to Java 9+](https://developer.ibm.com/languages/java/tutorials/java-modularity-5/)