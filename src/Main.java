import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

 class PersonalFinanceManagerGUI {
    private static final String FILE_NAME = "finance_records.txt";
    private final List<Transaction> transactions = new ArrayList<>();
    private final JFrame frame;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JLabel totalIncomeLabel;
    private final JLabel totalExpenseLabel;
    private final JLabel balanceLabel;

    public PersonalFinanceManagerGUI() {
        frame = new JFrame("Personal Finance Manager");
        tableModel = new DefaultTableModel(new String[]{"Type", "Description", "Amount"}, 0);
        table = new JTable(tableModel);
        totalIncomeLabel = new JLabel("Total Income: $0.0");
        totalExpenseLabel = new JLabel("Total Expense: $0.0");
        balanceLabel = new JLabel("Balance: $0.0");

        loadTransactions();
        setupGUI();
    }

    private void setupGUI() {
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1));

        JButton addIncomeButton = new JButton("Add Income");
        addIncomeButton.addActionListener(e -> addTransaction("Income"));
        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(e -> addTransaction("Expense"));
        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(e -> deleteSelectedTransaction());

        inputPanel.add(addIncomeButton);
        inputPanel.add(addExpenseButton);
        inputPanel.add(deleteButton);

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(3, 1));
        summaryPanel.add(totalIncomeLabel);
        summaryPanel.add(totalExpenseLabel);
        summaryPanel.add(balanceLabel);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.WEST);
        frame.add(summaryPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        updateSummaryReport();
    }

    private void addTransaction(String type) {
        JTextField descriptionField = new JTextField();
        JTextField amountField = new JTextField();
        Object[] message = {
                "Description:", descriptionField,
                "Amount:", amountField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Add " + type, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String description = descriptionField.getText();
            double amount;
            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Transaction transaction = new Transaction(type, description, amount);
            transactions.add(transaction);
            tableModel.addRow(new Object[]{type, description, amount});
            updateSummaryReport();
            saveTransactions();
        }
    }

    private void deleteSelectedTransaction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Remove from the table model
            tableModel.removeRow(selectedRow);
            // Remove from the transactions list
            transactions.remove(selectedRow);
            // Update summary and save changes
            updateSummaryReport();
            saveTransactions();
            JOptionPane.showMessageDialog(frame, "Transaction deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a transaction to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateSummaryReport() {
        double totalIncome = 0.0;
        double totalExpense = 0.0;

        for (Transaction transaction : transactions) {
            if (transaction.getType().equalsIgnoreCase("Income")) {
                totalIncome += transaction.getAmount();
            } else if (transaction.getType().equalsIgnoreCase("Expense")) {
                totalExpense += transaction.getAmount();
            }
        }

        totalIncomeLabel.setText("Total Income: $" + totalIncome);
        totalExpenseLabel.setText("Total Expense: $" + totalExpense);
        balanceLabel.setText("Balance: $" + (totalIncome - totalExpense));
    }

    private void saveTransactions() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Transaction transaction : transactions) {
                writer.println(transaction.getType() + "," + transaction.getDescription() + "," + transaction.getAmount());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving transactions: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTransactions() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String type = parts[0];
                    String description = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    transactions.add(new Transaction(type, description, amount));
                    tableModel.addRow(new Object[]{type, description, amount});
                }
            } catch (IOException | NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Error loading transactions: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PersonalFinanceManagerGUI::new);
    }

    // Inner class to represent a transaction
    static class Transaction {
        private final String type;
        private final String description;
        private final double amount;

        public Transaction(String type, String description, double amount) {
            this.type = type;
            this.description = description;
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }

        public double getAmount() {
            return amount;
 }
}}