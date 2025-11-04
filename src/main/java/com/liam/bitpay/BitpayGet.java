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

import lombok.Builder;
import lombok.Data;

/**
 * The BitpayGet class represents a data model for retrieving transaction-related
 * information in the BitPay system. This class is used to encapsulate the
 * details required to retrieve specific transaction information.
 *
 * It uses Lombok annotations for automatically generating boilerplate code
 * such as getters, setters, and the builder pattern.
 *
 * Fields:
 * - transId: Represents the transaction ID associated with the request.
 * - idGet: Represents the ID associated with the retrieval operation.
 * - factorId: Represents the factor ID for context-specific transaction identification.
 */
@Data
@Builder
public class BitpayGet {
  /**
   * Represents the unique identifier for a transaction in the BitPay system.
   * This field is used to uniquely identify a transaction when retrieving or
   * processing transaction-related information.
   */
  private String transId;
  /**
   * Represents the ID associated with the retrieval operation within the
   * BitPay system. This field is used to identify and retrieve specific
   * transaction-related information.
   */
  private String idGet;
  /**
   * Represents the factor ID associated with the operation. This is used to
   * identify a specific factor or context within a transaction or operation
   * related to the BitPay system.
   */
  private String factorId;
}
