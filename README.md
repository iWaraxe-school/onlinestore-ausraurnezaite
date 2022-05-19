# 5. Patterns

## Materials

[Patterns](https://refactoring.guru/design-patterns)

## VideoLectures

- [Patterns(part 1)](https://youtu.be/q5U92-p-a0s)
- [Patterns(part 2)](https://youtu.be/bR7M\_lv52S4)

## Task #5

Read all materials, try to find a `proper` place to your newly learned patterns in our app. There are a lot of design
patterns, but we advise you to pay attention to the following ones:

- Singleton;
- ChainOfResponsibility;
- Fabric.
  The application of patterns consists not only in their implementation, but also in knowing their weaknesses and
  strengths. Therefore, in addition to realising the selected design patterns in the code, you must write the following
  justification for each pattern (you can send it to me in the messenger, or you can add text to README.md):
- What is the Design Pattern?
- Where did you apply it?
- Justify why you chose this one and not another. What do you gain by using chosen Design Pattern?

## Hints

Rethink your application from SOLID point of view. Keep in mind that in addition to implementing multithreading, we will
also work with the database and http. In many ways, we will repeat what we did for the console application for both the
database and the http layers. It might be worth coming up with some common interfaces that different versions will
implement.

## ANSWERS

- Design patterns are typical solutions to common problems in software design. Each pattern is like a blueprint that you
  can customize to solve a particular design problem in your code.
- Patterns that were applied:
    - Singleton (Thread-safe Singleton with lazy loading) pattern was applied in the store class.
    - Builder pattern was applied in the Product class.
- Reasons these patterns were chosen for:
    - Singleton pattern ensures that a Store class has only one instance, while providing a global access point to this
      instance. Also, thread-safe Singleton with lazy loading prevents multiple threads from getting several instances
      of Store class.
    - Builder pattern helps in cases when there are too many constructor arguments - it prevents from arguments of the
      same type being swapped.