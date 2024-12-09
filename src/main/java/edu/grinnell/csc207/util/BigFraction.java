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
  private BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  private BigInteger denom;

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
    BigInteger[] bigInts = BigFraction.simplify(numerator, denominator);

    this.num = bigInts[0];
    this.denom = bigInts[1];
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
    BigInteger[] bigInts = BigFraction.simplify(BigInteger.valueOf(numerator),
        BigInteger.valueOf(denominator));

    this.num = bigInts[0];
    this.denom = bigInts[1];
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
      this.denom = BigInteger.ONE;
      return;
    } //if

    String top = str.substring(0, slash);
    String bottom = str.substring(slash + 1);

    BigInteger numerator = new BigInteger(top);
    BigInteger denominator = new BigInteger(bottom);

    BigInteger[] bigInts = BigFraction.simplify(numerator, denominator);

    this.num = bigInts[0];
    this.denom = bigInts[1];
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

    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * Multiplies two fractions together.
   * @param a
   * @return BigFraction
   */
  public BigFraction multiply(BigFraction a) {
    return new BigFraction((this.num.multiply(a.num)), (this.denom.multiply(a.denom)));
  } //multiplyFraction

    /**
   * Multiplies two fractions together.
   * @param a
   * @return BigFraction
   */
  public BigFraction divide(BigFraction a) {
    return new BigFraction(this.num.multiply(a.denom), this.denom.multiply(a.num));
  } //multiplyFraction


  /**
   * Gives a remainder that is always positive.
   * @return BigFration
   */
  public BigFraction fractional() {
    return new BigFraction(this.num.mod(this.denom), this.denom);
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
    } else if (this.denom.equals(BigInteger.ONE)) {
      return "" + this.numerator();
    } // if

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

    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * Simplify a given fraction.
   * @param a
   * @param b
   * @return BigFraction
   */
  public static BigInteger[] simplify(BigInteger a, BigInteger b) {
    BigInteger[] vals = new BigInteger[2];

    if (b.compareTo(BigInteger.ZERO) < 0) {
      b = b.negate();
      a = a.negate();
    } //if

    BigInteger gcd = a.gcd(b);

    vals[0] = a.divide(gcd);
    vals[1] = b.divide(gcd);

    return vals;
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
