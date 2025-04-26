package com.online.store.online.store.books.repo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.online.store.online.store.books.entity.BookDetails;

public interface BooksRepository extends PagingAndSortingRepository<BookDetails, Long>, JpaSpecificationExecutor<BookDetails>{
}

