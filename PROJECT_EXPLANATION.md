
# VangtiChai - Project Explanation

This document provides a detailed explanation of the VangtiChai Android application's codebase.

## 1. High-Level Overview

VangtiChai is a simple yet functional Android application built using Kotlin. Its primary purpose is to act as a change calculator. The user can input a monetary amount, and the application will calculate the minimum number of banknotes of various denominations required to make up that amount.

The application follows a single-activity architecture, with the main screen controlled by `MainActivity.kt`. The user interface (UI) is defined in the `activity_main.xml` layout file. It leverages standard Android Jetpack libraries, including AppCompat for backward compatibility and Material Components for a modern user interface.

## 2. Project Structure

The project follows the standard Android project structure:

```
VangtiChai/
├── app/
│   ├── build.gradle.kts  // App-level build script
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── vangtichai/
│   │   │   │               ├── MainActivity.kt  // The main activity
│   │   │   │               └── ui/              // UI-related classes (currently empty)
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml  // UI layout for the main activity
│   │   │   │   └── ... (other resource files)
│   │   │   └── AndroidManifest.xml  // App manifest file
│   └── ...
├── build.gradle.kts      // Project-level build script
└── ...
```

*   **`app/`**: This is the main module of the application.
*   **`app/build.gradle.kts`**: This file contains the dependencies and build configurations for the `app` module.
*   **`app/src/main/java`**: This directory contains the Kotlin source code for the application.
*   **`app/src/main/res`**: This directory contains all the resources for the application, such as layouts, strings, and drawables.
*   **`app/src/main/AndroidManifest.xml`**: This file describes the essential information about the application to the Android build tools, the Android operating system, and Google Play.

## 3. Dependencies

The application relies on the following key dependencies, as defined in `app/build.gradle.kts`:

*   **`androidx.core:core-ktx`**: Provides Kotlin extensions for Android's core functionalities.
*   **`androidx.appcompat:appcompat`**: Enables the use of modern Android features on older devices.
*   **`com.google.android.material:material`**: Implements Material Design components, which are used to create the app's UI.
*   **`androidx.constraintlayout:constraintlayout`**: A flexible and powerful layout manager for creating complex and responsive user interfaces.
*   **`junit`**, **`androidx.test.ext:junit`**, **`androidx.test.espresso:espresso-core`**: Libraries for unit and UI testing.

The project also has `viewBinding` enabled, which allows for type-safe access to views in the layout files, reducing the risk of null pointer exceptions and eliminating the need for `findViewById`.

## 4. UI Layout (`activity_main.xml`)

The user interface is defined in `activity_main.xml` and is built using a `ConstraintLayout` as the root element. This allows for a flat and flexible view hierarchy.

The UI is divided into two main sections:

*   **Left Side (Change Display):**
    *   A `TextView` (`amountTextView`) at the top displays the amount entered by the user.
    *   A `TableLayout` (`changeTableLayout`) displays the breakdown of the change into different banknote denominations (500, 100, 50, 20, 10, 5, 2, and 1). Each row in the table represents a denomination and contains two `TextView`s: one for the note's label and another to display the count of that note.

*   **Right Side (Keypad):**
    *   A `GridLayout` (`keypadGridLayout`) contains the numeric keypad (buttons 0-9) and a "Clear" button.
    *   The digit buttons all share the `onDigitClick` method as their `onClick` handler.
    *   The "Clear" button has the `onClearClick` method as its `onClick` handler.

A `Guideline` is used to create a vertical division between the change display and the keypad, ensuring a balanced and responsive layout.

## 5. Code Logic (`MainActivity.kt`)

The `MainActivity.kt` file is the heart of the application and contains all the logic for the app's functionality.

### 5.1. Class Properties

*   **`amountTextView`**, **`note500`**, ..., **`note1`**: These are `lateinit var` properties that hold references to the `TextView`s in the layout. They are initialized in the `onCreate` method.
*   **`amount`**: A `Long` variable that stores the amount entered by the user. It is initialized to 0.

### 5.2. Key Methods

