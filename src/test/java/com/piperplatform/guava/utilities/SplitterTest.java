package com.piperplatform.guava.utilities;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @Author shiyoufeng
 * @Date 2020/12/23 2:34 下午
 * @Version 1.0
 */
public class SplitterTest {

    @Test
    public void testSplitOnSplit(){
        List<String> result = Splitter.on("|").splitToList("hello|world");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));
    }

    @Test
    public void testSplitOnSplitOmitEmpty(){
        List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|||");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));
    }

    @Test
    public void testSplitOnSplitOmitEmptyTrimResult(){
        List<String> result = Splitter.on("|").omitEmptyStrings().trimResults().splitToList(" hello |world |||");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));
    }

    @Test
    public void testSplitFixLength(){
        List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
        assertThat(result.size(),equalTo(4));
        assertThat(result.get(0),equalTo("aaaa"));
        assertThat(result.get(3),equalTo("dddd"));
    }

    @Test
    public void testSplitOnSplitLimit(){
        List<String> result = Splitter.on("#").limit(3).splitToList("Google#Java#Guava#Scala#Kafka");
        assertThat(result.size(),equalTo(3));
        assertThat(result.get(0),equalTo("Google"));
        assertThat(result.get(1),equalTo("Java"));
        assertThat(result.get(2),equalTo("Guava#Scala#Kafka"));
    }

    @Test
    public void testSplitOnSplitToMap(){
        Map<String,String> result = Splitter.on("#").withKeyValueSeparator("=").split("Google=Java#Guava=Scala");
        assertThat(result.size(),equalTo(2));
        assertThat(result.get("Google"),equalTo("Java"));
        assertThat(result.get("Guava"),equalTo("Scala"));
    }

}
