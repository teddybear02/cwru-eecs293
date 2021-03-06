/*                                                                      *\
**                    Case Western Reserve University                   **
**                                                                      **
**                               EECS 293                               **
**                        Software Craftsmanship                        **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.eecs293.ttf10.uxb

/**
  * Represents a concrete UXB video peripheral device.
  * <p>
  * This just might be better than the [[http://www.ufunk.net/en/gadgets/goamateur/ GoAmateur]]!
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * @author Ted Frohlich
  */
class GoAmateur(private val builder: GoAmateur.Builder) extends AbstractVideo[GoAmateur.Builder](builder) {
  
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: StringMessage, connector: Connector) {
    validateRecv(message, connector)
    println("[Log] >>  " + "GoAmateur does not understand string messages: \"" + message.getString + "\"")
    println("          " + "  -> connector index: " + connector.getIndex)
  }
  
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: BinaryMessage, connector: Connector) {
    validateRecv(message, connector)
    send(BinaryMessage(293), connectors)  // respond by broadcasting the binary message: 293
    println("[Log] >>  " + "GoAmateur has responded to the binary message: " + message.getValue)
    println("          " + "by sending on all of its connectors the binary message: 293")
  }
  
}


object GoAmateur {
  
  /**
    * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
    *
    * @param version the UXB version that this device supports
    */
  class Builder(override protected val version: Int) extends AbstractVideo.Builder[Builder](version) {
  
    protected def getThis = this
  
    /**
      * Initializes the GoAmateur with the builder’s version, product code, serial number, and connector list.
      *
      * @return the initialized GoAmateur
      * @throws IllegalStateException if the version number is null, or if one of the connectors is <i>not</i> of type peripheral
      */
    @throws[IllegalStateException]
    def build(): GoAmateur = {
      validate()
      new GoAmateur(this)
    }
  
  }
  
}
