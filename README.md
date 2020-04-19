Load and performance test a useless Vert.x server.

- test-server: the Vert.x server we will test. <test-server/README.md>
- mock-server: the mock backend server that fakes a delay. <mock-server/README.md>

Follow both linked readmes to start each server.
Run your test script against the test-server,
which will then call the mock-server and echo back the response.

```
+-------------+    +-------------+    +-------------+
| test script | -> | test-server | -> | mock-server |
+-------------+    +-------------+    +-------------+
```
