package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * File created by Jacob Bell.
 *
 * Inteded use is to provided inputs and perform mathematical expressions
 */
public class QuickCalculator {

  /**
   * Main function for quickcalculator.
   * @param args
   */
  public static void main(String[] args) {

    PrintWriter pen = new PrintWriter(System.out, true);
    BFRegisterSet register = new BFRegisterSet();
    BFCalculator calculator = new BFCalculator();

    if (args.length == 0) {
      System.err.println("FAILED [invalid expression]");
      System.exit(0);
    } //if

    for (String eq : args) {

      int err = 0;
      String[] arrOfArg = eq.split(" ", -1);

      pen.print(eq + " -> ");
      pen.flush();

      // Need to create for loop that constantly adds.

      if (arrOfArg[0].equals("STORE")) {
        if (Character.isLowerCase(arrOfArg[1].charAt(0))) {
          pen.println("STORED" + calculator.get());
          register.store(arrOfArg[1].charAt(0), calculator.get());
          continue;
        } //if
        System.err.println("STORE command received invalid register");
        continue;
      } //if

      calculator.clear();

      if (arrOfArg.length == 1) {
        if (arrOfArg[0].length() == 1 && Character.isLowerCase(arrOfArg[0].charAt(0))) {
          calculator.set(register.get(arrOfArg[0].charAt(0)));
          pen.println(calculator.get());
          continue;
        } // if
        calculator.set(new BigFraction(arrOfArg[0]));
        pen.println(calculator.get());
        continue;
      } // if

      for (int i = 0; i < arrOfArg.length - 1; i++) {

        if (arrOfArg.length == 1) {
          break;
        } else if (arrOfArg.length % 2 != 1) {
          System.err.println("[Invalid Expression]");
          err = 1;
          break;
        } // if

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
          System.err.println("FAILED [Invalid Expression]");
          err = 1;
        } // else
      } // for

      if (err != 1) {
        BigFraction.printFrac(calculator.get());
      } // if

    } // for
  } // main
} // InteractiveCalculator
