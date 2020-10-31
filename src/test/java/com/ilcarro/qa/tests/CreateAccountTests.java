package com.ilcarro.qa.tests;

import com.ilcarro.qa.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateAccountTests extends TestBase {
    //preconditions: user should be logged out
    @DataProvider
    public Iterator<Object> validUser() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"fName", "lname", "lname@gmail.com", "1Qaaaaaa"});
        list.add(new Object[]{"aa", "FF", "1111@ss.com", "jklhgfd20"});
        list.add(new Object[]{"11", "66", "11_11@ss.com", "jklhgfd20"});

        return list.iterator();
    }

//    @DataProvider
//    public Iterator<Object[]> validUserFromCSV() {
//        List<Object[]> list = new ArrayList<>();
//        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/tests_newUser.csv")));
//        String line = reader.readLine();
//        String[] split = line.split(",");
//
//        list.add(new Object[]{new User().setfName(split[0]).setlName(split[1]).setEmail(split[2]).setPassword(split[3])})
//        return list.iterator();
//    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.header().isSignUpTabPresentInHeader()) {    //sign up not present
            app.header().clickLogout();
        }
    }

    @Test

    public void testSignUp() throws InterruptedException {
        app.header().clickSignUp();
        app.session().fillRegistrationForm(new User()
                .setfName("AS")
                .setlName("FV")
                .setEmail("aa" + System.currentTimeMillis() + "@bb318.com")
                .setPassword("1Aaaaaaaa"));

        //click submit button
        app.session().submitForm();


        //check, login form displayed
        Assert.assertTrue(app.session().isLoginFormPresent());
        logger.info(String.valueOf(app.session().isLoginFormPresent()));
    }

    @Test(dataProvider = "validUser", dataProviderClass = DataProviders.class)
    public void testSignUpFromDataProvider(String fName, String lName, String email, String password) throws InterruptedException {
        app.header().clickSignUp();
        app.session().fillRegistrationForm(new User()
                .setfName(fName)
                .setlName(lName)
                .setEmail(email)
                .setPassword(password));

        //click submit button
        app.session().submitForm();


        //check, login form displayed
        Assert.assertTrue(app.session().isLoginFormPresent());
        logger.info(String.valueOf(app.session().isLoginFormPresent()));
    }


}
