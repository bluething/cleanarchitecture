### Hexagonal Architecture

![hexagonal architecture](https://github.com/bluething/cleanarchitecture/blob/main/images/Hexagonal-architecture-with-adapters.gif?raw=true)

Problem that we have is _coupling_!  
- Application logic coupling to the user interface. For example the system can’t neatly be tested with automated test suites because part of the logic needing to be tested is dependent on oft-changing visual details such as field size and button placement.  
- Application logic coupling to the databases. How often we say that we can continue to work because the database was down or the vpn was down?

Our focus is not that between "left" and "right" sides of the application (both caused by the same error in design and programming) but between "inside" and "outside" of the application.  
The rule to obey is that code pertaining to the "inside" part should not leak into the "outside" part. The "outside" depends on the "inside, never the other way around."

The application communicates over "ports" to the external ("outside").  
When we talk about OS, every port have an "adapter" hat converts the API definition to the signals needed by that device and vice versa. The same thing happen here.

"port" will answer "what is this for?"  
Is the intention of the dialog, it's not technologies (technology neutral).

Where's our business logic? "inside" the hexagon.

![simplify hexagonal architecture](https://github.com/bluething/cleanarchitecture/blob/main/images/simplifyhexagonalarchitecture.png?raw=true)

### Read More

[Hexagonal architecture](https://alistair.cockburn.us/hexagonal-architecture/)