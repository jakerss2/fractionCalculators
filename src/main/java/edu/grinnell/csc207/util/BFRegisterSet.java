package edu.grinnell.csc207.util;

import java.util.ArrayList;

/**
 * File created by Jacob Bell.
 *
 * Hold previous commands inputed by user
 */
public class BFRegisterSet {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /**Create an array that holds all of the chars to be stored. */
  private ArrayList<Character> chars;

  /**Create an array that holds all of the BigFractions to be stored. */
  private ArrayList<BigFraction> bigFracs;

  /**
   * Initialize the register to have two arrays.
   */
  public BFRegisterSet() {
    this.chars = new ArrayList<>();
    this.bigFracs = new ArrayList<>();
  } //BFRegisterSet

  /**
   * Creates an array that stores the characters and their value.
   * @param register
   * @param val
   * @return
   */
  public void store(char register, BigFraction val) {
    int index = 0;
    for (; index < chars.size(); index++) {
      if (register == this.chars.get(index)) {
        this.chars.set(index, register);
        this.bigFracs.set(index, val);
      } // if
    } // for
    this.chars.add(register);
    this.bigFracs.add(val);
  } // store

  /**
   * Get the value stored with char register.
   * @param register
   * @return BigFraction
   */
  public BigFraction get(char register) {
    return bigFracs.get(chars.indexOf(register));
  } //get
} // BFRegisterSet
