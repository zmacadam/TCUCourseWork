#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>

void *foo_thread(void* data) {
    int* array = (int*)data;
    int ii;

    for(ii=0; ii<10; ii++) 
        printf("Hello from foo thread: %d\n", array[ii]);
    printf("\n");
}

void *bar_thread(void* data) {
    //long second_int = (long)data;
    printf("Hello from bar thread\n");
}


int main(int argc, char*argv[]) {

    pthread_t fooid;
    pthread_t barid;

    int A[]= {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

    pthread_create(&fooid, NULL, foo_thread, (void*)A);
    pthread_create(&barid, NULL, foo_thread, (void*)A);
    pthread_join(fooid, NULL);
    pthread_join(barid, NULL);


    return(EXIT_SUCCESS);
}
