# uMessage and Implementing Dictionaries

## Overview
This project implements a real-world chat application called "uMessage." In this project, I implemented various data structures, sorting algorithms, and evaluated the performance of different Dictionary implementations used in uMessage. 

## Project Structure

### Part 1: Another WorkList
In this section, I implemented a **MinFourHeapComparable** data structure, which serves as a four-heap for various operations like peeking at the minimum element, deleting the minimum element, and inserting new elements efficiently.

### Part 2: Implementing Dictionary Classes and Sorts
This part involved the creation of essential data structures and sorting algorithms:
- **MoveToFrontList**: I implemented a linked list variant where new items are inserted at the front, and existing items move to the front when referenced.
- **CircularArrayFIFOQueue**: I enhanced this structure by implementing equals and compareTo methods for better usability in Dictionary implementations.
- **AVLTree**: I created an AVLTree, a self-balancing binary search tree, which inherits from BinarySearchTree. I ensured efficient insertion and rotation operations without implementing the delete operation.
- **ChainingHashTable**: I implemented a ChainingHashTable using separate chaining. It dynamically resizes and maintains load factors and is designed to work with various Dictionary implementations.
- **Refactor uMessage**: To optimize uMessage, I replaced Java's HashMap with my ChainingHashTable, improving its performance.

### Part 3: uMessage
This section involved enhancing and testing the uMessage chat application:
- **MinFourHeap and The Sorts**: I modified MinFourHeap to use a Comparator and implemented sorting algorithms like HeapSort, QuickSort, and TopKSort.
- **NGramToNextChoicesMap**: I created a data structure that maps NGrams to words and their counts, using different Dictionary types. I performed performance comparisons to evaluate the efficiency of these implementations.

## Project Requirements
- Avoided using built-in Java data structures, demonstrating my ability to implement data structures from scratch.
- Leveraged the Math package and adhered to good design principles.
- Ensured my code maintained a clean structure and followed the architecture guidelines.
- Avoided duplicating fields present in superclasses and included thorough code comments.
- Debugged my code as I progressed through the project, resolving issues to ensure uMessage functionality.

## Running uMessage
- I increased the heap size to 6GB for optimal uMessage performance.
- Ensured my laptop was plugged in during uMessage operation.
- Edited variables at the top of uMessage.java, including the corpus, "n," "inner dictionary," and "outer dictionary."
- Rigorously tested and debugged uMessage with various configurations to ensure it operates smoothly.

## NGramToNextChoicesMap
- I implemented a feature to map NGrams to words and their counts, handling standardization of input and insertion of a "start of line" (SOL) marker.
- I made use of generic Dictionary types for both outer and inner dictionaries, ensuring flexibility and adaptability.

## Performance of Implementations of Dictionary
- I conducted experiments with different implementations of Dictionary structures to gain insights into their performance characteristics.
- I modified NGramToNextChoicesMap to use specific Dictionary implementations for outer and inner maps.
- I measured and compared the performance of these implementations in the context of uMessage.

## Conclusion
Project 2 has been an enriching experience in which I deepened my understanding of data structures, algorithms, and their real-world applications. My GitHub repository for this project showcases my ability to design and implement complex data structures and optimize an existing application for better performance.
