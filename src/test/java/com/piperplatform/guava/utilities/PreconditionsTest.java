package com.piperplatform.guava.utilities;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @Author shiyoufeng
 * @Date 2020/12/23 2:58 下午
 * @Version 1.0
 */
public class PreconditionsTest {

    @Test(expected = NullPointerException.class)
    public void testCheckNotNull() {
        Preconditions.checkNotNull(null);
    }

    @Test
    public void testCheckNotNullWithMessage() {
        try {
            Preconditions.checkNotNull(null, "The object should not be null");
        } catch (NullPointerException e) {
            assertThat(e, is(NullPointerException.class));
            assertThat(e.getMessage(), equalTo("The object should not be null"));
        }
    }

    @Test
    public void testCheckNotNullWithFormatMessage() {
        try {
            Preconditions.checkNotNull(null, "The object should not be null and size must be %s", 2);
        } catch (NullPointerException e) {
            assertThat(e, is(NullPointerException.class));
            assertThat(e.getMessage(), equalTo("The object should not be null and size must be 2"));
        }
    }

    @Test
    public void testCheckArgument() {
        final String type = "A";
        try {
            Preconditions.checkArgument(type.equals("B"));
        } catch (IllegalArgumentException e) {
            assertThat(e,is(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCheckState(){
        final String state = "A";
        try {
            Preconditions.checkState(state.equals("B"),"the state is illegal.");
            fail("Should not process to here.");
        } catch (IllegalStateException e) {
            assertThat(e,is(IllegalStateException.class));
            assertThat(e.getMessage(),equalTo("the state is illegal."));
        }
    }

    @Test
    public void testCheckIndex(){
        final List<String> list = ImmutableList.of();
        try {
            Preconditions.checkElementIndex(10,list.size());
            fail("Should not process to here.");
        } catch (IndexOutOfBoundsException e) {
            assertThat(e,is(IndexOutOfBoundsException.class));
        }
    }
}
