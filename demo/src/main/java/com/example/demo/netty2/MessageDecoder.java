package com.example.demo.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    private String message = "";

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String message = in.toString(StandardCharsets.UTF_8);
        out.add(message.trim());
    }
}

