#---------------------------------
#	Makefile
#	Antonio	Valenzuela
#	anlvalen@ucsc.edu
#	CS12M	-	Thursday	2pm-4pm
#	July 4, 2017
#	lab1
#	A	Makefile	with	macros
#------L---------------------------

JAVASRC	=	HelloUser.java	HelloUser2.java
SOURCES	=	README	Makefile	$(JAVASRC)
MAINCLASS	=	HelloUser2
CLASSES	=	HelloUser.class	HelloUser2.class
JARFILE	=	Hello
SUBMIT	=	submit	cmps012b-pt.u17	lab1

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
	ls	/afs/cats.ucsc.edu/class/cmps012b-pt.u17/lab1/anlvalen