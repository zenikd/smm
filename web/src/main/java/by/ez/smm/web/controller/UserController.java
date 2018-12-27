package by.ez.smm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import by.ez.smm.dal.orm.entity.User;
import by.ez.smm.service.UserService;
import by.ez.smm.service.impl.UserServiceImpl;

@Controller
@Path("/cafe")
public class UserController extends AbstractController
{
	@Autowired
	UserService userService;

	@GET
	@Path("/get")

	public Response getCurrentUser()
	{

		return Response.ok(userService.findOne()).build();
	}
}
