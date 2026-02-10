## Name: Mirza Radithya Ramadhana
## NPM: 2406405563
## Class: Pemrograman Lanjut A

---

### Reflection 1
Principles applied:
- Clean code

    a. Meaningful Names<br>
Class and method names such as ProductController, ProductRepository, create,and findById are clear enough for developers to understand.

    b. Functions<br>
Each method is designed for a specific task. For example, findById method is delegated just to find a product by its id. This follows the single responsibility principle.

    c. Comments<br>
The code minimizes unnecessary comments by relying on clear naming. Currently, no comments have been written because the naming conventions and code structure are self-explanatory.

    d. Error Handling<br>
The use of a custom exception (ProductNotFoundException) in the repository layer improves error handling by indicating when a product with a specific id does not exist. This makes errors easier to trace.

- Version Control

    Version control was applied throughout the development process to manage changes incrementally. Features such as creating, editing, deleting, and listing products were developed and tested step by step. This improves code management.

Improvements:

Input validation has not yet been implemented, such as checking whether product quantity is non-negative or that product names are not empty. Adding input validation in the service layer would improve code security. Overall, the secure coding principles have not been fully applied throughout the development process and can be improved in future development. 

---

### Reflection 2
1. After writing the unit tests, I feel more confident about the correctness of my code. Unit tests help ensure that every method I wrote works as expected. In my opinion, there is no fixed number of unit tests that should be written for a class. Instead, unit tests should be sufficient to make sure our code works as intended. To make sure that our unit tests are sufficient is by using code coverage metrics. Higher code coverages means more parts of the code executed during testing. Practically, aiming for 80-90% code coverages is generally enough to make sure our test sufficient. However, having 100% code coverages does not mean our code is completely bug-free. Unit tests are usually done in isolation, but bugs could still show up when multiple components integrated. Additionally, a bad code followed by bad unit test could lead to high code coverages.
2. I think creating another functional test with the same setup procedures and instance variables would reduce code quality. Code redundancy is surely the problem. Code redundancy could lead to waste of development time because we rewrite something that is already written. Not only time aspect, code redundancy could also lead to a difficult maintaining process in the future. When changing a logic in one code, we need to change the other code as well. To avoid this problem, we need to apply DRY (Don't Repeat Yourself) principle in our code. Shared setup logic and common variables can be refactored into reusable components. By doing so, we could save a lot of time and effort in development and maintenance process.

