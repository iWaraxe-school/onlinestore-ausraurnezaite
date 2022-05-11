# 4. Collections
## Materials
- [Collections](https://docs.oracle.com/javase/tutorial/collections/index.html)
- [Lecture 9](https://drive.google.com/file/d/13TibWYVYc8CaFVcLUMklHLrqkmS5Q6h\_/view?usp=sharing)
- [XML](https://en.wikipedia.org/wiki/XML)
- [XML processing](https://docs.oracle.com/javase/tutorial/jaxp/)

## VideoLectures
[Collections playlist](https://youtube.com/playlist?list=PL96uE92X-ozdXTE7PoMfFQJ2CBz-hv9YK)
- [Collections, Lists, Sets, Unmodifiable Lists and Sets](https://youtu.be/boKGhpMjqUw)
- [Lists, Sets, Maps, Unmodifiable Maps](https://youtu.be/48s7xoswhkE)
- [Generics (part 1)](https://youtu.be/vT2I3LcLWJM)
- [Generics (part 2)](https://youtu.be/9mwtYNzWUV0)
- [Functional Interfaces, Introduction to lambdas](https://youtu.be/Fp2R6uY02o8)
- [Lambdas](https://youtu.be/RQ30tUIppQU)
- [Stream API](https://youtu.be/4coew\_omxRo)
- [PrimitiveStream, Optional](https://youtu.be/KU6RbxTzTj0)
## Task #4
Starting extend our store. Please append ability user to interact with our store, while sending commands through read stream.
Add support of such commands: 
- `sort` - products from store according config. In resources folder create xml config file like
```
xml
<sort>
    <name>asc</name>
    <price>asc</price>
    <rate>desc</rate>
</sort>
```
Config file can contains from 1 to N fields. Sort should be done using `Comparator`. Sort and print should not modify
 default store product lists and their order.
- `top` - print top 5 products of whole store sorted via price desc
- `quit` - exit app

++Read this file when you have difficulty, or even before.
## Hints and FAQs
### Where to put classes?
It is best to place new classes that will deal with reading the XML file in a separate package. Possible classes that will need to be created are `XMLParser` which will read from the `config.xml` file, `Comparator` which will sort the contents of the list based on the contents of `config.xml`, it would be a good idea to add an `enum` class , which will contain the possible types of sorting.
### Packages
### Code in small chunks
#### XML reader and parser (30%)
The optimal structure that will store a **key-value** pair as **field - sort direction (`asc/desc`)** is `Map`. The important thing here is to use a kind of `Map` that remembers the order in which the `entries` are added.
#### Comparator (40%)
The key point is not to get hung up on the example presented in the condition, namely to implement only the sort order by `name`, `price` and `rate`. The order of the fields can be changed, there can be fewer, even one field!
#### Methods of sorting Store (20%)
Although we need to do two kinds of sorting: sorting the entire `Store` and finding the five most expensive items, this can actually be defined through the same `Comparator` that accepts different kinds of `Map`. For example, a descending sort by price can be obtained by passing `Map` to `Comparator`, which contains only one key-value pair: `price`-`desc`.
#### Interaction with Store (10%)
The usual endless `while` loop which waits for input from the console.
### How then to work smoother and faster?
We start in a new branch `04collection`, in which we will solve four tasks: XML parser, Comparator, Methods for store sorting, Interaction with Store. Accordingly, we will sequentially create four different branches: `04collection-XMLparser`, `04collection-Comparator`, `04collection-sorting`, `04collection-interaction`. At the end of each step, you need to create a `pull request` in `04collection`, not in `main`! 
