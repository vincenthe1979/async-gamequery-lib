/*
 * MIT License
 *
 * Copyright (c) 2016 Asynchronous Game Query Library
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ibasco.agql.protocols.valve.source.query;

import com.ibasco.agql.core.AbstractPacket;
import com.ibasco.agql.core.utils.ByteUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by raffy on 9/1/2016.
 */
public abstract class SourceServerPacket extends AbstractPacket {
    private byte[] protocolHeader = new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};

    public byte[] getProtocolHeader() {
        return this.protocolHeader;
    }

    public void setProtocolHeader(byte[] protocolHeader) {
        this.protocolHeader = protocolHeader;
    }

    public void setProtocolHeader(byte protocolHeader) {
        this.protocolHeader = new byte[]{protocolHeader};
    }

    @Override
    public ToStringBuilder toStringBuilder() {
        return super.toStringBuilder().append("protocol_header", ByteUtils.bytesToHex(this.protocolHeader));
    }
}
