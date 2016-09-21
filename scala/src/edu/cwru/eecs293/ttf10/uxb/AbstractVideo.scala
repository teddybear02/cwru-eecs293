package edu.cwru.eecs293.ttf10.uxb

/**
  * <p> Represents a prototypical UXB video peripheral device.
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1381847-dt-content-rid-4318401_1/xid-4318401_1 Hw3.pdf]]
  * @since Programming Assignment 3
  * <br> <i>
  * <br> Case Western Reserve University
  * <br> EECS 293: Software Craftsmanship
  * <br> 2016 Fall Semester
  * @author Theodore Frohlich &lt;ttf10@case.edu&gt;
  */
abstract class AbstractVideo[T <: AbstractVideo.Builder[T]]
  extends AbstractPeripheral[AbstractVideo.Builder[T]] {

  /**
    * Initializes the video device from the given builder.
    * @param builder a builder for initializing the video device
    */
  protected def this(builder: AbstractVideo.Builder[T]) {
    this
    productCode = builder.getProductCode
    serialNumber = builder.getSerialNumber
    version = builder.getVersion
    val connectorTypes = builder.getConnectors
    connectors = List.empty
    for (index <- connectorTypes.indices) {
      connectors ::= new Connector(this, index, connectorTypes(index))
    }
  }  // TODO: supposed to invoke parent constructor... why can't I??

  override def getDeviceClass: DeviceClass.DeviceClass = DeviceClass.VIDEO

}


object AbstractVideo {

  abstract class Builder[T] extends AbstractPeripheral.Builder[Builder[T]] {

    /**
      * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
      * @param version the UXB version that this device supports
      */
    def this(version: Int) {
      this
      this.version = version
      productCode(null.asInstanceOf[Int])
      serialNumber(null.asInstanceOf[BigInt])
      connectors(null)
    }  // TODO: supposed to invoke parent method... why can't I?

  }


}
