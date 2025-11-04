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
 * The BitpayGetResult class represents the result of a BitPay transaction or
 * operation, encapsulating details such as status, amount, and related identifiers.
 * This class is used within the BitPay system to provide structured access
 * to the outcome of specific operations.
 *
 * It relies on Lombok annotations to reduce boilerplate by automatically
 * generating methods like getters, setters, and the builder pattern.
 *
 * Fields:
 * - status: Represents the response status or outcome of the operation.
 * - amount: Represents the numerical value of the transaction amount.
 * - cardNum: Represents the card number involved in the operation.
 * - factorId: Represents the identifier for specific transaction factors.
 */
@Builder
@Data
public class BitpayGetResult {
  /**
   * Represents the status of the response or operation, typically used to
   * indicate success, failure, or other result states in the transaction
   * context of the BitPay system. Its specific interpretation depends on
   * the operation being performed.
   */
  private int status;
  /**
   * Represents the amount involved in the transaction.
   *
   * This field stores the numerical value corresponding to the transaction amount
   * and is used as part of the BitPay system's data model for handling transaction
   * operations.
   */
  private int amount;
  /**
   * Represents the card number associated with a transaction or operation.
   * This field is typically used to store or reference the card number involved
   * in operations within the BitPay system.
   */
  private String cardNum;
  /**
   * Represents the factor ID associated with the operation. This field is used
   * as a contextual identifier within the BitPay system, allowing specification
   * of a particular factor or context relevant to the associated transaction or
   * operation.
   */
  private String factorId;
}
