package com.hiberus.ejercicios.run;


import com.hiberus.ejercicios.login.LoginSuiteTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginSuiteTest.class,

})
public class RunTest {
}
