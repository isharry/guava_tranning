package com.piperplatform.guava.utilities;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @Author shiyoufeng
 * @Date 2020/12/23 1:08 下午
 * @Version 1.0
 */
public class JoinerTest {

    private final List<String> stringList = Arrays.asList(
            "Google", "Java", "Guava", "Scala", "Kafka"
    );
    private final List<String> stringListWithNullValue = Arrays.asList(
            "Google", "Java", "Guava", "Scala", null
    );
    private final ImmutableMap stringMap = ImmutableMap.of("Google", "Java", "Guava", "Scala");
    private final String targetFileName = "guava-joiner.txt";
    private final String targetFileNameWithMap = "guava-joiner-map.txt";

    @Test
    public void testJoinOnJoin() {
        String result = Joiner.on("#").join(stringList);
        assertThat(result, equalTo("Google#Java#Guava#Scala#Kafka"));
    }

    @Test(expected = NullPointerException.class)
    public void testJoinOnJoinWithNullValue() {
        String result = Joiner.on("#").join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Java#Guava#Scala#Kafka"));
    }

    @Test
    public void testJoinOnJoinWithNullValueButSkip() {
        String result = Joiner.on("#").skipNulls().join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Java#Guava#Scala"));
    }

    @Test
    public void testJoin_On_Join_WithNullValue_ButUseDefault() {
        String result = Joiner.on("#").useForNull("DEFAULT").join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Java#Guava#Scala#DEFAULT"));
    }

    @Test
    public void testJoin_On_Append_To_StringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder result = Joiner.on("#").useForNull("DEFAULT").appendTo(stringBuilder, stringListWithNullValue);
        assertThat(result, sameInstance(stringBuilder));
        assertThat(result.toString(), equalTo("Google#Java#Guava#Scala#DEFAULT"));
    }

    @Test
    public void testJoin_On_Append_To_Writer() {
        try (FileWriter writer = new FileWriter(new File(targetFileName))) {
            Joiner.on("#").useForNull("DEFAULT").appendTo(writer, stringListWithNullValue);
            assertThat(Files.isFile().test(new File(targetFileName)), equalTo(true));
        } catch (IOException e) {
            fail("append to the writer occur fetal error...");
        }
    }

    /**
     * 性能不如guava
     */
    @Test
    public void testJoiningByStreamWithNullValue() {
        String result = stringListWithNullValue.stream().filter(item -> item != null && !item.isEmpty()).collect(joining("#"));
        assertThat(result, equalTo("Google#Java#Guava#Scala"));
    }

    @Test
    public void testJoiningByStreamWithDefaultValue() {
        String result = stringListWithNullValue.stream()
                .map(item -> item == null || item.isEmpty() ? "DEFAULT" : item).
                        collect(joining("#"));
        assertThat(result, equalTo("Google#Java#Guava#Scala#DEFAULT"));
    }

    @Test
    public void testJoinOnWithMap(){
        String result = Joiner.on("#").withKeyValueSeparator("=").join(stringMap);
        assertThat(result,equalTo("Google=Java#Guava=Scala"));
    }

    @Test
    public void testJoinOnWithMapToAppendable(){
        try (FileWriter writer = new FileWriter(new File(targetFileNameWithMap))) {
            Joiner.on("#").withKeyValueSeparator("=").appendTo(writer, stringMap);
            assertThat(Files.isFile().test(new File(targetFileNameWithMap)), equalTo(true));
        } catch (IOException e) {
            fail("append to the writer occur fetal error...");
        }
    }


}
