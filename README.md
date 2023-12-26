# Introduction

I have not been able to generate a working solution. This README details my approach to this problem across a couple different veticals:
- First, I will address how my algorithm works at a data structure level
- Next, I will walk through my attempts to find edge cases or interpretations I could be missing
  - A living document detailing areas I am working on and my brainstorming process is available [here](./ideas.md)
- Finally, I will address my API connection architecture and controller methods here


## Algorithm: Data Structure and General Approach

For this exercise, I have used a Doubly Linked List in order to solve any given challenge.

### IBlockNode objects

IBlockNode Objects represent a node in a Linked list of block nodes. Each contains the contents of one "block" in a barcode. An entire list corresponds to one barcode.
![IBlockNode linked list](./src/LinkedList.png)

IBlockNode Objects support the following functions:
