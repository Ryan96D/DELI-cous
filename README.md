# DELI-cious Point of Sale System

Welcome to the **DELI-cious POS Application**, a custom-built ordering system for DELI-cious — a growing sandwich shop known for fully customizable sandwiches. This application helps automate the in-store order process and lays the groundwork for future online expansion.

## 🚀 Project Overview

Until recently, DELI-cious processed orders manually using pen and paper. As business has scaled, a more efficient, digital approach became necessary. This point of sale (POS) system allows staff to:

- Build fully customized sandwich orders
- Add drinks and chips
- Review and confirm entire orders
- Generate timestamped receipts automatically

This Java application is built with Object-Oriented Programming (OOP) principles and follows an Object-Oriented Analysis and Design (OOAD) approach, using classes, interfaces, and encapsulation to model real-world entities.

---

## 🧠 Features

### 🏠 Home Screen
- `1) New Order` — Start a new customer order
- `0) Exit` — Quit the application

### 🧾 Order Screen
- `1) Add Sandwich` — Create a custom sandwich
- `2) Add Drink` — Choose drink size and flavor
- `3) Add Chips` — Choose chip type
- `4) Checkout` — Review and confirm the full order
- `0) Cancel Order` — Discard the order and return home

### 🥪 Sandwich Builder
- **Select Bread**: White, Wheat, Rye, or Wrap  
- **Choose Size**: 4", 8", or 12"  
- **Add Toppings**:  
  - Meats (Premium)  
  - Cheeses (Premium)  
  - Other toppings (Regular)  
  - Sauces (Regular)  
- **Toasting Option**: Toasted or not  
- **Extra Toppings**: Premium extras cost more  

### 🥤 Drinks
- Select drink **size** and **flavor**

### 🍟 Chips
- Choose chip **type**

### 🧾 Checkout & Receipt
- Shows detailed breakdown of all items and total cost
- Confirms the order
- Automatically saves the receipt to the `/receipts` folder using format:  
  `yyyyMMdd-hhmmss.txt` (e.g., `20250329-141522.txt`)

---

## 💡 Design Approach

This application is designed using Object-Oriented Design principles. Key components are modeled as Java classes and enums to reflect real-world entities. These include but are not limited to:

- `Sandwich`, `Bread`, `Size`, `Meat`, `Cheese`, `Topping`, `Sauce`
- `Order`, `Drink`, `Chips`, `ReceiptWriter`
- `MenuManager`, `OrderManager`, `PricedItem` (interface)

A class diagram is included in the repository and is updated alongside feature changes.

---

## 📁 Repository Structure

DELI-cious/
│
├── src/
│ ├── enums/ # Size, Bread, Meat, Cheese, etc.
│ ├── models/ # Sandwich, Order, Drink, Chips, etc.
│ ├── managers/ # Handles menu logic and order flow
│ ├── utils/ # ReceiptWriter and helper utilities
│ └── Main.java # Entry point of the application
│
├── receipts/ # Auto-generated receipts folder
├── diagrams/ # UML diagrams for class structure
├── README.md
└── .gitignore


---

## ✅ Requirements Summary

- Object-oriented structure
- Interactive menu screens
- Dynamic sandwich customization
- Receipt generation with timestamped filename
- Persistent folder for receipts
- Maintain updated UML class diagram

---

## 🛠 Tech Stack

- **Language**: Java 21+  
- **Build Tool**: Maven  
- **IDE**: IntelliJ IDEA (recommended)  
- **Version Control**: Git + GitHub

---

## 🧾 Sample Receipt
Order Summary - 20250329-141522

1x Sandwich - 8" Wheat Bread, Toasted

Meat: Turkey, Bacon (Extra)

Cheese: Swiss (Extra)

Toppings: Lettuce, Tomato, Pickles

Sauce: Mayo, Mustard

1x Drink - Large, Lemonade
1x Chips - BBQ

Total: $15.75


---

## 📌 Future Enhancements

- Enable online ordering via web interface  
- Add customer name and payment info  
- Connect with a database for order history  
- Add admin dashboard for analytics  

---

## 👤 Author

Created by Ryan Do  
For educational use at [YearUp United](https://www.yearup.org/)

---

## 📄 License

This project is for educational purposes and is not currently licensed for commercial use.

