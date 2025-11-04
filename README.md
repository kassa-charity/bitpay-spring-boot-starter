# Bitpay Spring Boot Starter

یک Spring Boot Starter برای یکپارچه‌سازی آسان با درگاه پرداخت بیت‌پی (BitPay.ir)

## 📋 فهرست مطالب

- [درباره پروژه](#-درباره-پروژه)
- [ویژگی‌ها](#-ویژگی‌ها)
- [پیش‌نیازها](#-پیشنیازها)
- [نصب و راه‌اندازی](#-نصب-و-راهاندازی)
- [پیکربندی](#-پیکربندی)
- [نحوه استفاده](#-نحوه-استفاده)
- [کدهای خطا](#-کدهای-خطا)
- [مثال‌های کاربردی](#-مثالهای-کاربردی)
- [مشارکت در توسعه](#-مشارکت-در-توسعه)
- [مجوز](#-مجوز)

## 🎯 درباره پروژه

این کتابخانه یک Spring Boot Starter است که امکان یکپارچه‌سازی ساده و سریع با سرویس درگاه پرداخت بیت‌پی را فراهم می‌کند. با استفاده از این کتابخانه، می‌توانید به راحتی درخواست‌های پرداخت ایجاد کرده و وضعیت تراکنش‌ها را پیگیری کنید.

## ✨ ویژگی‌ها

- ✅ پیکربندی خودکار با Spring Boot Auto-Configuration
- ✅ ارسال درخواست پرداخت به درگاه بیت‌پی
- ✅ دریافت و بررسی وضعیت تراکنش‌ها
- ✅ مدیریت خطاها با کدهای خطای استاندارد
- ✅ پشتیبانی از Lombok برای کاهش کد تکراری
- ✅ استفاده از RestClient مدرن Spring
- ✅ مستندسازی کامل JavaDoc

## 📦 پیش‌نیازها

- Java 17 یا بالاتر
- Spring Boot 3.x
- Maven یا Gradle

## 🚀 نصب و راه‌اندازی

### Maven

```xml
<dependency>
    <groupId>io.github.kassa-charity</groupId>
    <artifactId>bitpay-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```


### Gradle

```textmate
implementation 'com.liam:bitpay-spring-boot-starter:1.0.0'
```


## ⚙️ پیکربندی

تنظیمات زیر را در فایل `application.properties` یا `application.yml` خود اضافه کنید:

### application.properties

```properties
# کلید API دریافتی از بیت‌پی (اجباری)
bitpay.api-key=your-api-key-here

# آدرس پایه سرویس (اختیاری - پیش‌فرض: https://bitpay.ir)
bitpay.base-url=https://bitpay.ir
```


### application.yml

```yaml
bitpay:
  api-key: your-api-key-here
  base-url: https://bitpay.ir  # اختیاری
```


## 💻 نحوه استفاده

### 1. تزریق BitpayClient

```java
@Service
public class PaymentService {
    
    private final BitpayClient bitpayClient;
    
    public PaymentService(BitpayClient bitpayClient) {
        this.bitpayClient = bitpayClient;
    }
}
```


### 2. ارسال درخواست پرداخت

```java
public SendResult createPayment(PaymentRequest request) {
    BitpaySend bitpaySend = BitpaySend.builder()
        .amount("10000")                           // مبلغ به ریال (اجباری)
        .redirect("https://yoursite.com/callback") // آدرس بازگشت (اجباری)
        .name("علی احمدی")                        // نام پرداخت‌کننده
        .email("ali@example.com")                  // ایمیل
        .mobileNum("09123456789")                  // شماره موبایل
        .description("خرید محصول")                // توضیحات
        .factorId("INV-1001")                      // شماره فاکتور
        .build();
    
    try {
        SendResult result = bitpayClient.send(bitpaySend);
        
        // هدایت کاربر به آدرس redirectUrl
        return result;
        
    } catch (IllegalArgumentException e) {
        // خطای اعتبارسنجی ورودی
        log.error("Invalid input: {}", e.getMessage());
        throw e;
    } catch (RuntimeException e) {
        // خطای ارتباط با درگاه
        log.error("Payment gateway error: {}", e.getMessage());
        throw e;
    }
}
```


### 3. دریافت وضعیت تراکنش

```java
public BitpayGetResult verifyPayment(String transId, String idGet) {
    BitpayGet bitpayGet = BitpayGet.builder()
        .transId(transId)     // شناسه تراکنش
        .idGet(idGet)         // شناسه دریافت
        .factorId("INV-1001") // شماره فاکتور
        .build();
    
    try {
        BitpayGetResult result = bitpayClient.get(bitpayGet);
        
        // بررسی وضعیت پرداخت
        if (result.getStatus() == 1) {
            // پرداخت موفق
            log.info("Payment successful. Amount: {}", result.getAmount());
        } else {
            // پرداخت ناموفق
            log.warn("Payment failed. Status: {}", result.getStatus());
        }
        
        return result;
        
    } catch (RuntimeException e) {
        log.error("Error verifying payment: {}", e.getMessage());
        throw e;
    }
}
```


## ⚠️ کدهای خطا

### خطاهای ارسال پرداخت (BitpaySendErrorCodes)

| کد | توضیح |
|----|-------|
| `-1` | کلید API نامعتبر است |
| `-2` | مبلغ نامعتبر است |
| `-3` | آدرس بازگشت (redirect) نامعتبر است |
| `-4` | درگاه نامعتبر یا پشتیبانی نمی‌شود |
| `-5` | خطای عمومی درگاه |

### خطاهای دریافت وضعیت (BitpayGetErrorCodes)

| کد | توضیح |
|----|-------|
| `-1` | کلید API نامعتبر است |
| `-2` | شناسه تراکنش نامعتبر است |
| `-3` | شناسه دریافت (idGet) نامعتبر است |
| `-4` | شماره فاکتور یافت نشد یا عملیات ناموفق بود |

## 📚 مثال‌های کاربردی

### مثال کامل کنترلر پرداخت

```java
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    
    private final BitpayClient bitpayClient;
    
    public PaymentController(BitpayClient bitpayClient) {
        this.bitpayClient = bitpayClient;
    }
    
    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
        BitpaySend bitpaySend = BitpaySend.builder()
            .amount(request.getAmount())
            .redirect("https://mysite.com/payment/callback")
            .name(request.getCustomerName())
            .email(request.getCustomerEmail())
            .mobileNum(request.getCustomerMobile())
            .description(request.getDescription())
            .factorId(request.getOrderId())
            .build();
        
        SendResult result = bitpayClient.send(bitpaySend);
        
        PaymentResponse response = new PaymentResponse();
        response.setRedirectUrl(result.getRedirectUrl());
        response.setTransactionCode(result.getResult());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/callback")
    public String paymentCallback(@RequestParam String trans_id, 
                                  @RequestParam String id_get,
                                  @RequestParam String factor_id) {
        BitpayGet bitpayGet = BitpayGet.builder()
            .transId(trans_id)
            .idGet(id_get)
            .factorId(factor_id)
            .build();
        
        BitpayGetResult result = bitpayClient.get(bitpayGet);
        
        if (result.getStatus() == 1) {
            // ذخیره تراکنش موفق در دیتابیس
            return "redirect:/payment/success";
        } else {
            // مدیریت تراکنش ناموفق
            return "redirect:/payment/failed";
        }
    }
}
```


## 🤝 مشارکت در توسعه

مشارکت شما در بهبود این پروژه بسیار ارزشمند است! لطفاً مراحل زیر را دنبال کنید:

1. این مخزن را Fork کنید
2. یک شاخه جدید برای ویژگی خود ایجاد کنید (`git checkout -b feature/amazing-feature`)
3. تغییرات خود را Commit کنید (`git commit -m 'Add some amazing feature'`)
4. شاخه را Push کنید (`git push origin feature/amazing-feature`)
5. یک Pull Request ایجاد کنید

## 📝 مجوز

این پروژه تحت مجوز MIT منتشر شده است. برای اطلاعات بیشتر، فایل `LICENSE` را مشاهده کنید.

## 📞 پشتیبانی

- 🌐 وبسایت بیت‌پی: [https://bitpay.ir](https://bitpay.ir)
- 📧 ایمیل پشتیبانی: support@bitpay.ir
- 📖 مستندات API: مراجعه به پنل کاربری بیت‌پی

---

**نکته مهم:** این کتابخانه غیررسمی است و توسط تیم بیت‌پی پشتیبانی نمی‌شود. لطفاً قبل از استفاده در محیط تولید، تست‌های کامل انجام دهید.