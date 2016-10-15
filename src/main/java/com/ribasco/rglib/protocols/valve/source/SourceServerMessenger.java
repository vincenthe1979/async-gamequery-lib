/***************************************************************************************************
 * MIT License
 *
 * Copyright (c) 2016 Rafael Ibasco
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
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 **************************************************************************************************/

package com.ribasco.rglib.protocols.valve.source;

import com.ribasco.rglib.core.enums.ChannelType;
import com.ribasco.rglib.core.enums.ProcessingMode;
import com.ribasco.rglib.core.handlers.ErrorHandler;
import com.ribasco.rglib.core.messenger.GameServerMessenger;
import com.ribasco.rglib.core.transport.NettyUdpTransport;
import com.ribasco.rglib.protocols.valve.source.handlers.SourcePacketAssembler;
import com.ribasco.rglib.protocols.valve.source.handlers.SourcePacketDecoder;
import com.ribasco.rglib.protocols.valve.source.handlers.SourceRequestEncoder;
import com.ribasco.rglib.protocols.valve.source.request.*;
import com.ribasco.rglib.protocols.valve.source.response.*;
import io.netty.channel.ChannelOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by raffy on 9/14/2016.
 */
public class SourceServerMessenger extends GameServerMessenger<SourceServerRequest, SourceServerResponse, NettyUdpTransport> {

    private static final Logger log = LoggerFactory.getLogger(SourceServerMessenger.class);

    public SourceServerMessenger() {
        //Use the default session manager
        super(new NettyUdpTransport(), ProcessingMode.ASYNCHRONOUS);
    }

    @Override
    public void configureTransport(NettyUdpTransport transport) {
        //Set to NIO UDP Type
        transport.setChannelType(ChannelType.NIO_UDP);

        //Instantiate our packet builder
        SourcePacketBuilder builder = new SourcePacketBuilder(transport.getAllocator());

        //Set our channel initializer
        transport.setChannelInitializer(channel -> {
            log.debug("Initializing Channel");
            channel.pipeline().addLast(new ErrorHandler());
            channel.pipeline().addLast(new SourceRequestEncoder(builder));
            channel.pipeline().addLast(new SourcePacketAssembler());
            channel.pipeline().addLast(new SourcePacketDecoder(this, builder));
        });

        //Channel Options
        transport.addChannelOption(ChannelOption.SO_SNDBUF, 1048576);
        transport.addChannelOption(ChannelOption.SO_RCVBUF, 1048576 * 8);
    }

    @Override
    public void configureMappings(Map<Class<? extends SourceServerRequest>, Class<? extends SourceServerResponse>> map) {
        map.put(SourceInfoRequest.class, SourceInfoResponse.class);
        map.put(SourceChallengeRequest.class, SourceChallengeResponse.class);
        map.put(SourcePlayerRequest.class, SourcePlayerResponse.class);
        map.put(SourceRulesRequest.class, SourceRulesResponse.class);
        map.put(SourceMasterServerRequest.class, SourceMasterServerResponse.class);
    }
}