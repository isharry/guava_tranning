package com.piperplatform.guava.utilities;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
    public void testCharMatcher(){
        assertThat(CharMatcher.javaDigit().matches('5'),equalTo(true));
        assertThat(CharMatcher.javaDigit().matches('x'),equalTo(false));
        assertThat(CharMatcher.is('P').countIn("Piper Sharing the Google Guava to Us"),equalTo(1));
        assertThat(CharMatcher.breakingWhitespace().collapseFrom("    Hello World    ",'*'),equalTo("*Hello*World*"));
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello 234 world"),equalTo("helloworld"));
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom("hello 234 world"),equalTo(" 234 "));


    }
    @Test
    public void testCharsets(){
        Charset utf8 = StandardCharsets.UTF_8;
        Charset charset = Charset.forName("UTF-8");
        assertThat(utf8,equalTo(charset));
        assertThat(utf8,equalTo(Charsets.UTF_8));
    }
}
