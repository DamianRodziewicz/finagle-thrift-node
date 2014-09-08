package pl.binary

import com.twitter.util.Future
import pl.binary.thrift.{BinaryUploadRequest, BinaryUpload}

class FinagleBinaryUploadServer extends BinaryUpload.FutureIface {
  override def upload(request: BinaryUploadRequest): Future[Unit] = {
    println("Received:")
    BufferLogger.printHex(request.blob)
    Future()
  }
}
