#include<stdlib.h>
#include<errno.h>
#include<stdio.h>
#include<string.h>
#include<unistd.h>
#include<fcntl.h>
#include<ctype.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>

//Change maximum number of arguments per command here
#define max_args 20

// hfsh.c
// Zach Macadam
// Operating Systems
// Dr. Scherger
// Due Date: 3/1/2020

//Struct used to hold command names and their arguments
   struct command {
	char *cmd;
	char *args[max_args];
	int num_args;
	int redirect;
	char *output;
   };

int main(int argc,char* argv[])
{
//Change number of maximum commands per line here
    int max_cmds = 10;
//Change number of maximum PATH variables here
    int max_path = 25;

	
//Defining data members to be used throughout execution
    size_t full_path;
    int input;
    int parallel;
    int amp;
    int error;
    int redirect;
    pid_t pp;
    pid_t *processes;
    FILE *fp;
    struct command *commands;
    char **PATH;
    char *cur;
    char *tok;
    int test;
    int num_cmds;
    int temp_num_a;
    char *line = NULL;          // Buffer used to hold the line read from getline
    size_t len = 0;             // Length of the line read
    char error_message[30] = "An error has occurred\n";

//Create the PATH array of strings, it can hold 25 PATH strings
    PATH = malloc(max_path * sizeof(char*));
//Set the PATH to "/bin" upon starting the shell
    PATH[0] = "/bin";
//Set the PATH length to 1
    int path_len = 1;

//If argc == 1, run the shell in interactive mode
    if (argc == 1) {
	input = 1;
//If argc == 2, run the shell in batch mode
    } else if (argc == 2) {
        fp = fopen(argv[1], "r");       // Attempt to open the file
        if (fp == NULL) {
		//ERROR
		write(STDERR_FILENO, error_message, strlen(error_message));
        	exit(1);
        } else {
		input = 0;
	}
//Else the shell program was invoked with invalid arguments
    } else {
	input = -1;
	write(STDERR_FILENO, error_message, strlen(error_message));
	exit(1);
    } 
//Keep looping until end of file reached
	while (1) {	
		//Reset variables to 0 so the shell continues executing correctly 
		amp = 0;
		error = 0;
		redirect = 0;
		parallel = 0;
		num_cmds = 0;
		temp_num_a = 0;
//If input == 1 then the shell is in interactive mode
		if (input) {
			fputs("hfsh> ", stdout);
        		getline(&line, &len, stdin); 
			//CTRL+D has been recognized, exit gracefully
			if(feof(stdin)) {
				printf("\n");
				//graceful exit :)
				exit(0);
			} 
//Else the shell is in batch mode
		} else {
			if ((getline(&line, &len, fp)) == -1) {
				//The end of file has been recognized, exit gracefully
				//graceful exit :)
				exit(0);
			}
			//This loop checks for unnecessary white space (lines containing no characters other than spaces)
			int white_check = 1;
			for (int i = 0; i < strlen(line); i++) {
                                if (!isspace(line[i]))
                                        white_check = 0;
                        }
			//If the line is all white space, read the next line instead
			if (white_check) {
				if ((getline(&line, &len, fp)) == -1) {
					exit(0);
				}
			}
		}
		//Check to see if the ext command has been reached to avoid unnecessary parsing operations from running
		test = strcmp("exit\n", line);
		if (test == 0) {
			exit(0);
		}
		//Check to see if the line contains parallel commands
		if (strstr(line, "&") != NULL) {
			parallel = 1;
			int num_amp = 0;
			//Count the number of "&" in the line
			for (int i = 0; i < strlen(line); i++) {
				char amp_check = line[i];
				if (amp_check == '&') {
					num_amp++;
				}
			}
			//New strings are used to parse the input correctly
			char *newstr = malloc(sizeof(char) * strlen(line)+25);
			char *space = malloc(sizeof(char) * strlen(line)+25);
			//For each "&", we are looking for a command preceeding it
			for (int j = 0; j < num_amp; j++) {
				cur = strsep(&line, "&");
				if ((strcmp("", cur)) == 0) {
					break;
				}
				strcat(newstr, cur);
				strcat(newstr, " & ");
				if ((strcmp("", cur)) == 0 || (strcmp("\n",cur)) == 0) {
					break;
				}
			}
			//Get the rest of the line following the first command and "&" pair 
			cur = strsep(&line, "");
			strcat(newstr, cur);
			line = strdup(newstr);
                        int s = 0;
			//This loop gets rid of unnecessary white space by looking for two or more characters in a row that are whitespace and condensing them
                        for (int i = 0; i < strlen(line)-1; i++) {
                                if (isspace(line[i])) {
                                        while (i < strlen(line)-1 && isspace(line[i+1])) {
                                                i++;
                                        }
                                        if (strlen(space) != 0) {
                                                space[s] = ' ';
                                                s++;
                                        }
                                } else {
                                        space[s] = line[i];
                                        s++;
                                }
                        }
			//Set line to our new parsed input with correct spacing
			line = strdup(space);
                	free(newstr);
		}	 
		if (strstr(line, ">") != NULL) {
                        int num_red = 0;
			//Count the number of ">" in the line
                        for (int i = 0; i < strlen(line); i++) {
                                char red_check = line[i];
                                if (red_check  == '>') {
                                        num_red++;
                                }
                        }
                        redirect = 1;
			//New strings are used to parse the input correctly
			char *newstr = malloc(sizeof(char) * strlen(line) + 25);
			char *space = malloc(sizeof(char) * strlen(line) + 25);
			//For each ">", we are looking for a command preceeding it and an output file after it
			for (int j = 0; j < num_red; j++) {
                                cur = strsep(&line, ">");
                                if ((strcmp("", cur)) == 0 && j == 0) {
					error = 1;
                                        break;
                                }
                                strcat(newstr, cur);
                                strcat(newstr, " > ");
                                if ((strcmp("", cur)) == 0 || (strcmp("\n",cur)) == 0) {
                                        break;
                                }
                        }
                        cur = strsep(&line, "");
			if ((strcmp("", cur)) == 0 || (strcmp("\n",cur)) == 0) {
                                error = 2;
                        }
			strcat(newstr, cur);
			line = strdup(newstr);
			int s = 0;
			int ln;
			if (!error) {
			if (parallel) {
				ln = strlen(line)+1;
			} else {
				ln = strlen(line)-1;
			}
			//This loop gets rid of unnecessary white space by looking for two or more characters in a row that are whitespace and condensing them
			for (int i = 0; i < ln; i++) {
				if (isspace(line[i])) {
					while (i < strlen(line)-1 && isspace(line[i+1])) {
						i++;
					} 
					if (strlen(space) != 0) {
						space[s] = ' ';
						s++;
					}
				} else {
					space[s] = line[i];
					s++;
				}
			}
			}
			//Set line to the new parsed line with corrected spacing
			line = strdup(space);
			free(newstr);
		}
		 if (redirect == 0 && parallel == 0) {
                        char *space = malloc(sizeof(char) * strlen(line) + 12);
			int s = 0;
			//Loop to get rid of white space as seen above 
			for (int i = 0; i < strlen(line); i++) {
                                if (isspace(line[i])) {
					while (i < strlen(line)-1 && isspace(line[i+1])) {
                                                i++;
                                        }
					if (strlen(space) != 0) {
                                        	space[s] = ' ';
                                        	s++;
					}
                                } else {
                                        space[s] = line[i];
                                        s++;
                                }
                        }
		//Set the line to the new parsed line with correct spacing
			line = strdup(space);
			free(space);
			if (line[strlen(line)-1] == ' ') {
				line[strlen(line)-1] = '\0';
			}
		}
		//Get the first command in the line
		tok = strsep(&line, " ");
		tok[strcspn(tok, "\r\n")] = 0;
		
		//Allocate space for the array of command structs, this shell can take 10 commands as input with 20 arguments per command
		commands = malloc(max_cmds * sizeof(struct command));
		
		//If the first command is cd, it is bulit in and does not need the first argument to be set to cd
		if ((strcmp("cd", tok)) == 0) {
			commands[0].cmd = tok;
		//If the first command is path, it is built in and does not need the first argument to be set to path
		} else if ((strcmp("path", tok)) == 0) { 
			commands[0].cmd = tok;
		//If the "command" is a single ampersand, we know to not execute commands but to keep note of it, it is not an error!
		} else if ((strcmp("&", tok)) == 0) {
			amp = 1;
		//Else the command is not built in to the shell, and we should set the first argument in the command struct to be the command itself
		}  else {
			commands[0].cmd = tok;
			commands[0].args[0] = tok;
			commands[0].num_args = 1;
			temp_num_a = 1;
		}
		//While we have more tokens in the line, keep running to correctly place it
		while ((cur = strsep(&line, " ")) != NULL) {
			//If the current token is "&"
			if ((strcmp(cur, "&")) == 0) {
				//Get the next command following the "&"
				cur = strsep(&line, " ");
				//If the command exists
				if (cur != NULL) {
					cur[strcspn(cur, "\r\n")] = 0;
					//If the command is built in, remember to not set the first argument to the command
					if ((strcmp(cur, "cd")) == 0 || (strcmp(cur, "path")) == 0) { 
						num_cmds++;
                                		commands[num_cmds].cmd = cur;
						temp_num_a = 0;
					//Else the command is not built in, set the first argument to the command itself
                       			} else {
						num_cmds++;
						commands[num_cmds].cmd = cur;
						commands[num_cmds].args[0] = cur;
						commands[num_cmds].num_args = 1;
						temp_num_a = 1;
					}
				} 
			//If the current token is ">"
			} else if ((strcmp(cur, ">")) == 0) {
				//Set the redirect flag of the command to 1
				commands[num_cmds].redirect = 1;
				//Get the output file of the command
				cur = strsep(&line, " ");
				//Set the output file of the command
				commands[num_cmds].output = cur;
				//Get the rest of the line following the output file
				cur =  strsep(&line,"");
				//If the line has not reached the end yet
				if (cur != NULL) {
					//Set line to the current "token" which is actually the entire rest of the line
					line = strdup(cur);
					//Get the next token, it should be a "&" or else it is another output file, which is an error
					char *tok = strtok(cur, " ");
					if ((strcmp(tok,"&")) != 0) {
						error = 3;
					}
				} 
			//Else the current token is an argument and should be added to the current commands argument array
			} else {
				cur[strcspn(cur, "\r\n ")] = 0;
				commands[num_cmds].args[temp_num_a] = cur;
				commands[num_cmds].num_args++;
				temp_num_a++;
			} 
		}
		if (error == 0 && amp == 0) {
		//Allocate memory for the proccess number array for the number of commands given
		processes = malloc(sizeof(pid_t) * num_cmds);
		//Loop for the number of commands input  
		for (int j = 0; j <= num_cmds; j++){
			//Get the current command
                        tok = commands[j].cmd;
			//If the current command is cd, handle it differently
                        if ((strcmp("cd", tok)) == 0 ) {
				//Only one argument should be passed to the cd command
                                if (commands[j].num_args != 1) {
                                        //ERROR TOO MANY ARGS
                                        write(STDERR_FILENO, error_message, strlen(error_message));
                                        break;
                                } else {
					//Try to change directories, if the directory does not exist, it is an error
                                        int ctest = chdir(commands[j].args[0]);
                                        if (ctest != 0) {
                                                //ERROR
                                                write(STDERR_FILENO, error_message, strlen(error_message));
                                                break;
                                        }
                                }
			//If the current command is path, handle it differently
                        } else if ((strcmp("path", tok)) == 0 || (strcmp("path\n", tok)) == 0) {
				//Set path_len to 0 to accomodate the new length
                                path_len = 0;
				//Free the memory associated with the old PATH array
                                free(PATH);
				//Allocate memory for a new PATH array
                                PATH = malloc(max_path * sizeof(char*));
				//If the arguments passed with the path command are not empty
                                if (temp_num_a > 0) {
					//Add each argument to the new PATH array
                                        for (int i = 0; i < commands[j].num_args; i++) {
                                                PATH[i] = commands[j].args[i];
                                                path_len++;
                                        }
                                }
			//Else the current command is not built in and should be executed using execv
                        } else {
				//For each string in the PATH array, we try to execute the command
                                for (int i = 0; i <= path_len; i++) {
                                        if (PATH[i] != NULL) {
						//Allocate memory for a new string, the full path to the command we are trying to execute
                                                cur = PATH[i];
						full_path = strlen(cur) + strlen(tok) + 2;
						char *newstr = malloc(sizeof(char) * full_path);
						for (int j = 0; j < strlen(cur); j++) {
							newstr[j] = cur[j];
						}
						newstr[strlen(cur)] = '/';
						int tok_index = 0;
						for (int k = strlen(newstr);k < (strlen(newstr) + strlen(tok)); k++) {
							newstr[k] = tok[tok_index];
							tok_index++;
						}
						//Try to access the file at the full path length
                                                int at = access(newstr, X_OK);
						//If the file exists and is executable
                                                if (at == 0) {
							//Fork a new process to execute the command
                                                        pp = fork();
							//Add the process to the process id array
							processes[j] = pp;
							//If the current command has its redirect flag set
                                                        if(pp == 0 && commands[j].redirect) {
								//Open a file to write the output to, create it if it does not exist, truncate if it does exist
								int fd = open(commands[j].output, O_RDWR | O_CREAT | O_TRUNC ,S_IRUSR | S_IWUSR);
								//Write both STDOUT and STDERR to the file
								dup2(fd,1);
								dup2(fd,2);
								close(fd);
								//Execute the command with the given arguments and exit on completion
                                                                execv(newstr, commands[j].args);
                                                                exit(EXIT_SUCCESS);
							//The current command does not require redirection, so just execute and exit on success
                                                        } else if (pp == 0) {
								execv(newstr, commands[j].args);
								exit(EXIT_SUCCESS);	
                                                        }
						//We no longer need to loop through the PATH array because the command was executed
						break;
                				}
					//The PATH to the file did not exist, this is an error
					} else {
						//PATH NULL
						write(STDERR_FILENO, error_message, strlen(error_message));
						break;
					}
				}
			}
		}	
		//For each command that was prompted to run
		for (int i =0; i <= num_cmds; i++) {
                        //Get each process id
                        pp =  processes[i];
                        //Infinite loop until the process is finished executing
                        while ((pp = waitpid(-1, NULL, 0))) {
                                if (errno == ECHILD) {
                                        break;
                                }
                        }
                }
		free(processes);
		//I could totally just change the last else statement to "else if (amp == 0)" but this works
		} else if (amp == 0) {	
			write(STDERR_FILENO, error_message, strlen(error_message));
		}
		//All processes have finished executing, go again!
    }
    exit(0);
}

