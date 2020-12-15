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
    ssize_t nread;
    int counter;		// Counter used for multiple file inputs
    if(argc==1) {		
	printf("tgrep:	searchterm [file...]\n"); 	// tgrep was passed no arguments
	exit(1);
    } 
    if(argc==2) {
	while ((nread = getline(&line, &len, stdin)) != -1) {	// tgrep was passed a searchterm but no file
	    char *exists = strstr(line, argv[1]);		// compare the search term to standard input 
	    if(exists) {
		printf("%s", line);
	    }			
	}
    }
    if(argc>=3)
    {
        for (counter=2;counter<argc;counter++) {		// for each file passed in
            FILE *fp = fopen(argv[counter], "r");		// try to open the file
            if (fp == NULL) {					// if the file does not open correctly, exit(1)
                printf("tgrep: cannot open file\n");
                exit(1);
            }
            while ((nread = getline(&line, &len, fp)) != -1) {	// read the entire file 
		char *exists = strstr(line, argv[1]);		// check if the search term exists in each line of the file
		if(exists) {
		    printf("%s", line);
		}	
	    }
            fclose(fp);						// close the file
        }
    }
    return 0;
}

