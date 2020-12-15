#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <string.h>

int gindex;
int gargc;
char ** gargv;

void* cons_proc(void *data) {
      while (gindex < gargc) {
        if (isalpha( gargv[gindex][0] ) && !strchr( "AEIOUaeiou", gargv[gindex][0] )) {
            printf("CONSONANT: %s\n", gargv[gindex]);
            gindex++;
        } else {
            sched_yield();
        }
    }
}
void* vowel_proc(void *data) {
    while (gindex < gargc) {
        if (isalpha( gargv[gindex][0] ) && strchr( "AEIOUaeiou", gargv[gindex][0] )) {
            printf("VOWEL: %s\n", gargv[gindex]);
            gindex++;
        } else {
            sched_yield();
        }
    }
}
void* others_proc(void *data) {
    while (gindex < gargc) {
        if (!isalpha( gargv[gindex][0])) {
            printf("OTHER %s\n", gargv[gindex]);
            gindex++;
        } else {
            sched_yield();
        }
    }         
}

int main(int argc, char *argv[]) {
    gindex = 1;
    gargc = argc;
    gargv = argv;
    
    pthread_t vowels;
    pthread_t consonants;
    pthread_t others;

    if (argc < 2)
        printf("need to have some command line arguments\n");
    else {
        pthread_create(&consonants, NULL, cons_proc, NULL);
        pthread_create(&vowels, NULL, vowel_proc, NULL);
        pthread_create(&others, NULL, others_proc, NULL);

        pthread_join(consonants, NULL);
        pthread_join(vowels, NULL);
        pthread_join(others, NULL);
    }
}