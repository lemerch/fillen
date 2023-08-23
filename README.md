<div align="center">
<img alt="logo" height="150" src="static/logo.png" width="150"/>


<p style="font-size: 35px;"> Fillen</p>
<p style="margin-top: -25px;">small library for filling in class fields</p>
</div>

# About

The name Fillen is a play on the words Filler and Filin, it would be possible to add another letter 'l' to this name to get Filllen, but let's not chat on the post

Why do I need this library? The original purpose of this bible library was to fill in the fields of sufficiently complex classes for testing them at work, but now I can confidently say that these goals can be greatly expanded. For example, you can use this library for more convenient, simple and structured processing of classes and their fields, you can check fields for annotations, data types, access modifiers, generalized types, etc. This will probably allow you to create your own tools, which I am sure will help no generation of programmers yet ;)

Individual application examples can be viewed in the [tests](/src/test/java/com/github/shakal76/fillen/ExampleTest.java)

# How it works?

<img alt="first" height="50" src="static/1.png" width="50" style="display: inline;"/>  
Did you know how owls differ from filins?
I confess honestly and I didn't know the answer to this question myself until recently.
Both of these species belong to the order of owls. Unlike owls, filins are their biggest representatives. Filins are nocturnal predators. While the filins tracks down prey at any time of the day. His diet is more varied.

## Fillen Lifecycle

In reality, filins are born in a strong family, whose parents will be with them until they grow up to independent life. And even after that, the parents will not fly away forever, but will live in the same territory. 
Yes, Filinov are exemplary family men)

The path of **our** filin begins with the default constructor.

```java
Fillen fillen = new Fillen();
```

### Meal
<img alt="second" height="50" src="static/2.png" width="50" style="display: inline;"/>  
The filins is active mainly at night, in the first few hours after sunset and in the last few hours before sunrise.
Noticing a potential victim, the predator rushes at her with a stone and plunges its claws into her, snatched and dinners her

Unlike real filins, **ours** prefers different classes in java, which of course he will be able to see, otherwise he will either skip a piece of it (the class field) or ignore it (make it null).

```java
Fillen fillen = new Fillen();
MyClass my = fillen.diet(MyClass.class);
my.getSomeField();
        ...
```

**NOTICE** - if the field of your class is an array or a list and its final type is ignored, then the array itself will be initialized, but its first element will be null.

But why can Fillen not notice a 'piece of food'? - The thing is that he is limited by his diet)

### Heart
<img alt="third" height="50" src="static/3.png" width="50" style="display: inline;"/>
I think the most interesting feature of filins is their eyesight. 
They eyes are very large and look straight ahead, according to the position of the eye sockets on the front side of the facial parts of the skull, that is, an filin cannot move its eyes like a human. The eyes of filins stand motionless in place throughout their lives. The world appears to filins in black and white. Contrary to the popular belief that filins do not see anything during the day, the eyes of filins are not so sensitive to daylight; an filin, for example, sees perfectly during the day even at a great distance. With age, they change their color from light yellow to dark orange.

Fillen also has a heart that you can't see, but that you can see, and moreover, you can customize it for yourself.

To do this, you just need to make a menu for our **filin**. You see, Fillen have only a basic diet at the start, which implies the types string, byte, short, int, long, float, double, array, list, which generate random values (except list, array).

To diversify his diet and thus adjust his vision, you can use the **abstract class Fillen.Diet**, where there is only one method not implemented - **Object menu(Object)**, 
where you can tell the Fillen how to process new data types. At the entrance, you will receive the ingredients class, which contains information about the field. To get the data type of the passed field, you can refer to it as **ingredients.type**. For the convenience of verification, you can use the built-in **isTypesEquals(one, two)** method. By implementing this class, you will get the corresponding object that should be placed in the constructor of our filin.

```java
Fillen.Diet diet = new Fillen.Diet() {
    @Override
    public Object menu(Ingredients ingredients) throw UserDietException {
        if (isTypesEquals(ingredients.type, BigDecimal.class)) {
            return new BigDecimal(123);
        }
        // ye, it's safety :)
        return null;
    }
};

Fillen fillen = new Fillen(diet);
        ...
```


