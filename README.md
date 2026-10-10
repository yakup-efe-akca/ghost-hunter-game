# 👻 GhostHunterINC (2D Arcade Game)

This project is a 2D grid-based arcade game developed in Java. A level-based arcade game where the player must clear ghosts using a vacuum and advance to progressively harder stages before the time runs out.

## 👥 Development Team & Roles

* **Yakup Efe Akca:** 
  * **Game Engine:** Built the core `GameLoop` using JavaFX `AnimationTimer` for smooth 60 FPS gameplay.
  * **Game State & Levels:** Created the level progression, win/lose conditions, timers, and the in-game HUD.
  * **Spawning System:** Programmed the spawn mechanics for all enemies and power-up tokens.
  * **Collision Logic:** Implemented `Shape.intersect` for enemy damage, token collection, and vacuum mechanics.
  * **Player Mechanics:** Developed the `Hunter` class (4-way movement, health/energy tracking, map boundaries).
  * **Configuration:** Built the `Config` class to read game settings from a text file into a `HashMap`.
     
* **Arif Erdem Taşgın:** [Onun görevleri, örn: Character movement mechanics and map design]
  
* **Yavuz Selim Durdubaş:** 
  * **UI & HUD Implementation:** Developed the `BarsAndTimes` HUD to display and dynamically scale the health and vacuum energy bars.
  * **Menu & Scene Management:** Built the `MenuManager` to create the Main Menu, Level Selection scenes, and manage stage transitions.
  * **End-Game Interfaces:** Designed the `EndGamePanels` to display transparent overlays for "Win" and "Game Over" screens alongside the final score.
  * **Custom UI Components:** Created interactive JavaFX buttons (`MenuButton`, `LoseButton`, `SelectLevelButton`) with custom fonts, graphic-based previews, and hover/click color feedback.
  * **Level Design:** Designed the visual theme and spatial dimensions for the Level 3 environment.

## 🚀 Installation and Setup

To run this project on your local machine:
1. Click the green "Code" button at the top right and select "Download ZIP".
2. Extract the downloaded folder and open it with your preferred Java IDE (IntelliJ IDEA, Eclipse, etc.).
3. Compile and run the `GameLoop.java` file.
