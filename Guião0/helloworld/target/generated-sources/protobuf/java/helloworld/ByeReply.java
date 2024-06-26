// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: helloworld.proto

package helloworld;

/**
 * Protobuf type {@code helloworld.ByeReply}
 */
public final class ByeReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:helloworld.ByeReply)
    ByeReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ByeReply.newBuilder() to construct.
  private ByeReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ByeReply() {
    bye_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ByeReply();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return helloworld.Helloworld.internal_static_helloworld_ByeReply_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return helloworld.Helloworld.internal_static_helloworld_ByeReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            helloworld.ByeReply.class, helloworld.ByeReply.Builder.class);
  }

  public static final int BYE_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object bye_ = "";
  /**
   * <code>string bye = 1;</code>
   * @return The bye.
   */
  @java.lang.Override
  public java.lang.String getBye() {
    java.lang.Object ref = bye_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      bye_ = s;
      return s;
    }
  }
  /**
   * <code>string bye = 1;</code>
   * @return The bytes for bye.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getByeBytes() {
    java.lang.Object ref = bye_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      bye_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(bye_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, bye_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(bye_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, bye_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof helloworld.ByeReply)) {
      return super.equals(obj);
    }
    helloworld.ByeReply other = (helloworld.ByeReply) obj;

    if (!getBye()
        .equals(other.getBye())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + BYE_FIELD_NUMBER;
    hash = (53 * hash) + getBye().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static helloworld.ByeReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static helloworld.ByeReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static helloworld.ByeReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static helloworld.ByeReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static helloworld.ByeReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static helloworld.ByeReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static helloworld.ByeReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static helloworld.ByeReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static helloworld.ByeReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static helloworld.ByeReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static helloworld.ByeReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static helloworld.ByeReply parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(helloworld.ByeReply prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code helloworld.ByeReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helloworld.ByeReply)
      helloworld.ByeReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return helloworld.Helloworld.internal_static_helloworld_ByeReply_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return helloworld.Helloworld.internal_static_helloworld_ByeReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              helloworld.ByeReply.class, helloworld.ByeReply.Builder.class);
    }

    // Construct using helloworld.ByeReply.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      bye_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return helloworld.Helloworld.internal_static_helloworld_ByeReply_descriptor;
    }

    @java.lang.Override
    public helloworld.ByeReply getDefaultInstanceForType() {
      return helloworld.ByeReply.getDefaultInstance();
    }

    @java.lang.Override
    public helloworld.ByeReply build() {
      helloworld.ByeReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public helloworld.ByeReply buildPartial() {
      helloworld.ByeReply result = new helloworld.ByeReply(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(helloworld.ByeReply result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.bye_ = bye_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof helloworld.ByeReply) {
        return mergeFrom((helloworld.ByeReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(helloworld.ByeReply other) {
      if (other == helloworld.ByeReply.getDefaultInstance()) return this;
      if (!other.getBye().isEmpty()) {
        bye_ = other.bye_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              bye_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object bye_ = "";
    /**
     * <code>string bye = 1;</code>
     * @return The bye.
     */
    public java.lang.String getBye() {
      java.lang.Object ref = bye_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        bye_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string bye = 1;</code>
     * @return The bytes for bye.
     */
    public com.google.protobuf.ByteString
        getByeBytes() {
      java.lang.Object ref = bye_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        bye_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string bye = 1;</code>
     * @param value The bye to set.
     * @return This builder for chaining.
     */
    public Builder setBye(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      bye_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string bye = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearBye() {
      bye_ = getDefaultInstance().getBye();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string bye = 1;</code>
     * @param value The bytes for bye to set.
     * @return This builder for chaining.
     */
    public Builder setByeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      bye_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:helloworld.ByeReply)
  }

  // @@protoc_insertion_point(class_scope:helloworld.ByeReply)
  private static final helloworld.ByeReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new helloworld.ByeReply();
  }

  public static helloworld.ByeReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ByeReply>
      PARSER = new com.google.protobuf.AbstractParser<ByeReply>() {
    @java.lang.Override
    public ByeReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<ByeReply> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ByeReply> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public helloworld.ByeReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

