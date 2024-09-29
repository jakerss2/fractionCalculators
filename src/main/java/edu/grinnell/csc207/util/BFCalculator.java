package edu.grinnell.csc207.util;

import java.math.BigInteger;


/**
 * File created by Jacob Bell.
 *
 * Intended use is to have the methods in order to make a calculator work
 */
public class BFCalculator {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The last stored fraction. Can be positive, zero or negative. */
  private BigFraction prev;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a calculator.
   */
  public BFCalculator() {
    this.prev = new BigFraction(0, 0);
  } // BFCalculator

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the fraction.
   * @return the previous fraction stored
   */
  public BigFraction get() {
    return this.prev;
  } // Get

  /**
   * Set the current calculator to val.
   * @param val
   */
  public void set(BigFraction val) {
    this.prev = val;
  } //set

  /**
   * Add another fraction to this fraction.
   *
   * @param val
   *   The fraction to add.
   *
   */
  public void add(BigFraction val) {
    this.prev = this.prev.add(val);
  } // add(BFCalculator)

    /**
   * Add another fraction to this fraction.
   *
   * @param val
   *   The fraction to subtract.
   *
   */
  public void subtract(BigFraction val) {

    //If we are subtracing from the number 0
    if (this.prev.numerator() == BigInteger.valueOf(0)) {
      this.prev = new BigFraction(BigInteger.valueOf(0).subtract(val.numerator()),
          val.denominator());
    } else {
      this.prev = this.prev.subtract(val);
    } // if
  } // add(BigFraction)

  /**
   * Multiply two BigFractions together.
   * @param val
   */
  public void multiply(BigFraction val) {

    //multiply the nums and denoms
    this.prev = this.prev.multiplyFraction(val);
  } // add(BigFraction)

  /**
   * Divide two fractions.
   * @param val
   */
  public void divide(BigFraction val) {
    this.prev = this.prev.divideFraction(val);
  } // divide(BigFraction)

  /**
   * Clear the fractions by setting the num and denom to 0.
   */
  public void clear() {
    this.prev = new BigFraction(0, 0);
  } //clear
} //BFCalculator
