package com.piperplatform.guava.utilities;

import com.google.common.base.Strings;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @Author shiyoufeng
 * @Date 2020/12/23 4:12 下午
 * @Version 1.0
 */
public class StringsTest {

    @Test
    public void testStringsMethod() {
        assertThat(Strings.emptyToNull(""), nullValue());
        assertThat(Strings.nullToEmpty(null),equalTo(""));
        assertThat(Strings.nullToEmpty("hello"),equalTo("hello"));
        assertThat(Strings.isNullOrEmpty(null),equalTo(true));
        assertThat(Strings.isNullOrEmpty(""),equalTo(true));
        assertThat(Strings.repeat("Alex",3),equalTo("AlexAlexAlex"));
        assertThat(Strings.commonPrefix("Hello","Hit"),equalTo("H"));
        assertThat(Strings.commonPrefix("Hello","Echo"),equalTo(""));
        assertThat(Strings.commonSuffix("Hello","Echo"),equalTo("o"));
        assertThat(Strings.padStart("Alex",3,'H'),equalTo("Alex"));
        assertThat(Strings.padStart("Alex",5,'H'),equalTo("HAlex"));
        assertThat(Strings.padEnd("Alex",5,'H'),equalTo("AlexH"));
    }

    @Test
    public void testCharsets(){


    }
}
