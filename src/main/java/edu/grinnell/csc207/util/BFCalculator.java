package edu.grinnell.csc207.util;

import java.math.BigInteger;


/**
 * File created by Jacob Bell.
 *
 * Inteded use is to have the methods in order to make a calculator work
 */
public class BFCalculator {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigFraction prev;

  /**
   * Create a calculator.
   */
  public BFCalculator() {
    this.prev = new BigFraction(0, 0);
  } // BFCalculator

  /**
   * Get the fraction.
   * @return BigFraction
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
   * @return
   */
  public void add(BigFraction val) {
    this.prev = this.prev.add(val);
  } // add(BFCalculator)

    /**
   * Add another fraction to this fraction.
   *
   * @param val
   *   The fraction to add.
   *
   * @return
   */
  public void subtract(BigFraction val) {
    if (this.prev.num == BigInteger.valueOf(0)) {
      this.prev = new BigFraction(BigInteger.valueOf(0).subtract(val.num), val.denom);
    } else {
      this.prev = this.prev.subtract(val);
    } // if
  } // add(BigFraction)

  /**
   * Multiply two BigFractions together.
   * @param val
   */
  public void multiply(BigFraction val) {
    this.prev = new BigFraction(this.prev.num.multiply(val.num),
        this.prev.denom.multiply(val.denom));
  } // add(BigFraction)

  /**
   * Divide two fractions.
   * @param val
   */
  public void divide(BigFraction val) {
    this.prev = BigFraction.simplify(new BigFraction(this.prev.num.multiply(val.denom),
        this.prev.denom.multiply(val.num)));
  } // divide(BigFraction)

  /**
   * Clear the fractions.
   */
  public void clear() {
    this.prev = new BigFraction(0, 0);
  } //clear
} //BFCalculator
