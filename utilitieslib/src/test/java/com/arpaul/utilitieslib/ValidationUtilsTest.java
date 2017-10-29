package com.arpaul.utilitieslib;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by ARPaul on 12-02-2017.
 */
public class ValidationUtilsTest {
    @Test
    public void validateEmail() throws Exception {
        assertThat(ValidationUtils.validateEmail("name@email.com"), is(false));
    }

    @Test
    public void validatePhoneNumber() throws Exception {
        assertThat(ValidationUtils.validatePhoneNumber("9030303407"), is(true));
    }

}