#------------------------------------------------------------------------------
#	Makefile
#	Antonio	Valenzuela
#	anlvalen@ucsc.edu
#	CS12B
#	August 19, 2017
#	PA5
#	A	Makefile	with	macros
#------------------------------------------------------------------------------

ADT	=	Dictionary
EXEBIN	=	$(ADT)Client
SOURCES	=	$(ADT).c	$(EXEBIN).c
OBJECTS	=	$(ADT).o	$(EXEBIN).o
HEADERS	=	$(ADT).h
SUBMIT	=	submit	cmps012b-pt.u17	pa5
SUBSOURCES	=	$(ADT).c	$(ADT)Client.c	$(ADT)Test.c	Makefile	Makefile1	README	$(ADT).h

all:	$(EXEBIN)

$(EXEBIN)	:	$(OBJECTS) $(HEADERS)
	gcc	-o	$(EXEBIN)	$(OBJECTS)

$(OBJECTS)	:	$(SOURCES)	$(HEADERS)
	gcc	-c	$(FLAGS)	$(SOURCES)

clean	:
	rm	-f	$(EXEBIN)	$(OBJECTS)

check:
	valgrind	--leak-check=full	$(EXEBIN)

submit:	$(SUBSOURCES)
	$(SUBMIT)	$(SUBSOURCES)
 
checksub:
	ls	/afs/cats.ucsc.edu/class/cmps012b-pt.u17/pa5/anlvalen