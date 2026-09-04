[README.md](https://github.com/user-attachments/files/31840367/README.md)
# Task Manager

A simple console-based Task Manager built in Java as a first OOP project. Add tasks, mark them complete, delete them, and persist everything to disk as JSON.

## Features

- Add tasks with a name and description
- View active (incomplete) tasks
- View completed tasks
- Mark tasks as complete / mark them active again
- Delete tasks (with confirmation)
- Live task counts shown in the main menu
- Data automatically saved to `tasks.json` on exit and loaded back in on startup

## Tech Stack

- **Java** — core language, no frameworks
- **Maven** — dependency management and build
- **[Gson](https://github.com/google/gson)** — JSON serialization/deserialization for persistence

## Getting Started

### Prerequisites

- JDK installed
- Maven (or an IDE with built-in Maven support, e.g. IntelliJ IDEA)

### Running

Clone the repo and run via your IDE, or from the command line:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="Main"
```

On first run, no `tasks.json` file exists yet — the app starts with an empty task list. From then on, tasks are saved automatically when you exit through the menu.

## Menu Overview

```
[0] Exit
[1] Add new task
[2] View active tasks
[3] View completed tasks
```

