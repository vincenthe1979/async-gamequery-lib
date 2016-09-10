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

package com.ribasco.gamecrawler.protocols.valve.server.packets.response;

import com.ribasco.gamecrawler.protocols.valve.server.SourceResponsePacket;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.ribasco.gamecrawler.utils.ByteBufUtils.readString;

/**
 * Created by raffy on 9/6/2016.
 */
public class SourceRulesResponsePacket extends SourceResponsePacket<Map<String, String>> {
    public SourceRulesResponsePacket(ByteBuf buffer) {
        super(buffer);
    }

    @Override
    protected Map<String, String> createFromBuffer() {
        Map<String, String> ruleList = new HashMap<>();
        ByteBuf data = getBuffer();
        short numOfRules = data.readShortLE();
        for (int i = 0; i < numOfRules; i++) {
            String name = readString(data);
            String value = readString(data);
            ruleList.put(name, value);
        }
        return ruleList;
    }
}