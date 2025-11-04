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

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

/**
 * A client for interacting with the Bitpay payment gateway.
 * This class provides functionality to send payment requests and
 * retrieve payment results using the Bitpay API. It uses a {@link RestClient}
 * for HTTP communication and handles key request and response processing tasks.
 */
public class BitpayClient {
  /**
   * Represents the URI endpoint for sending payment requests to the gateway.
   * This constant is used to construct the URL for sending payment-related data.
   */
  private static final String GATEWAY_SEND_URI = "/payment/gateway-send";
  /**
   * The endpoint URI for handling the results of the payment gateway process.
   * This constant represents the specific path used to retrieve or manage
   * the gateway result in the second phase of the payment operations.
   */
  private static final String GATEWAY_RESULT_URI = "/payment/gateway-result-second";
  /**
   * A string pattern used to format URLs for redirecting to a specific payment gateway.
   * The pattern includes a placeholder for a numeric code that identifies the gateway.
   * Example pattern: "/payment/gateway-%d-get".
   * <p>
   * This variable is utilized in constructing redirect URLs to payment gateways
   * dynamically by replacing the placeholder with the appropriate gateway code.
   */
  private static final String GATEWAY_REDIRECT_PATTERN = "/payment/gateway-%d-get";
  /**
   * A constant flag that specifies whether JSON responses are enabled for the client.
   * It is set to "1" to indicate that JSON responses are enabled. This flag is primarily
   * utilized internally within the BitpayClient class for configuring and handling API responses.
   */
  private static final String JSON_ENABLED = "1";

  /**
   * An instance of {@link RestClient} used for making REST API calls to the Bitpay service.
   * This variable is final and must be initialized upon creation of the containing class.
   * It is a core component for executing HTTP requests with the base URL, headers,
   * and other configurations provided through the application's context.
   */
  private final RestClient restClient;
  /**
   * The API key used for authenticating requests to the BitPay service.
   * This key is required for all API calls and should be kept private and secure.
   * It is provided by the BitPay platform and is unique to each merchant or integration.
   */
  private final String apiKey;
  /**
   * The base URL for the Bitpay client to interact with the payment gateway API.
   * This URL serves as the foundational endpoint for constructing API requests.
   * It is utilized for various operations such as payment submissions, result retrievals,
   * and managing redirects within the BitpayClient.
   */
  private final String baseUrl;

  /**
   * Constructs a new {@code BitpayClient} that interacts with the Bitpay API.
   *
   * @param restClient the {@code RestClient} instance used to send HTTP requests
   * @param apiKey the API key for authenticating with the Bitpay service
   * @param baseUrl the base URL for the Bitpay API
   */
  public BitpayClient(RestClient restClient, String apiKey, String baseUrl) {
    this.restClient = restClient;
    this.apiKey = apiKey;
    this.baseUrl = baseUrl;
  }

  /**
   * Sends a payment request using the specified BitpaySend parameters. This
   * method validates the provided data, constructs the required form data,
   * sends the request to the payment gateway, processes the response,
   * and returns the result.
   *
   * @param bitpaySend an instance of BitpaySend containing the payment request details
   *                   such as amount, redirect URL, and optional user information.
   * @return a SendResult object that contains the result code of the operation
   *         and the associated redirect URL, allowing the client to navigate
   *         to the appropriate page based on the operation's outcome.
   * @throws IllegalArgumentException if any required BitpaySend attributes
   *         (e.g., amount or redirect) are missing or invalid.
   * @throws RuntimeException if there are issues with the response format or
   *         if the response body is missing.
   */
  public SendResult send(BitpaySend bitpaySend) {
    validateBitpaySend(bitpaySend);

    MultiValueMap<String, String> formData = buildSendFormData(bitpaySend);

    ResponseEntity<String> response = restClient.post()
      .uri(GATEWAY_SEND_URI)
      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
      .body(formData)
      .retrieve()
      .toEntity(String.class);

    int resultCode = handleSendResponse(response);

    return SendResult.builder()
      .result(resultCode)
      .redirectUrl(getRedirectUrl(resultCode))
      .build();
  }

