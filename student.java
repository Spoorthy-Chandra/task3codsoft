import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

class Student {
    private int rollNumber;
    private String name;
    private String grade;
    private String section;

    public Student(int rollNumber, String name, String grade, String section) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.grade = grade;
        this.section = section;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public String getSection() {
        return section;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setSection(String section) {
        this.section = section;
    }
}

class StudentManagementSystemGUI extends JFrame {
    private ArrayList<Student> students;
    private JTextArea outputArea;
    private JTextField rollNumberField, nameField, gradeField, sectionField;

    public StudentManagementSystemGUI() {
        students = new ArrayList<>();
        setTitle("Student Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Roll Number:"));
        rollNumberField = new JTextField();
        inputPanel.add(rollNumberField);
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);
        inputPanel.add(new JLabel("Section:"));
        sectionField = new JTextField();
        inputPanel.add(sectionField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new AddButtonListener());
        inputPanel.add(addButton);

        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new RemoveButtonListener());
        inputPanel.add(removeButton);

        JButton editButton = new JButton("Edit Student Details");
        editButton.addActionListener(new EditButtonListener());
        inputPanel.add(editButton);

        JPanel searchPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(15);
        searchPanel.add(new JLabel("Enter Roll Number:"));
        searchPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener(searchField));
        searchPanel.add(searchButton);

        JButton displayAllButton = new JButton("Display All Students");
        displayAllButton.addActionListener(new DisplayAllButtonListener());
        searchPanel.add(displayAllButton);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(searchPanel, BorderLayout.CENTER);
        contentPane.add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rollNumber = Integer.parseInt(rollNumberField.getText());
            String name = nameField.getText();
            String grade = gradeField.getText();
            String section = sectionField.getText();
            students.add(new Student(rollNumber, name, grade, section));
            outputArea.append("Student added successfully.\n");
            clearFields();
        }
    }

    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rollNumberToRemove = Integer.parseInt(rollNumberField.getText());
            Iterator<Student> iterator = students.iterator();
            boolean found = false;
            while (iterator.hasNext()) {
                Student student = iterator.next();
                if (student.getRollNumber() == rollNumberToRemove) {
                    iterator.remove();
                    outputArea.append("Student removed successfully.\n");
                    found = true;
                    break;
                }
            }
            if (!found) {
                outputArea.append("Student not found.\n");
            }
            clearFields();
        }
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rollNumber = Integer.parseInt(rollNumberField.getText());
            boolean found = false;
            for (Student student : students) {
                if (student.getRollNumber() == rollNumber) {
                    String name = nameField.getText();
                    String grade = gradeField.getText();
                    String section = sectionField.getText();
                    student.setName(name);
                    student.setGrade(grade);
                    student.setSection(section);
                    outputArea.append("Student details updated successfully.\n");
                    clearFields();
                    found = true;
                    break;
                }
            }
            if (!found) {
                outputArea.append("Student not found.\n");
                clearFields();
            }
        }
    }

    private class SearchButtonListener implements ActionListener {
        private JTextField searchField;

        public SearchButtonListener(JTextField searchField) {
            this.searchField = searchField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int searchRollNumber = Integer.parseInt(searchField.getText());
            boolean found = false;
            for (Student student : students) {
                if (student.getRollNumber() == searchRollNumber) {
                    outputArea.append("Roll Number: " + student.getRollNumber() + "\n");
                    outputArea.append("Name: " + student.getName() + "\n");
                    outputArea.append("Grade: " + student.getGrade() + "\n");
                    outputArea.append("Section: " + student.getSection() + "\n\n");
                    found = true;
                    break;
                }
            }
            if (!found) {
                outputArea.append("Student not found.\n\n");
            }
        }
    }

    private class DisplayAllButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Student student : students) {
                outputArea.append("Roll Number: " + student.getRollNumber() + "\n");
                outputArea.append("Name: " + student.getName() + "\n");
                outputArea.append("Grade: " + student.getGrade() + "\n");
                outputArea.append("Section: " + student.getSection() + "\n\n");
            }
        }
    }

    private void clearFields() {
        rollNumberField.setText("");
        nameField.setText("");
        gradeField.setText("");
        sectionField.setText("");
    }

    public static void main(String[] args) {
        new StudentManagementSystemGUI();
    }
}
