A Vert.x test server with a single endpoint:

    get host:44778/test/<delay>

The endpoint will call the mock-server, passing along the delay,
and then echo back the response from the mock server.
Delay must be >= 1 ms.

Build:

    ./gradlew ktlintFormat build installDist

Run:

    sh build/install/test-server/bin/test-server

Test:

    curl localhost:44778/test/2000
