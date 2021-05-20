package com.neosoft.gk;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.gk.model.Project;
import com.neosoft.gk.model.Student;
import com.neosoft.gk.resultmodel.ResultModel;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class MainAppStudentTests {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeAll
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void createStudent() throws Exception{
		   
		Student Student=new Student();
		Student.setStudentId(4);
		Student.setFirstName("Gouresh");
		Student.setLastName("Khochare");
		Student.setEmail("gouresh@gmail.com");
		Student.setContact("9090909090");


		String JsonRequest= objectMapper.writeValueAsString(Student);
		MvcResult result=mockMvc.perform(post("/studenttest").content(JsonRequest).contentType(
		MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk()).
		andReturn();
		String resultContext =result.getResponse().getContentAsString(); 
		ResultModel response=objectMapper.readValue(resultContext, ResultModel.class);
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success",response.getProgressMessage());
	}

	@Test
	public void getAllStudents() throws Exception{
		MvcResult result = mockMvc.perform(get("/studentstest").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		String resultContext = result.getResponse().getContentAsString();
		ResultModel response = objectMapper.readValue(resultContext, ResultModel.class);
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success",response.getProgressMessage());
	}

	@Test
	public void getEmployeeById() throws Exception{
		MvcResult result = mockMvc.perform(get("/studenttest/2").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		String resultContext = result.getResponse().getContentAsString();
		ResultModel response = objectMapper.readValue(resultContext, ResultModel.class);
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success",response.getProgressMessage());
	}
	
	@Test
	public void updateStudentTest() throws Exception{
		Student student=new Student();
		student.setFirstName("Rohan");
		student.setLastName("Patil");
		student.setEmail("rohan@gmail.com");
		student.setContact("8080808080");
		String JsonRequest= objectMapper.writeValueAsString(student);
		MvcResult result=mockMvc.perform(put("/studenttest/2").content(JsonRequest).contentType(
		MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk()).
		andReturn();
		String resultContext =result.getResponse().getContentAsString(); 
		ResultModel response=objectMapper.readValue(resultContext, ResultModel.class);
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success",response.getProgressMessage());
	}

	@Test
	public void deleteStudentTest() throws Exception{
		MvcResult result =  mockMvc.perform(delete("/studenttest/3").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).
	    andExpect(status().isOk()).andReturn(); 
		String resultContext=result.getResponse().getContentAsString(); 
		ResultModel response=objectMapper.readValue(resultContext, ResultModel.class);
	    Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
	    Assertions.assertEquals("Success",response.getProgressMessage()); 
	}


}
