package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * File created by Jacob Bell.
 *
 * Inteded use is to provided inputs and perform mathematical expressions
 */
public class InteractiveCalculator {

  /**
   * Main function to run calculator.
   * @param args
   */
  public static void main(String[] args) {

    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);
    BFRegisterSet register = new BFRegisterSet();
    BFCalculator calculator = new BFCalculator();

    while (true) {

      int err = 0;
      pen.print("Enter commands: ");
      pen.flush();
      String stuff = eyes.nextLine();
      if (stuff.equals("QUIT")) {
        eyes.close();
        return;
      } //if

      if (stuff.length() == 0) {
        System.err.println("FAILED [invalid expression]");
        System.exit(0);
      } //if

      String[] arrOfArg = stuff.split(" ", -1);

     //Need to create for loop that constantly adds.

      if (arrOfArg[0].equals("STORE")) {
        if (Character.isLowerCase(arrOfArg[1].charAt(0))) {
          register.store(arrOfArg[1].charAt(0), calculator.get());
          continue;
        } //if
        System.err.println("STORE command received invalid register");
        continue;
      } //if

      calculator.clear();

      if (arrOfArg.length == 1) {
        calculator.set(new BigFraction(arrOfArg[0]));
      } //if

      for (int i = 0; i < arrOfArg.length - 1; i++) {

        if (arrOfArg.length == 1) {
          break;
        } else if (arrOfArg.length % 2 != 1) {
          System.err.println("Invalid Expression");
          break;
        } //else if

        if (arrOfArg[i + 1].length() == 1 && Character.isLowerCase(arrOfArg[i + 1].charAt(0))) {
          calculator.set(BigFraction.testArgs(calculator.get(),
              register.get(arrOfArg[i + 1].charAt(0)), arrOfArg[i]));
          i++;
        } else if (Character.isLowerCase(arrOfArg[i].charAt(0)) && i == 0) {
          calculator.set(register.get(arrOfArg[i].charAt(0)));
        } else if (arrOfArg[i].length() == 1
            && !(Character.isDigit(arrOfArg[i].charAt(0)))) {
          calculator.set(BigFraction.testArgs(calculator.get(),
              new BigFraction(arrOfArg[i + 1]), arrOfArg[i]));
          i++;
        } else if (i == 0) {
          calculator.set(new BigFraction(arrOfArg[i]));
        } else {
          System.err.println("Invalid Expression");
          err = 1;
        } //else
      } //for

      if (err != 1) {
        BigFraction.printFrac(calculator.get());
      } //if

    } //while
  } //main
} //InteractiveCalculator
