package com.online.store.online.store.books.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.online.store.online.store.common.util.SearchBaseRequest;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookSearchRequest extends SearchBaseRequest{

	private Long bookId;
	
	private String bookName;
	
	private String bookAuthor;
}
