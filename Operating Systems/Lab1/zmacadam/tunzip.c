#include<stdio.h>
#include<stdlib.h>

// tunzip.c
// Zach Macadam
// Operating Systems
// Dr. Scherger
// Due Date: 2/2/2020


int main(int argc,char* argv[])
{
    char cur;						// current character read from the file		
    int counter;					// file counter	
    int count;						// character count
    unsigned long filesize;				// length of file in bytes
    if(argc==1) {					// no file was passed to tunzip, exit(1) 
	printf("tunzip:	file1 [file2...]\n");
	exit(1);
    }
    if(argc>=2)
    {
        for (counter=1;counter<argc;counter++) {	// for each file passed
            FILE *fp = fopen(argv[counter], "r");	// attempt to open the file
            if (fp == NULL) {				// file failed to open, exit(1)
                printf("tunzip: cannot open file\n");
                exit(1);
            } else {					// calculate the length of the file
	   	fseek(fp, 0 , SEEK_END);
 		filesize = ftell(fp);
  		fseek(fp, 0 , SEEK_SET);
	    }
	for (int s = 0; s < (filesize/5); s++) {		// loop over the length of the file divided by 5 (because 5 bytes encodes the number and character to unzip)
	    if (fread(&count, sizeof(int), 1, fp) == 1) {
		if (fread(&cur, 1, 1, fp) == 1) {
	            for (int i = 0; i < count; i++) {		// loop for the integer read from the file
			 printf("%c", cur);			// print the character read from the file
		    }
		}	
            } 
	}
	fclose(fp);						// close the file 
        }
    }
    return 0;
}

