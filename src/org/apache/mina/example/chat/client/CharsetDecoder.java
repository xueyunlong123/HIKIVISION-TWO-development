package org.apache.mina.example.chat.client;

 

import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import org.apache.mina.core.buffer.IoBuffer;

import org.apache.mina.core.session.IoSession;

import org.apache.mina.filter.codec.ProtocolDecoder;

import org.apache.mina.filter.codec.ProtocolDecoderOutput;

 

/**

 * <b>function:</b> �ַ����



 */

public class CharsetDecoder implements ProtocolDecoder {

 

    private final static Logger log = Logger.getLogger(CharsetDecoder.class);

    

    private final static Charset charset = Charset.forName("UTF-8");    

    // �ɱ��IoBuffer��ݻ�����

    private IoBuffer buff = IoBuffer.allocate(100).setAutoExpand(true);

    

    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

        log.info("#########decode#########");

        

        // �������Ϣ

        while (in.hasRemaining()) {

            // �ж���Ϣ�Ƿ��ǽ����ͬƽ̨�Ľ����Ҳ��һ��

            // windows���з�\r\n������Ϊ��һ��������Ϣ�Ľ�����ˣ� UNIX ��\n��MAC ��\r

            byte b = in.get();

            if (b == '\n') {

                buff.flip();

                byte[] bytes = new byte[buff.limit()];

                buff.get(bytes);

                String message = new String(bytes, charset);

                

                buff = IoBuffer.allocate(100).setAutoExpand(true);

                

                // �������ˣ���д��ת�������

                out.write(message);

                //log.info("message: " + message);

            } else {

                buff.put(b);

            }

        }

    }

 

    public void dispose(IoSession session) throws Exception {

        log.info("#########dispose#########");

        log.info(session.getCurrentWriteMessage());

    }

 

    

    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {

        log.info("#########��ɽ���#########");

    }

}