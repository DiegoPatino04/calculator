package edu.uptc.swi.Calculator.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uptc.swi.Calculator.IOExeptions.InvalidOperationException;

public class calculator {

    private static final Logger logger = LoggerFactory.getLogger(calculator.class);

    public double add(double a, double b) {
        logger.info("Adding {} + {}", a, b);
        return a + b;
    }

    public double subtract(double a, double b) {
        logger.info("Subtracting {} - {}", a, b);
        return a - b;
    }

    public double multiply(double a, double b) {
        logger.info("Multiplying {} * {}", a, b);
        return a * b;
    }

    public double divide(double a, double b) throws InvalidOperationException {
        if (b == 0) {
            logger.error("Attempted division by zero");
            throw new InvalidOperationException("Cannot divide by zero");
        }
        logger.info("Dividing {} / {}", a, b);
        return a / b;
    }

    public double naturalLog(double a) throws InvalidOperationException {
        if (a <= 0) {
            logger.error("Attempted natural log of non-positive number: {}", a);
            throw new InvalidOperationException("Natural log input must be positive");
        }
        logger.info("Calculating natural log of {}", a);
        return Math.log(a);
    }

    public double sine(double a) {
        logger.info("Calculating sine of {}", a);
        return Math.sin(a);
    }

    public double cosine(double a) {
        logger.info("Calculating cosine of {}", a);
        return Math.cos(a);
    }
}
