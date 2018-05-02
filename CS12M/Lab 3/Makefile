#---------------------------------
#	Makefile
#	Antonio	Valenzuela
#	anlvalen@ucsc.edu
#	CS12B
#	July 19, 2017
#	Lab3
#	A	Makefile	with	macros
#------L---------------------------

CSRC	=	FileReverse.c
FLAGS	=	-std=c99	-Wall
SOURCES	=	README	Makefile	$(CSRC)
OBJECTS	=	FileReverse.o
EXEBIN	=	FileReverse
SUBMIT	=	submit	cmps012b-pt.u17	lab3

all:	$(EXEBIN)

$(EXEBIN)	:	$(OBJECTS)
	gcc	-o	$(EXEBIN)	$(OBJECTS)

$(OBJECTS)	:	$(SOURCES)
	gcc	-c	$(FLAGS)	$(CSRC)

clean:
	rm	-f	$(EXEBIN)	$(OBJECTS)

submit:	$(SOURCES)
	$(SUBMIT)	$(SOURCES)

check:
	ls	/afs/cats.ucsc.edu/class/cmps012b-pt.u17/lab3/anlvalen