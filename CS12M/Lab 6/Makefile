#------------------------------------------------------------------------------
#	Makefile
#	Antonio	Valenzuela
#	anlvalen@ucsc.edu
#	CS12B
#	August 15, 2017
#	Lab6
#	Makefile with macros
#------------------------------------------------------------------------------

# Variables
JARFILE	=	ListClient
MAINCLASS	=	ListClient
SOURCES	=	List.java	ListInterface.java	ListClient.java\
	ListIndexOutOfBoundsException.java
CLASSES	=	List.class	ListInterface.class	ListClient.class	List\$$Node.class\
	ListIndexOutOfBoundsException.class
SUBSOURCES	=	List.java	ListInterface.java	ListClient.java	ListIndexOutOfBoundsException.java\
	Makefile	Makefile1	README	ListTest.java
SUBMIT	=	submit	cmps012b-pt.u17	lab6

# Build Targets
all:	$(JARFILE)

$(JARFILE):	$(CLASSES)
	echo	Main-class:	$(MAINCLASS)	>	Manifest
	jar	cvfm	$(JARFILE)	Manifest	$(CLASSES)
	rm	Manifest
	chmod	+x	$(JARFILE)

$(CLASSES):	$(SOURCES)
	javac	-Xlint	$(SOURCES)  

clean:
	rm	-f	$(CLASSES)	$(JARFILE)

submit:	$(SOURCES)
	$(SUBMIT)	$(SUBSOURCES)

check:
	ls	/afs/cats.ucsc.edu/class/cmps012b-pt.u17/lab6/anlvalen
