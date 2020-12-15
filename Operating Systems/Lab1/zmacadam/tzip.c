#include<stdio.h>
#include<stdlib.h>
#include<string.h>

// tzip.c
// Zach Macadam
// Operating Systems
// Dr. Scherger
// Due Date: 2/2/2020


int main(int argc,char* argv[])
{
    int counter, count;				// file counter and character counter	
    int cur;					// current character read from file
    int prev;					// previous character read from file
    unsigned long fileLen;			// length of file read used to loop through file 1 byte at a time
    unsigned long i;				// loop variable
    if(argc==1) {
        printf("tzip:   file1 [file2...]\n");	// no file was passed to the program, exit(1)
        exit(1);
    }
    if(argc>=2)
    {
        for (counter=1;counter<argc;counter++) {	// for each file given
            FILE *fp = fopen(argv[counter], "rb");	// attempt to open the file
            if (fp == NULL) {				// if the file failed to open
                printf("tzip: cannot open file\n");	// exit(1)
                exit(1);
            }
		fseek(fp, 0, SEEK_END);			// calculate the length of the file
		fileLen = ftell(fp);
		fseek(fp, 0, SEEK_SET);
		count = 0;
		for (i = 0; i < fileLen+1; i++) {			// for the length of the file (in bytes)
			cur = fgetc(fp); 				// get the current byte read from the file
			if (count == 0) {				// if count is 0, this is the first byte of the file
				prev = cur;				// set the previous character
				count++;				// increment the number of repeated characters
			} else if (cur == prev) {			// if the previous and current character are repeated
				count++;				// increment the number of repeated characters	
			} else {					// the characters did not match	 	
				fwrite(&count, sizeof(int), 1, stdout); // write the 4 byte integer denoting how many characters were repeated
				fwrite(&prev, 1, 1, stdout);		// write the character	
				prev = cur;
				count = 1;
			}
		} 
            fclose(fp);							// close the file
        }
    }    
    return 0;
}

