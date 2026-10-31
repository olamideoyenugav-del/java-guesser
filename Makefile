.PHONY: all run clean
all: Guesser.class
Guesser.class: Guesser.java
	javac Guesser.java
run: all
	java Guesser
clean:
	rm -f *.class
