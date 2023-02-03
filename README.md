# Pacman
A pacman game based on a game engine inspired by the structure of DOOM.
Pacman Game READ.ME File
In our pacman game, the main goal is for the pacman to collect coins in order to pass as many levels as possible. During his chase for the coins, the pacman needs to deal with ghost figures which are trying to hunt him. 

Our system is built based on a state machine moving from the WelcomeState, which is the opening state. Next comes PlayState – where most of the game occurs and lastly the ResultState - where you can find  all the details about how the game ended – win or lose, score and last level.

First, when arriving to the Welcome State, a menu is presented. The menu allows you to choose between 3 different options.
“Start game” will lead you to the play state with the default version of the game mainly regarding the speed of the pacman.
“Pacman turbo” leads you to the play state as well but to an accelerated version, pacman speed wise.
“Exit” option will exit the game. 

Our implementation includes 4 main characters – Pacman, Ghosts, Coins(regular coin, special coin) and the maze itself.
As the pacman starts the movement inside the boundaries of the maze, each collision with the wall forces him to stay put or move to an available direction rather than the wall.
While collecting the coins, each collision with a coin causes a couple of events:
1) The coin disappears from the maze(“collected”).
2) The coin collected is added to the score according to its value. 
Regular coin – 1 point, Special coin – 50 point.
Simultaneously, the pacman needs to avoid collision with ghosts. In case of a ghost-pacman collsion there a 2 phases. The first phase is a color flickering phase in which the pacman’s colors flickers from yellow to white and he moves to a state where he can’t collect coins but also can’t get hit by a ghost. After 5 seconds the pacman shifts to a dying state. The dying state includes 1 life decrement and also a repositioning to the starting point. 
More collisions are ghost-to-ghost collision which makes the ghosts change color and ghost-to-maze collision which makes the specific cell of the collided cell maze.
All collisions are managed in pairs of characters. The two characters and the intersection between them are checked and valued mainly referring the dimensions and activeness of the characters. For example if the coin still appears in the maze.

