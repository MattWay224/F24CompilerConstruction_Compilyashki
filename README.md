# F24CompilerConstruction
### Our team:
- **Matthew Rusakov:** Responsible for refactoring and optimizing code, organizing the codebase with design patterns, and developing automated unit tests.
- **Aliia Bogapova:** Responsible for writing the compiler code and testing it based on previously written tests.
- **Polina Pushkareva:** Responsible for organizational aspects, presentations, and reports.

### Project Overview:
**Project-F:** 

The goal of this project is to develop an interpreter for a Lisp-like functional programming language. The project will be completed in several stages, starting with lexical analysis and progressing through syntax analysis, semantic analysis, and finally code interpretation.

**Technologies Used:** 

- *Language:* Java
- *Lexer:* Handwritten lexer
- *Parser:* Handwritten parser

**Project stages:**

The project will be implemented in four stages:

1. Lexical Analyzer (Lexer)
2. Syntax Analyzer (Parser)
3. Semantic Analyzer (Analyzer)
4. Code Interpretation

### 1. *Lexical Analyser:*

**Purpose:**

The lexical analyzer (also called the "lexer" or "tokenizer") reads the input code and breaks it down into tokens. Tokens are the smallest units of meaning in the code, such as parentheses, operators, keywords, numbers, and literals. The lexical analyzer is responsible for recognizing these tokens and categorizing them into different types.

**Technical Details:**

Class: ```Flexer```

The ```Flexer``` class is responsible for implementing the lexical analyzer in Java. It takes the input source code, processes it character by character, and generates a sequence of tokens.

Inner Class: ```Token```

The ```Token``` class represents a single token, which contains:

- *type*: The type of the token (e.g., keyword, operator, literal, etc.).
- *value*: The actual content of the token (e.g., +, 42, true, etc.).

Ennumeration: ```TokenType```

We use an enumeration called ```TokenType``` to classify various token types. Some of the primary token types in our language are:

- *Parentheses*: Open ```(``` and close ```)```.
- *Keywords*: Reserved words in the language like ```func```, ```cond```, ```while```, ```lambda```, etc.
- *Operators*: Arithmetic and logical operators like ```+```, ```-```, ```*```, ```/```, ```and```, ```or```.
- *Literals*: Constants like numbers: integers and reals (```42```, ```3.14```) and booleans (```true```, ``false``).
- *Quoted Expressions*: Expressions starting with a single quote, such as ```'expr```.

Key Methods in Flexer:

- ```tokenize()```: This is the main method that takes the input string and processes it to generate a list of tokens. It handles the breakdown of the input code by analyzing each character and determining whether it forms part of a token.
- ```parseNumber()```: This method identifies and processes numeric values (integers and real numbers) in the input.
- ```parseIdOrKeyword()```: This method processes identifiers, which can be function names, function names, or keywords.
- *Error Handling*: The lexical analyzer also includes mechanisms for identifying and handling invalid tokens. If an unknown character or sequence is encountered, an error is thrown.

### 2. *Syntax Analyser*

**Purpose:**

The syntax analyzer, or parser, takes the sequence of tokens generated by the lexical analyzer and constructs an Abstract Syntax Tree (AST). The AST represents the hierarchical structure of the source code, capturing the relationships between language constructs like functions, variables, and expressions. The parser checks whether the token sequence follows the correct syntax rules of the  F-language, ensuring the code is grammatically correct.

**Technical Details:**

*Parsing Approach:*

We use top-down parsing to build the AST, starting from the highest-level grammar rules and breaking them down into smaller components. The parser traverses the token sequence and recursively parses each construct, creating corresponding nodes in the AST.

*Abstract Syntax Tree (AST):*

- *Nodes:* Each AST node represents a syntactic structure in the source code, such as a function definition, an arithmetic operation, or a conditional statement.
- *Composite Pattern:* We apply the *Composite Pattern* to manage complex AST structures, where certain nodes (e.g., high-order function nodes) can have multiple child nodes, representing nested expressions or function arguments.
- *Factory Pattern:* A *Factory* is used to generate different types of AST nodes, ensuring clean and modular node creation.

*Symbol Table:*

The parser maintains a symbol table that stores information about each declared entity (such as variables, functions, and constants). For each entity, the symbol table records:

- *Name:* The identifier used in the source code.
- *Value:* The current value or expression associated with the entity.
- *Span:* The range of source code covered by the entity (start and end positions).
- *Extra Info:* Metadata like the line number, which is used for error reporting.

*Design Patterns:*

