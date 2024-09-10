package com.user.api.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.user.api.enity.UserInfoEntity;
import com.user.api.repo.UserInfoRepo;
import com.user.api.request.LoginRequest;
import com.user.api.request.PwdChangeReqeust;
import com.user.api.request.SignUpReqeust;
import com.user.api.responce.DashboardResponse;
import com.user.api.responce.LoginResponse;
import com.user.api.responce.SignUpRespose;
import com.user.api.utility.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoRepo userRepo;

	@Autowired
	private EmailUtils emailUtils;

	
	SignUpRespose response = new  SignUpRespose();

	@Override
	public SignUpRespose saveUser(SignUpReqeust request) {
		// for dulicate mail-> Account already exist with this email
		
		// for registration success -> Registration Success
		
		// Any exception -> Registraton faild
		
		// unique mail validation
		UserInfoEntity user = userRepo.findByEmail(request.getEmail());
		if (user != null) {
			response.setMsg("User already exits with this email");
			return response;
		}

		// create temp password
		String tempPwd = generateTempPwd();

		request.setPwd(tempPwd);
		request.setPwdChanged(false);

		// add user to database
		UserInfoEntity entity = new UserInfoEntity();
		BeanUtils.copyProperties(request, entity); // to copy data to request to entity
		userRepo.save(entity);

		// send mail to user with creadentials
		String subject = "IES - Account Created";
		String body = "your login password :: " + tempPwd;

		boolean issend = emailUtils.sendMail(request.getEmail(), subject, body);

		if (issend) {
			response.setMsg("Registration Success");
		} else {
			response.setErrorMsg("Registration faild");

		}
		return response;

	}

	@Override
	public LoginResponse userLogin(LoginRequest request) {

		LoginResponse response = new LoginResponse();
		// 1: findByMethod 2: custom Query 3: Query By Example
		// Query By Example
		UserInfoEntity entity = new UserInfoEntity();

		entity.setEmail(request.getEmail());
		entity.setPwd(request.getPwd());

		Example<UserInfoEntity> of = Example.of(entity);

		List<UserInfoEntity> entities = userRepo.findAll(of);

		if (!entities.isEmpty()) {
			UserInfoEntity user = entities.get(0);
			response.setUserId(user.getUserId());
			response.setUserType(user.getUserType());

			if (user.isPwdChanged()) {
				response.setPwdChanged(true);
				response.setValidLogin(false);

				// set dashboard Response
				DashboardResponse dash = new DashboardResponse();
				dash.setPlanCount((long) 32500.00);
				dash.setCitizensApprovedCnt((long) 654165);
				dash.setCitizensBenifitCnt((long) 465421);
				dash.setCitizensDeniedCnt((long) 5465456);

				response.setDashboardResponse(dash);

			} else {
				// first login
				response.setPwdChanged(false);
				response.setValidLogin(true);
			}
		}

		return response;
	}

	@Override
	public LoginResponse updatePwd(PwdChangeReqeust request) {
		LoginResponse response = new LoginResponse();

		Integer userId = request.getUserId();

		Optional<UserInfoEntity> findById = userRepo.findById(userId);
		if (findById.isPresent()) {
			UserInfoEntity entity = findById.get();

			entity.setPwd(request.getConfirmedpwd());
			entity.setPwdChanged(true);

			userRepo.save(entity);

			response.setValidLogin(true);

			response.setUserId(entity.getUserId());
			response.setUserType(entity.getUserType());

			// set dashboard Response
			DashboardResponse dash = new DashboardResponse();
			dash.setPlanCount((long) 32500.00);
			dash.setCitizensApprovedCnt((long) 654165);
			dash.setCitizensBenifitCnt((long) 465421);
			dash.setCitizensDeniedCnt((long) 5465456);

			response.setDashboardResponse(dash);
		}

		return response;
	}

	@Override
	public boolean recoverPwd(String mail) {

		UserInfoEntity user = userRepo.findByEmail(mail);

		if (user == null) {
			return false;
		}
		String subject = "IES - Recover Password";

		String body = "Your password :: " + user.getPwd();

		return emailUtils.sendMail(mail, subject, body);

	}

	public String generateTempPwd() {

		String randomString = "GLSDGSLDFOGSLFGKSDFGLK123465789";
		Random rnd = new Random();

		StringBuilder builder = new StringBuilder();

		int length = 5;

		for (int i = 0; i <= length; i++) { // Iterate up to length
			int index = rnd.nextInt(randomString.length());
			builder.append(randomString.charAt(index));
		}

		return builder.toString(); // Return the generated string

	}

}
