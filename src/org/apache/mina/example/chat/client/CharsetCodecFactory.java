package org.apache.mina.example.chat.client;

 

import org.apache.mina.core.session.IoSession;

import org.apache.mina.filter.codec.ProtocolCodecFactory;

import org.apache.mina.filter.codec.ProtocolDecoder;

import org.apache.mina.filter.codec.ProtocolEncoder;

 

/**

 * <b>function:</b> �ַ���롢���빤���࣬������˹���

 * @author hoojo

 * @createDate 2012-6-26 ����01:08:50

 * @file CharsetCodecFactory.java

 * @package com.hoo.mina.code.factory

 * @project ApacheMiNa

 * @blog http://blog.csdn.net/IBM_hoojo

 * @email hoojo_@126.com

 * @version 1.0

 */

public class CharsetCodecFactory implements ProtocolCodecFactory {

 




    public ProtocolDecoder getDecoder(IoSession session) throws Exception {

        return new CharsetDecoder();

    }

 

    public ProtocolEncoder getEncoder(IoSession session) throws Exception {

        return new CharsetEncoder();

    }

}
