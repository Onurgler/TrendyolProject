package com.basket.basketservice.repository;

import java.util.List;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.stereotype.Repository;
import com.basket.basketservice.domain.Basket;

@Repository
public interface BasketRepository extends CouchbaseRepository<Basket,String> {

	@Query("#{#n1ql.selectEntity} WHERE $1 IN products[*].id")
	List<Basket> findAllUsingN1ql(String productId);
}
