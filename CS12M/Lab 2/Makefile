#---------------------------------
#	Makefile
#	Antonio	Valenzuela
#	anlvalen@ucsc.edu
#	CS12B
#	July 12, 2017
#	PA1
#	A	Makefile	with	macros
#---------------------------------

JAVASRC	=	FileReverse.java
SOURCES	=	README	Makefile	$(JAVASRC)
MAINCLASS	=	FileReverse
CLASSES	=	FileReverse.class
JARFILE	=	FileReverse
SUBMIT	=	submit	cmps012b-pt.u17	lab2

all:	$(JARFILE)

$(JARFILE):	$(CLASSES)
	echo	Main-class:	$(MAINCLASS)	>	Manifest
	jar	cvfm	$(JARFILE)	Manifest	$(CLASSES)
	rm	Manifest
	chmod	+x	$(JARFILE)	
$(CLASSES):	$(JAVASRC)
	javac	-Xlint	$(JAVASRC)

clean:
	rm	$(CLASSES)	$(JARFILE)

submit:	$(SOURCES)
	$(SUBMIT)	$(SOURCES)

check:
	ls	/afs/cats.ucsc.edu/class/cmps012b-pt.u17/lab2/anlvalen