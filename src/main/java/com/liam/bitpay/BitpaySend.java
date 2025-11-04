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
 * The BitpaySend class represents a data model for sending transaction-related information
 * in the BitPay system. This class is used to encapsulate the details required for initiating
 * or processing a transaction.
 *
 * It uses Lombok annotations for automatically generating boilerplate code
 * such as getters, setters, and the builder pattern.
 *
 * Fields:
 * - amount: Represents the amount of the transaction.
 * - redirect: Represents the URL to redirect to after the transaction is processed.
 * - factorId: Represents the factor ID for context-specific transaction identification.
 * - name: Represents the name of the individual associated with the transaction.
 * - email: Represents the email address of the individual associated with the transaction.
 * - description: Represents a text-based description of the transaction.
 * - mobileNum: Represents the mobile number of the individual associated with the transaction.
 * - cardNum: Represents the card number used in the transaction.
 */
@Builder
@Data
public class BitpaySend {
  /**
   * Represents the amount associated with a transaction in the BitPay system.
   * This field indicates the monetary value involved in the transaction,
   * typically expressed as a string to preserve formatting and precision.
   */
  private String amount;
  /**
   * Represents the URL to redirect to after the transaction is processed.
   * This field is used within the BitPay system to guide users or systems
   * to a specific location post-transaction, such as a confirmation page
   * or status page.
   */
  private String redirect;
  /**
   * Represents the factor ID associated with a specific transaction or operation
   * in the BitPay system. This field is used to provide context or identify
   * a particular factor relevant to the transaction details.
   */
  private String factorId;
  /**
   * Represents the name of the individual associated with the transaction.
   * This field is used to identify the user involved in the transaction
   * within the BitPay system.
   */
  private String name;
  /**
   * Represents the email address associated with the transaction.
   * This field is used to identify and contact the individual involved
   * in the transaction process.
   */
  private String email;
  /**
   * Represents a text-based description of the transaction. This field contains
   * details or additional information that describes the purpose or context of
   * the transaction within the BitPay system.
   */
  private String description;
  /**
   * Represents the mobile number of the individual associated with the transaction.
   * This field is used to store and process the mobile contact information
   * required for transaction-related operations in the BitPay system.
   */
  private String mobileNum;
  /**
   * Represents the card number used in the transaction.
   * This field is typically associated with the payment method and
   * identifies the card utilized for completing the transaction.
   */
  private String cardNum;
}
