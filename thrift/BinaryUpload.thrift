namespace java pl.binary.thrift

struct BinaryUploadRequest {
  1: string id;
  2: binary blob;
}

service BinaryUpload {
  void upload(1: BinaryUploadRequest request);
}
