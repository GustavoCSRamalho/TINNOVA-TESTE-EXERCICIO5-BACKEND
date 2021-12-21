package com.gustavo.tinnova;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavo.tinnova.controller.VeiculosController;
import com.gustavo.tinnova.models.Veiculo;
import com.gustavo.tinnova.models.VeiculoResposta;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TinnovaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void whenGetVeiculosReturn200AndList() throws Exception {
		mockMvc.perform(get("/veiculos")
				.contentType("application/json"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(2)
	void whenGetVeiculosFindReturn200AndList() throws Exception {
		mockMvc.perform(get("/veiculos/find?q=VW")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	@Order(3)
	void whenGetVeiculosIdReturn200AndList() throws Exception {
		mockMvc.perform(get("/veiculos/1")
//				.content(new Veiculo(Long.getLong("0"),"teste","AGCO",2019,"muito bom",true))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(4)
	void whenPostVeiculosIdReturn200AndList() throws Exception {
		mockMvc.perform(post("/veiculos")
				.contentType("application/json")
				.content(asJsonString(new Veiculo(Long.getLong("0"),"teste","AGCO",2019,"muito bom",true))))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());;
	}

	@Test
	@Order(5)
	void whenPutVeiculosIdReturn200AndList() throws Exception {
		mockMvc.perform(put("/veiculos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(new Veiculo(Long.getLong("0"),"ola","VW",2020,"muito bom mesmo",false))))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(6)
	void whenPatchVeiculosIdReturn200AndList() throws Exception {

		mockMvc.perform(patch("/veiculos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(new Veiculo(Long.getLong("0"),"ola","VW",2010,"muito bom mesmo",false))))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(7)
	void whenDeleteVeiculosIdReturn200AndList() throws Exception {
		mockMvc.perform(delete("/veiculos/1")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
