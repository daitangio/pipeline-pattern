package com.gioorgi.pattern.pipeline;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static  org.junit.jupiter.api.Assumptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.*;
import com.gioorgi.pattern.pipeline.entity.SurfGuy;
import com.gioorgi.pattern.pipeline.entity.SurfTable;
import com.gioorgi.pattern.pipeline.repository.*;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional()
@Slf4j
class PipelineApplicationTests {

	@Autowired
	SurfGuyRepository surfGuyRepository;

	@Test
	void surfTest1() {
		SurfGuy eric=SurfGuy.builder().name("Eric").surname("Templeton").build();
		
		log.info("Eric here: {}",eric);
		surfGuyRepository.save(eric);
		log.info("Surf guys {}",surfGuyRepository.count());
	}

	/** In this test we try to take a surf table already assigned and we try to assign to a new guy
	 * hibernate will detect the error and throw an exception
	 */
	@Test
	@Transactional
	void richJack() {
		SurfTable lastTable = createJackGuy();
		try {
			tryToCheat(lastTable); 
			fail("Surf table cannot have multiple assignments");
		}catch(org.springframework.dao.DataIntegrityViolationException e){
			//	ok expected
			log.info("Ok uniqueness works", e);
		}
			
		log.info("Surf guys {}",surfGuyRepository.count());
		
	}

	private void tryToCheat(SurfTable lastTable) {
		SurfGuy eric=SurfGuy.builder().name("Eric").surname("Templeton").build();
		eric.setOwnedTables(new ArrayList<>());
		surfGuyRepository.save(eric);
		eric.getOwnedTables().add(lastTable);
		surfGuyRepository.save(eric);
		// We need to FLUSH the entities now to get the error as soon as possible
		surfGuyRepository.flush();
	}


	private SurfTable createJackGuy() {
		SurfGuy j=SurfGuy.builder().name("Jack").surname("Templeton").build();
		
		log.info("{}",j);
		j.setOwnedTables(new ArrayList<>());
		SurfTable lastTable=null;
		for(int i=0; i<2; i++){
			lastTable=SurfTable.builder().lengthCm(26).tableName("Flurp"+i).build();
			j.getOwnedTables().add(lastTable);
		}
		surfGuyRepository.save(j);
		return lastTable;
	}

}
