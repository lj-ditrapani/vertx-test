A mock server with a single endpoint:

    get host:44770/test/<delay>

The endpoint will wait for delay in milliseconds and then respond with a 200 OK.
Delay must be >= 1 ms.

Build:

    ./gradlew ktlintFormat build installDist

Run:

    sh build/install/mock-server/bin/mock-server

Test:

    curl localhost:44770/test/2000
