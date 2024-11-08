package edu.uptc.swi.Calculator.controller;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import edu.uptc.swi.Calculator.model.calculator;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);
    private final calculator calculator = new calculator();

    @GetMapping
    public String showCalculator() {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(
        @RequestParam("operation") String operation,
        @RequestParam("num1") String num1Str,
        @RequestParam(value = "num2", required = false) String num2Str,
        Model model
    ) {
        double result;
        try {
            // Convert num1 and num2, handle empty strings
            double num1 = num1Str.isEmpty() ? 0 : Double.parseDouble(num1Str);
            double num2 = (num2Str == null || num2Str.isEmpty()) ? 0 : Double.parseDouble(num2Str);

            switch (operation) {
                case "add":
                    result = calculator.add(num1, num2);
                    break;
                case "subtract":
                    result = calculator.subtract(num1, num2);
                    break;
                case "multiply":
                    result = calculator.multiply(num1, num2);
                    break;
                case "divide":
                    if (num2 == 0) {
                        logger.warn("Division by zero attempted");
                        model.addAttribute("error", "Cannot divide by zero");
                        return "calculator";
                    }
                    result = calculator.divide(num1, num2);
                    break;
                case "log":
                    if (num1 <= 0) {
                        logger.warn("Attempted to compute log of non-positive number");
                        model.addAttribute("error", "Logarithm requires a positive number");
                        return "calculator";
                    }
                    result = calculator.naturalLog(num1);
                    break;
                case "sine":
                    result = calculator.sine(num1);
                    break;
                case "cosine":
                    result = calculator.cosine(num1);
                    break;
                default:
                    logger.warn("Unknown operation: {}", operation);
                    model.addAttribute("error", "Invalid operation");
                    return "calculator";
            }
            model.addAttribute("result", result);
        } catch (NumberFormatException e) {
            logger.error("Invalid input: {}", e.getMessage());
            model.addAttribute("error", "Invalid input. Please enter valid numbers.");
        } catch (Exception e) {
            logger.error("Error performing calculation: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
        }
        return "calculator";
    }
}