# CSC 130 Final

This project was made for my CSC 130 (Data Structures and Algorithms) class. It is a simple 2D game with collision detection and object interaction. This was my very first big project made, excuse me for the spaghetti code.

---

## Quickstart

### Prerequisites
- [Java JDK 8+](https://www.oracle.com/java/technologies/javase-downloads.html) installed  
- (Optional) [Eclipse IDE](https://www.eclipse.org/downloads/) if you want to open and edit the project  

---

## Running the Project

### Option 1: Run in Eclipse
1. Open Eclipse  
2. Go to: `File` → `Import...` → `Existing Projects into Workspace`  
3. Select the project folder and click **Finish**  
4. Right-click `Main.java` (inside the `src/Main/` folder) → `Run As` → `Java Application`

### Option 2: Run without Eclipse (for users)
1. Open a terminal in the project directory  
2. Compile all source files into the `bin/` folder:
   ```bash
   javac -d bin src/*/*.java
3. Run the Main class
    '''bash
    java -cp bin Main.Main

## Project Structure

    '''bash
    your-project/
    ├── .settings/        # Eclipse settings
    ├── Art/              # Art assets
    ├── bin/              # Compiled classes
    │   ├── Data/
    │   ├── FileIO/
    │   ├── gameloop/
    │   ├── Graphics/
    │   ├── Input/
    │   ├── logic/
    │   ├── Main/         # Contains Main.class (entry point)
    │   └── timer/
    ├── Font/             # Font assets
    └── src/              # Java source code
        ├── Data/
        ├── FileIO/
        ├── gameloop/
        ├── Graphics/
        ├── Input/
        ├── logic/
        └── Main/         # Contains Main.java (entry point)
