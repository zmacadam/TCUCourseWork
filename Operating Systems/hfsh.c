#define _POSIX_C_SOURCE 200809L
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

// tgrep.c
// Zach Macadam
// Operating Systems
// Dr. Scherger
// Due Date: 2/2/2020


int main(int argc,char* argv[])
{
    char *line = NULL;		// Buffer used to hold the line read from getline
    size_t len = 0;		// Length of the line read
    int counter;		// Counter used for multiple file inputs 
	while (1) {
        getline(&line, &len, stdin); 	// tgrep was passed a searchterm but no file 
		printf("%s", line);		
    }
    return 0;
}

