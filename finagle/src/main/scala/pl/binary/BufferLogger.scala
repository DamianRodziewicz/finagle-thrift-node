package pl.binary

import java.nio.ByteBuffer

object BufferLogger {
  def printHex(buffer: ByteBuffer) = {
    for (byte <- buffer.array()) {
      printf ("%02X ", (byte) )
      print (" ")
    }
    
    println
  }
}
