package com.damon.media.domain.test;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ApplicationTest {

    @Test
    public void testMockito() {
        List<String> mockedList = mock(List.class);

        when(mockedList.get(0)).thenReturn("Hello");
        when(mockedList.get(1)).thenReturn("World");

        assertEquals("Hello", mockedList.get(0));
        assertEquals("World", mockedList.get(1));

        verify(mockedList, times(1)).get(0);
        verify(mockedList, times(1)).get(1);

        List<String> newList = new LinkedList<>();
        List<String> spy = spy(newList);

        when(spy.size()).thenReturn(20);

        spy.add("we");
        spy.add("are");
        spy.add("winner");

        assertEquals(spy.get(0), "we");
        assertEquals(spy.get(1), "are");
        assertEquals(spy.get(2), "winner");

        assertEquals(spy.size(), 20);
    }
}
