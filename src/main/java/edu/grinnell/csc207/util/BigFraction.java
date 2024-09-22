package edu.grinnell.csc207.util;

import java.io.PrintWriter;
import java.math.BigInteger;


/**
 * A simple implementation of arbitrary-precision Fractions.
 *
 * @author Samuel A. Rebelsky
 * @author Jacob Bell
 *
 * Originally made by Samuel A. Rebelsky, edited by Jacob Bell for mini-project 2.
 */
public class BigFraction {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**DEFAULT_DENOMINATOR
   * Build a new fraction with numerator num and denominator denom.
   *
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   *
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
  } // BigFraction(int, int)

    /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * @param wholeNum
   *   The numerator of the fraction.
   */
  public BigFraction(int wholeNum) {
    this.num = BigInteger.valueOf(wholeNum);
    this.denom = BigInteger.valueOf(1);
  } //BigFraction

  /**
   * Build a new fraction by parsing a string.
   *
   * @param str
   *   The fraction in string form
   */
  public BigFraction(String str) {
    int slash = str.indexOf("/");
    if (slash == -1) {
      this.num = BigInteger.valueOf(Integer.parseInt(str));
      this.denom = BigInteger.valueOf(1);
      return;
    } //if

    String top = str.substring(0, slash);
    String bottom = str.substring((slash + 1));

    this.num = BigInteger.valueOf((Integer.parseInt(top)));
    this.denom = BigInteger.valueOf((Integer.parseInt(bottom)));
  } // BigFraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   *
   * @return the fraction approxiamted as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add another faction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    if (this.num == BigInteger.valueOf(0)) {
      return new BigFraction(addend.num, addend.denom);
    } //if

    resultDenominator = this.denom.multiply(addend.denom);
    resultNumerator = (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    return BigFraction.simplify(new BigFraction(resultNumerator, resultDenominator));
  } // add(BigFraction)

  /**
   * Multiplies two fractions together.
   * @param a
   * @return BigFraction
   */
  public BigFraction multiplyFraction(BigFraction a) {
    return BigFraction.simplify(new BigFraction((this.num.multiply(a.num)),
        (this.denom.multiply(a.denom))));
  } //multiplyFraction

  /**
   * Gives a remainder that is always positive.
   * @return BigFration
   */
  public BigFraction fractional() {
    return BigFraction.simplify(new BigFraction(this.num.mod(this.denom), this.denom));
  } //fractional

  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  public String toString() {
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } //toString

    return this.num + "/" + this.denom;
  } // toString()


  /**
   * Subtract two fractions.
   *
   * @param subtracthend
   * @return BigFraction
   */
  public BigFraction subtract(BigFraction subtracthend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    resultDenominator = this.denom.multiply(subtracthend.denom);
    resultNumerator = (this.num.multiply(subtracthend.denom))
        .subtract(subtracthend.num.multiply(this.denom));

    return BigFraction.simplify(new BigFraction(resultNumerator, resultDenominator));
  } // add(BigFraction)

  /**
   * Simplify a given fraction.
   * @param sim
   * @return BigFraction
   */
  public static BigFraction simplify(BigFraction sim) {

    int top = sim.num.intValueExact();
    int bottom = sim.denom.intValueExact();
    int largestPrime = -1;

    if (bottom < 0) {
      bottom *= -1;
      top *= -1;
    } //if

    for (int i = 2; i * i <= bottom; i++) {
      while (bottom % i == 0 && top % i == 0) {
        bottom = bottom / i;
        top = top / i;
        largestPrime = i;
      } //while
    } //for

    if (largestPrime > 1) {
      return new BigFraction(top, bottom);
    } //if
    return new BigFraction(top, bottom);
  } //simplify

  /**
   * Print the fraction given.
   * @param frac
   */
  public static void printFrac(BigFraction frac) {
    PrintWriter pen = new PrintWriter(System.out, true);

    if (frac.denom.intValue() == 1) {
      pen.println(frac.numerator());
      return;
    } //if
    pen.println(frac);
  } //printFrac

  /**
   * Test what the action is and call the correct function.
   * @param frac1
   * @param frac2
   * @param action
   * @return BigFraction
   */
  public static BigFraction testArgs(BigFraction frac1, BigFraction frac2, String action) {
    BFCalculator calc1 = new BFCalculator();
    calc1.add(frac1);

    switch (action) {
      case "/" -> calc1.divide(frac2);
      case "*" -> calc1.multiply(frac2);
      case "+" -> calc1.add(frac2);
      case "-" -> calc1.subtract(frac2);
      default -> {
        System.err.println("Incorrect action");
      } //default case
    } //switch
    return calc1.get();
  } //testArgs
} // class BigFraction
