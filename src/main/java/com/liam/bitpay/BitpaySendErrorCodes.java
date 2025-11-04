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
 * The BitpaySendErrorCodes class defines a set of static constants representing
 * error codes used within the BitPay system for identifying specific errors
 * related to sending transactions.
 * <p>
 * Error code constants:
 * - INVALID_API_KEY: Indicates that the provided API key is invalid.
 * - INVALID_AMOUNT: Indicates that the specified amount for the transaction is invalid.
 * - INVALID_REDIRECT: Indicates that the provided redirect URL is invalid.
 * - INVALID_GATEWAY: Indicates that the specified gateway is invalid or not supported.
 * - GATEWAY_ERROR: Indicates an unspecified error while interacting with the gateway.
 * <p>
 * This class is intended to centralize error code definitions, enabling consistent
 * handling and interpretation of errors throughout the BitPay system.
 * <p>
 * Note: Constants are represented as integer values.
 */
public final class BitpaySendErrorCodes {
  /**
   * Error code indicating that the provided API key is invalid.
   * This constant is used within the BitPay system to identify and
   * handle scenarios where the provided API key is not recognized
   * or fails validation, potentially due to being expired, revoked,
   * or incorrectly formatted.
   */
  public static final int INVALID_API_KEY = -1;
  /**
   * Indicates that the specified amount for the transaction is invalid.
   * This error code is used within the BitPay system to denote a scenario
   * where the provided transaction amount does not meet the required criteria
   * (e.g., non-numeric value, negative value, or a value that falls outside
   *  the permissible range for transactions).
   */
  public static final int INVALID_AMOUNT = -2;
  /**
   * Indicates that the provided redirect URL is invalid.
   * This error code is used within the BitPay system to signal that
   * the redirect URL specified in a transaction or operation does not
   * meet the expected criteria or contains invalid formatting.
   */
  public static final int INVALID_REDIRECT = -3;

  /**
   * Error code representing an invalid or unsupported gateway in the BitPay system.
   * This error indicates that the specified gateway provided during a transaction
   * or operation is incorrectly configured, unavailable, or not supported by the system.
   * <p>
   * Value: -4
   */
  public static final int INVALID_GATEWAY = -4;

  /**
   * Represents an error code indicating an unspecified error occurred while interacting with the payment gateway.
   * <p>
   * This error code is used within the BitPay system to signal that a problem was encountered during
   * communication or operations involving the payment gateway, for which a specific issue could not
   * be identified.
   * <p>
   * Value: -5
   */
  public static final int GATEWAY_ERROR = -5;
}
