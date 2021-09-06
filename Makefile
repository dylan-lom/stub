.PHONY: clean

CLASSES=Stub.class

test: $(CLASSES)
	javap Stub | java -ea Stub 2>/dev/null

%.class: %.java
	javac -Xlint $<

clean:
	rm -f $(CLASSES)
