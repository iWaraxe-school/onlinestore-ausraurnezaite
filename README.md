### 8. HTTP

----
#### Materials

[HTTP](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol)
[HTTP simple servers (plain java/lib)](https://syntaxcorrect.com/Java/5_Ultra_Lightweight_Http_Server_Implementations_in_Java_for_Blazing_Fast_Microservices_APIs_or_Even_Websites)
[Basic auth](https://en.wikipedia.org/wiki/Basic_access_authentication)
[RestAssured](https://rest-assured.io/)
#### Task #8

The same task as previous, but to store and get store data you should 
- implement an HTTP server (use included in java or external lib), that will handle in memory or DB your categories, but you will receive them via HTTP protocol.
- Also, you should implement `add the product to cart` logic in this server, and process this request via HTTP.
- Finally, your HTTP service should be secured with basic authentication  (credentials can be hardcoded).
- For HTTP client-side you also can use the default (included in java) or use RestAssured. 
