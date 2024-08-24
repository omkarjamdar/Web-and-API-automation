package testrunner;

import org.testng.TestNG;
import testcases.ConfigFileReader;
import utility.BaseClass;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            TestNG testng = new TestNG();
            System.out.println("Setting TestNG suites");
            List<String> suites = new ArrayList<>();
            String filePath = args[0];
            ConfigFileReader configFileReader = new ConfigFileReader(filePath);
            suites.add(filePath +"/java/testrunner/testng.xml");
            testng.setTestSuites(suites);
            System.out.println("Running TestNG");
            testng.run();
            System.out.println("TestNG run completed");
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }
}