*   **`onCreate(savedInstanceState: Bundle?)`**:
    *   This method is called when the activity is first created.
    *   It sets the content view to `R.layout.activity_main`.
    *   It initializes all the `TextView` properties using `findViewById`.
    *   It checks for a `savedInstanceState` to restore the `amount` if the activity was re-created (e.g., due to a screen rotation).

*   **`onSaveInstanceState(outState: Bundle)`**:
    *   This method is called to save the activity's state before it is destroyed.
    *   It saves the current value of `amount` to the `outState` bundle.

*   **`onDigitClick(view: View)`**:
    *   This method is the `onClick` handler for all the digit buttons.
    *   It retrieves the digit from the clicked button's text.
    *   It updates the `amount` by appending the new digit.
    *   It calls `updateUI()` to refresh the screen.

*   **`onClearClick(view: View)`**:
    *   This is the `onClick` handler for the "Clear" button.
    *   It resets the `amount` to 0.
    *   It calls `updateUI()` to refresh the screen.

*   **`updateUI()`**:
    *   This private helper method is responsible for updating the UI.
    *   It sets the `text` of the `amountTextView` to the current `amount`.
    *   It calls `calculateChange()` to perform the change calculation.

*   **`calculateChange(totalAmount: Long)`**:
    *   This is the core logic of the application.
    *   It takes the `totalAmount` as input.
    *   It uses a series of integer divisions and modulo operations to calculate the number of notes for each denomination.
    *   It updates the `text` of the corresponding `TextView` for each note with the calculated count.

## 6. How It All Works Together

1.  The user launches the application, and the `MainActivity` is created.
2.  The `onCreate` method initializes the UI and sets up the initial state.
3.  The user presses the digit buttons to enter an amount.
4.  For each digit button press, the `onDigitClick` method is called, which updates the `amount` and calls `updateUI`.
5.  The `updateUI` method displays the new amount and calls `calculateChange`.
6.  The `calculateChange` method calculates the note breakdown and updates the respective `TextView`s on the screen.
7.  If the user presses the "Clear" button, the `onClearClick` method resets the amount and updates the UI.
8.  If the user rotates the screen, the `onSaveInstanceState` method saves the current amount, and the `onCreate` method restores it, ensuring a seamless user experience.

This comprehensive explanation should provide a solid understanding of the VangtiChai application's codebase and help you in your viva and presentation.

## 7. Stateful vs. Stateless and Their Relation to the Code

### Stateful

A stateful component remembers information about past interactions. In VangtiChai, the `MainActivity` is a prime example of a stateful component.

**How it relates to the code:**

*   **`amount` variable:** The `MainActivity` has a class-level variable `amount` which stores the current number entered by the user. This variable's value persists across different method calls like `onDigitClick` and `onClearClick`.
*   **`onSaveInstanceState` and `onCreate`:** The statefulness is further reinforced by the implementation of `onSaveInstanceState` and `onCreate`. When a configuration change occurs (like screen rotation), the Android system destroys and recreates the activity. To prevent the loss of the entered amount, `onSaveInstanceState` is used to save the value of the `amount` variable into a `Bundle`. Then, in the `onCreate` method, the code checks if a `savedInstanceState` bundle exists and, if so, restores the value of `amount`. This mechanism explicitly manages and preserves the state of the activity.

### Stateless

A stateless component, in contrast, does not hold any memory of past events. Its operations are self-contained, and its output is solely determined by its input for a given call.

**How it relates to the code:**

*   **`calculateChange(totalAmount: Long)` function:** The `calculateChange` function in `MainActivity` is a perfect illustration of a stateless operation. It takes a `totalAmount` as an argument and calculates the number of notes for each denomination based purely on this input. It does not rely on any class-level variables or any previously stored state. If you call this function multiple times with the same `totalAmount`, it will always produce the exact same result, without any side effects on the application's state.
*   **`onDigitClick(view: View)` (in isolation):** While the `onDigitClick` method as a whole contributes to the stateful nature of the `MainActivity` by modifying the `amount` variable, the initial part of the method that simply reads the digit from the clicked button (`(view as Button).text.toString().toLong()`) is a stateless operation. It takes the `View` as input and returns the corresponding digit without reference to any previous state.
