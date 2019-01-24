## Impulse's codestyle guidelines 
We follow Google’s Java code style guidelines with a few additions and modifications, which are described herein.
Please follow the code style guideline as it is important to keep readability, maintainability and consistency.

#### Function block complexicity
To maintain this huge codebase it is required to keep a cool head and mind. For maintain- and readability reasons we came up
with the decision that any function block has a limit of 50 lines.

> **Function block:** Anything which starts with `{` and ends with `}` containing actual logic.
This can be a constructor, method, static initializer block etc.

### Code conventions for API design
- When a method is expected to return a null reference/value instead of an actual object or value then please use `Optional<T>`s
- Method parameters accepting `null` must be annotated with `@Nullable` (from the javax.* package), all methods and parameters are `@Nonnull` by default
- When evaluating method parameters use Google's precondition utility for checking
- Use `java.nio` instead of `java.io` for IO. However, there's an exception for this: You are allowed to use `java.io` components if they are **absolutely necessary** or are **not avoidable** for your development purposes.

#### Naming
##### Package namespaces
- Avoid using plural package namespaces
- Every package namespace **is required** to start with `impulse.*`
- Package **must not** contain a name of fictional characters or/and a person. Doing something like `impulse.dog123.*` (dog123 = username) is prohibited

#### Line endings
We are using Unix line endings when committing to the Impulse repository. Windows users of Git can do `git config --global core.autocrlf true`
to let Git convert them automatically. 

#### Column width
- JavaDoc: Must not exceed 90 chararcters per line
- Comments: Must not exceed 70 characters per line
- Source code: Must not exceed 100 characters per line

Feel free to wrap code if it does help with readability.

#### Indentation
We are using 2 spaces for indentations.

<img src="../.docs/assets/naah.png" align="left" width="80" height="80"/>

```java
 public class Hello {
     public static void main(String... args) {
         if (args.length > 0) {
             for (String arg : args) {
                 System.out.println("Hello, " + arg + "!");
             }
         } else {
             System.out.println("Hello, world!");
         }
     }
 }
```

<img src="../.docs/assets/yeeh.png" align="left" width="80" height="80"/>


```java
 public class Hello {
   public static void main(String... args) {
     if (args.length > 0) {
       for (String arg : args) {
         System.out.println("Hello, " + arg + "!");
       }
     } else {
       System.out.println("Hello, world!");
     }
   }
 }
```

 
#### Blank lines
Place a blank line before the first member of a class, interface, enum, etc. as well as after the last member.

<img src="../.docs/assets/naah.png" align="left" width="80" height="80"/>

```java
public class HelloWorld {
  private String meow;
    
  public void foo() {
    System.out.println("Pink fluffy unicors...");
  }
}
```

<img src="../.docs/assets/naah.png" align="left" width="80" height="80"/>

```java
public class HelloWorld {

  private String meow;
    
  public void foo() {
    System.out.println("Pink fluffy unicors...");
  }
}
```

<img src="../.docs/assets/naah.png" align="left" width="80" height="80"/>

```java
public class HelloWorld {
  private String meow;
    
  public void foo() {
    System.out.println("Pink fluffy unicors...");
  }
    
}
```

<img src="../.docs/assets/yeeh.png" align="left" width="80" height="80"/>

```java
public class HelloWorld {

  private String meow;
    
  public void foo() {
    System.out.println("Pink fluffy unicors...");
  }
    
}
```

#### File headers
Each source code file is required to contain the license header for the project. We are using the following license header:
```
/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
```

If you are using our code style scheme for IntelliJ IDEA then the license header will be added automatically whenever creating a source code file.

#### Imports
- Static imports from Impulse packages `impulse.*`
<br><br>
- Static imports
<br><br>
- All imports from Impulse packages `impulse.*`
<br><br>
- All other imports
<br><br>
- java.*
- javax.*

Blank lines represent actual blank lines between the import statements.

<img src="../.docs/assets/naah.png" align="left" width="80" height="80"/>

```java
import com.google.guava.A;
import com.google.guava.B;
import com.google.guava.C;
import io.impulsecloud.core.AB;
import io.impulsecloud.core.AD;
import io.impulsecloud.core.AF;
import io.impulsecloud.core.AEE;
import java.util.X;
import java.util.Y;
import java.util.Z;
import java.util.Deedeli;
import java.util.Deedelu;

import static org.bukkit.Material.WOOD;
import static org.bukkit.Material.WOOL;
import static io.impulsecloud.ABC.CONSTANT1;
import static io.impulsecloud.ABC.CONSTANT2;
```

<img src="../.docs/assets/yeeh.png" align="left" width="80" height="80"/>

