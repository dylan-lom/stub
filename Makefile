.PHONY: clean

CLASSES=Stub.class

all: $(CLASSES)

test: $(CLASSES)
	java -ea Stub < Example.def

%.class: %.java
	javac -Xlint $<

clean:
	rm -f $(CLASSES)