  /**
   * Retrieves the result of a BitPay operation using the provided {@link BitpayGet} object.
   *
   * @param bitpayGet an object containing the required information for retrieving the payment result, such as
   *                  transaction ID, ID for the get request, and factor ID.
   * @return the result of the BitPay operation, encapsulated in a {@link BitpayGetResult} object, which includes
   *         information like status, amount, card number, and factor ID.
   * @throws RuntimeException if the response body is null or if there is an error indicated by the response status.
   */
  public BitpayGetResult get(BitpayGet bitpayGet) {
    MultiValueMap<String, String> formData = buildGetFormData(bitpayGet);

    ResponseEntity<BitpayGetResult> response = restClient.post()
      .uri(GATEWAY_RESULT_URI)
      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
      .body(formData)
      .retrieve()
      .toEntity(BitpayGetResult.class);

    validateResponse(response, "Error getting payment");

    BitpayGetResult body = response.getBody();
    if (body == null) {
      throw new RuntimeException("No response body");
    }

    return body;
  }

  /**
   * Validates the provided BitpaySend object for required fields.
   * Ensures that the amount and redirect properties are not null or empty.
   *
   * @param bitpaySend the BitpaySend object containing the details for validation
   * @throws IllegalArgumentException if the amount or redirect fields are null or empty
   */
  private void validateBitpaySend(BitpaySend bitpaySend) {
    if (bitpaySend.getAmount() == null || bitpaySend.getAmount().isEmpty()) {
      throw new IllegalArgumentException("Amount is required");
    }
    if (bitpaySend.getRedirect() == null || bitpaySend.getRedirect().isEmpty()) {
      throw new IllegalArgumentException("Redirect is required");
    }
  }

  /**
   * Builds the form data required for sending a payment request to the Bitpay service.
   *
   * @param bitpaySend an instance of {@link BitpaySend} containing the details of the payment to be sent,
   *                   including the amount, redirect URL, user details, and optional mobile or card number.
   * @return a {@code MultiValueMap<String, String>} containing the form data to be sent in the request.
   */
  private MultiValueMap<String, String> buildSendFormData(BitpaySend bitpaySend) {
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("api", apiKey);
    formData.add("amount", bitpaySend.getAmount());
    formData.add("redirect", bitpaySend.getRedirect());
    formData.add("name", bitpaySend.getName());
    formData.add("email", bitpaySend.getEmail());
    formData.add("description", bitpaySend.getDescription());
    formData.add("factorId", bitpaySend.getFactorId());

    if (bitpaySend.getMobileNum() != null) {
      formData.add("mobileNum", bitpaySend.getMobileNum());
    }
    if (bitpaySend.getCardNum() != null) {
      formData.add("cardNum", bitpaySend.getCardNum());
    }

    return formData;
  }

  /**
   * Builds a form data map for the GET operation with the appropriate parameters.
   *
   * @param bitpayGet the BitpayGet object containing the required parameters for the request
   * @return a MultiValueMap containing the form data with keys such as "api", "trans_id", "id_get", and "json"
   */
  private MultiValueMap<String, String> buildGetFormData(BitpayGet bitpayGet) {
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("api", apiKey);
    formData.add("trans_id", bitpayGet.getTransId());
    formData.add("id_get", bitpayGet.getIdGet());
    formData.add("json", JSON_ENABLED);
    return formData;
  }

  /**
   * Handles the response received from sending a payment, validating it,
   * parsing its body, and returning the response code.
   *
   * @param response the HTTP response entity containing the response body
   *                 and status code for the payment operation.
   * @return the response code as an integer parsed from the response body.
   * @throws RuntimeException if the response body is missing, empty,
   *                          or cannot be parsed into an integer.
   */
  private int handleSendResponse(ResponseEntity<String> response) {
    validateResponse(response, "Error sending payment");

    String body = response.getBody();
    if (body == null || body.isEmpty()) {
      throw new RuntimeException("No response body");
    }

    try {
      return Integer.parseInt(body);
    } catch (NumberFormatException e) {
      throw new RuntimeException("Invalid response format: " + body, e);
    }
  }

  /**
   * Validates the response from an HTTP request and throws a runtime exception
   * if the response indicates an error status.
   *
   * @param response The response entity to validate.
   * @param errorMessage The error message to include in the exception if validation fails.
   * @throws RuntimeException if the response status indicates an error or if the response body is null.
   */
  private void validateResponse(ResponseEntity<?> response, String errorMessage) {
    if (response.getStatusCode().isError()) {
      Object body = response.getBody();
      throw new RuntimeException(errorMessage + ": " + (body != null ? body : "No response"));
    }
  }

  /**
   * Constructs the redirect URL based on the given response code by formatting
   * the predefined gateway redirect pattern with the provided code.
   *
   * @param code the response code used to construct the redirect URL
   * @return the constructed redirect URL as a string
   */
  private String getRedirectUrl(int code) {
    return baseUrl + String.format(GATEWAY_REDIRECT_PATTERN, code);
  }
}