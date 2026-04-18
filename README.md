# 💰 Personal Finance Manager

A lightweight, desktop-based **Personal Finance Manager** built with **Java Swing**. Track your income and expenses, view live balance summaries, and persist your data across sessions — all from a clean GUI window.

---

## 📸 Overview

| Feature | Details |
|---|---|
| **Language** | Java (JDK 8+) |
| **GUI Framework** | Java Swing |
| **Data Persistence** | File-based (`finance_records.txt`) |
| **Architecture** | Single-file MVC-style with inner `Transaction` class |

---

## ✨ Features

- ➕ **Add Income** — Log income entries with a description and amount
- ➖ **Add Expense** — Log expense entries with a description and amount
- 🗑️ **Delete Transactions** — Select and remove any transaction from the table
- 📊 **Live Summary Panel** — Automatically updates Total Income, Total Expense, and Balance
- 💾 **Auto Save & Load** — All transactions are saved to `finance_records.txt` and reloaded on next launch
- ⚠️ **Input Validation** — Alerts user if a non-numeric amount is entered

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) **8 or higher**
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code with Java extension) or terminal

### Clone the Repository

```bash
git clone https://github.com/KamranKhan2k24/personal-finance-manager.git
cd personal-finance-manager
```

### Compile & Run

**Using terminal:**
```bash
javac PersonalFinanceManagerGUI.java
java PersonalFinanceManagerGUI
```

**Using an IDE:**
1. Open the project folder in your IDE
2. Run `PersonalFinanceManagerGUI.java` directly

---

## 🖥️ How to Use

1. **Launch** the application — previous transactions load automatically.
2. Click **"Add Income"** or **"Add Expense"** — enter a description and amount in the dialog.
3. Your transaction appears in the table and the **summary updates instantly**.
4. To remove a transaction, **select a row** in the table and click **"Delete Selected"**.
5. All changes are **saved automatically** to `finance_records.txt`.

---

## 📁 Project Structure

```
personal-finance-manager/
│
├── PersonalFinanceManagerGUI.java   # Main application file
│   ├── PersonalFinanceManagerGUI    # Main GUI class
│   └── Transaction                  # Inner class: data model
│
└── finance_records.txt              # Auto-generated data file (created on first run)
```

---

## 🔧 Data Storage Format

Transactions are stored in plain text, one per line:

```
Income,Freelance Payment,5000.0
Expense,Grocery Shopping,120.5
Expense,Internet Bill,800.0
```

Format: `Type,Description,Amount`

---

## 🛠️ Built With

- **Java Swing** — GUI components (`JFrame`, `JTable`, `JOptionPane`)
- **Java I/O** — `FileWriter`, `BufferedReader` for file persistence
- **Java Collections** — `ArrayList` for in-memory transaction management

---

## 🔮 Possible Future Enhancements

- [ ] Category tagging for transactions (Food, Rent, Salary, etc.)
- [ ] Date/time stamps for each transaction
- [ ] Monthly or category-wise charts using JFreeChart
- [ ] Export to CSV or PDF
- [ ] Search and filter functionality
- [ ] Dark mode UI

---

## 👨‍💻 Author

**Kamran Khan**
- GitHub: [@KamranKhan2k24](https://github.com/KamranKhan2k24)
- Instagram: [@yourkamranhere](https://instagram.com/yourkamranhere)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

> Built as part of a Java GUI project | Integral University, Lucknow
