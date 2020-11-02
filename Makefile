# Darius-Florentin Neatu neatudarius@gmail.com
# Copyright 2018

# Exemplu de Makefile pentru tema

# tag-uri obligatorii (nume + comportament identic)
# build    = compileaza toata tema 
#             (ATENTIE! E important - NU compilati in tag-urile de run. Sesizati)
# run-p$ID = ruleaza problema cu ID-ul specificat (1, 2, 3, 4)
# clean    = sterge toate fisierele generate

# restul este la alegerea studentului
# TODO

# nume necesar (build)
build: 
	javac *.java

run-p1: 
	java Bani

run-p2: 
	java Gard

run-p3: 
	java Bomboane

run-p4:
	 java Sala 

clean:	
	rm -f *.class
