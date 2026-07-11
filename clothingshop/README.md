# StyleShop — Online Clothing E-Shop

> **Coding Factory 10 | Τελικό Project | Athens University of Economics and Business**

---

## Περιγραφή | Description

**GR:** Ένα online κατάστημα ρούχων με σύστημα εγγραφής/σύνδεσης χρηστών, προβολή προϊόντων και διαχείριση παραγγελιών. Αναπτύχθηκε ως τελικό project του Coding Factory 10.

**EN:** An online clothing store with user authentication, product browsing and order management. Developed as the final project for Coding Factory 10.

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java 21, Spring Boot 3.x |
| Frontend | Thymeleaf, HTML, CSS |
| Database | MySQL 8 |
| Security | Spring Security (BCrypt) |
| ORM | Spring Data JPA / Hibernate |
| Build | Maven |

---

## Αρχιτεκτονική | Architecture

```
Entity → Repository → Service → Controller → Thymeleaf View
```

```
src/main/java/com/eshop/clothingshop/
├── model/         → Product, User, Order, OrderItem
├── repository/    → JPA Repositories
├── service/       → Business Logic
├── controller/    → MVC Controllers
├── security/      → Spring Security Config
└── config/        → Data Initializer
```

---

## Εγκατάσταση | Installation

### Προαπαιτούμενα | Prerequisites
- Java 21
- Maven
- MySQL 8

### Βήματα | Steps

**1. Clone το repository**
```bash
git clone https://github.com/pannteros/ClothingShop.git
cd ClothingShop
```

**2. Ρύθμιση βάσης | Database setup**

Δεν χρειάζεται manual δημιουργία — το Spring Boot δημιουργεί αυτόματα τη βάση.

Άλλαξε το password στο `src/main/resources/application.properties`:
```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

**3. Build & Run**
```bash
mvn clean install
mvn spring-boot:run
```

**4. Άνοιξε τον browser | Open browser**
```
http://localhost:8080
```

---

## Default Accounts

| Role | Username | Password |
|------|----------|----------|
| Admin | admin | admin123 |

*(Δημιουργούνται αυτόματα κατά την εκκίνηση / Created automatically on startup)*

---

## Σελίδες | Pages

| URL | Περιγραφή | Πρόσβαση |
|-----|-----------|----------|
| `/products` | Λίστα προϊόντων | Όλοι |
| `/products/{id}` | Λεπτομέρειες προϊόντος | Όλοι |
| `/auth/login` | Σύνδεση | Όλοι |
| `/auth/register` | Εγγραφή | Όλοι |
| `/orders` | Παραγγελίες μου | USER |
| `/admin` | Dashboard | ADMIN |
| `/admin/products` | Διαχείριση προϊόντων | ADMIN |
| `/admin/orders` | Διαχείριση παραγγελιών | ADMIN |

---

## Βάση Δεδομένων | Database Schema

```
users        → id, username, email, password, role, ...
products     → id, name, description, price, category, size, stock, ...
orders       → id, user_id, total_price, status, order_date, ...
order_items  → id, order_id, product_id, quantity, price
```

---

## Features

- Εγγραφή & Σύνδεση χρηστών (Spring Security + BCrypt)
- Δύο ρόλοι: USER και ADMIN
- Προβολή & αναζήτηση προϊόντων ανά κατηγορία
- Παραγγελία προϊόντων
- Ιστορικό παραγγελιών
- Admin panel: CRUD προϊόντων & διαχείριση παραγγελιών
- Αυτόματη δημιουργία βάσης & αρχικών δεδομένων

---

## Developer

**Coding Factory 10** — Athens University of Economics and Business (AUEB)
Κέντρο Επιμόρφωσης και Δια Βίου Μάθησης
