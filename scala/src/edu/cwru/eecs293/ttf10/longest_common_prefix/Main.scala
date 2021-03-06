package edu.cwru.eecs293.ttf10.longest_common_prefix

import scala.io.StdIn

/**
  * Main class for Programming Assignment 1
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1366059-dt-content-rid-4244627_1/courses/eecs293_vxl11/Hw1.pdf Hw1.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
object Main {

  def main(args: Array[String]) {
    System.out.println("\n< Programming Assignment 1 (Java) >")

    System.out.print("\nList A:  ")
    val a = readList

    System.out.print("\nList B:  ")
    val b = readList

    System.out.print("\nLongest common prefix:  ")
    System.out.println(longestPrefix(a, b, Ordering[String]))
  }

  /**
    * Reads a line of text as a list of comma-separated strings.
    * @return a list of strings, with leading and trailing whitespace removed
    */
  private def readList: List[String] = {
    StdIn.readLine()
      .split(",")
      .map(_.trim)
      .toList
  }

  /**
    * Finds the longest common prefix in two lists.
    * @param a the first list
    * @param b the second list
    * @param ord an admissible comparison function, which imposes a <i>total ordering</i> on the list elements
    * @tparam T the type of the list elements to be compared
    * @return the prefix of maximum length that is common to both lists
    */
  private[longest_common_prefix] def longestPrefix[T](a: List[T],
                                                      b: List[T],
                                                      ord: Ordering[_ >: T]
                                                     ): List[T] = {
    //  // How I'd normally implement this method...
    //  var len = 0
    //  while (ord.compare(a(len), b(len)) == 0) {
    //    len += 1
    //  }
    //  a.slice(0, len)

    // What I presented in discussion...
    val ita: Iterator[T] = a.iterator
    val itb: Iterator[T] = b.iterator
    var len = 0
    while (ita.hasNext && itb.hasNext && ord.compare(ita.next(), itb.next()) == 0) {
      len += 1
    }
    a.slice(0, len)

    //  // Rewritten using feedback from discussion... TODO
//    val iter = a.iterator.zip(b.iterator)
//    var next: (T, T) = null
//    var len = -1
//    do {
//      next = iter.next()
//      len += 1
//    } while (next != null && ord.compare(next._1, next._2) == 0)
//    a.slice(0, len)
  }

}
