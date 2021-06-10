# Film Koleksiyonu 

![](https://github.com/erhancnlc/movie-collection/blob/main/images/spring-boot.png)

**movie-collection**, film koleksiyon bilgileri tutan bir projedir. Film koleksiyonu, içerisinde film bilgileri ve oyuncu bilgilerini barındırır.
Proje içerisinde oyuncu veya film ekleyebilir, güncelleyebilir ve silebilirsiniz. Bazı film öz niteliklere göre arama yapabilir ve sıralayabilirsiniz.

## Kullanılan Teknolojiler
- Spring Boot
- Spring MVC
- Spring Security
- JPA/Hibernate
- MySQL 8
- Thymeleaf
- Javascript
- HTML, CSS, Bootstrap

## Gereksinimler 
- Java 8
- Git komut satır aracı
- Tercih Ettiğiniz IDE  
  * Intellij Idea
  * Eclipse 
  * VS code
  
## Nasıl Çalıştırılır

Projeyi isterseniz herhangi bir IDE yardımıyla başlatabilir veya consol üzerinden çalıştırabilirsinz. 
Konsol üzerinden kolayca çalıştırmak için;

Repository kopyalayın ve komut satırından yürütün:
``` 
git clone https://github.com/erhancnlc/movie-collection.git
``` 
Oluşturulan yeni klasöre gidin:
```
cd movie-collection
``` 
Repository maven ile kurun:
```bash
mvn install
``` 
Projeyi çalıştırabilirsiniz:
``` bash
/mvnw clean spring-boot:run
``` 

Uygulamayı ```localhost:8080``` adresinden başlamış durumda. :thumbsup:

Uygulamayı başlattıktan sonra sisteme giriş sayfası yönlendirilicektir. Sisteme giriş yapabilmek için 2 farklı kullanıcı tipi bulunmaktadır. Kullanıcı tanımlama silme ve
yetkilendirme gibi bütün işlere yönetebilen admin kullanıcıları bulunmaktadır. Diğer kullanıcı türü olan emploee ise film ekleme, güncelleme yetkileri bulunan kullanıcılardır.
Kullanıcı türleri rol yapısına göre yetkilendirilmiştir. Employee kullanıcılar silme işlemlerine gerçekleştiremez ve admin panelini yönetemez.

Spring boot projemizde Data Jpa kullandığımız için tablolar otomatik oluşucaktır. Tek yapılması gereken bir schema oluşturup 
veritabanınızın ismini belirledikten sonra "application.properties" dosyasında DataSource kısmının altındaki url adresini belirlemiş
olduğunuz schema ismine göre düzenlenmelidir. Sisteme giriş yapabilmek için ilk kullanıcıyı veritabanından kendi bilgileriniz ile oluşturabilir veya 
projenin içinde bulunan databaseRequirements.txt dosyasının içerisindeki bilgileri kopyalayıp veritabanınızın içerisinde çalıştırabilir ve 
sisteme giriş yapabilirsiniz. 

## Proje Yapısı

- Lombok kütüphanesi kullanılarak standart olarak yazılması gereken kod parçacıklarını annotation kullanılarak oluşturuldu. Bu sayede yazılan standart kodların
 miktarı azaldı ve okunulabilirliği arttırıldı.
- Spring Data Jpa kullanılarak bünyesinde bulundurduğu sorgu metodları ve custom query'ler kullanılarak veritabanı işleri kolaylaştırıldı.
- Sistemde kullanıcı ekleme ve tüm silme işlemlerini sadece admin türündeki kullanıcılar yapabilmektedir. Eğer employee türünde kullanıcı bir silme işlemi gerçekleştirse
sistem otomatik olarak hata mesajı dönecektir.
- Employee kullanıcısının sisteme giriş endpoint ile admin kullanıcısının sisteme giriş endpointleri farklıdır. Rol yapısına göre yetkinlendirilme gerçekleştirilmiştir. Bu sayede farklı kulllanıcılar için farklı sayfalar gösterilmiştir.
- Sistemdeki tüm kullanıcıların şifreleri veritabanında bcrypt algoritması kullanılarak şifrelenmektedir. Bunu yapma sebebimiz ise sistemimize bir şekilde ulaşabilen birisinin 
hiç bir kullanıcı şifrelerimize ulaşamaması için oluşturulan bir yapıdır.
- İncelemek istediğimiz hata durumlarını, sistem de oluşan olaylar hakkında bilgileri takip etmek için log4j kütüphanesi kullanılmıştır. 


















