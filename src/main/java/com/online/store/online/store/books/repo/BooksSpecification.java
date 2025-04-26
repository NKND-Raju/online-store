package com.online.store.online.store.books.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.online.store.online.store.books.entity.BookDetails;
import com.online.store.online.store.books.request.BookSearchRequest;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BooksSpecification implements Specification<BookDetails>{

	private BookSearchRequest criteria;
	
	public BooksSpecification(BookSearchRequest booksSearchRequest) {
		this.criteria = booksSearchRequest;
	}
	@Override
	public Predicate toPredicate(Root<BookDetails> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Path<String> bookId = root.get("bookId");
		Path<String> bookName = root.get("bookName");
		Path<String> bookAuthor = root.get("bookAuthor");
		List<Predicate> predicate = new ArrayList<Predicate>();
		if(criteria.getBookId()!=null) {
			predicate.add(cb.equal(bookId, criteria.getBookId()));
		}
		if(criteria.getBookName()!=null && (!criteria.getBookName().isEmpty())) {
			predicate.add(cb.like(cb.lower(bookName), "%"+criteria.getBookName().toLowerCase()+"%"));
		}
		if(criteria.getBookAuthor()!=null && (!criteria.getBookAuthor().isEmpty())) {
			predicate.add(cb.like(cb.lower(bookAuthor), "%"+criteria.getBookAuthor().toLowerCase()+"%"));
		}
		query.orderBy(cb.desc(root.get("bookId")));
		return cb.and(predicate.toArray(new Predicate[predicate.size()]));
	}
	
	

}
