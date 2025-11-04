/*
 * MIT License
 *
 * Copyright (c) 2025 Dr.Liam
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
 *
 */

package com.liam.bitpay;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

/**
 * Configuration class for setting up BitPay-related beans.
 * <br>
 * This class is annotated as an autoconfiguration class and is enabled with
 * BitpayProperties to get required configuration properties for interacting
 * with the BitPay service.
 */
@AutoConfiguration
@EnableConfigurationProperties(BitpayProperties.class)
public class BitpayConfiguration {

  private final BitpayProperties bitpayProperties;

  public BitpayConfiguration(BitpayProperties bitpayProperties) {
    this.bitpayProperties = bitpayProperties;
  }

  @Bean
  RestClient restClient(RestClient.Builder builder) {
    return builder
      .baseUrl(bitpayProperties.baseUrl())
      .build();
  }

  @Bean
  BitpayClient bitpayClient(RestClient restClient) {
    return new BitpayClient(restClient, bitpayProperties.apiKey(), bitpayProperties.baseUrl());
  }
}
