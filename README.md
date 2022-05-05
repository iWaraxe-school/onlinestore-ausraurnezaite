# 3. OOP
## Materials
- [OOP](https://docs.oracle.com/javase/tutorial/java/concepts/index.html)
- [Lecture 3](https://drive.google.com/file/d/17R4SCXEd9B8a89UsJ-nLNYMFwM\_p8Fn9/view?usp=sharing)
- [Lecture 4](https://drive.google.com/file/d/14RC5dJno\_FcSfBe\_FAd9zeqpFSUj8odH/view?usp=sharing)
- [Reflection](https://docs.oracle.com/javase/tutorial/reflect/)
- [Reflections Lib](https://github.com/ronmamo/reflections)
- [Faker](https://github.com/DiUS/java-faker)
## VideoLectures
-  [03.oop.u1. Classes & Object](https://youtu.be/e8CFYiHeUi0) - RU
-  [03.oop.u2. Reflections. ENUM](https://youtu.be/RrqTz-Qbtqg) - RU
-  [03.oop.u3. OOP principles](https://youtu.be/zG-MNwEW6JY) - RU
-  [03.oop.u4. Equals&HashCodes. Abstract Classes](https://youtu.be/zPmcv6xRoag) - RU
-  [03.oop.u5. Wrapper Classes](https://youtu.be/W5uTGYVeslk) - RU
-  [03.oop.u6. Interfaces](https://youtu.be/JGgKbMcDnOo) - RU
## Task #3
Before start creating source code, read carefully all materials about OOP. It is not only 3 principles for interview;)
Store functionality should be based on above principles.
Classes to create:
- `Product` with such attributes as [name, rate, price]
- `Category` classes with the `name` attribute, for each store category [bike, phone, milk] and `products list`
- `Store` - class that should handle `category list`
- `RandomStorePopulator` - utility class that will populate out store/category with fake data using `Faker` lib
- `StoreApp` - class with main method to execute our store scenario.
When invoke main method, application should init store with categories and products and `pretty` print this data.
Also, categories should be read dynamically (at runtime), from base category package using `reflections` lib.
