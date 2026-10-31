# Java Guesser — CS121

A menu-driven Java program with two games: the human guesses a random number from 1 to 100, or the computer finds a number chosen by the human using binary search. All functionality is in one `Guesser` class.

## Compile and run

```bash
javac Guesser.java
java Guesser
```

Or use `make` and `make run`. `make clean` removes compiled class files.

## Algorithms and pseudocode

### Main
1. Create a `Guesser` object.
2. Its constructor starts the main menu loop.

```
MAIN:
    CREATE new Guesser
```

### Menu
1. Print the available commands.
2. Read the user's choice as a string.
3. Return the choice.

```
MENU:
    PRINT 0 Exit, 1 Human Guesser, 2 Computer guesser
    PROMPT for choice
    READ one line and remove surrounding spaces
    RETURN choice
```

### Main loop (constructor)
1. Create a Scanner for standard input.
2. Show the menu until the user chooses 0.
3. Run the selected game, or display an error for an invalid menu choice.

```
CREATE Scanner
REPEAT:
    choice = MENU()
    IF choice is "1": HUMAN_GUESSER()
    ELSE IF choice is "2": COMPUTER_GUESSER()
    ELSE IF choice is not "0": PRINT invalid choice
UNTIL choice is "0"
```

### Human guesser
1. Generate one random integer in the range 1–100.
2. Start the attempt counter at zero.
3. Ask the human for a guess; read it as text.
4. Reject input that is not a whole number from 1–100 without counting it.
5. Count a valid guess.
6. Say too low, too high, or correct; repeat until correct.

```
HUMAN_GUESSER:
    target = random integer from 1 through 100
    attempts = 0
    LOOP:
        PROMPT for a number
        READ text
        IF text is not an integer: PRINT error; CONTINUE
        CONVERT text to integer guess
        IF guess outside 1..100: PRINT error; CONTINUE
        attempts = attempts + 1
        IF guess < target: PRINT too low
        ELSE IF guess > target: PRINT too high
        ELSE: PRINT correct and attempts; RETURN
```

### Computer guesser — binary search
1. Ask the human to think of a number from 1–100.
2. Set the possible range to `low = 1`, `high = 100`.
3. Guess the midpoint (integer division). This starts at 50.
4. Ask whether the guess is too high (H), too low (L), or correct (C).
5. If high, set `high = guess - 1`; if low, set `low = guess + 1`.
6. If correct, finish. Otherwise repeat while a possible number remains.
7. If the range becomes empty, report inconsistent answers.

```
COMPUTER_GUESSER:
    low = 1
    high = 100
    attempts = 0
    WHILE low <= high:
        guess = low + (high - low) / 2 using integer division
        attempts = attempts + 1
        PRINT guess
        READ H, L, or C (ignore case)
        IF C: PRINT success and attempts; RETURN
        ELSE IF H: high = guess - 1
        ELSE IF L: low = guess + 1
        ELSE: PRINT error; attempts = attempts - 1
    PRINT contradictory answers
```

With consistent responses, binary search finds any number in 1–100 in at most 7 guesses. It never uses randomness.

## Extra effort
- Invalid menu, number, and H/L/C input is handled without crashing.
- Invalid human guesses do not count as attempts.
- Inconsistent computer-game answers are detected rather than causing an infinite loop.
- The program shows how many guesses were needed.
