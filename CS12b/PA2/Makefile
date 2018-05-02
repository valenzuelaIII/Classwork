#---------------------------------
#	Makefile
#	Antonio	Valenzuela
#	anlvalen@ucsc.edu
#	CS12B
#	July 16, 2017
#	PA2
#	A	Makefile	with	macros
#------L---------------------------

JAVASRC	=	Search.java
SOURCES	=	README	Makefile	$(JAVASRC)
MAINCLASS	=	Search
CLASSES	=	Search.class
JARFILE	=	Search
SUBMIT	=	submit	cmps012b-pt.u17	pa2

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
	ls	/afs/cats.ucsc.edu/class/cmps012b-pt.u17/pa2/anlvalen