/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wwtbam;

/*
 * Date: April 9, 2018
 * Name: Brian Zhang
 * Teacher: Mr. Chu
 * 'Who Wants to be a Millionaire' Summative Assignment
 */

import java.util.Scanner;
import java.util.Random;

public class WWTBAM {

    static String[] easyQues = new String[5];
    static String[] medQues = new String[5];
    static String[] hardQues = new String[5];
    static boolean[] easyStates = new boolean[5];
    static boolean[] medStates = new boolean[5];
    static boolean[] hardStates = new boolean[5];
    static String[] easyAns = {"A", "C", "B", "D", "C"};
    static String[] medAns = {"B", "B", "D", "C", "B"};
    static String[] hardAns = {"A", "C", "C", "A", "D"};
    static int[] money = {0,100, 200, 500, 750, 1500, 3000, 5000, 10000, 15000, 20000, 50000, 100000, 200000, 500000, 1000000};
    static boolean fiftyFifty = false;
    static boolean callFriend = false;
    static boolean pollAudience = false;
    static String name;
    static String changeRandLetter;
    static Scanner sc = new Scanner(System.in);
    static int answeredQuestions = 0;
    static Random rand = new Random();
    
    static boolean reprintQuestion=true;

    public static void main(String[] args) {
        initializeQuestions();
        printMessages();

        if (!AskQuestionGroup(easyQues, easyStates, easyAns)) {
            return;
        }
        if (!AskQuestionGroup(medQues, medStates, medAns)) {
            return;
        }
        AskQuestionGroup(hardQues, hardStates, hardAns);
    }

    private static void initializeQuestions() {
        easyQues[0] = "How do you score points in basketball?\n a) Shoot a ball in a net\n b) Hit a birdie with a racquet\n c) Kick a ball across the field\n d) Knockout the opposing players\n";
        easyQues[1] = "How many players does a team have playing on an NBA court at one time?\n a) 6\n b) 10\n c) 5\n d) 3\n";
        easyQues[2] = "Which team won the 2016-2017 NBA championship?\n a) Cleveland Cavaliers\n b) Golden State Warriors\n c) Toronto Raptors\n d) Boston Celtics\n";
        easyQues[3] = "Which team does Lamarcus Aldridge currently play for?\n a) Houston Rockets\n b) Portland Trailblazers\n c) Memphis Grizzlies\n d) San Antonio Spurs\n";
        easyQues[4] = "How many total teams are there in the NBA currently?\n a) 10\n b) 25\n c) 30\n d) 20\n";

        medQues[0] = "How many NBA championships has Michael Jordan won?\n a) 5\n b) 6\n c) 7\n d) 8\n";
        medQues[1] = "Who holds the record for most points scored in one game?\n a) Kobe Bryant\n b) Wilt Chamberlain\n c) Michael Jordan\n d) Bill Russell\n";
        medQues[2] = "What division are the Toronto Raptors in?\n a) Central Division\n b) Southeast Division\n c) Pacific Division\n d) Atlantic Division\n";
        medQues[3] = "Which team has the most amount of championships in history?\n a) Los Angeles Lakers\n b) Chicago Bulls\n c) Boston Celtics\n d) Miami Heat\n";
        medQues[4] = "Who is the youngest player to ever win the MVP award?\n a) LeBron James\n b) Derrick Rose\n c) Stephen Curry\n d) Kawhi Leonard\n";

        hardQues[0] = "Which team won the 1994-1995 NBA championship?\n a) Houston Rockets\n b) Chicago Bulls\n c) Orlando Magic\n d) Phoenix Suns\n";
        hardQues[1] = "What is the current NBA championship trophy called?\n a) Chris MacFord Trophy\n b) Walter A. Brown Trophy\n c) Larry O’Brien Trophy \n d) Jolly Mazgard Trophy\n";
        hardQues[2] = "Which player won the iconic 1988 3-pt contest?\n a) Drazen Petrovic\n b) Peja Stojakovic\n c) Larry Bird\n d) Pete Maravich\n";
        hardQues[3] = "Which player is nicknamed “The Glove” in the NBA?\n a) Gary Payton\n b) Reggie Miller\n c) Charles Barkley\n d) Shawn Kemp\n";
        hardQues[4] = "In which year was the NBA founded?\n a) 1943\n b) 1944\n c) 1945\n d) 1946\n";
    }

