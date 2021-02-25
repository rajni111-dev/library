package com.ibm.library.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ibm.library.endpoint.BookInventoryEndpoint;
import com.ibm.library.model.BookData;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class LibraryServiceImplTest {

	@Mock
	private BookInventoryEndpoint bookInventoryEndpoint;

	@InjectMocks
	private LibraryServiceImpl libraryService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Test libraryService getBook with valid isbn")
	@Test
	void testGetBookExistingIsbn() {
		// Given -set the parameter values and mock the methods for this test case
		String isbn = "1234";
		BookData bookDataMocked = new BookData("FICTION", isbn, "SomeBook", "SomeAuthor");

		when(bookInventoryEndpoint.getBook(isbn)).thenReturn(bookDataMocked);

		// When -call the method being tested and save the response
		BookData bookData = libraryService.getBook(isbn);

		// Then -check that the results are valid (and that the expected mocked methods were called)
        assertNotNull(bookData , "bookData should not be null");
        assertEquals(bookData , bookDataMocked , "bookData should be the same as : " + bookDataMocked);
     
        verify(bookInventoryEndpoint).getBook(isbn);
	}

}
