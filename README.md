# Minesweeper

Classic Minesweeper with a JavaFX UI — built as a four-person team project for Bucknell's software engineering course (Spring 2022).

## Features

- Four difficulty levels — easy, medium, hard, insane — with increasing bomb counts
- Light and dark themes
- Bombs are placed after the first click, so the opening move is always safe
- Flagging, neighbor-bomb counts, a game timer, and win/loss detection

## Tech

- Java 17 + JavaFX 17 (graphics, controls, FXML)
- Gradle build with the OpenJFX plugin
- JUnit 5 tests
- Game logic (`Board`, `Cell`, `Game`) separated from the JavaFX view/controller layer

## Build & Run

Requires JDK 17.

```bash
./gradlew build   # compile + run tests
./gradlew run     # launch the game
```

## Design Docs

UML class diagram, use cases, and the design/user manuals live in [`Design/`](Design) and [`Docs/`](Docs).
