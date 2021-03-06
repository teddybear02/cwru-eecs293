/*                                                                      *\
**                    Case Western Reserve University                   **
**                                                                      **
**                               EECS 293                               **
**                        Software Craftsmanship                        **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.eecs293.ttf10.uxb

import org.junit.{Before, Test}

/**
  * A test that, given a <tt>List[Device]</tt> and a <tt>List[Message]</tt>, delivers all messages to all devices on their zero connector (if any). The device list should contain at least one device of each type, and the message list should contain at least one binary and at least one string message.
  *
  * @since Programming Assignment 2
  * @author Ted Frohlich
  */
class Broadcast {

  private var devices: Set[Device] = _
  private var messages: List[Message] = _

  @Before
  def setUp() {
    // Instantiate device set
    val conList = List(Connector.Type.PERIPHERAL)
    devices = Set(
      new Hub.Builder(2016)
        .connectors(Connector.Type.COMPUTER :: conList)
        .build(),
      new SisterPrinter.Builder(2016)
        .serialNumber(13579)
        .productCode(293)
        .connectors(conList)
        .build(),
      new CannonPrinter.Builder(2016)
        .serialNumber(24680)
        .connectors(conList)
        .build(),
      new GoAmateur.Builder(2016)
        .connectors(conList)
        .build()
    )
    // Initialize message list
    messages = List(
      StringMessage("Hello, world!"),
      StringMessage("My name is Ted."),
      StringMessage(""),
      BinaryMessage(0),
      BinaryMessage(1)
    )
  }

  @Test
  @throws[Exception]
  def broadcast() {
    devices.foreach(device =>
      messages.foreach(message =>
        device.getConnector(0).recv(message)))
  }

}
