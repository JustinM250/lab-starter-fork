REFLECTION PART 

1.12. REFLECTION: Use the terms “subtype”, “polymorphism” and “dynamic method dispatch” to explain why Main.run works correctly for ANY of your Logger implementations.
    
    Main.run works on any of the loggers we pass in because they implement the Logger interface. 
    They're considered to be subtypes of logger. 

    So we can define a logger like: Logger l = new <<Type>>; 
    As long as that Type impelments that interface, we can call the log method on it. 

    This is an example of polymorphism because we're reusing/repurposing a type.
    This is an example of dynamic method dispatch because we're overriding the log method.

1.13. REFLECTION: Why can the Logger.formatMessage method have a default implementation? Why must the Logger.log method be abstract?

    The formatLog method makes sense to be default because every implemetation should have it. We're including
    that method so we don't have to repeat ourselves with formatting. DRY - Don't Repeat Yourself

    The log method must be abstract because we're stating that all implementations must have that method,
    not worrying about the details of the implementations of the method. We're decoupling here.

1.14.3. REFLECTION: Among all the Loggers you have implemented, ONLY MemoryLogger objects can be passed to the Main.export method. Why? Why MUST the declaration for the MemoryLogger use ‘MemoryLogger’ instead of just ‘Logger’ as its type? (Try changing the declaration line to ‘Logger’ and see what happens.)

    You can only pass MemoryLogger's into the Main.export because it expects an Exportable. Our other
    interfaces don't implement Exportable, only Logger. If we trie to change the declaration, it would
    break the type safety.