"# 4-Square-Cipher-File-Encryption" 
Description 
The program takes in a specified text file alongside a user-specified output file. It then encrypts 
the input file, outputting this encryption to the self-specified output file. this can then decrypt 
the output file, to match the text of the originally encrypted input file again. 

To run 
The program will allow you to select options [1] through [4]. 
The application may optionally list available input files from a directory named txtfiles, located 
in the project’s working directory at runtime using option [1], the directory is not included in the 
submission and must be created by the user if they wish to use this feature. 
Input files can alternatively be specified directly by relative path. 

-Option 2: will prompt you for the name of the file you wish to encrypt. 
You will then be prompted for what output file you wish to use; which you need only to type 
[filename]; the name you typed in will be used to create a .txt file. 

-Option 3: upon selecting this option; the currently encrypted output file will be decrypted, this 
decryption will be within the same output file; just with the previously decrypted text now 
overwritten. 

Features

-"Which file would you like to encrypt" 
Will list all the files in the “txtfiles” directory. 

-"Specify output file"
This feature will take input from the user “randomfilename” and append the file path for the 
.txtfiles directory alongside appending the string “.txt” to the end of the specified file name; this creates an output file where the encrypted text will go for the user. 

-"Randomly generated 4 Square"
Upon booting of the program, without requiring the user to enter any key input themselves, the 
program creates and shuffles two 25 letter char arrays; and uses these to create the CipherText 
quadrants of the 4 square.
