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
 * The SendResult class represents the result of a transaction or operation
 * in the context of the BitPay system. This class is used to encapsulate
 * the outcome of a specific operation, including its status and any
 * redirection URL for further actions or information.
 * <p>
 * It uses Lombok annotations to automatically generate boilerplate code
 * such as getters, setters, and the builder pattern.
 * <p>
 * Fields:
 * - result: Represents the outcome or status of the operation. Typical usage
 *   includes values such as success, failure, or error codes to signify
 *   the result of the operation.
 * - redirectUrl: Represents the URL to which a client or system can be redirected
 *   after the operation is processed. It is often used for guiding users to
 *   confirmation pages, status updates, or additional actions.
 */
@Builder
@Data
public class SendResult {
  /**
   * Represents the outcome or status of a transaction or operation in the BitPay system.
   * This field is used to indicate the result of an operation, with possible values
   * such as success, failure, or error codes that define the status of the process.
   * <p>
   * Typical usage may involve:
   * - Success codes indicating the operation was completed successfully.
   * - Error or failure codes providing insights into issues encountered.
   */
  private int result;
  /**
   * Represents the URL to which a user or client should be redirected after the
   * processing of a specific operation or transaction in the BitPay system.
   * This field is typically used to guide users to a confirmation page,
   * status page, or other relevant destination based on the operation's result.
   */
  private String redirectUrl;
}
