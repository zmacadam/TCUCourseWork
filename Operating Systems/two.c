#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>

// global variables
double gavg = 0.0;
int geven = 0;
int godd = 0;


// function prototypes
void* compute_avg(void *data) {
    int ii;
    int *a = (int*)data;
    int size = a[0];
    int sum = 0;

    for(ii=1; ii<=size; ii++) {
        sum += a[ii];
    }
    gavg = (sum*1.0)/size;
}
void* compute_even(void *data) {
    int ii;
    int *a = (int*)data;
    int size = a[0];
    
    for(ii=1; ii<=size; ii++) {
        if(a[ii]%2 == 0)
            geven++;
    }
}
void* compute_odd(void *data) {
    int ii;
    int *a = (int*)data;
    int size = a[0];
    
    for(ii=1; ii<=size; ii++) {
        if(a[ii]%2 == 1)
            godd++;
    }
}



int main(int argc, char *argv[]) {

    // local variables
    int* array;
    int ii;
    pthread_t avgt;
    pthread_t event;
    pthread_t oddt;

    if(argc == 1) {
        printf("Usage: %s i1 [i2...]\n", argv[0]);
    } else {
        array = (int*)malloc(argc*sizeof(int));
        array[0] = argc-1;
        for(ii=1; ii<argc; ii++) {
            array[ii] = atoi(argv[ii]);
        }
        pthread_create(&avgt, NULL, compute_avg, (void*)array);
        pthread_create(&event, NULL, compute_even, (void*)array);
        pthread_create(&oddt, NULL, compute_odd, (void*)array);
        
        pthread_join(avgt, NULL);
        pthread_join(event, NULL);
        pthread_join(oddt, NULL);

        printf("%lf\t%d\t%d\n", gavg, geven, godd);
    }


    return(EXIT_SUCCESS);
}