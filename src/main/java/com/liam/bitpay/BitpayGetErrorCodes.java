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

/**
 * The BitpayGetErrorCodes class defines a set of constant error codes used in the BitPay system.
 * These error codes are intended to represent specific error conditions encountered
 * during various BitPay operations, such as API key validation, transaction processing,
 * and retrieval operations.
 *
 * Each error code is represented as a constant integer with an associated descriptive comment,
 * allowing developers to handle specific error cases more effectively by referencing
 * these predefined constants.
 *
 * This class acts as a centralized repository for error code definitions, promoting
 * consistency and reducing the risk of hardcoded values in the codebase.
 */
public class BitpayGetErrorCodes {
  /**
   * This constant represents the error code for an invalid API key in the BitPay system.
   * It is used to indicate that the provided API key is invalid or not recognized
   * during authentication or API operations.
   *
   * Value: -1
   */
  public static final int INVALID_API_KEY = -1;
  /**
   * Error code indicating an invalid transaction ID in the BitPay system.
   *
   * This constant is used to represent an error state where the provided
   * transaction ID is invalid or cannot be processed. It typically occurs
   * when the transaction ID is missing, malformed, or does not match any
   * existing records within the system.
   */
  public static final int INVALID_TRANS_ID = -2;
  /**
   * Error code representing an invalid ID error during a retrieval operation
   * in the BitPay system. This constant is used to indicate that the provided
   * ID for the retrieval operation is invalid or could not be processed.
   */
  public static final int INVALID_ID_GET = -3;
  /**
   * Error code indicating that the factor ID was either not found or the related
   * operation encountered a failure. This code is used in scenarios where a
   * specific factor ID could not be processed or retrieved successfully.
   *
   * Value: -4
   */
  public static final int FACTOR_ID_NOTFOUND_OR_FAILED = -4;
}
