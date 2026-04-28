package com.mycompany.gymsystem1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private final TextField nameField = new TextField();
    private final TextField idField = new TextField();
    private final TextField contactField = new TextField();
    private final TextField dateField = new TextField();
    private final TextField trainerField = new TextField();
    private final TextField specField = new TextField();
    private final TextField shiftField = new TextField();
    private final TextField monthsField = new TextField();
    private final ComboBox<String> planBox = new ComboBox<>();
    private final TextArea summaryArea = new TextArea();

    @Override
    public void start(Stage stage) {
        Label title = new Label("IronPulse Gym Management");
        title.getStyleClass().add("title-label");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(8);
        form.setPadding(new Insets(10, 0, 10, 0));

        idField.setPromptText("e.g. 101");
        nameField.setPromptText("Member name");
        contactField.setPromptText("Phone");
        dateField.setPromptText("dd-MMM-yyyy");
        trainerField.setPromptText("Trainer name");
        specField.setPromptText("Specialization");
        shiftField.setPromptText("Shift");
        monthsField.setPromptText("Months");
        planBox.getItems().addAll("Basic", "Premium", "VIP");
        planBox.getSelectionModel().selectFirst();

        addRow(form, 0, "Member ID:", idField);
        addRow(form, 1, "Name:", nameField);
        addRow(form, 2, "Contact:", contactField);
        addRow(form, 3, "Enrollment Date:", dateField);
        addRow(form, 4, "Trainer Name:", trainerField);
        addRow(form, 5, "Specialization:", specField);
        addRow(form, 6, "Shift:", shiftField);
        addRow(form, 7, "Plan:", planBox);
        addRow(form, 8, "Months:", monthsField);

        Button registerBtn = new Button("Register & Show Summary");
        registerBtn.setOnAction(e -> handleRegister());

        summaryArea.setEditable(false);
        summaryArea.setPrefRowCount(10);
        summaryArea.getStyleClass().add("summary-area");

        Label summaryLabel = new Label("Summary");
        summaryLabel.getStyleClass().add("section-label");

        VBox root = new VBox(10, title, form, registerBtn, summaryLabel, summaryArea);
        root.setAlignment(Pos.TOP_LEFT);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 480, 600);
        var cssUrl = getClass().getResource("styles.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.err.println("WARNING: styles.css not found on classpath");
        }

        stage.setTitle("IronPulse Gym");
        stage.setScene(scene);
        stage.show();
    }

    private void addRow(GridPane g, int row, String labelText, javafx.scene.Node field) {
        Label l = new Label(labelText);
        l.getStyleClass().add("field-label");
        g.add(l, 0, row);
        g.add(field, 1, row);
    }

    private void handleRegister() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            int months = Integer.parseInt(monthsField.getText().trim());
            if (months <= 0) {
                throw new IllegalArgumentException("Months must be > 0");
            }

            Member member = new Member(id, nameField.getText().trim(),
                    contactField.getText().trim(), dateField.getText().trim());
            Trainer trainer = new Trainer(trainerField.getText().trim(),
                    specField.getText().trim(), shiftField.getText().trim());

            MembershipPlan plan;
            switch (planBox.getValue()) {
                case "Premium": plan = new PremiumPlan(); break;
                case "VIP":     plan = new VIPPlan(); break;
                default:        plan = new BasicPlan();
            }

            Admin admin = new Admin();
            Registration reg = admin.register(member, plan, trainer, months);
            reg.confirmPayment();

            String summary = buildSummary(member, trainer, plan, months, reg);
            summaryArea.setText(summary);
            saveToFile(summary);
        } catch (NumberFormatException ex) {
            summaryArea.setText("Error: Member ID and Months must be valid numbers.");
        } catch (IllegalArgumentException ex) {
            summaryArea.setText("Error: " + ex.getMessage());
        } catch (Exception ex) {
            summaryArea.setText("Unexpected error: " + ex.getMessage());
        }
    }

    private String buildSummary(Member m, Trainer t, MembershipPlan p, int months, Registration r) {
        StringBuilder sb = new StringBuilder();
        sb.append("----- Registration Summary -----\n");
        sb.append("Member ID   : ").append(m.getMemberId()).append('\n');
        sb.append("Name        : ").append(m.getName()).append('\n');
        sb.append("Contact     : ").append(m.getContact()).append('\n');
        sb.append("Enrolled    : ").append(m.getEnrollmentDate()).append('\n');
        sb.append("Plan        : ").append(p.getPlanName()).append('\n');
        sb.append("Trainer     : ").append(t.getName())
                .append(" (").append(t.getSpecialization()).append(", ")
                .append(t.getShift()).append(")\n");
        sb.append("Duration    : ").append(months).append(" month(s)\n");
        sb.append("Total Cost  : ").append(r.getTotalCost()).append('\n');
        sb.append("Payment     : Paid\n");
        return sb.toString();
    }

    private void saveToFile(String summary) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("registrations.txt", true))) {
            w.write(summary);
            w.newLine();
        } catch (IOException ex) {
            System.err.println("Failed to save record: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
