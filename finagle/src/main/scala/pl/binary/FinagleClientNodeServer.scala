package pl.binary

import java.nio.ByteBuffer

import com.twitter.finagle.Thrift
import com.twitter.util.Await
import pl.binary.thrift.{BinaryUpload, BinaryUploadRequest}

object FinagleClientNodeServer extends App {
  val serverAddress = "localhost:9090"
  val client = Thrift.newIface[BinaryUpload.FutureIface](serverAddress)

  var byteArray = Array.fill[Byte](256)(0)
  for (i <- 0 to 255) {
    byteArray(i) = i.toByte
  }

  val buffer = ByteBuffer.wrap(byteArray)

  println("Sending:")
  BufferLogger.printHex(buffer)

  Await.ready(client.upload(BinaryUploadRequest("ID", buffer)))
}
