# Soldier Client

**English | [فارسی](#فارسی)**

A fast, lightweight Android VPN / proxy client based on **v2rayNG**, powered by **Xray-core**.

Soldier adds SNI/CDN tools, scanners, and settings that help TLS configs work better under heavy network filtering.

> Inspired by [2dust/v2rayNG](https://github.com/2dust/v2rayNG).  
> Thanks to the original v2rayNG and Xray-core developers.

---

## Features

### Core
- VLESS / VMess / Trojan / Shadowsocks / WireGuard / Hysteria2
- Reality, TLS, gRPC, WebSocket, HTTP Upgrade, SplitHTTP, H2
- Subscriptions, routing rules, IPv6, per-app proxy
- Connection duration timer

### Menu tools

| Tool | Description |
|------|-------------|
| **SNI Spoof** | Enter IP, Port and SNI for special SNI Spoof configs (usually `127.0.0.1:40443`). Applied to those local spoof profiles. |
| **Spoof Scanner** | Provide an SNI Spoof config plus IP ranges and ports. Finds matching IPs and can export subscription links. |
| **Soldier Scanner** | Scan custom IP ranges and show healthy / whitelist-friendly IPs. |
| **IP Lookup** | Enter a domain and list the IPs behind it. |

### Settings

| Option | Description |
|--------|-------------|
| **CDN IP (TLS)** | Address/port override for **TLS** configs (VLESS, VMess, SS, Trojan, H2, …). |
| **CDN Spoof** | Use a CDN domain for routing when filtering is strict. |
| **DNS Resolver List** | Custom DNS resolvers for all configs. |

---

## Telegram

**https://t.me/SoldierClient**

---

## Downloads

[Releases](https://github.com/moorvye/Soldier-Client/releases) — APK files

---

## Build

Open the project in Android Studio or use Gradle Wrapper.  
Do not commit keystores or `local.properties`.

---

## License

GPL-3.0 (compatible with upstream).  
Independent community project — not an official 2dust product.

---

# فارسی

کلاینت اندروید سبک و سریع مبتنی بر **v2rayNG** با هسته **Xray**.

Soldier ابزارهای SNI/CDN، اسکنر و تنظیمات اضافه دارد تا کانفیگ‌های TLS در محدودیت‌های شدید اینترنت پایدارتر کار کنند.

> الهام‌گرفته از [2dust/v2rayNG](https://github.com/2dust/v2rayNG)  
> با تشکر از توسعه‌دهندگان اصلی v2rayNG و Xray-core

---

## قابلیت‌ها

### هسته
- VLESS / VMess / Trojan / Shadowsocks / WireGuard / Hysteria2
- Reality، TLS، gRPC، WebSocket، HTTP Upgrade، SplitHTTP، H2
- سابسکریپشن، مسیریابی، IPv6، پروکسی بر اساس اپ
- تایمر مدت زمان اتصال

### ابزارهای منو

| ابزار | توضیح |
|--------|--------|
| **SNI Spoof** | آی‌پی، پورت و SNI را وارد می‌کنید؛ روی کانفیگ‌های مخصوص SNI Spoof (معمولاً `127.0.0.1` و پورت `40443`) اعمال می‌شود. |
| **Spoof Scanner** | یک کانفیگ SNI Spoof به‌همراه IP Range و پورت‌ها؛ اسکن می‌کند و IPهای مناسب را به‌صورت ساب‌لینک قابل‌استفاده درمی‌آورد. |
| **Soldier Scanner** | رنج IP دلخواه را اسکن می‌کند و IPهای سالم / وایت‌لیست را نشان می‌دهد. |
| **IP Lookup** | دامنه یا آدرس سایت را می‌گیرید و IPهای پشت آن را نمایش می‌دهد. |

### تنظیمات

| گزینه | توضیح |
|--------|--------|
| **CDN IP (TLS)** | برای کانفیگ‌های **TLS** (VLESS، VMess، SS، Trojan، H2 و …)؛ آدرس/پورت را برای عملکرد بهتر در محدودیت‌ها تنظیم می‌کند. |
| **CDN Spoof** | استفاده از دامنه CDN برای مسیریابی در فیلترینگ شدید. |
| **DNS Resolver List** | لیست DNS سفارشی برای همه کانفیگ‌ها. |

این گزینه‌ها می‌توانند پایداری کانفیگ‌های TLS را بهتر کنند و برای دور زدن محدودیت‌های شبکه مفید باشند.

---

## تلگرام

کانال رسمی برای آپدیت و آموزش:

**https://t.me/SoldierClient**


## دانلود

بخش [Releases](https://github.com/moorvye/Soldier-Client/releases) — فایل‌های APK


## ساخت (Build)

پروژه را در Android Studio باز کنید یا با Gradle Wrapper بسازید.  
فایل keystore و `local.properties` را commit نکنید.


## لایسنس

GPL-3.0 (هم‌راستا با پروژه اصلی).  
پروژه مستقل جامعه — محصول رسمی 2dust نیست.

## سلب مسئولیت

فقط مطابق قوانین محل زندگی و شرایط ارائه‌دهنده اینترنت استفاده کنید. مسئولیت سوءاستفاده با کاربر است.
