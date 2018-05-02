#------------------------------------------------------------------------------
#	Makefile
#	Antonio	Valenzuela
#	anlvalen@ucsc.edu
#	CS12B
#	Aug 18, 2017
#	Lab7
#	A	Makefile	with	macros
#------------------------------------------------------------------------------

JAVAC	=	javac 
MAINCLASS	=	DictionaryClient
JAVASRC	=	$(wildcard	*.java)
SOURCES	=	$(JAVASRC)	Makefile	README
CLASSES	=	$(patsubst	%.java,	%.class,	$(JAVASRC))
JARCLASSES	=	$(patsubst	%.class,	%*.class,	$(CLASSES))
JARFILE	=	$(MAINCLASS)
SUBMIT	=	submit	cmps012b-pt.u17	lab7

all:	$(JARFILE)

$(JARFILE):	$(CLASSES)
	echo Main-class:	$(MAINCLASS)	>	Manifest
	jar	cvfm	$(JARFILE)	Manifest	$(JARCLASSES)
	chmod	+x	$(JARFILE)
	rm	Manifest

%.class:	%.java
	$(JAVAC)	$<

clean:
	rm	-f	*.class	$(JARFILE)

submit:	$(SOURCES)
	$(SUBMIT)	$(SOURCES)

check:
	ls	/afs/cats.ucsc.edu/class/cmps012b-pt.u17/lab7/anlvalen
