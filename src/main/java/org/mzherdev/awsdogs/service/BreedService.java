package org.mzherdev.awsdogs.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.mzherdev.awsdogs.HttpUtils;
import org.mzherdev.awsdogs.model.DogBreed;
import org.mzherdev.awsdogs.model.RandomLink;
import org.mzherdev.awsdogs.repo.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class BreedService {

	@Autowired
	private BreedRepository breedRepository;
	@Autowired
	private AwsS3Service awsS3Service;

	private final ObjectMapper mapper = new ObjectMapper();

	public List<String> findAll() {
		return breedRepository.findAll().stream().map(b -> StringUtils.capitalize(b.getName())).collect(Collectors.toList());
	}

	public DogBreed findById(Integer id) {
		return breedRepository.getOne(id);
	}

	public List<DogBreed> findByName(String name) {
		return breedRepository.findByNameContainingIgnoreCase(name);
	}

	@Transactional
	public DogBreed generate() {
		RandomLink randomLink = getRandomLink();
		DogBreed dogBreed = new DogBreed();
		if(randomLink != null && randomLink.getStatus().equals("success")) {
			String imageLink = randomLink.getMessage();
			String breedName = getBreedName(imageLink);
			String imageLocation = awsS3Service.saveImage(breedName, HttpUtils.getContent(imageLink));

			dogBreed.setName(breedName);
			dogBreed.setImageLocation(imageLocation);
			dogBreed.setDateCreated(LocalDateTime.now());
			return breedRepository.save(dogBreed);
		}
		return dogBreed;
	}

	@Transactional
	public void deleteById(Integer id) {
		breedRepository.deleteById(id);
	}

	private RandomLink getRandomLink() {
		final String dogUrl = "https://dog.ceo/api/breeds/image/random";
		RandomLink randomLink = null;
		try {
			String jsonString = IOUtils.toString(HttpUtils.getContent(dogUrl));
			randomLink = mapper.readValue(jsonString, RandomLink.class);
		} catch (IOException e) {
			log.error("Some error happened during parsing result", e);
		}
		return randomLink;
	}

	private String getBreedName(String imageLink) {
		int i = imageLink.lastIndexOf("/");
		int j  = imageLink.lastIndexOf("/", i -1);
		return imageLink.substring(j + 1, i);
	}
}