- *Factory Pattern:* The *Factory Pattern* is applied for creating AST nodes, simplifying the process of generating various node types (e.g., function nodes, operation nodes, and conditional nodes).
- *Visitor Pattern:* We use the *Visitor Pattern* for traversing and processing the AST. This pattern is particularly useful for exporting the tree structure to external formats like text files, facilitating AST visualization and debugging.
- *Composite Pattern:* The *Composite Pattern* allows for flexible and hierarchical organization of AST nodes. For instance, high-order functions, which take other functions as arguments, are treated as composite nodes that may contain multiple child nodes.

### 3. *Semantic Analyser*

**Purpose:**

The semantic analyzer validates the correctness of operations in the code, ensuring type compatibility and logical integrity. It also simplifies the AST for efficient execution.

**Technical Details:**

*Key Features:*

- Arithmetic Operations:
    + Checks for operand presence and type compatibility.
    + Simplifies constant expressions.
- Logical Operations:
    + Validates boolean operands.
- Comparison Operations:
    + Ensures type compatibility for comparisons.

*Implementation Details:*
- Operates directly on the AST without modifying its structure.
- Optimizes constant expressions to enhance interpretation speed.

### 4. *Code Interpretation*

**Purpose:**

The code interpreter processes the Abstract Syntax Tree (AST) to evaluate and execute the source code. It supports a wide range of language constructs, including variables, functions, loops, conditional statements, and arithmetic operations.

**Technical Details:**

1. *Key Features:*

    Core Language Constructs:
    - Variables: 
        - Supports declaration, assignment, and referencing.
    - Functions:
        - Handles function declarations and calls.
        - Replaces function parameters with arguments during execution.
    - Loops:
        - Implements iteration constructs such as ```while``` loops.
        - Includes robust handling for the ```break``` statement to enable early exit from loops.
    - Conditional Statements:
        - Supports ```cond``` constructs, allowing conditional branching.
    - Arithmetic Operations:
        - Evaluates mathematical expressions involving addition, subtraction, multiplication, division, and nested expressions.
2. *Advanced Features:*

    - Break Statement Handling:
        - ```break``` statements are implemented using custom exceptions for controlled flow interruption.
        - During loop execution, encountering a ```break``` statement raises a specific exception, terminating the loop gracefully and resuming execution outside the loop.
    - Scope Management:
        - Manages function parameters, local variables, and nested scopes.
        - Provides proper isolation and ensures that variables in one scope do not affect others.
        - Allows seamless management of nested function calls and iterative constructs.
    - Expression Simplification:
        - Replaces function parameters with actual arguments during calls.
        - Pre-evaluates arithmetic operations and constant expressions during AST analysis, reducing runtime overhead.
    - Execution Flow
        - AST Traversal: Recursively evaluates nodes in the AST.
            - **Leaf Nodes**: Directly resolved as literals or variables.
            - **Composite Nodes**: Evaluates child nodes before combining their results.
        - Error Handling:
            - Provides clear and precise error messages for issues such as undefined variables, incorrect types, or invalid operations.
            - Maintains execution stability even in the presence of recoverable errors.

3. *Code Interpretation Process:*

    1. AST Traversal:
        - Interpretation begins at the root node (```ProgNode```) of the AST.
        - The interpreter recursively visits all child nodes using the *Visitor pattern*.
        - Each node type is evaluated based on its specific behavior and structure:
            - **Literal Nodes**: Directly resolve to their values.
            - **Variable Nodes**: Retrieve or assign values in the current scope.
            - **Composite Nodes**: Combine results of child nodes (e.g., expressions, function calls).
    2. Node Evaluation:
        - ```ProgNode```:
            - Acts as the entry point of interpretation.
            - Evaluates child nodes and outputs their results to the terminal if they belong to the global scope.
        - **Function Nodes**:
            - Parameters are replaced with arguments during evaluation.
            - Executed within their own local scope, maintaining isolation.
        - **Control Flow Nodes**:
            - Conditional statements (```cond```) directly execute based on evaluated conditions.
            - Loops (```while```) repeatedly execute their body until the condition is no longer satisfied.
        - **Break Handling**:
            - ```break``` statement is implemented using exception handling to terminate loops cleanly.
    3. Scope Handling:
        - **Global Scope**:
            - Stores global variables and function definitions.
            - Outputs the final results of computations.
        - **Local Scope**:
            - Created for functions and control structures (e.g., loops).
            - Ensures variables and computations are encapsulated and do not interfere with other scopes.
