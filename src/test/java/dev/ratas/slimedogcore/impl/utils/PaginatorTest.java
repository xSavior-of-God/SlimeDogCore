package dev.ratas.slimedogcore.impl.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaginatorTest {

    @Test
    public void test_emptyPage1Valid() {
        Assertions.assertTrue(Paginator.isValidPage(Arrays.asList(), 1, 5));
    }

    @Test
    public void test_emptyPage2NotValid() {
        Assertions.assertFalse(Paginator.isValidPage(Arrays.asList(), 2, 5));
    }

    @Test
    public void test_page1StartCorrect() {
        Assertions.assertEquals(0, Paginator.getPageStart(1, 5));
    }

    @Test
    public void test_page2StartCorrect() {
        Assertions.assertEquals(5, Paginator.getPageStart(2, 5));
    }

    @Test
    public void test_page1EndCorrect() {
        Assertions.assertEquals(5, Paginator.getPageEnd(1, 5));
    }

    @Test
    public void test_page2EndCorrect() {
        Assertions.assertEquals(10, Paginator.getPageEnd(2, 5));
    }

    @Test
    public void test_perPage0Fails() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Paginator<>(Arrays.asList(), 1, 0));
    }

    @Test
    public void test_perPageNegativeFails() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Paginator<>(Arrays.asList(), 1, -1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Paginator<>(Arrays.asList(), 1, -2));
    }

    @Test
    public void test_emptyPaginatorInitializesPage1() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(), 1, 5);
        Assertions.assertNotNull(pa);
        Assertions.assertTrue(pa.getOnPage().isEmpty());
    }

    @Test
    public void test_emptyPaginatorFailsPage2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Paginator<>(Arrays.asList(), 2, 5));
    }

    @Test
    public void test_nonEmptyPaginatorInitializesPage1() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(1, 2, 3), 1, 5);
        Assertions.assertNotNull(pa);
        Assertions.assertFalse(pa.getOnPage().isEmpty());
    }

    @Test
    public void test_paginatorPage1CorrectStart() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(1, 2, 3), 1, 5);
        Assertions.assertEquals(0, pa.getPageStart());
    }

    @Test
    public void test_paginatorPage1CorrectEndRegular() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(1, 2, 3), 1, 2);
        Assertions.assertEquals(2, pa.getPageEnd());
    }

    @Test
    public void test_paginatorPage1CorrectEndShortList() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(1, 2, 3), 1, 5);
        Assertions.assertEquals(3, pa.getPageEnd());
    }

    @Test
    public void test_paginatorContainsContentPage1() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(1, 2, 3), 1, 5);
        Assertions.assertNotNull(pa);
        Assertions.assertFalse(pa.getOnPage().isEmpty());
        Assertions.assertTrue(pa.getOnPage().size() == 3);
        Assertions.assertTrue(pa.getOnPage().contains(1));
        Assertions.assertTrue(pa.getOnPage().contains(2));
        Assertions.assertTrue(pa.getOnPage().contains(3));
    }

    @Test
    public void test_paginatorFailsTooHighPage() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Paginator<>(Arrays.asList(1, 2, 3), 2, 5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Paginator<>(Arrays.asList(1, 2, 3), 12, 5));
    }

    @Test
    public void test_paginatorFails0Page() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Paginator<>(Arrays.asList(1, 2, 3), 0, 5));
    }

    @Test
    public void test_paginatorFailsNegativePage() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Paginator<>(Arrays.asList(1, 2, 3), -1, 5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Paginator<>(Arrays.asList(1, 2, 3), -5, 5));
    }

    @Test
    public void test_paginatorContainsContentPage1OfLong() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 1, 5);
        Assertions.assertNotNull(pa);
        Assertions.assertFalse(pa.getOnPage().isEmpty());
        Assertions.assertTrue(pa.getOnPage().size() == 5);
        Assertions.assertTrue(pa.getOnPage().contains(1));
        Assertions.assertTrue(pa.getOnPage().contains(2));
        Assertions.assertTrue(pa.getOnPage().contains(3));
        Assertions.assertTrue(pa.getOnPage().contains(4));
        Assertions.assertTrue(pa.getOnPage().contains(5));
    }

    @Test
    public void test_paginatorPreservesOrderPage1OfLong() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Paginator<Integer> pa = new Paginator<>(list, 1, 5);
        Assertions.assertNotNull(pa);
        Assertions.assertEquals(list.subList(0, 5), pa.getOnPage());
    }

    @Test
    public void test_paginatorPage2CorrectStart() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(1, 2, 3, 4, 5), 2, 3);
        Assertions.assertEquals(3, pa.getPageStart());
    }

    @Test
    public void test_paginatorPage2CorrectEndRegular() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(1, 2, 3, 4, 5, 6), 2, 3);
        Assertions.assertEquals(6, pa.getPageEnd());
    }

    @Test
    public void test_paginatorPage2CorrectEndShortList() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(1, 2, 3, 4), 2, 3);
        Assertions.assertEquals(4, pa.getPageEnd());
    }

    @Test
    public void test_paginatorContainsContentPage2OfLong() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 2, 5);
        Assertions.assertNotNull(pa);
        Assertions.assertFalse(pa.getOnPage().isEmpty());
        Assertions.assertTrue(pa.getOnPage().size() == 5);
        Assertions.assertTrue(pa.getOnPage().contains(6));
        Assertions.assertTrue(pa.getOnPage().contains(7));
        Assertions.assertTrue(pa.getOnPage().contains(8));
        Assertions.assertTrue(pa.getOnPage().contains(9));
        Assertions.assertTrue(pa.getOnPage().contains(10));
    }

    @Test
    public void test_paginatorPreservesOrderPage2OfLong() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Paginator<Integer> pa = new Paginator<>(list, 2, 5);
        Assertions.assertNotNull(pa);
        Assertions.assertEquals(list.subList(5, 10), pa.getOnPage());
    }

    @Test
    public void test_paginatorContainsContentPage3OfLong() {
        Paginator<Integer> pa = new Paginator<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 3, 5);
        Assertions.assertNotNull(pa);
        Assertions.assertFalse(pa.getOnPage().isEmpty());
        Assertions.assertTrue(pa.getOnPage().size() == 2);
        Assertions.assertTrue(pa.getOnPage().contains(11));
        Assertions.assertTrue(pa.getOnPage().contains(12));
    }

    @Test
    public void test_paginatorPreservesOrderPage3OfLong() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Paginator<Integer> pa = new Paginator<>(list, 3, 5);
        Assertions.assertNotNull(pa);
        Assertions.assertEquals(list.subList(10, 12), pa.getOnPage());
    }

    @Test
    public void test_paginatorGetOnPageIteratesOverAll() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Paginator<Integer> pa = new Paginator<>(list, 1, 5);
        for (int i : pa.getOnPage()) {
            Assertions.assertEquals(list.get(i - 1), i);
        }
    }

    @Test
    public void test_paginatorIterableEquivelantToGetOnPage() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Paginator<Integer> pa = new Paginator<>(list, 1, 5);
        for (int i : pa) {
            Assertions.assertEquals(list.get(i - 1), i);
        }
    }

    @Test
    public void test_paginatorPaginateDoesWork() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Paginator.paginate(list, 1, 5, i -> {
            Assertions.assertEquals(list.get(i - 1), i);
        });
    }

}
