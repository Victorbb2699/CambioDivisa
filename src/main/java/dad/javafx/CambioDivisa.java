package dad.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	private TextField primerText;
	private TextField segundoText;
	private ComboBox<String> authModeCombo;
	private ComboBox<String> authModeCombo2;
	private Button convertirButton;
	private Divisa euro = new Divisa("Euro", 1.0);
	private Divisa libra = new Divisa("Libra", 0.8873);
	private Divisa dolar = new Divisa("Dolar", 1.2007);
	private Divisa yen = new Divisa("Yen", 133.59);

	@Override
	public void start(Stage primaryStage) throws Exception {

		primerText = new TextField();
		primerText.setPromptText("0");
		primerText.setMaxWidth(50);

		segundoText = new TextField();
		segundoText.setPromptText("0");
		segundoText.setMaxWidth(50);

		authModeCombo = new ComboBox<String>();
		authModeCombo.getItems().addAll("Euro", "Libra", "Dolar", "Yen");
		authModeCombo.setMinWidth(80);

		authModeCombo2 = new ComboBox<String>();
		authModeCombo2.getItems().addAll("Euro", "Libra", "Dolar", "Yen");
		authModeCombo2.setMinWidth(80);

		convertirButton = new Button("Cambiar");
		convertirButton.setDefaultButton(true);
		convertirButton.setOnAction(e -> onConvertirButtonAcction(e));
		convertirButton.setMinWidth(80);
		HBox primerBox = new HBox(5, primerText, authModeCombo);
		primerBox.setAlignment(Pos.CENTER);

		HBox segundoBox = new HBox(5, segundoText, authModeCombo2);
		segundoBox.setAlignment(Pos.CENTER);

		VBox root = new VBox(5, primerBox, segundoBox, convertirButton);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Cambio de divisa");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void onConvertirButtonAcction(ActionEvent e) {
		double cantidad = Double.parseDouble(primerText.getText());

		if (Double.parseDouble(primerText.getText()) < 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cambio de divisas");
			alert.setHeaderText("Error");
			alert.setContentText("Introduce un número válido");
			alert.showAndWait();
		} else {
			if (authModeCombo.getSelectionModel().getSelectedItem().equals("Euro")) {
				String aux = authModeCombo2.getSelectionModel().getSelectedItem();
				if (aux.equals(yen.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(euro, yen, cantidad)));
				} else if (aux.equals(libra.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(euro, libra, cantidad)));

				} else if (aux.equals(dolar.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(euro, dolar, cantidad)));
				}

			} else if (authModeCombo.getSelectionModel().getSelectedItem().equals("Libra")) {
				String aux = authModeCombo2.getSelectionModel().getSelectedItem();
				if (aux.equals(yen.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(libra, yen, cantidad)));

				} else if (aux.equals(euro.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(libra, euro, cantidad)));

				} else if (aux.equals(dolar.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(libra, dolar, cantidad)));

				}
			} else if (authModeCombo.getSelectionModel().getSelectedItem().equals("Dolar")) {
				String aux = authModeCombo2.getSelectionModel().getSelectedItem();
				if (aux.equals(yen.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(dolar, yen, cantidad)));
				} else if (aux.equals(libra.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(dolar, libra, cantidad)));
				} else if (aux.equals(euro.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(dolar, euro, cantidad)));
				}
			} else if (authModeCombo.getSelectionModel().getSelectedItem().equals("Yen")) {
				String aux = authModeCombo2.getSelectionModel().getSelectedItem();
				if (aux.equals(euro.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(yen, euro, cantidad)));
				} else if (aux.equals(libra.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(yen, libra, cantidad)));
				} else if (aux.equals(dolar.getNombre())) {
					segundoText.setText(String.valueOf(Divisa.fromTo(yen, dolar, cantidad)));
				}
			}
		}

	}

	public static void main(String[] args) {
		launch(args);

	}

}