If you have created too many diets and you need to hand them over to the Fillen in a nice way, then Bag can help you with this.

```java
Bag bag = new Bag();
bag.put(firstDiet);
bag.put(otherDiet);

Fillen fillen = new Fillen(bag);
```

Yes, in case you don't know what type of data the Fillen is transmitting to you, you can safely pass null
If an earlier handler defined the type, then your handler will not override its value.

In addition, you can redefine already existing diets. The override system works quite simply. 
If you have created several diets and they have been dropped into the bag, then the last diet from it will have priority. 
But, if you need a 100% guarantee that it is your diet that overrides even subsequent ones, then use the **setPriority(Priority.HIGH)** method to your diet.

```java
Fillen.Diet diet = new Fillen.Diet() {
        ...  
}.setPriority(Priority.HIGH);
```

or after creation

```java
myDiet.setPriority(Priority.HIGH);
```

**NOTICE** - avoid this, especially if your diets will be used by other developers, since the logic of redefining diets with a high priority is exactly the opposite of a low one, i.e. the diet that our filin will get first will be redefined.


### Briefly about Ingredients
<img alt="fourth" height="50" src="static/4.png" width="50" style="display: inline;"/>
This class provides you with information about the field passed to your handler (diet). It consists of:

- Class<?> type

- String name

- Generic generic

- Annotation[] declaredAnnotations

- int modifier

But its important feature is that all these fields are immutable, even though there are setters in this class, they will not change the current object, but create a new one based on an existing one with the value you changed. 

What should I do if I need to add processing of an internal type whose fields also need to be processed, in which case we have a method available only inside Fillen.Diet - dinner, just pass it the appropriate type

### About Container types
<img alt="fourth" height="50" src="static/5.png" width="50" style="display: inline;"/>
At the moment, only List and Array are implemented. Their logic is simple, they create a container from as many measures as were specified and only add the value/values of the target type at the end. Yes, I didn't make a reservation - values are also possible. You can pass a FillenList of values with normal handlers and they will be passed to the field, if the type was not a container, then only the first cell of your result will be passed, otherwise it will pass all the values.

You can also create such logical handlers - you can see more information about this [here](/src/main/java/com/github/shakal76/fillen/base/BaseDiet.java)

_______________

Phew, well, let's get some rest

### To be simpler
<img alt="fourth" height="50" src="static/6.png" width="50" style="display: inline;"/>
Sometimes in life we need to think. Often we like to complicate a lot for no apparent reason. And that's why sometimes we should stop and think.

In many cases, we can simplify our lives if we use one small but very useful method - fillen.setField(String fieldName, Object value)

```java
fillen.setField("myFieldName", 123456);
        ...
```

**NOTICE** - it will be performed on top of all diets.

<img alt="fourth" height="50" src="static/7.png" width="50" style="display: inline;"/>
And sometimes we just need to relax and relax.

Filins sleep an average of 10-12 hours a day. They try to choose a place to spend the night away from interference and often high up in the branches of trees.

To give our Fillen a little rest, we need to use the ingoreFields(String... fieldNames) method.
and it is best to do this during the flight:

```java
Flight flight = fillen.ignoreFields("firstFieldName", "otherFieldName");
Example example = flight.dinner(Example.class);
        ...
```

Why do we need to 'fly'? - This property can be extremely useful, as we separate our diets from our 'simplifications', which leads to greater mobility of use.

_______________

### Time

<img alt="fourth" height="50" src="static/8.png" width="50" style="display: inline;"/>
But time goes by, days pass and one day we will have to leave. No matter how difficult it is to accept, but our filin ends up in the Converter class.
...

_______________

# TODO:

- ADD CONVERTER and info about it into readme
- Improve image quality
- Check text of readme
- add to readme - userdietexception
- add commit rules
- add javadoc to BaseDiet, Generic, Heart
- add diagram of architype

Filins color - #53F4CC