```java
import static io.impulsecloud.ABC.CONSTANT1;
import static io.impulsecloud.ABC.CONSTANT2;

import static org.bukkit.Material.WOOD;
import static org.bukkit.Material.WOOL;

import io.impulsecloud.core.AB;
import io.impulsecloud.core.AD;
import io.impulsecloud.core.AF;
import io.impulsecloud.core.AEE;

import com.google.guava.A;
import com.google.guava.B;
import com.google.guava.C;

import java.util.X;
import java.util.Y;
import java.util.Z;
import java.util.Deedeli;
import java.util.Deedelu;
import javax.annotation.*;
```

#### Exception handling
Exceptions which are ignored, name the exception variable `ignored`.

<img src="../.docs/assets/naah.png" align="left" width="80" height="80"/>

```java
try {
  id = Integer.parseInt(xyz);
} catch(NumberFormatException e) {
  // No need for handling or printing exception
}
```

<img src="../.docs/assets/yeeh.png" align="left" width="80" height="80"/>

```java
try {
  id = Integer.parseInt(xyz);
} catch(NumberFormatException ignored) {
  // No need for handling or printing exception
}
```

#### Field access
- All local class fields and functions should be accessed using `this` (`this.name = name;`) 
- If you access a superclass field or function use `super` (`super.xyz();`) as long you are not overriding that function

<img src="../.docs/assets/naah.png" align="left" width="80" height="80"/>

```java
public class A {
    
  private String name;
    
  public A(String name) {
    this.name = name;
  }
 
  public void foo() {
    System.out.println(name); // Meh, don't do this
  }
 
}

public class B extends A {

  public B(String name) {
    super(name);
    foo(); // Baddd, real bad
  }

}
```

<img src="../.docs/assets/yeeh.png" align="left" width="80" height="80"/>

```java
public class A {
    
  private String name;
    
  public A(String name) {
    this.name = name;
  }
 
  public void foo() {
    System.out.println(this.name); // Good boy/girl!
  }
 
}

public class B extends A {

  public B(String name) {
    super(name);
    super.foo(); // Excellent
  }

}

public class C extends B {
  
  public C(String name) {
    super(name);
    this.foo(); // Wowzers
  }

  @Override
  public void foo() {
    System.out.println("LUL");
  }

}
```

#### JavaDoc
JavaDoc is absolutely required when opening a pull request to push to the `master` branch. Every class header
declaration must have a JavaDoc comment block containing at least `@version` and `@since`. 
- `@author`: Do not use any `@author` tags in any JavaDoc comment block
- `@since`: In which minor release of Impulse this class was added, for example `1.0`
- `@version`: The version of this implementation. Start with `1.0` and increase the last digit by one if you are making big changes to the implementation. If the class itself gains a re-implementation then increase the first digit by one and set the last digit to zero. 

#### Class Content Arrangement
```diff
- (prohibited): This is not allowed to declare in any form anywhere.
```

<b>[Statement]</b> `package`<br>
<b>[Statement]</b> `import static`<br>
<b>[Statement]</b> `import`<br><br>

<b>[Declaration]</b> `class type`<br><br>

<b>[Field]</b> `public static final`<br>
<b>[Field]</b> `protected static final`<br>
<b>[Field]</b> `package private static final`<br>
<b>[Field]</b> `private static final`<br><br>

<b>[Field]</b> `public static` **(prohibited)**<br>
<b>[Field]</b> `protected static`<br>
<b>[Field]</b> `package private static`<br>
<b>[Field]</b> `private static`<br><br>

<b>[Declaration]</b> static initializer block<br><br>

<b>[Field]</b> `public final` **(prohibited)**<br>
<b>[Field]</b> `protected final`<br>
<b>[Field]</b> `package private final`<br>
<b>[Field]</b> `private final`<br><br>

<b>[Field]</b> `public` **(prohibited)**<br>
<b>[Field]</b> `protected`<br>
<b>[Field]</b> `package private`<br>
<b>[Field]</b> `private`<br><br>

<b>[Method]</b> `public static`<br>
<b>[Method]</b> `protected static`<br>
<b>[Method]</b> `package private static`<br>
<b>[Method]</b> `private static`<br><br>

<b>[Declaration]</b> initializer block **(prohibited)**<br><br>

<b>[Constructor]</b> `public`<br>
<b>[Constructor]</b> `protected`<br>
<b>[Constructor]</b> `package private`<br>
<b>[Constructor]</b> `private`<br><br>

<b>[Method]</b> <b>[Overwrite]</b> `public`<br>
<b>[Method]</b> <b>[Overwrite]</b> `protected`<br>
<b>[Method]</b> <b>[Overwrite]</b> `package private`<br>
<b>[Method]</b> <b>[Overwrite]</b> `private`<br><br>

<b>[Method]</b> `public`<br>
<b>[Method]</b> `protected`<br>
<b>[Method]</b> `package private`<br>
<b>[Method]</b> `private`<br><br>

<b>[Inner Class]</b> `class static`<br><br>

<b>[Inner Class]</b> `enum`<br>
<b>[Inner Class]</b> `interface`<br>
<b>[Inner Class]</b> `class`<br>
