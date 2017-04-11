package com.myorg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.myorg.exception.StorageFileNotFoundException;
import com.myorg.service.FileService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FileUploadTest {
	@Autowired
    private MockMvc mvc;

	@Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private FileService fileService;

	@Test
    public void shouldSaveUploadedFile() throws Exception {
        MockMultipartFile multipartFile =
                new MockMultipartFile("file", "test.txt", "text/plain", "Spring Framework".getBytes());
        this.mvc.perform(fileUpload("/file/saveToDisk").file(multipartFile))
                .andExpect(status().isOk());
     
        then(this.fileService).should().saveFileToDisk(multipartFile);
    }
	
	@Test
    public void should200WhenFoundFile() throws Exception {
		ClassPathResource resource = new ClassPathResource("test.txt", getClass());
        given(this.fileService.loadAsResource("test.txt")).willReturn(resource);
      

        this.mvc.perform(get("/file/searchByName/test.txt"))
                .andExpect(status().isOk());
    }
	
	
	@SuppressWarnings("unchecked")
	@Test
    public void should404WhenMissingFile() throws Exception {
        given(this.fileService.loadAsResource("test.txt"))
                .willThrow(StorageFileNotFoundException.class);

        this.mvc.perform(get("/files/test.txt"))
                .andExpect(status().isNotFound());
    }


	@Test
	public void shouldDownloadFile() throws Exception {
	    ClassPathResource resource = new ClassPathResource("test.txt", getClass());
	        given(this.fileService.loadAsResource("test.txt")).willReturn(resource);

	    ResponseEntity<String> response = this.restTemplate
	                .getForEntity("/file/searchByName/{filename}", String.class, "test.txt");

	        assertThat(response.getStatusCodeValue()).isEqualTo(200);
	        assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION))
	                .isEqualTo("attachment; filename=\"test.txt\"");
	        assertThat(response.getBody()).isEqualTo("Spring Framework");  
	}

}
