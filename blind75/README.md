#   Blind 75 Java Solutions

Java IMplementations of the Blind 75 algorithm problems, organized as one class per problem.

This repository is intended as a personal interview-prep reference with readable Java solutions
and helper data structures

---

## Table of Contents

- [Project Layout](#project-layout)
- [How to Compile](#how-to-compile)
- [How to Run](#how-to-run)


---

## Project Layout

blind75/
    README.md
    src/
        roo/
            solution/
                P01TwoSum.java
            utils/
                DebugLogger.java
                ListNode.java
                TreeNode.java

Each problem file generally follows this naming pattern:
    P<number><ProblemName>.java


## How to Compile

From the project root
    mkdir -p bin
    javac -d bin src/roo/solution/*java

## How to Compile
    java -cp bin roo.solution.SomeClassName
