#------------------------------------------------------------------------------
#	Makefile
#	Antonio	Valenzuela
#	anlvalen@ucsc.edu
#	CS12B
#	August 9, 2017
#	PA4
#	A	Makefile	with	macros
#------------------------------------------------------------------------------

JAVAC	=	javac 
MAINCLASS	=	Simulation
TESTCLASS	=	QueueTest
JAVASRC	=	$(wildcard	*.java)
SOURCES	=	$(JAVASRC)	Makefile	README
CLASSES	=	$(patsubst	%.java,	%.class,	$(JAVASRC))
JARCLASSES	=	$(patsubst	%.class,	%*.class,	$(CLASSES))
JARFILE	=	$(MAINCLASS) 
JARTEST	=	$(TESTCLASS)
SUBMIT	=	submit	cmps012b-pt.u17	pa4

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
	ls	/afs/cats.ucsc.edu/class/cmps012b-pt.u17/pa4/anlvalen

test:	$(TESTCLASS)

$(TESTCLASS):	$(CLASSES)
	echo Main-class:	$(TESTCLASS)	>	Manifest
	jar	cvfm	$(JARTEST)	Manifest	$(JARCLASSES)
	chmod	+x	$(JARTEST)
	rm	Manifest
	
cleantest:
	rm	-f	*.class	$(JARTEST)