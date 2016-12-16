package org.bridgelabz.docsigner.service;

import org.bridgelabz.docsigner.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {

	public void addUser(User user);

	public User authUser(String email, String password);

}
