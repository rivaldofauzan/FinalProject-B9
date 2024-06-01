# SwagLabs Web Testing Automation

Ini adalah proyek pengujian otomatis pada web e-commerce https://saucedemo.com/ menggunakan Behavior-Driven Development (BDD) dengan menggunakan bahasa pemrograman Java dan framework Cucumber + Selenium. Proyek ini dibuat untuk memenuhi tugas mata kuliah Pengujian Perangkat Lunak (Software Testing) pada pertemuan ke-12 dan 13.

## Table of Contents

- [Software Under Test (SUT)](#software-under-test-sut)
- [Tools yang Digunakan](#tools-yang-digunakan)
- [Getting Started](#getting-started)
  - [Pre-requisites](#pre-requisites)
  - [Installation & Running Test Script](#installation)
- [Alur Kerja Pembuatan Test Script](#alur-kerja-pembuatan-test-script)
- [Test Case Design](#test-case-design)
- [Author](#author)
- [References](#references)

## Software Under Test (SUT)

Software yang diuji adalah aplikasi web e-commerce bernama [Swag Labs](https://www.saucedemo.com/). Aplikasi ini menyediakan enam buah produk yang dapat dibeli oleh pengguna. Pengguna dapat melakukan login terlebih dahulu untuk mengakses fitur-fitur yang ada pada aplikasi ini. Aplikasi ini memiliki beberapa fitur, dengan fitur yang dijadikan fokus pada pengujian ini adalah:

1. Fitur login
2. Fitur logout<br>

**Kembali ke [Daftar Isi](#table-of-contents)**

## Tools yang Digunakan

- Java Development Kit (JDK) 8
- Apache Maven 3.6.3
- Maven Surefire Plugin 3.0.0-M5
- Cucumber 7.11.1
- Cucumber (Gherkin) Full Support Extension
- WebDriverManager 5.8.0
- Selenium 4.21.0
- JUnit Jupiter 5.9.2
- Google Chrome dan Chrome Driver
- Git dan Github
- CLI (cmd atau Powershell)
- Chrome 125
- Intellij Idea

**Kembali ke [Daftar Isi](#table-of-contents)**

## Getting Started

### Pre-requisites

Agar dapat menjalankan project ini, pastikan bahwa perangkat Anda telah memenuhi spesifikasi berikut:

- [x] Java Development Kit (JDK) versi 8 atau lebih tinggi
- [x] Chrome dengan versi 125
- [x] Apache Maven versi 3.6.3 atau lebih tinggi
- [x] Web browser Google Chrome yang telah terinstall di dalam perangkat
- [x] Memiliki ekstensi Cucumber (Gherkin)
- [x] Sangat disarankan menggunakan Intellij Idea

**Kembali ke [Daftar Isi](#table-of-contents)**

### Installation & Running Test Script

1. Clone this repository to your local machine using this command on your terminal or command prompt:

```bash
git clone https://github.com/GianSandrova/Web-AutomationB9.git
```

2. After you have clone this repository, open the project using Intellij Idea.

3. Buka file src/test/java/com/sparta/swaglabs/cucumber/RunTests.java

4. Run file tersebut dan automation testing pun dimulai

**Kembali ke [Daftar Isi](#table-of-contents)**

## Alur Kerja Pembuatan Test Script

1.  Membuat file `.feature` pada folder `src/test/resources/com/swaglab/features` yang berisi skenario pengujian yang akan dijalankan dengan sintaks Gherkin. Pastikan nama file bersesuaian dengan nama fitur yang akan diuji.<br>
    a. Format dasar dalam file feature adalah sebagai berikut:
    ```gherkin
     Feature: <nama fitur yang diuji>
       Rule: <nama rules>
         Scenario: <nama skenario yang diuji>
           Given <langkah awal>
           When <langkah aksi>
           Then <langkah hasil>
    ```
2.  Membuat file `Step Definitions` pada folder `src/test/java/com/swaglab/stepDefinitions` yang berisi implementasi dari skenario pengujian yang ada pada file `.feature`. Jumlah file `Step Definitions` harus sama dengan jumlah file `.feature`.<br>
    a. Format dasar dalam file `Step Definitions` adalah sebagai berikut:

    ```java
     package com.swaglab.stepDefinitions;

     public class <nama file> {

       // misal untuk keperluan inisialisasi nama lokator dan driver
       private static <nama kelas yang diimpor> nama-variabel;

       @Before
       public void setUp() {
         if (driver == null) {
           // dapatkan nama driver dari WebDriverSetup

           // instansiasi lokator ke-1
           // initElements dari PageFactory lokator ke-1

           // instansiasi lokator ke-n
           // initElements dari PageFactory lokator ke-n
         }
       }

       @Given("<langkah awal>")
       public void langkah_awal() {
         // implementasi langkah awal
       }

       @When("<langkah aksi>")
       public void langkah_aksi() {
         // implementasi langkah aksi
       }

       @Then("<langkah hasil>")
       public void langkah_hasil() {
         // implementasi langkah hasil
       }

       @AfterAll
       public void tearDown() {
         // tutup driver
       }
     }
    ```

     <br>
     b. Tambahkan komentar yang berarti secara ringkas. Bisa secara inline ataupun block comment (khusus di atas nama kelas). Contoh:<br>
         
         /**
          * Contoh block comment:
          * Komentar ini berisi penjelasan singkat dari kelas ini
          */

         public class LoginSteps {
           // Komentar inline
           private static WebDriver driver;
         }

     <br>

3.  Membuat file `WebDriverSetup` pada folder `src/test/java/com/swaglab/utils` yang berisi setup WebDriver yang akan digunakan dalam pengujian. Contoh:<br>

    ```java
    package com.swaglab.utils;

    public class WebDriverSetup {

        private static WebDriver driver;

        private WebDriverSetup() {
            WebDriverManager.chromedriver().setup();
        }

        public static WebDriver getDriver() {
            if (driver == null) {
                WebDriverSetup.driver = new ChromeDriver();
            }

            return driver;
        }
    }
    ```

4.  Membuat file `TestRunner` pada folder `src/test/java/com/swaglab/runner` yang berisi konfigurasi untuk menjalankan test case. Contoh paling minimum:

    ```java
      @Suite
      @IncludeEngines("cucumber")
      @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:com/swaglab/features")
      public class TestRunner {

      }
    ```

5.  Membuat file properties `junit-platform.properties` pada folder `src/test/resources` yang berisi konfigurasi properti Cucumber saat kelas `TestRunner` dijalankan. Format:
    ```properties
    cucumber.<nama-properties-1>=<value-1>
    .
    .
    cucumber.<nama-properties-n>=<value-n>
    ```
6.  Membuat file-file locators pada folder `src/test/java/com/swaglab/locators` yang berisi locator dari elemen-elemen yang ada pada aplikasi web yang akan diuji. Di dalamnya, terdapat repositori elemen-elemen web yang akan diuji beserta metode-metode yang digunakan untuk mengakses elemen-elemen tersebut.<br>
    a. Simpan URL statis disini dengan scope public<br>
    b. Simpan nilai-nilai statis dari elemen-elemen web yang akan diuji disini dengan scope public<br>
    c. Simpan web element dengan anotasi `@FindBy` disini, dalam scope protected.<br>
    d. Buat constructor public untuk menginisialisasi web element<br>
    e. Tambahkan metode getter public untuk mengakses web element<br>
    f. Tambahkan metode public lain untuk mengakses elemen-elemen web yang ada pada aplikasi web yang akan diuji. Misal:<br>

    ```java
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void clickBurgerMenu() {
        burgerMenu.click();
    }
    ```

**Kembali ke [Daftar Isi](#table-of-contents)**

## Test Case Design

Terdapat total 9 (sembilan) buah test case. Berikut merupakan desain test case yang digunakan dalam pengujian web secara otomatis:

### Test Case Login

1. **Successful Login with valid username and password**<br>
   User successfully login to the website using valid credentials (valid username and password).
2. **Failed Login (Incorrect Password)**<br>
   User failed to login to the website using incorrect password.
3. **Failed Login (Unregistered username)**<br>
   User failed to login to the website using unregistered username.
4. **Failed Login (Empty Username and Password)**<br>
   User failed to login to the website using empty username and password.
5. **Failed Login (Empty Username)**<br>
   User failed to login to the website using empty username.
6. **Failed Login (Empty Password)**<br>
   User failed to login to the website using empty password.

### Test Case Logout

7. **Successful Logout**<br>
   User successfully logout from the website after login and access logout menu from burger menu.

**Kembali ke [Daftar Isi](#table-of-contents)**

## Author

- Rivaldo Fauzan Robani - NIM. 211524060
- Egi Satria Dharma .Y .W - NIM. 211524040
- Gian Sandrova - NIM. 211524043

Mahasiswa Kelas 3B Program Studi DIV-Teknik Informatika<br>
Jurusan Teknik Komputer dan Informatika<br>
Politeknik Negeri Bandung<br>
(C) Mei 2024

## References

Berikut merupakan daftar resource yang dapat dipelajari lebih lanjut terkait pengujian web secara otomatis:

1. [Selenium WebDriver](https://www.selenium.dev/documentation/en/webdriver/)
2. [Cucumber](https://cucumber.io/docs/guides/10-minute-tutorial/)
3. [Page Object Model & Page Factory](https://www.browserstack.com/guide/page-object-model-in-selenium)
4. [WebDriverManager](https://bonigarcia.dev/webdrivermanager/)

**Kembali ke [Daftar Isi](#table-of-contents)**
