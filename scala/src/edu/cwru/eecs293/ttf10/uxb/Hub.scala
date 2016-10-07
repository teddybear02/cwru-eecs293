package edu.cwru.eecs293.ttf10.uxb

import DeviceClass._

/**
  * <p> Represents a UXB hub, which can have multiple computer-side and peripheral-side connectors.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * <br> [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4287477_1/xid-4287477_1 Hw2.pdf]]
  * @since Programming Assignment 2
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Ted Frohlich < ttf10@case.edu >
  */
class Hub(private val builder: Hub.Builder) extends AbstractDevice(builder) {
  
  override def getDeviceClass: DeviceClass = DeviceClass.HUB
  
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: StringMessage, connector: Connector) {
    validateRecv(message, connector)
    send(message, connector)
    println("[Log] >>  " + "Hub has forwarded on the string message: " + message.getString)
  }
  
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: BinaryMessage, connector: Connector) {
    validateRecv(message, connector)
    send(message, connector)
    println("[Log] >>  " + "Hub has forwarded on the binary message: " + message.getValue)
  }
  // TODO: recv methods too similar? --write hubRecv helper method?
  
  /**
    * Forwards the received message on all its connectors except the one from which the message was received.
    *
    * @param message   the received message
    * @param connector the connector from which the message was received
    */
  private def send(message: Message, connector: Connector) {
    (connectors.toSet - connector)
      .foreach(con => con.recv(message))
  }
  
}


object Hub {
  
  /**
    * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
    *
    * @param version the UXB version that this device supports
    */
  class Builder(override protected val version: Int) extends AbstractDevice.Builder[Builder](version) {
  
    protected def getThis = this
  
    /**
      * Initializes the hub with the builder’s version, product code, serial number, and connector list.
      *
      * @return the initialized hub
      * @throws IllegalStateException if the version is null, or the hub has no computer connector, or the hub has no peripheral connector.
      */
    @throws[IllegalStateException]
    def build(): Hub = {
      validate()
      new Hub(this)
    }
  
    /**
      * Validates this builder.
      *
      * @throws IllegalStateException if and only if the version number is null, or if the hub has no computer connector, or if the hub has no peripheral connector
      */
    @throws[IllegalStateException]
    override protected def validate() {
      // Check if the version number is null
      try {
        super.validate()
      } catch {
        case e: NullPointerException => throw new IllegalStateException(e)
      }
      // Check if the hub has no computer connector
      if (!getConnectors.contains(Connector.Type.COMPUTER)) {
        throw new IllegalStateException("Validation failed: hub has no computer connector.")
      }
      // Check if the hub has no peripheral connector
      if (!getConnectors.contains(Connector.Type.PERIPHERAL)) {
        throw new IllegalStateException("Validation failed: hub has no peripheral connector.")
      }
    }
  
  }
  
}
