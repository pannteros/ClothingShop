# 👗 StyleShop — Online Clothing E-Shop
**Coding Factory 10 | Τελικό Project | ΟΠΑ**

## 🛠️ Tech Stack
- **Backend:** Java 17 + Spring Boot 3.x
- **Frontend:** Thymeleaf + CSS
- **Database:** MySQL
- **Security:** Spring Security (BCrypt)
- **ORM:** Spring Data JPA / Hibernate

## 📁 Αρχιτεκτονική (Domain-Driven Design)
```
src/main/java/com/eshop/clothingshop/
├── model/          → Entities: Product, User, Order, OrderItem
├── repository/     → JPA Repositories
├── service/        → Business Logic (ProductService, UserService, OrderService)
├── controller/     → MVC Controllers (Product, Auth, Order, Admin)
├── security/       → Spring Security Config
└── config/         → DataInitializer (seed data)
```

## ⚙️ Ρύθμιση & Εκκίνηση

### 1. MySQL
```sql
-- Δεν χρειάζεται να φτιάξεις manually τη ΒΔ.
-- Το Spring Boot τη δημιουργεί αυτόματα με createDatabaseIfNotExist=true
```

### 2. application.properties
Άλλαξε το password στο `src/main/resources/application.properties`:
```properties
spring.datasource.password=YOUR_PASSWORD_HERE
```

### 3. Build & Run
```bash
mvn clean install
mvn spring-boot:run
```

Η εφαρμογή τρέχει στο: **http://localhost:8080**

## 👤 Default Accounts (δημιουργούνται αυτόματα)

| Role  | Username | Password  |
|-------|----------|-----------|
| Admin | admin    | admin123  |

## 🗺️ URLs
| URL | Περιγραφή |
|-----|-----------|
| `/` | Αρχική → redirect στα products |
| `/products` | Λίστα προϊόντων |
| `/products/{id}` | Λεπτομέρειες προϊόντος |
| `/auth/login` | Σύνδεση |
| `/auth/register` | Εγγραφή |
| `/orders` | Παραγγελίες χρήστη (απαιτεί login) |
| `/admin` | Admin Dashboard (απαιτεί ADMIN role) |
| `/admin/products` | CRUD Προϊόντων |
| `/admin/orders` | Διαχείριση Παραγγελιών |

## 🧪 Testing
- Unit tests: `mvn test`
- Integration: Postman collection (αν προστεθεί REST API)

## 📦 Domain Model
```
Product ←──── OrderItem ────→ Order ←──── User
```

## 🚀 Docker (προαιρετικά)
```bash
docker run --name clothingshop-db \
  -e MYSQL_ROOT_PASSWORD=rootpass \
  -e MYSQL_DATABASE=clothingshop \
  -p 3306:3306 -d mysql:8
```
