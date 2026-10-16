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
     
* **Arif Erdem Taşgın:**
* # Ghost Hunter - Core Engine & Mechanics

This section of the project focuses on the core game engine architecture, the physics/collision system, and the primary gameplay mechanics built using JavaFX.

## 🚀 Key Features & Responsibilities (Contributor 3)

### 1. Game Loop Architecture (AnimationTimer)
Implemented a custom, time-based game loop using JavaFX's `AnimationTimer`. 
* Calculated precise delta time (`second`) to ensure consistent movement, energy depletion, and entity scaling regardless of frame rate fluctuations.
* Handled concurrent modifications safely by iterating through entity and token lists dynamically (`size() - 1 to 0`).

### 2. Polymorphic Token System
Engineered a scalable, object-oriented buff system using inheritance and polymorphism.
* **Health Token:** Instantly restores player HP using pre-configured map bounds.
* **Range Token:** Permanently expands the player's vacuum scanner area.
* **Eye Token (Time-based):** Triggers a 5-second "X-Ray" vision mode, revealing all invisible entities globally. 
* *Architecture:* Handled uniformly via a polymorphic `applyBenefit(Hunter h)` method, allowing the game loop to process all token types seamlessly without messy `switch` statements.

### 3. Advanced Collision Detection
Solved the infamous JavaFX nested `Group` coordinate problem (where inner shapes default to `0,0`).
* Replaced flawed `Shape.intersect` logic with robust `localToScene` and `getBoundsInParent()` bounding box calculations.
* Ensured pixel-perfect collision detection between the Hunter, dynamic Tokens, and paranormal entities regardless of their parent group translations.

### 4. Vacuum Mechanic & Mouse Tracking
Developed the primary "Luigi's Mansion" style ghost-catching mechanic.
* **Dynamic Shrinking:** Ghosts caught inside the scanner bounds progressively scale down (`setScaleX/Y`) based on delta time, and are entirely removed from the scene and memory upon reaching a `<0.1` scale threshold.
* **Trigonometric Aiming:** Integrated `Math.atan2` to track the user's mouse coordinates (`setOnMouseMoved` / `setOnMouseDragged`), allowing the hunter's scanner triangle to smoothly rotate 360 degrees and aim precisely at the cursor.

### 5. Debugging & Cheat Key System
Built comprehensive developer tools to assist with balancing and testing:
* ** God Mode:** Instantly restores Health and Vacuum Energy to maximum, and applies an infinite duration to the Eye Token (Global visibility).
* ** Debug Hitboxes:** Toggles a visual overlay showing the exact dashed physical borders of the playable area and forces all ghost entities to become visible to test boundary collisions.
* *Logic Management:* Built a unified, conflict-free visibility state manager (`h1.isEyeActive() || isBeingVacuumed || cheatActive`) to prevent frame-flickering.
  
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
