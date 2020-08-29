package app_gourmet;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import app_gourmet.models.Food;

public class Main {

	public static void main(String[] args) {
		String title = "Gourmet Guesser";
		String labelPrincipal = "Gourmet Guesser System";
		String buttonPrincipal = "começar";

		String firstQuestion = "Sua comida seria um(a) %s?";
		String secondQuestion = "Sua comida é %s?";

		String firstInput = "Digite o nome da comida: ";
		String secondInput = "%s  é ______ e %s não é.";

		List<Food> listYesFood = new ArrayList<Food>();
		listYesFood.add(new Food("Sushi", "Peixe"));
		List<Food> listNoFood = new ArrayList<Food>();
		listNoFood.add(new Food("Batata Frita", "Fast-food"));

		JFrame mainFrame = new JFrame(title);
		JLabel label = new JLabel(labelPrincipal, SwingConstants.CENTER);
		JButton button = new JButton(buttonPrincipal);
		button.setPreferredSize(new Dimension(100, 100));
		mainFrame.setLayout(new GridLayout(2, 0));
		mainFrame.add(label);
		mainFrame.add(button);
		mainFrame.setSize(400, 400);
		mainFrame.setVisible(true);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "Pense em uma comida");
				int secondPanel = createOptionDialog(String.format(firstQuestion, listYesFood.get(0).getType()), title);

				List<Food> currentFoodList = (secondPanel == 0) ? listYesFood : listNoFood;

				for (int index = 0; index < currentFoodList.size(); index++) {
					Food food = currentFoodList.get(index);
					int thirdPanel = 1;

					if (index != 0 || secondPanel == 1) {
						secondPanel = createOptionDialog(String.format(firstQuestion, food.getType()), title);
					}
					if (secondPanel == 0) {
						thirdPanel = createOptionDialog(String.format(secondQuestion, food.getName()), title);
					}

					if (thirdPanel == 0) {
						JOptionPane.showMessageDialog(mainFrame, "Acertei! :D");
						return;
					} else {
						if (index == currentFoodList.size() - 1) {
							String newFoodName = createInputDialog(firstInput);
							if (newFoodName != null) {
								String newFoodType = createInputDialog(
										String.format(secondInput, newFoodName, food.getName()));
								if (newFoodType != null) {
									currentFoodList.add(new Food(newFoodName, newFoodType));
								}
							}

							return;
						}
					}
				}
			}
		});
	}

	public static int createOptionDialog(String question, String title) {
		String[] defaultOptions = { "Sim", "Não" };
		return JOptionPane.showOptionDialog(null, question, title, JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, defaultOptions, defaultOptions[0]);
	}

	public static String createInputDialog(String message) {
		return JOptionPane.showInputDialog(message);
	}

}
