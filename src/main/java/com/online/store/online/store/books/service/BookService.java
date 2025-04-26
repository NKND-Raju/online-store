package com.online.store.online.store.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.online.store.online.store.books.entity.BookDetails;
import com.online.store.online.store.books.repo.BooksRepository;
import com.online.store.online.store.books.repo.BooksSpecification;
import com.online.store.online.store.books.request.BookSearchRequest;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BookService {

	@Autowired
	BooksRepository booksRepository;
	
	public Page<BookDetails> findAll(BookSearchRequest request) {
		BooksSpecification spec = new BooksSpecification(request);
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
		Page<BookDetails> booksResp = null;
		try {
		    booksResp = booksRepository.findAll(spec, pageable);
		} catch (Exception ex) {
			log.error("Exception OfferService.findPaginated : " , ex);
			
		}
		return booksResp;
	}

}
