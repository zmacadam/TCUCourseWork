#include<stdio.h> 
#include<stdlib.h>

// tcat.c 
// Zach Macadam
// Operating Systems
// Dr. Scherger
// Due Date: 2/2/2020 
  
int main(int argc,char* argv[]) 
{
    char line[256]; // Buffer used to read lines from file
    int counter;    // Counter used for multiple file inputs 
    if(argc==1) {   // If no file is specified, return 0
	return 0;
    }
    if(argc>=2) 
    { 
        for (counter=1;counter<argc;counter++) { 	// For each file given as input
	    FILE *fp = fopen(argv[counter], "r");	// Attempt to open the file
	    if (fp == NULL) {
		printf("tcat: cannot open file\n");
		exit(1);
	    }
	    while (fgets(line, 256, fp) != NULL) {	// Read the entire file
		printf("%s", line);			// Print the current line of the file
	    }
	    fclose(fp);					// Close the file 
	} 
    } 
    return 0; 
} 
