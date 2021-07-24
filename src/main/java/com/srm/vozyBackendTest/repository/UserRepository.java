/**
 * 
 */
package com.srm.vozyBackendTest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.srm.vozyBackendTest.document.User;

/**
 * @author sebastian
 *
 */
public interface UserRepository extends MongoRepository<User,String>{
	
	User findByUserName(String userName);

}
