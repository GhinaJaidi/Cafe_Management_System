package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cafe extends JFrame implements ActionListener {
    JPanel mainPanel, leftPanel, rightPanel;
    JPanel listOfProducts;
    JLabel totalLabel;
    double total = 0.0;
    JButton pay;
    JButton newItem;
    JButton cancel;
    CardLayout cardLayout;
    JPanel centerCards;

    public Cafe() {
        setTitle("Cafe Management System");
        setSize(1024, 768);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\15.jpg").getImage());
        getContentPane().setBackground(new Color(240, 240, 240));
        setLayout(new BorderLayout());

        addingRightPanel();
        addingLeftPanel();
        addingMainPanel();
    }

    private void addingMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(122, 84, 70));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void addingRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(219, 166, 122));
        rightPanel.setPreferredSize(new Dimension(300, getHeight()));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel check = new JLabel("Checkout", SwingConstants.CENTER);
        check.setOpaque(true);
        check.setBackground(new Color(218, 229, 224));
        check.setFont(new Font("Arial", Font.BOLD, 13));
        check.setAlignmentX(Component.CENTER_ALIGNMENT);
        check.setMaximumSize(new Dimension(280, 30));
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(check);

        JPanel info = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        info.setBackground(new Color(241, 241, 241));
        info.setMaximumSize(new Dimension(280, 30));
        Font font = new Font("Arial", Font.BOLD, 11);
        JLabel name = new JLabel("Name");
        JLabel qty = new JLabel("QTY");
        JLabel price = new JLabel("Price");
        name.setFont(font);
        qty.setFont(font);
        price.setFont(font);
        info.add(name);
        info.add(qty);
        info.add(price);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(info);

        listOfProducts = new JPanel();
        listOfProducts.setLayout(new BoxLayout(listOfProducts, BoxLayout.Y_AXIS));
        listOfProducts.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(listOfProducts);
        scrollPane.setPreferredSize(new Dimension(280, 400));
        scrollPane.setMaximumSize(new Dimension(280, 400));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(scrollPane);

        totalLabel = new JLabel("Total: $0.00", SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setMaximumSize(new Dimension(280, 30));
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(totalLabel);

        pay = new JButton("Pay (" + totalLabel.getText() + ")");
        pay.setMaximumSize(new Dimension(280, 30));
        pay.setAlignmentX(Component.CENTER_ALIGNMENT);
        pay.add(Box.createVerticalStrut(10));
        pay.addActionListener(e -> {
            ImageIcon imageIcon = new ImageIcon("C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\1.jpg");
            Image scaledImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            if (total > 0) {
                JOptionPane.showMessageDialog(
                        Cafe.this,
                        "✔️ Payment Successful!\n\nThank you for your purchase!\n" + totalLabel.getText(),
                        "Success",
                        JOptionPane.PLAIN_MESSAGE,
                        icon
                );

                listOfProducts.removeAll();
                listOfProducts.revalidate();
                listOfProducts.repaint();

                total = 0.0;
                totalLabel.setText("Total: $0.00");
                pay.setText("Pay (" + totalLabel.getText() + ")");
            } else {
                JOptionPane.showMessageDialog(
                        Cafe.this,
                        "Your cart is empty! Please add products before paying.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        pay.setFont(new Font("Arial", Font.BOLD, 15));
        pay.setBackground(new Color(16, 154, 102));
        rightPanel.add(pay);
    }

    private void addingLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(219, 166, 122));
        leftPanel.setPreferredSize(new Dimension(700, getHeight()));
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(Color.WHITE);
        JButton drinkBtn = new JButton("Drinks");
        drinkBtn.setBackground(new Color(242, 238, 229));
        JButton dessertBtn = new JButton("Dessert");
        dessertBtn.setBackground(new Color(242, 238, 229));
        topPanel.add(drinkBtn);
        topPanel.add(dessertBtn);

        cardLayout = new CardLayout();
        centerCards = new JPanel(cardLayout);

        JPanel drinksPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        drinksPanel.setBackground(new Color(242, 238, 229));
        addProductToPanel(drinksPanel, "Costa Coffee", 7.99, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\7.jpg");
        addProductToPanel(drinksPanel, "Mocha", 9.99, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\8.jpg");
        addProductToPanel(drinksPanel, "Caramel Latte", 8.49, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\11.jpg");
        addProductToPanel(drinksPanel, "Espresso", 6.99, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\5.jpg");
        addProductToPanel(drinksPanel, "Flat White", 8.99, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\3.jpg");

        JPanel dessertPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        dessertPanel.setBackground(new Color(242, 238, 229));
        addProductToPanel(dessertPanel, "Cheesecake", 5.50, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\6.jpg");
        addProductToPanel(dessertPanel, "Brownie", 4.25, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\12.jpg");
        addProductToPanel(dessertPanel, "Tiramisu", 6.00, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\10.jpg");
        addProductToPanel(dessertPanel, "Chocolate Cake", 5.75, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\9.jpg");
        addProductToPanel(dessertPanel, "Baklava", 4.80, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\4.jpg");


        centerCards.add(new JScrollPane(drinksPanel), "Drinks");
        centerCards.add(new JScrollPane(dessertPanel), "Desserts");

        drinkBtn.addActionListener(e -> cardLayout.show(centerCards, "Drinks"));
        dessertBtn.addActionListener(e -> cardLayout.show(centerCards, "Desserts"));


        newItem = new JButton("+ Add New Item");
        newItem.setBackground(new Color(255, 255, 255));
        newItem.setForeground(new Color(16, 160, 107));
        newItem.setOpaque(true);
        newItem.addActionListener(e -> {
            String[] options = {"Drink", "Dessert"};
            int type = JOptionPane.showOptionDialog(
                    Cafe.this,
                    "Select product type:",
                    "product type",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (type == -1) return;

            String name = JOptionPane.showInputDialog(Cafe.this, "Enter product name:");
            if (name == null || name.trim().isEmpty()) return;

            String priceStr = JOptionPane.showInputDialog(Cafe.this, "Enter product price:");
            if (priceStr == null || priceStr.trim().isEmpty()) return;

            try {
                double p = Double.parseDouble(priceStr);
                if (type == 0) {
                    addProductToPanel(drinksPanel, name, p, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\13.jpg");
                    drinksPanel.revalidate();
                    drinksPanel.repaint();
                } else { // حلو
                    addProductToPanel(dessertPanel, name, p, "C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\14.jpg");
                    dessertPanel.revalidate();
                    dessertPanel.repaint();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Cafe.this, "Invalid price entered!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(new Color(229, 41, 41));
        cancel.setBorder(BorderFactory.createLineBorder(Color.RED));
        cancel.addActionListener(e -> {
            listOfProducts.removeAll();
            listOfProducts.revalidate();
            listOfProducts.repaint();

            total = 0.0;
            totalLabel.setText("Total: $0.00");
            pay.setText("Pay (" + totalLabel.getText() + ")");
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(newItem);
        bottomPanel.add(cancel);

        leftPanel.add(topPanel, BorderLayout.NORTH);
        leftPanel.add(centerCards, BorderLayout.CENTER);
        leftPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addProductToPanel(JPanel panel, String productName, double productPrice, String imagePath) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(new Color(255, 255, 255));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel name = new JLabel(productName);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel price = new JLabel("$" + productPrice);
        price.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton addBtn = new JButton("Add");
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.setBackground(new Color(0, 153, 76));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setFont(new Font("Arial", Font.BOLD, 12));
        addBtn.addActionListener(e -> addToCart(productName, productPrice));

        itemPanel.add(Box.createVerticalStrut(10));
        itemPanel.add(imgLabel);
        itemPanel.add(name);
        itemPanel.add(price);
        itemPanel.add(addBtn);

        panel.add(itemPanel);
    }

    private void addToCart(String productName, double productPrice) {
        JPanel item = new JPanel();
        item.setLayout(new BorderLayout());
        item.setMaximumSize(new Dimension(260, 60));
        item.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        item.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("<html><b>" + productName + "</b></html>");
        JLabel priceLabel = new JLabel("$" + String.format("%.2f", productPrice));
        priceLabel.setForeground(new Color(0, 102, 0));

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        top.add(nameLabel, BorderLayout.WEST);
        top.add(priceLabel, BorderLayout.EAST);

        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        qtyPanel.setBackground(Color.WHITE);

        JLabel qtyLabel = new JLabel("1");
        JButton inc = new JButton("+");
        JButton dec = new JButton("-");
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\Ghina-PC\\IdeaProjects\\Cafe\\src\\main\\java\\org\\example\\2.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JButton remove = new JButton(scaledIcon);
        remove.setPreferredSize(new Dimension(30, 30));
        remove.setBorderPainted(false);

        remove.setContentAreaFilled(false);
        remove.setFocusPainted(false);

        inc.setMargin(new Insets(2, 5, 2, 5));
        dec.setMargin(new Insets(2, 5, 2, 5));
        remove.setMargin(new Insets(2, 5, 2, 5));

        inc.addActionListener(e -> {
            int qty = Integer.parseInt(qtyLabel.getText()) + 1;
            qtyLabel.setText(qty + "");
            updateTotal(productPrice);
        });

        dec.addActionListener(e -> {
            int qty = Integer.parseInt(qtyLabel.getText());
            if (qty > 1) {
                qty--;
                qtyLabel.setText(qty + "");
                updateTotal(-productPrice);
            }
        });

        remove.addActionListener(e -> {
            int qty = Integer.parseInt(qtyLabel.getText());
            listOfProducts.remove(item);
            listOfProducts.revalidate();
            listOfProducts.repaint();
            updateTotal(-productPrice * qty);
        });

        qtyPanel.add(remove);
        qtyPanel.add(dec);
        qtyPanel.add(qtyLabel);
        qtyPanel.add(inc);

        item.add(top, BorderLayout.NORTH);
        item.add(qtyPanel, BorderLayout.CENTER);

        listOfProducts.add(Box.createVerticalStrut(5));
        listOfProducts.add(item);
        listOfProducts.revalidate();
        listOfProducts.repaint();
        updateTotal(productPrice);
    }

    private void updateTotal(double change) {
        total += change;
        totalLabel.setText("Total: $" + String.format("%.2f", total));
        pay.setText("Pay (" + totalLabel.getText() + ")");
    }

    public static void main(String[] args) {
        Cafe cafe = new Cafe();
        cafe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

