# The escape from the woods
## Read the map file from the resources folder and count the least number of steps needed to get out of the forest.

## Map file examples:
## Map1.txt

### 11111
### 1 X 1
### 1 1 1
### 1   1
### 111 1

## Map2.txt

### 11111111111
### 1     1   1
### 1 1 1 111 1
### 1 1 1     1
### 1 1 1 11111
### 1   1X    1
### 1 1111111 1
### 1   1     1
### 111 1 111 1
### 1   1 1   1
### 1 111 11111

## Game rules
### • Find the fastest way out from the forest and return steps count as an answer (output)
### • At the starting position (at the symbol “X”), your steps count is 0
### • You can only move through the empty spaces (symbol “ ”)
### • If the file is corrupted or there is no exit – return 0

## Map Characteristics
### • The map is a rectangular shape
### • Axis size: 5 <= X <= 11000, 5 <= Y <= 11000
### • Map consists of symbols “ ”, “1”, “X”:
### • “ ” – empty spaces where you can move
### • “1” – forest/trees
### • “X” – your start position
### • There could be from 0 to 1000 exits
