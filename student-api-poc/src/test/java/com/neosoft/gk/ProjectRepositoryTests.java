package com.neosoft.gk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;
import com.neosoft.gk.model.Project;
import com.neosoft.gk.repository.ProjectRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class ProjectRepositoryTests {
	 
	@Autowired
	private ProjectRepository repo;
	
	@Test
	public void testCreateProject() {
		Project savedProject=repo.save(new Project(1,"Angular"));
		assertThat(savedProject.getId()).isGreaterThan(0);
		
	}
	
}
