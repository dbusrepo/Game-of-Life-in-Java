# Game of Life in Java
This is a Java gui application implementing the famous Game of Life. Graphics output is based on another project of mine based on Swing/AWT (included here as git subtree).

In this project I have implemented and compared three possible different implementations of Life:

1. basic impl with neighbors counting for each cell
2. cells storing alive neighbors counter, updating it when state changes
3. same as above but with a change list to avoid a full scan of the grid at each gen

The 2nd and 3rd are much more efficient, with the the 3rd being the fastest, because:

* After a few dozens generations most of the cellmap consists of dead cells
* Cells change state relatively infrequently
* Cells that are going to change in the next gen are only the neighbors of those cells that changed in the current gen

Screen:

[![life-In-Action.png](https://i.postimg.cc/zGrZrP0q/life-In-Action.png)](https://postimg.cc/rKQYGjBH)

### How to run
Run with gradle using `./gradlew run` or `gradlew.bat run` from the cl.