    private static void printMessages() {
        System.out.println("Welcome to 'Who Wants to be a Millionaire'!");
        System.out.println("There will be a total of 15 questions.");
        System.out.println("You must answer all of them correctly in order to win the grand prize of $1 000 000!");
        System.out.println("This round's theme will be basketball, and the questions get harder and harder.");
        System.out.println("To help with your journey, you have one of each: 50:50, call a friend, ask the audience.");
        System.out.println("To use 50:50 enter 1, to use call a friend enter 2, to use ask the audience enter 3.");
        System.out.println("You also have the option to leave with your money after every question by entering 0.");
        System.out.println("For each question, answer either a, b, c or d.");
        System.out.println("Good luck!\n");
        System.out.println("Enter your name: ");
        name = sc.nextLine();
    }

    /**
     * Processing the interaction of a question group. Question group refer to the easy, medium and difficult groups
     * @param questions the questions in the group.
     * @param states the state array to record the whether the question has been asked or not.
     * @param answers the array to save the correct answers for the group.
     * @return 
     */
    private static boolean AskQuestionGroup(String[] questions, boolean[] states, String[] answers) {
        for (int i = 0; i < 5; i++) {
            int idx = getNewQuestionIndex(states);
            if (!handleOneQuestion(questions[idx], answers[idx])) {
                return false;
            }
        }
        return true;
    }

    private static int getNewQuestionIndex(boolean[] states) {
        int random = rand.nextInt(5);
        while (states[random]) { 
            // check if the random number refers to an answered question.
            random = rand.nextInt(5); // if the question is answered, change to another question.
        }
        states[random] = true;
        return random;
    }

