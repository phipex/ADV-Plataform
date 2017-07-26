/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ies.raspb_cab.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.*;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QrcodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QrcodeService.class);

    public byte[] generateQRCode(String text, int width, int height) throws IOException {

        Assert.hasText(text);
        Assert.isTrue(width > 0);
        Assert.isTrue(height > 0);

        LOGGER.info("Will generate image  text=[{}], width=[{}], height=[{}]", text, width, height);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitMatrix matrix = null;

        try {
            matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException e) {
            //e.printStackTrace();
            LOGGER.error("",e);
            matrix = null;
        }

        MatrixToImageWriter.writeToStream(matrix, MediaType.IMAGE_PNG.getSubtype(), baos, new MatrixToImageConfig());
        return baos.toByteArray();
    }

    @Async
    public ListenableFuture<byte[]> generateQRCodeAsync(String text, int width, int height) throws IOException {
        return new AsyncResult<>(generateQRCode(text, width, height));
    }

}
