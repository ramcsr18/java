/* 
 * Copyright (c) 2009, 2012 IBM Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dave Locke - initial API and implementation and/or initial documentation
 */
package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * An on-the-wire representation of an MQTT PUBREL message.
 */
public class MqttPubRel extends MqttPersistableWireMessage {

	/**
	 * Createa a pubrel message based on a pubrec
	 * @param pubRec
	 */
	public MqttPubRel(MqttPubRec pubRec) {
		super(MqttWireMessage.MESSAGE_TYPE_PUBREL);
		this.setMessageId(pubRec.getMessageId());
	}
	
	/**
	 * Creates a pubrel based on a pubrel set of bytes read fro the network
	 * @param info
	 * @param data
	 * @throws IOException
	 */
	public MqttPubRel(byte info, byte[] data) throws IOException {
		super(MqttWireMessage.MESSAGE_TYPE_PUBREL);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
		dis.close();
	}

	protected byte[] getVariableHeader() throws MqttException {
		return encodeMessageId();
	}
	
	protected byte getMessageInfo() {
		return (byte)( 2 | (this.duplicate?8:0));
	}

	public String toString() {
    	return super.toString() + " msgId " + msgId;
	}

}
