/** 
 * Brian Fekete
 * 3/10/17
 */
#include <sys/types.h>
#include <stdio.h>	
#include <unistd.h> // chdir
#include <string.h> // strtoka

#define MAX_LINE 80 /* The maximum length command */
#define BUFFER_SIZE 25
#define READ_END 0
#define WRITE_END 1

void parseCommand(char *line, char **argArr);

int main(void) {
  printf("Welcome to Brian Fekete's Shell\n");
  
  char line[MAX_LINE];
  char *args[MAX_LINE/2 + 1]; /* command line arguments */
  int should_run = 1; /* Flag to determine when to exit program */
  
  while(should_run){
    printf("bfek>");
    fflush(stdout);
    
    fgets(line, sizeof(line), stdin);
    
    /**
     * After reading user input, the steps are:
     * (1) Fork a child process using fork()
     * (2) The child process will invoke execvp()
     * (3) If command included &, parent will invoke wait()
     */
     if(strncmp(line, "exit", 4) == 0){
       printf("Exiting Shell...\n");
       should_run = 0;
     } else {
       parseCommand(line, args);
       
       if(strncmp(args[0], "cd", 2) == 0){
        chdir(args[1]);
        continue;
       }
       
     /*
      char write_msg[BUFFER_SIZE] = "Greetings";
      char read_msg[BUFFER_SIZE];
      
      int fd[2];
      // Create the pipe 
      if (pipe(fd) == -1) {
        fprintf(stderr, "Pipe Failed");
        return 1;
      }
      */
      pid_t pid;
      pid = fork();
      
      if (pid < 0) { // error occurred 
        fprintf(stderr, "Fork failed");
        return 1;
      } else if (pid > 0){ // Parent Process
        // Parent will wait for the child to complete
        wait(NULL);
        
        /*
        //close the unused end of the pipe
        close(fd[READ_END]);
        
        // Write to the pipe 
        write(fd[WRITE_END], write_msg, strlen(write_msg) + 1);
        
        // close the write end of the pipe 
        close(fd[WRITE_END]);
        */
      } else if (pid == 0) { // Child Process
        printf("Child Process\n");
        int execvpStatusCode = execvp(*args, args);
        if(execvpStatusCode != 0){
          printf("*args:%s\nargs[0]:%s\nCode:%d\n",*args, args[0], execvpStatusCode);
          fprintf(stderr, "Command Failed\n");
        }
        /*
        // close the unused end of the pipe 
        close(fd[WRITE_END]);
        
        // Read from the pipe 
        read(fd[READ_END], read_msg, BUFFER_SIZE);
        printf("read %s\n", read_msg);
        
        // Close the read end of the pipe 
        close(fd[READ_END]);
        */  
      }
       
     }
     
  }
  return 0;
}

void parseCommand(char *line, char **argArr){
  printf("parseCommand *line:%s\n" , line);
  char *args = strtok(line, "\n "); // Execvp fails if i just use a space
  printf("parseCommand *args:%s\n", args); 
  int i = 0;
  
  while(args != NULL){
    argArr[i] = args;
    printf("while:%s\n", argArr[i]);
    args = strtok(NULL, "\n ");  // strtok returns a pointer to a null terminated string containing the next token
    i++;
  }
  argArr[i] = NULL;
  printf("parseCommand Successful\n");
}


