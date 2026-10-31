# Soldier Client

[English](#english) | [فارسی](#فارسی)

---

## English

A fast, lightweight Android VPN / proxy client based on **v2rayNG**, powered by **Xray-core**.

Soldier adds practical tools for restricted networks: **Aether**, scanners, a spoof-config converter, and config encryption helpers.

> Inspired by [2dust/v2rayNG](https://github.com/2dust/v2rayNG)  
> Thanks to the original v2rayNG and Xray-core developers.

**Independent community project — not an official 2dust product.**

---

### Features

#### Core
- VLESS / VMess / Trojan / Shadowsocks / WireGuard / Hysteria2
- Reality, TLS, gRPC, WebSocket, HTTP Upgrade, SplitHTTP, H2
- Subscriptions, routing rules, IPv6, per-app proxy
- Updated Xray core

#### Aether
- Aether profiles for advanced tunnels (WireGuard, MASQUE, nested modes)
- **Psiphon only** and **Tor only** as alternative paths under heavy filtering
- Scan / endpoint helpers and connection flow tuned for slow or restricted networks

#### Menu tools
| Tool | Description |
|------|-------------|
| **SNI Spoof (local engine)** | For special SNI Spoof configs that use local listen **`127.0.0.1:40443`**. Applied to those local spoof profiles. |
| **Soldier Scanner** | Scan IP ranges and list healthy / usable IPs for restricted conditions. |
| **IP Lookup** | Enter a domain and list the IPs behind it. |
| **Spoof Config Converter** | Convert VLESS / Trojan / VMess links into local spoof-ready configs (`127.0.0.1:40443`). |
| **Config Encrypt / Decrypt** | Encrypt or decrypt configs for safer sharing and storage. |

#### Notes
- Aether WARP-family modes (WireGuard / MASQUE, …) typically exit via Cloudflare; **Psiphon only** / **Tor only** are for non-Cloudflare exit paths.
- Local SNI Spoof tools are meant for configs designed around **`127.0.0.1:40443`**.

---

### Telegram
Official channel for updates and guides:  
https://t.me/SoldierClient

### Downloads
[Releases](../../releases) — APK files

### Build
Open the project in Android Studio or use the Gradle Wrapper.  
Do not commit keystores or `local.properties`.

### License
GPL-3.0 (compatible with upstream)

Independent community project — not an official 2dust product.

### Disclaimer
Use only in accordance with local laws and terms of service. The user is responsible for any misuse.

---

## فارسی

کلاینت اندروید سبک و سریع مبتنی بر **v2rayNG** با هسته **Xray**.

Soldier ابزارهای کاربردی برای شرایط محدود شبکه اضافه می‌کند: **Aether**، اسکنر، مبدل کانفیگ اسپوف، و رمزنگاری/رمزگشایی کانفیگ.

> الهام‌گرفته از [2dust/v2rayNG](https://github.com/2dust/v2rayNG)  
> با تشکر از توسعه‌دهندگان اصلی v2rayNG و Xray-core

**پروژه مستقل جامعه — محصول رسمی 2dust نیست.**

---

### قابلیت‌ها

#### هسته
- VLESS / VMess / Trojan / Shadowsocks / WireGuard / Hysteria2
- Reality، TLS، gRPC، WebSocket، HTTP Upgrade، SplitHTTP، H2
- سابسکریپشن، مسیریابی، IPv6، پروکسی بر اساس اپ
- هسته Xray به‌روزشده

#### Aether
- پروفایل‌های Aether برای تونل‌های پیشرفته (WireGuard، MASQUE و حالت‌های ترکیبی)
- حالت‌های **Psiphon only** و **Tor only** برای مسیر جایگزین در فیلترینگ سخت
- اسکن اندپوینت و اتصال مناسب شبکه‌های کند یا محدود

#### ابزارهای منو
| ابزار | توضیح |
|------|--------|
| **SNI Spoof (موتور محلی)** | برای کانفیگ‌های مخصوص SNI Spoof با آدرس محلی **`127.0.0.1:40443`** |
| **Soldier Scanner** | اسکن رنج IP و نمایش IPهای سالم / قابل‌استفاده در شرایط محدود |
| **IP Lookup** | دامنه را وارد کنید؛ IPهای پشت آن را نشان می‌دهد |
| **مبدل کانفیگ اسپوف** | تبدیل لینک‌های VLESS / Trojan / VMess به کانفیگ آماده اسپوف محلی (`127.0.0.1:40443`) |
| **رمزنگاری / رمزگشایی کانفیگ** | رمزگذاری یا رمزگشایی کانفیگ برای اشتراک و نگهداری امن‌تر |

#### نکات
- حالت‌های خانواده WARP در Aether (مثل WireGuard / MASQUE) معمولاً از Cloudflare خارج می‌شوند؛ برای خروجی غیر Cloudflare از **Psiphon only** یا **Tor only** استفاده کنید.
- ابزارهای اسپوف محلی برای کانفیگ‌هایی است که روی **`127.0.0.1:40443`** طراحی شده‌اند.

---

### تلگرام
کانال رسمی برای آپدیت و آموزش:  
https://t.me/SoldierClient

### دانلود
بخش [Releases](../../releases) — فایل‌های APK

### ساخت (Build)
پروژه را در Android Studio باز کنید یا با Gradle Wrapper بسازید.  
فایل keystore و `local.properties` را commit نکنید.

### لایسنس
GPL-3.0 (هم‌راستا با پروژه اصلی)

پروژه مستقل جامعه — محصول رسمی 2dust نیست.

### سلب مسئولیت
فقط مطابق قوانین محل زندگی و شرایط ارائه‌دهنده اینترنت استفاده کنید. مسئولیت سوءاستفاده با کاربر است.
