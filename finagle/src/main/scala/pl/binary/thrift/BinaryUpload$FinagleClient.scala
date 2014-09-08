/**
 * Generated by Scrooge
 *   version: 3.16.1
 *   rev: 5121dbb0c9be44a53567dfa52592a7dc017853f8
 *   built at: 20140625-213527
 */
package pl.binary.thrift

import com.twitter.finagle.{SourcedException, Service => FinagleService}
import com.twitter.finagle.stats.{NullStatsReceiver, StatsReceiver}
import com.twitter.finagle.thrift.ThriftClientRequest
import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import com.twitter.util.{Future, Return, Throw}
import java.nio.ByteBuffer
import java.util.Arrays
import org.apache.thrift.protocol._
import org.apache.thrift.TApplicationException
import org.apache.thrift.transport.{TMemoryBuffer, TMemoryInputTransport}
import scala.collection.{Map, Set}


@javax.annotation.Generated(value = Array("com.twitter.scrooge.Compiler"))
class BinaryUpload$FinagleClient(
  val service: FinagleService[ThriftClientRequest, Array[Byte]],
  val protocolFactory: TProtocolFactory = new TBinaryProtocol.Factory,
  val serviceName: String = "",
  stats: StatsReceiver = NullStatsReceiver
) extends BinaryUpload[Future] {
  import BinaryUpload._

  protected def encodeRequest(name: String, args: ThriftStruct) = {
    val buf = new TMemoryBuffer(512)
    val oprot = protocolFactory.getProtocol(buf)

    oprot.writeMessageBegin(new TMessage(name, TMessageType.CALL, 0))
    args.write(oprot)
    oprot.writeMessageEnd()

    val bytes = Arrays.copyOfRange(buf.getArray, 0, buf.length)
    new ThriftClientRequest(bytes, false)
  }

  protected def decodeResponse[T <: ThriftStruct](resBytes: Array[Byte], codec: ThriftStructCodec[T]) = {
    val iprot = protocolFactory.getProtocol(new TMemoryInputTransport(resBytes))
    val msg = iprot.readMessageBegin()
    try {
      if (msg.`type` == TMessageType.EXCEPTION) {
        val exception = TApplicationException.read(iprot) match {
          case sourced: SourcedException =>
            if (serviceName != "") sourced.serviceName = serviceName
            sourced
          case e => e
        }
        throw exception
      } else {
        codec.decode(iprot)
      }
    } finally {
      iprot.readMessageEnd()
    }
  }

  protected def missingResult(name: String) = {
    new TApplicationException(
      TApplicationException.MISSING_RESULT,
      name + " failed: unknown result"
    )
  }

  protected def setServiceName(ex: Exception): Exception =
    if (this.serviceName == "") ex
    else {
      ex match {
        case se: SourcedException =>
          se.serviceName = this.serviceName
          se
        case _ => ex
      }
    }

  // ----- end boilerplate.

  private[this] val scopedStats = if (serviceName != "") stats.scope(serviceName) else stats
  private[this] object __stats_upload {
    val RequestsCounter = scopedStats.scope("upload").counter("requests")
    val SuccessCounter = scopedStats.scope("upload").counter("success")
    val FailuresCounter = scopedStats.scope("upload").counter("failures")
    val FailuresScope = scopedStats.scope("upload").scope("failures")
  }
  
  def upload(request: BinaryUploadRequest): Future[Unit] = {
    __stats_upload.RequestsCounter.incr()
    this.service(encodeRequest("upload", upload$args(request))) flatMap { response =>
      val result = decodeResponse(response, upload$result)
      val exception: Future[Nothing] =
        null
  
      if (exception != null) exception else Future.Done
    } respond {
      case Return(_) =>
        __stats_upload.SuccessCounter.incr()
      case Throw(ex) =>
        __stats_upload.FailuresCounter.incr()
        __stats_upload.FailuresScope.counter(ex.getClass.getName).incr()
    }
  }
}