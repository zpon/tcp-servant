tcp-servant
===========

Java framework for creating tcp servers

Overview
--------

There is a few classes you should know about in order to use `tcp-servant`. The main entry point is named `Servant`. This class is in charge of setting op the socket and waiting for connection. When a connection happens, the `Servant` class creates a new instance of the `Connection` class. The `Connection` class is in charge of reading data from the client and writing back the responses.

To handle request, there is currently two classes: `FilterRequestHandler` and `ReflectorRequestHandler` (more can be implemented by implementing `IRequestHandler`). I will not go into detail about how to use these, to learn more, you should lookup the source code of them and look in the "examples" folder.

The `FilterRequestHandler` has a list of `IRequestFilter` objects, when the `FilterRequestHandler` recieves a new request it asks the request filters until it finds one which is able to handle the request.

The `ReflectorRequestHandler` uses reflection to find which method in a specified `IReflectorService` is able to handle a request, and then call the given method. To help the `ReflectorRequestHandler` find the name of the method able to handle a request, the user also has to implement the `IReflectorSerializer`.

More than one `IRequestHandler` can be specified for a server, e.g. if you want a failover request handler in case the primary handler was not able to handle a request. To specify the request handler for the `Connection` to use, the `IRequestHandlerFactory` is used.
