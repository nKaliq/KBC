#include<stdio.h>
#include<conio.h>
#include<process.h>
#include<dos.h>
#include<string.h>
#define MAX 20
/*
Max Defines maximum number of question to be asked
*/

int t1,t2,t3; //token to check if the lifelines are taken or not

void title();
void lifeline(char);
void question(char[],char[],char[], char[],char[], char);
void StartScreen();

void game(); //game code
void inst(); //instructinos
void winner(); //winner screen

void read(); //read from file to array
void write(); //write to file

void message(char[]);   //put warning message
void clrmsg();          //clr warning message

//for having set of 20 question with their options and answers
struct quest
{
	char q[80];
	char a[30], b[30], c[30], d[30];
	char ans;
};

struct quest set[MAX];


int main()
{
  clrscr();
  StartScreen();

  getch();
  return 0;
}


// Title of the Game
void title()
{
  gotoxy(35,1);
  textcolor(6);
  cprintf("Welcome To");
  gotoxy(30,2);
  textcolor(WHITE);
  cprintf("Kon Banega Crorepati");
  gotoxy(37,3);
  textcolor(GREEN);
  cprintf("2017");
}


// Lifeline codes
void lifeline(int i)
{
  /*assing value 1 to t1,t2,t3 denotes that
  these timeline options have been used */

  if(i==1||t1==1)
  t1=1;
  else if(i==2||t2==2)
  t2=1;
  else if(i==3||t3==1)
  t3=1;

    textcolor(BLUE);
  if(t1==0){
  gotoxy(10,7);
  cprintf("#1 Phone a Frined");}
  if(t2==0){
  gotoxy(35,7);
  cprintf("#2 50-50");}
  if(t3==0){
  gotoxy(50,7);
  cprintf("#3 Audience Poll");}

}

//for displaying a particular set of questions
void question(char q[80], char a[15],char b[15], char c[15], char d[15], char ans)
{
gotoxy(5,18);
printf("Q. %s",q);
gotoxy(5,20);
printf("A. %s",a);
gotoxy(38,20);
printf("B. %s",b);
gotoxy(5,22);
printf("C. %s",c);
gotoxy(38,22);
printf("D. %s",d);

}


// The main menu
void StartScreen()
{
  char a;
  do
  {
  clrscr();
  title();
  gotoxy(10,7);
  cprintf("______________________________________________________________");
  gotoxy(1,9);

  printf("\t\t\tPress A to start Game\n");
  printf("\t\t\tPress B for Instructions\n");
  printf("\t\t\tPress C to add new Question\n");
  printf("\t\t\tPress E to know About KBC17\n");
  printf("\t\t\tPress X to exit\n");
  printf("\n\t\t\tEnter your choice: ");
  a=getch();
  if(a=='A'||a=='a')
  {
       game();
       break;
  }
  else if(a=='B'||a=='b')
  {
	inst();
	break;
  }
  else if(a=='C'||a=='c')
  {
	write();
	break;
  }
  else if(a=='E'||a=='e')
  {
  clrscr();
  title();
  gotoxy(20,5);
  printf("\t   MCA 1st Semester Project");
  printf("\n\t\t\tProblem Solving and Programming");
  printf("\n\n\t\t\t\tDeveloped By -");
  printf("\n\n\t\t\t * * * * NITIN KUMAR * * * *");

  gotoxy(10,23);
  printf("Press any key to go back....");

  getch();
  StartScreen();
  }
  else if(a=='X'||a=='x')
  {
  exit(0);
  }
  else
  {
  printf("Invalid Choice!!!");
  getch();
  }
  }while(1);

}


void write()
{
char c;
int i;
clrscr();
    for(i=0;i<MAX;i++)
    {
    printf("\n %d : ",i+1);
    puts("Enter Question");
     gets(set[i].q);
     puts("*Enter Option A");
     gets(set[i].a);
     puts("*Enter Option B");
     gets(set[i].b);
     puts("*Enter Option C");
     gets(set[i].c);
     puts("*Enter Option D");
     gets(set[i].d);
     puts("*** Enter Correct Option");
     set[i].ans=getch();
     printf("%c\n\n",set[i].ans);

     puts("Any key for next || Exit:x || save and Exit: s  ");
     c=getch();
     if(c=='x')
     StartScreen();
     else if(c=='s')
     break;


    }

     FILE* data;
    if ( (data = fopen("data.bin", "wb")) == NULL )
    {
	printf("Error opening file\n");
	getch();
	exit(0);
    }

    fwrite(set, sizeof(struct quest) * MAX, 1, data);
    fclose(data);

   StartScreen();
}