    /**
     * process the interaction of one question.
     * @param question The question.
     * @param answer The answer for the question
     * @return true if the game is to be continued. false if the game is over.
     */
    private static boolean handleOneQuestion(String question, String answer) {
        String response = null;
        reprintQuestion=true;  
        while (true) {
            if (reprintQuestion)
                System.out.println(question);
            response = sc.nextLine();
            if ("abcd".contains(response.toLowerCase())) {
                // if the user response is a,b,c,d
                return handleAnswer(response, answer);
            } else if (response.equals("0")) {
                // if the user input 0 and may quit
                System.out.println("You have currently won $" + money[answeredQuestions]);
                System.out.println("Are you sure you want to leave? (yes or no)");
                response = sc.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    System.out.println("Alright, Thanks for playing.");
                    return false;
                }
            } else if (response.equals("1")) {
                System.out.println("Are you sure you want to use the 50:50 lifeline? (yes or no)");
                response = sc.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    if (fiftyFifty) {
                        System.out.println("You have already used 50:50 lifeline.");
                    }
                    else {
                        fiftyFifty=true;
                        handleFiftyfifty(answer);
                    }
                }
            } else if (response.equals("2")) {
                if (callFriend) {
                    System.out.println("You have already used call friend.");
                } else {
                    callFriend=true;
                    handleFriendHelp(answer);
                }
            } else if (response.equals("3")) {
                if (pollAudience) {
                    System.out.println("You have already used poll audience.");
                } else {
                    pollAudience=true;
                    handlePoll(answer);
                }
            }
        }
    }

    /**
     * Process the interaction if user has input an answer as a,b,c or d.
     * @param response the user input
     * @param answer the correct answer of this question.
     * @return true for continue to play the game. false for exit the game.
     */
    private static boolean handleAnswer(String response, String answer) {
        reprintQuestion=true;
        if (response.equalsIgnoreCase(answer)) {
            // if the user answer is correct, continue
            answeredQuestions++;
            System.out.println("You are right! And now you get $" + money[answeredQuestions ]);
            return true;
        } else {
            // if the user answer is wrong, game over.
            System.out.println("You lost! Too bad too sad. The correct answer was " + answer + ".");
            System.out.println("You are still going home with $" + money[answeredQuestions] + " for trying!");
            return false;
        }
    }

    /**
     * Process when user choose to call friend.
     * @param answer The correct answer of the question.
     * @return true for continue the game, false for game over.
     */
    private static boolean handleFriendHelp(String answer) {
        reprintQuestion=false;
        System.out.println("Your friend suggests you to pick " + simulateFriendAnswer(answer) + ".");
        return true;
    }

    /**
     * Process when user ask for 50:50 lifeline.
     * @param answer the correct answer of the question.
     * @return true for continue the game, false for ending the game.
     */
    private static boolean handleFiftyfifty(String answer) {
        reprintQuestion=false;
        double random=rand.nextDouble();
        int c1,c2,temp;
        // 1/3 probability for choosing the first fake answer.
        if (random<=1/3) 
            c1=getAnswerOtherThanCorrect(0,answer);
        else if (random<=2/3) 
            c1=getAnswerOtherThanCorrect(1,answer);
        else c1=getAnswerOtherThanCorrect(2,answer);
        c2=getChoiceIndex(answer);
        if (c1>c2) { // order the 2 choices.
            temp=c1;
            c1=c2;
            c2=temp;
        }
        System.out.printf("Now the choices are %c and %c\r\n",
                getCharChoiceFromIndex(c1),getCharChoiceFromIndex(c2));
        return true;
    }

    /**
     * Process when user choose to poll audience.
     * @param answer the correct answer of the question.
     * @return true to continue the game. false to end the game.
     */
    private static boolean handlePoll(String answer) {
        reprintQuestion=false;
        int[] percentages=simulatePollAnswers(answer);
        System.out.printf("The poll result is A(%d%%) B(%d%%) C(%d%%) D(%d%%)\r\n",
                percentages[0],percentages[1],percentages[2],percentages[3]);
        return true;
    }

    /**
     * Simulate an answer from friend.
     * @param answer
     * @return 
     */
    private static String simulateFriendAnswer(String answer) {
        double random=rand.nextDouble();
        if (random>0.2) return answer; // for 80% probability for friend gives the correct answer.
        else {
            // equal percentage or probability for friend gives a incorrect answer.
            if (random<=0.2/3) {
                return getStringChoiceFromIndex(getAnswerOtherThanCorrect(0,answer));
            } else if (random<=2*0.2/3) {
                return getStringChoiceFromIndex(getAnswerOtherThanCorrect(1,answer));
            } else {
                return getStringChoiceFromIndex(getAnswerOtherThanCorrect(2,answer));
            }
        }
    }

    /**
     * map incorrect answer(0,1,2) to the answer index (0,1,2,3) which should avoid the correct answer
     * @param i indicate input answer to be mapped.
     * @param answer the correct answer
     * @return the mapped incorrect answer.
     */
    private static int getAnswerOtherThanCorrect(int i, String answer) {
        if (i<getChoiceIndex(answer)) return i;
        else return i+1;
    }

    /**
     * Simulate getting the poll audience answers with percentage.
     * @param answer the correct answer.
     * @return the array contains the simulated percentage.
     */
    private static int[] simulatePollAnswers(String answer) {
        int totalPercentage=100;
        int maxPercentageIndex=0;
        int[] percentages=new int[4];
        // get 4 percentages added up to 100%
        for (int i=0;i<4;i++) {
            if (i<3)
                percentages[i] = rand.nextInt(totalPercentage);
            else 
                // if the last percentage, it should take all the percentage left.
                percentages[i] = totalPercentage;
            totalPercentage-=percentages[i]; // minus to get the left percentage 
            if (percentages[i]>percentages[maxPercentageIndex]) // check the largest percentage.
                maxPercentageIndex=i; // store the index of the largest percentage
        }
        //We need to make sure the correct answer has the largest percentage.
        int answerIndex=getChoiceIndex(answer);
        if (maxPercentageIndex!=answerIndex) { 
            // if the largest percentage index is not the answer index, we need to swap them
            int tmpPercentage=percentages[maxPercentageIndex];
            percentages[maxPercentageIndex] = percentages[answerIndex];
            percentages[answerIndex]=tmpPercentage;
        }
        return percentages;
    }

    /**
     * map answers from a,b,c,d to 0,1,2,3
     * @param answer the answer in String type
     * @return return the answer in integer.
     */
    private static int getChoiceIndex(String answer) {
        return getChoiceIndex(answer.toLowerCase().charAt(0));
    }
    /**
     * map answers from a,b,c,d to 0,1,2,3
     * @param answer the answer in char type.
     * @return the answer in integer
     */
    private static int getChoiceIndex(char answer) {
        return answer-'a';
    }
    
    /**
     * map answer from 0,1,2,3 to a,b,c,d
     * @param i the answer in int type
     * @return the answer in string type.
     */
    private static String getStringChoiceFromIndex(int i) {
        return ""+getCharChoiceFromIndex(i);
    }
    /**
     * map answer from 0,1,2,3 to a,b,c,d
     * @param i the answer in int type
     * @return the answer in char type.
     */
    private static char getCharChoiceFromIndex(int i) {
        return (char)('A'+i);
    }
}
