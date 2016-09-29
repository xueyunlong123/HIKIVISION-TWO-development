package org.apache.mina.example.chat.client;

 

import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import org.apache.mina.core.buffer.IoBuffer;

import org.apache.mina.core.session.IoSession;

import org.apache.mina.filter.codec.ProtocolEncoder;

import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import org.apache.mina.filter.codec.textline.LineDelimiter;

 

/**

 * <b>function:</b> �ַ����
 * @version 1.0

 */

public class CharsetEncoder implements ProtocolEncoder {

    private final static Logger log = Logger.getLogger(CharsetEncoder.class);

    private final static Charset charset = Charset.forName("UTF-8");

    


    public void dispose(IoSession session) throws Exception {

        log.info("#############dispose############");

    }

 


    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {

        log.info("#############�ַ����############");

        IoBuffer buff = IoBuffer.allocate(100).setAutoExpand(true);

        buff.putString(message.toString(), charset.newEncoder());

        // put ��ǰϵͳĬ�ϻ��з�

        buff.putString(LineDelimiter.DEFAULT.getValue(), charset.newEncoder());

        // Ϊ��һ�ζ�ȡ�����׼��

        buff.flip();

        

        out.write(buff);

    }

}