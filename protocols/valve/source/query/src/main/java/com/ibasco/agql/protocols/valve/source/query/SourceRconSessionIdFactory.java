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

import com.ibasco.agql.core.AbstractMessage;
import com.ibasco.agql.core.session.AbstractSessionIdFactory;
import com.ibasco.agql.core.session.SessionId;

/**
 * A factory class that generates a unique session id for each client request.
 *
 * Created by raffy on 9/26/2016.
 */
public class SourceRconSessionIdFactory extends AbstractSessionIdFactory<SourceRconRequest, SourceRconResponse> {
    @Override
    public SessionId createId(AbstractMessage message) {
        if (!(message instanceof SourceRconMessage)) {
            throw new IllegalStateException("Message is not an instance of SourceRconMessage");
        }
        /*String id = new StringBuffer().append(createIdStringFromMsg(message)).toString();*/
        String id = new StringBuffer().append(createIdStringFromMsg(message)).append(":")
                .append(((SourceRconMessage) message).getRequestId()).toString();
        return new SessionId(id);
    }

    @Override
    public SessionId duplicate(SessionId id) {
        return new SessionId(id);
    }
}
