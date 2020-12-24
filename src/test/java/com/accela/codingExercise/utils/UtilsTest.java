package com.accela.codingExercise.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {
    @Test
    public void isInteger(){
        Boolean result = Utils.isInteger("1");
        assertTrue(result);
    }

    @Test
    public void isIntegerWhenIsFloat(){
        Boolean result = Utils.isInteger("1.6");
        assertFalse(result);
    }

    @Test
    public void isIntegerWhenIsAlphanumeric(){
        Boolean result = Utils.isInteger("x");
        assertFalse(result);
    }

    @Test
    public void isIntegerWhenIsNull(){
        Boolean result = Utils.isInteger(null);
        assertFalse(result);
    }
}