void read()
{
 FILE* data;
    if ((data = fopen("data.bin", "rb")) == NULL)
    {
	printf("Error opening file\n");
	getch();
	exit(0);
    }


    fread(set, sizeof(struct quest) * MAX /*Reading all set*/, 1, data);

    fclose(data);

}

void inst()
{
clrscr();
gotoxy(1,5);
printf("******* KBC 17 Instructions ********\n");
printf("\n * This game is based on Answering Multiple Choise Quesions");
printf("\n * To select an option press the Alphabet key associated with the option.");
printf("\n * If your chosen option is correct then you move on to the next question");
printf("\n * If the chosen option is incorrect you lose the game");
printf("\n * You have three life lines that you and access only once each.");
printf("\n * To select a life line, press the number key associated with it.");
printf("\n * There will be total 20 questions being asked");
printf("\n * At any moment you wish to quit the game to main menu press ESC");

printf("\n\n\n GOOD LUCK");
printf("\n\n\n press any key to go back ....");
getch();
StartScreen();
}

void game()
{
	char ch;
	int i=0;
	int life=0;
	t1=t2=t3=0;
	read();
 do
 {
  clrscr();
  title();
  lifeline(life);
  question(set[i].q,set[i].a,set[i].b,set[i].c,set[i].d, set[i].ans );
  ch=getch();


  if(ch=='1')
  {
       if(t1==1){
       message("You already used this life line");
       getch();
       }
       else if(t1==0)
       {
       message("Life line 1 to be shown here");
       getch();
       }

       life=1;
  }
  else if(ch=='2')
  {
       if(t2==1){
       message("You already used this life line");
       getch();
       }
       else if(t2==0)
       {
       message("A=30%  ||  B=70%   ||   C=15%   ||   D=90%");
       getch();
       }

       life=2;
  }
  else if(ch=='3')
  {
       if(t3==1){
       message("You already used this life line");
       getch();
       }
       life=3;
  }
  else if(ch=='A'||ch=='a')
  {
       if(set[i].ans=='a' || set[i].ans=='A')
       {
       i++;
       message("     Congratulation ! It is Correct Answer    ");
       getch();
       }
       else
       {
	message("Your Answer is Incorrect! Better Luck next time");
	getch();
	StartScreen();
       }
  }
  else if(ch=='B'||ch=='b')
  {
       if(set[i].ans=='b' || set[i].ans=='B')
       {
	i++;
	message("     Congratulation ! It is Correct Answer    ");
	getch();
       }
       else
       {
	message("Your Answer is Incorrect! Better Luck next time");
	getch();
	StartScreen();
       }
  }
  else if(ch=='C'||ch=='c')
  {
       if(set[i].ans=='c' || set[i].ans=='C')
       {
	i++;
	message("     Congratulation ! It is Correct Answer     ");
	getch();
       }
       else
       {
	message("Your Answer is Incorrect! Better Luck next time");
	getch();
	StartScreen();
       }
  }
  else if(ch=='D'||ch=='d')
  {
       if(set[i].ans=='d' || set[i].ans=='D')
       {
	i++;
	message("    Congratulation ! It is Correct Answer      ");
       getch();
       }
       else
       {
	message("Your Answer is Incorrect! Better Luck next time");
	getch();
	StartScreen();
       }
  }
  else if(ch==27)
  {
  StartScreen();
  break;
  }

  if(i==MAX-1)
  {
    winner();
  }

  }while(1);
}

void winner()
{
     clrscr();
     puts("u win");
     getch();
     StartScreen();
}

void message(char msg[30])
{
     gotoxy(8,9);
     cprintf("_________________________________________________________");
     gotoxy(8,16);
     cprintf("_________________________________________________________");

     gotoxy(10,11);
     cprintf(msg);
}

void clrmsg()
{
     gotoxy(8,11);
     cprintf("                                                         .");
     gotoxy(8,12);
     printf("                                                          .");
}
