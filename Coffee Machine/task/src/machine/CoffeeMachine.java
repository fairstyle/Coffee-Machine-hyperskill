package machine;

import java.util.Scanner;

enum inputState{
    NOREAD,
    READING,
    READED
}

enum machineState{
    OFF,
    WAITING,
    BUY,
    FILL,
    TAKE,
    REMAINING,
}

enum fillState{
    NOFILL,
    FILLWATER,
    FILLMILK,
    FILLCOFFE,
    FILLCUP
}

public class CoffeeMachine {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        machineState maquina = machineState.WAITING;
        inputState input = inputState.NOREAD;
        fillState fill = fillState.NOFILL;
        
        String opt = "no-data";
        
        int machineMoney = 550;
        
        int[] machineMats = {400, 540, 120, 9};

        int[][] coffeMats = {
                {250, 0, 16, 4}, //ESPRESSO
                {350, 75, 20, 7}, // LATTE
                {200, 100, 12, 6} //  CAPPUCCINO
        };

        while(maquina != machineState.OFF) {

            if (input == inputState.READING) {
                opt = scanner.nextLine();
                input = inputState.READED;
            }

            switch (maquina) {

                case WAITING: {

                    if (input == inputState.NOREAD) {
                        System.out.println();
                        System.out.println("Write action (buy, fill, take, remaining, exit):");
                        input = inputState.READING;
                    }

                    if(input == inputState.READED) {

                        switch (opt.toLowerCase()) {
                            case "buy": {
                                maquina = machineState.BUY;
                                break;
                            }
                            case "fill": {
                                maquina = machineState.FILL;
                                fill = fillState.FILLWATER;
                                break;
                            }
                            case "take": {
                                maquina = machineState.TAKE;
                                break;
                            }
                            case "remaining": {
                                maquina = machineState.REMAINING;
                                break;
                            }
                            case "exit": {
                                maquina = machineState.OFF;
                                break;
                            }
                            default: {
                                System.out.println("try again");
                                break;
                            }
                        }

                        input = inputState.NOREAD;
                    }
                    break;
                }

                case BUY: {

                    if (input == inputState.NOREAD) {
                        System.out.println();
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        input = inputState.READING;
                    }

                    if(input == inputState.READED) {

                        switch (opt.toLowerCase()) {
                            case "1": {}
                            case "2": {}
                            case "3": {
                                try{
                                    int errCode = calculateCoffe(machineMats, coffeMats[Integer.parseInt(opt) - 1]);
                                    if (errCode == 0) {
                                        System.out.println("I have enough resources, making you a coffee!");
                                        System.out.println();
                                        int count = 1;
                                        machineMats = makeCoffe(machineMats, coffeMats[Integer.parseInt(opt) - 1], count);
                                        machineMoney += (coffeMats[Integer.parseInt(opt) - 1][3]) * count;
                                    } else{
                                        switch (errCode) {
                                            case 1: {
                                                System.out.println("Sorry, not enough water!");
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Sorry, not enough milk!");
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Sorry, not enough coffee beans!");
                                                break;
                                            }
                                            case 4: {
                                                System.out.println("Sorry, not enough disposable cups!");
                                                break;
                                            }
                                        }
                                    }
                                    maquina = machineState.WAITING;
                                }catch(NumberFormatException e){
                                    System.out.println("not number!, try again");
                                    input = inputState.READING;
                                }
                                break;
                            }
                            case "back": {
                                maquina = machineState.WAITING;
                                break;
                            }
                            default: {
                                System.out.println("try again");
                                break;
                            }
                        }

                        input = inputState.NOREAD;
                    }
                    break;
                }

                case FILL: {

                    switch (fill){
                        case FILLWATER:{

                            if(input == inputState.NOREAD) {
                                System.out.println();
                                System.out.println("Write how many ml of water do you want to add:");
                                input = inputState.READING;
                            }

                            if(input == inputState.READED){
                                try {
                                    machineMats[0] += Integer.parseInt(opt);
                                    fill = fillState.FILLMILK;
                                    input = inputState.NOREAD;
                                }catch (NumberFormatException e){
                                    System.out.println("not number!, try again");
                                    input = inputState.READING;
                                }
                            }
                            break;

                        }
                        case FILLMILK:{

                            if(input == inputState.NOREAD) {
                                System.out.println("Write how many ml of milk do you want to add:");
                                input = inputState.READING;
                            }

                            if(input == inputState.READED){
                                try {
                                    machineMats[1] += Integer.parseInt(opt);
                                    fill = fillState.FILLCOFFE;
                                    input = inputState.NOREAD;
                                }catch (NumberFormatException e){
                                    System.out.println("not number!, try again");
                                    input = inputState.READING;
                                }
                            }
                            break;

                        }
                        case FILLCOFFE:{

                            if(input == inputState.NOREAD) {
                                System.out.println("Write how many grams of coffee beans do you want to add:");
                                input = inputState.READING;
                            }

                            if(input == inputState.READED){
                                try {
                                    machineMats[2] += Integer.parseInt(opt);
                                    fill = fillState.FILLCUP;
                                    input = inputState.NOREAD;
                                }catch (NumberFormatException e){
                                    System.out.println("not number!, try again");
                                    input = inputState.READING;
                                }
                            }
                            break;

                        }
                        case FILLCUP:{

                            if(input == inputState.NOREAD) {
                                System.out.println("Write how many disposable cups of coffee do you want to add:");
                                input = inputState.READING;
                            }

                            if(input == inputState.READED){
                                try {
                                    machineMats[3] += Integer.parseInt(opt);
                                    fill = fillState.NOFILL;
                                    input = inputState.NOREAD;
                                }catch (NumberFormatException e){
                                    System.out.println("not number!, try again");
                                    input = inputState.READING;
                                }
                            }
                            break;

                        }
                        case NOFILL:{
                            maquina = machineState.WAITING;
                            break;
                        }

                    }

                    break;
                }

                case TAKE: {
                    machineMoney = take(machineMoney);
                    maquina = machineState.WAITING;
                    break;
                }

                case REMAINING: {
                    remaining(machineMats, machineMoney);
                    maquina = machineState.WAITING;
                    break;
                }

            }
        }
    }

    public static int[] makeCoffe(int [] machineMats, int[] coffeCup ,int count){

        machineMats[0] -= coffeCup[0]*count;
        machineMats[1] -= coffeCup[1]*count;
        machineMats[2] -= coffeCup[2]*count;
        machineMats[3] -= 1*count;

        return machineMats;
    }

    public static int calculateCoffe(int[] machineMats, int[] coffeCup){

        int[] machineMatsClone = machineMats.clone();
        int errCode = 0;


        if(machineMatsClone[0] < coffeCup[0] || machineMatsClone[1] < coffeCup[1] || machineMatsClone[2] < coffeCup[2] || machineMatsClone[3] < 1){

            if(machineMatsClone[0] < coffeCup[0])
                errCode= 1;

            if(machineMatsClone[1] < coffeCup[1])
                errCode = 2;

            if(machineMatsClone[2] < coffeCup[2])
                errCode = 3;

            if(machineMatsClone[3] < 1)
                errCode = 4;
        }

        return errCode;
    }

    public static int take(int machineMoney){
        System.out.println();
        System.out.println("I gave you $" + machineMoney);
        machineMoney = 0;
        return machineMoney;
    }

    public static void remaining(int[] machineMats, int machineMoney){
        System.out.println();
        System.out.println("The coffe machine has:");
        System.out.println(machineMats[0]+" of water");
        System.out.println(machineMats[1]+" of milk");
        System.out.println(machineMats[2]+" of coffee beans");
        System.out.println(machineMats[3]+" of disposable cups");
        System.out.println("$"+machineMoney+" of money");
    }

}