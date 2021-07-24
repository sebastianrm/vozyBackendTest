/**
 * 
 */
package com.srm.vozyBackendTest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.srm.vozyBackendTest.document.User;
import com.srm.vozyBackendTest.repository.UserRepository;

/**
 * @author sebastian
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserCrudControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository userRepo;
    
	private User mockUser=new User("sebastian","$2a$10$mbNd7yfniCWRHApvTZFotuMndmEhy2Ex1FBa3Pn0OgGVx5ssyLvSm","asd@asd",true);
	
	String userLoginJson = "{\"username\":\"sebastian\",\"password\":\"123\"}";
	String userEditJson = "{\"username\":\"xx\",\"password\":\"123\",\"correo\":\"xx@sebastian.com\",\"activo\":\"true\"}";
	String userAddJson = "{\"username\":\"xx\",\"password\":\"123\",\"correo\":\"xx@sebastian.com\",\"activo\":\"true\"}";
	String userDeleteJson = "{\"username\":\"xx\",\"password\":\"123\",\"correo\":\"xx@sebastian.com\",\"activo\":\"true\"}";
	
	/**
	 * Test method for
	 * {@link com.srm.vozyBackendTest.controller.UserCrudController#login(com.srm.vozyBackendTest.vo.UserVo)}.
	 */
	@Test
	void testLogin() {
		
		
		Mockito.when(
				userRepo.findByUserName(Mockito.anyString())).thenReturn(mockUser);
		
		try {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/login").content(userLoginJson)
	            .contentType(MediaType.APPLICATION_JSON);
		
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
		    MockHttpServletResponse response = result.getResponse();

		    assertEquals(HttpStatus.OK.value(), response.getStatus());

//		    assertEquals("http://localhost/user/login",
//		            response.getHeader(HttpHeaders.LOCATION));
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.srm.vozyBackendTest.controller.UserCrudController#registrar(com.srm.vozyBackendTest.document.User)}.
	 */
	@Test
	void testRegistrar() {
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/singin").content(userAddJson)
	            .contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}

	/**
	 * Test method for
	 * {@link com.srm.vozyBackendTest.controller.UserCrudController#editarUsuario(com.srm.vozyBackendTest.document.User)}.
	 */
	@Test
	void testEditarUsuario() {

		String token = login();
		
		try {
			if (token != null) {
			RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/edituser")
					.content(userEditJson)
					.header("Authorization","Bearer "+ token)
					.contentType(MediaType.APPLICATION_JSON);
			
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
			MockHttpServletResponse response = result.getResponse();
			
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			}else {
				fail("Test");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link com.srm.vozyBackendTest.controller.UserCrudController#eliminarUsuario(com.srm.vozyBackendTest.document.User)}.
	 */
	@Test
	void testEliminarUsuario() {
		
		String token = login();
		
		try {
			
			if (token != null) {
				RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteuser")
						.content(userEditJson)
						.header("Authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_JSON);
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				assertEquals(HttpStatus.OK.value(), response.getStatus());
			}else {
				fail("Test");
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private String login() {

//		login
		Mockito.when(userRepo.findByUserName(Mockito.anyString())).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login")
				.content(userLoginJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
			result.getResponse().getContentAsString();
			String[] split = result.getResponse().getContentAsString().split(":");
			
			return split[1].trim().replaceAll("}","");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;

	}
		

}
