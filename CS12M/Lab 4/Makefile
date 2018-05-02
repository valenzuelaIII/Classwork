#---------------------------------
#	Makefile
#	Antonio	Valenzuela
#	anlvalen@ucsc.edu
#	CS12B
#	July 29, 2017
#	Lab4
#	A	Makefile	with	macros
#------L---------------------------

CSRC	=	charType.c
FLAGS	=	-std=c99	-Wall
SOURCES	=	README	Makefile	$(CSRC)
OBJECTS	=	charType.o
EXEBIN	=	charType
SUBMIT	=	submit	cmps012b-pt.u17	lab4

all:	$(EXEBIN)

$(EXEBIN)	:	$(OBJECTS)
	gcc	-o	$(EXEBIN)	$(OBJECTS)

$(OBJECTS)	:	$(SOURCES)
	gcc	-c	$(FLAGS)	$(CSRC)

clean:
	rm	-f	$(EXEBIN)	$(OBJECTS)

check:
	valgrind	--leak-check=full	charType	charType.c	outfile

submit:	$(SOURCES)
	$(SUBMIT)	$(SOURCES)

checksub:
	ls	/afs/cats.ucsc.edu/class/cmps012b-pt.u17/lab4/anlvalen