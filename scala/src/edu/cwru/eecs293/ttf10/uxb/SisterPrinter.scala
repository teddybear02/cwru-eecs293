/*                                                                      *\
**                    Case Western Reserve University                   **
**                                                                      **
**                               EECS 293                               **
**                        Software Craftsmanship                        **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.eecs293.ttf10.uxb

/**
  * Represents a concrete UXB printer.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * @author Ted Frohlich
  */
class SisterPrinter(private val builder: SisterPrinter.Builder) extends AbstractPrinter(builder) {
  
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: StringMessage, connector: Connector) {
    validateRecv(message, connector)
    println("[Log] >>  " + "Sister printer has printed the string: \"" + message.getString + "\"")
    println("          " + "  -> printer serial number: " + serialNumber.get)
  }
  
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: BinaryMessage, connector: Connector) {
    validateRecv(message, connector)
    val result: BigInt = message.getValue + productCode.getOrElse[Int](0)
    println("[Log] >>  " + "Sister printer has printed the binary message: " + result)
  }
  
}


object SisterPrinter {
  
  /**
    * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
    *
    * @param version the UXB version that this device supports
    */
  class Builder(override protected val version: Int) extends AbstractPrinter.Builder[Builder](version) {
  
    protected def getThis = this
  
    /**
      * Initializes the sister printer with the builder’s version, product code, serial number, and connector list.
      *
      * @return the initialized sister printer
      * @throws IllegalStateException if the version number is null, or if one of the connectors is <i>not</i> of type peripheral
      */
    @throws[IllegalStateException]
    def build(): SisterPrinter = {
      validate()
      new SisterPrinter(this)
    }
  
  }
  
}
