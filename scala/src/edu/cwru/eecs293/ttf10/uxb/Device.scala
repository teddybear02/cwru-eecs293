package edu.cwru.eecs293.ttf10.uxb

/** <p>
  * Represents a UXB device, which is a UXB-enabled computer, peripheral, or hub.
  * <p>
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf Hw2.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
trait Device {

  /**
    * Returns the product code of this device.
    * @return the product code of this device. If the product code is unknown, return an empty optional.
    */
  def getProductCode: Option[Int]

  /**
    * Returns the serial number of this device.
    * @return the serial number of this device. If the serial number is unknown, return an empty optional.
    */
  def getSerialNumber: Option[BigInt]

  /**
    * Returns the UXB version that this device supports.
    * @return the UXB version that this device supports
    */
  def getVersion: Int

  /**
    * Returns the class of this UXB device.
    * @return the class of this UXB device
    */
  def getDeviceClass: DeviceClass.DeviceClass

  /**
    * Returns the number of connectors that this device has.
    * @return the number of connectors that this device has
    */
  def getConnectorCount: Int

  /**
    * Returns the type of each connector in this device.
    * @return the type of each connector in this device
    */
  def getConnectors: List[Connector]

  /**
    * Returns the connector of this device at the given index.
    * @param index the plug number of the connector in this device
    * @return the connector of this device at the given index
    */
  def getConnector(index: Int): Connector

  /**
    * Signifies the arrival of a message at the given connector in the device.
    * @param message the message being received
    * @param connector the connector at which the message arrived
    * @throws NullPointerException if either argument is null
    * @throws IllegalArgumentException if the connector does not belong to this device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  private def recv$(message: Message, connector: Connector) {
    if (message == null || connector == null) {
      throw new NullPointerException("Message not received: null argument.")
    }
    if (connector.getDevice != this) {
      throw new IllegalArgumentException("Message not received: connector does not belong to this device.")
    }
  }

  /**
    * Signifies the arrival of a message at the given connector in the device.
    * @param message the string message being received
    * @param connector the connector at which the message arrived
    * @throws NullPointerException if either argument is null
    * @throws IllegalArgumentException if the connector does not belong to this device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: StringMessage, connector: Connector) {
    recv$(message, connector)
  }

  /**
    * Signifies the arrival of a message at the given connector in the device.
    * @param message the binary message being received
    * @param connector the connector at which the message arrived
    * @throws NullPointerException if either argument is null
    * @throws IllegalArgumentException if the connector does not belong to this device
    */
  @throws[NullPointerException]
  @throws[IllegalArgumentException]
  def recv(message: BinaryMessage, connector: Connector) {
    recv$(message, connector)
  }

}
