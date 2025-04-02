package com.example;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertJTest {
    @Test
    public void test() {
        int a = 1;
        int b = 2;
        int sum = 3;

        //Assertions.
                assertThat(a + b).isEqualTo(sum);
    }
}
