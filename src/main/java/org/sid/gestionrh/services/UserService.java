package org.sid.gestionrh.services;

import org.sid.gestionrh.models.requests.LoginRequest.LoginRequest;
import org.sid.gestionrh.models.response.LoginResponse.LoginResponse;
import org.sid.gestionrh.services.RegesterRequest.RegesterRequest;
import org.sid.gestionrh.services.UserResponse.UserResponse;

public interface UserService /*extends CrudService<UserRequest,LoginRequest,UserResponse,User>*/{
    LoginResponse login(LoginRequest loginRequest);
    UserResponse register(RegesterRequest regesterRequest);

}
