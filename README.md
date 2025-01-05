# java-memory-game

lang [EN]: [en](https://github.com/fpetranzan/java-memory-game/blob/master/README.md) | [it](https://github.com/fpetranzan/java-memory-game/blob/master/README_it.md)

Made during my study period, this is my first most complex project.

Memory is a game in which all cards are placed face down on a surface and two cards are turned face up each turn. The aim of the game is to discover matching pairs of cards.

A player's turn ends when he turns over two cards and finds no pair. Otherwise, he has the right to be able to lay down two more cards.

Memory can be played both by multiple players and solo.

## Technology

- Java 8
- Java Swing

The project I created tries to replicate this game, trying to create different game modes and implementing some features similar to a computer game. The cards present and the pairs available to be completed will be represented with numbers.

PREMISE: the texts within the images will be in Italian

<img src="https://github.com/user-attachments/assets/eda1aae9-62a3-46b7-b3d4-7f76fa8c4248" alt="Enter Name" width="400"/>

<img src="https://github.com/user-attachments/assets/99f224f1-aa55-450b-b4c3-1acc2f5484e5" alt="Start Game" width="400"/>

All features available:

File

- New Game
- Load Game
- Restart Game
- Save Game
- Exit

Edit

- Resolution
- Mode
- Difficulty
- Sounds

View

- Game rules
- Ranking

The main features I want to explain more about are:

### Load Game

<img src="https://github.com/user-attachments/assets/c58d8e58-7223-4243-a811-affc769e4f3f" alt="Load Game" width="400"/>

During a game it is possible to select the "Save Game" item and by providing only a name for saving it will be stored.

At a later time it will be possible to recover the save and resume the game where you left off.

Saving occurs by writing to a file, which is read and loaded when requested

### Difficulty

<img src="https://github.com/user-attachments/assets/3a6fba2a-cf0d-4dfd-a068-2add9b321c32" alt="Change Difficulty" width="400"/>

In the “Edit > Difficulty” menu you can choose between three different options:

- Easy (4x4)
- Medium (6x6)
- Hard (8x8)

Depending on the difficulty chosen, the player's field will be modified by showing him a larger field, with a greater number of cards and pairs to find.

### Mode

<img src="https://github.com/user-attachments/assets/f8b8fe59-ee87-46b4-bd4a-0a3a2a2ffeb6" alt="Change Mode" width="400"/>

You can choose between three different game modes:

- Single Player
- Two Players
- Computer

In the “Two Players” mode you will be asked to enter a new name and the two players will be able to challenge each other.

In “Computer” mode, the opponent will not be a player but the program. The logic implemented for the choice of cards by the program is very simple, the first card drawn is always random among those still available on the field, if the chosen card is equal to one of the last 4 cards turned over in the whole game, the pair is completed (this logic allows the program to "simulate" a memory) otherwise, if it is not possible to complete the pair, the second card is also drawn randomly

ATTENTION: This "memory" of the program has some defects since, by always extracting the first card randomly, the following interaction occurs during the game:

| Turn      | Card 1 | Card 2 |
|-----------| --- | --- |
| Computers | “5” | “12” (1) |
| Player    | “9” | “12” (2) |

Having found the pair of “12s”, a player would be able to complete it. In this case, the computer would only be able to complete the pair if it were to randomly draw "12" as the first card.

| Turn | Card 1 | Card 2 | Result |
| --- | --- | --- | --- |
| Computers | “12” (1) | “12” (2) | ✅ |
| Computers | “7” | … | ❌ |

ATTENTION: It is possible to combine the choice of Mode with Difficulty so it will be possible to play:

| Difficulty | Mode |
| --- | --- |
| Easy | Single Player |
| Difficult | Single Player |
| Medium | Two Players |
| Difficult | Computers |
| … | … |

### Turns and Scoring

In order to make the game and the different modes a little more "challenging", I introduced two new aspects to the game.

The first concerns the management of turns, in fact, each player (but also in "Single Player" mode) for each turn will have a limited time to turn over the two cards. (yellow square of the image)

The time available is represented in the timer present in the information bar above the playing field and corresponds to a time of 9 seconds.

At the end of this time, if the player has not turned over both cards, the first chosen card will be covered and the turn will end.

<img src="https://github.com/user-attachments/assets/e1f4110c-2d11-462a-804e-1e4d8ef16d06" alt="Turns and Points" width="400"/>

The second aspect concerns the score (red square in the image), when the player taking the turn finds a pair 5 points will be added to his score, while for each wrong pair 1 point will be subtracted.

Obviously the score can never go below 0 and in the case of modes other than "Single Player" the winner will be the one who, at the end of the game and with all the pairs found, has the highest score.

In addition to these there are other features, but of lesser impact so I found their explanation secondary.

I remember that this was, what for me can be defined as, my first project.

Obviously it has several BUGS and above all the quality of the code is certainly not good.
