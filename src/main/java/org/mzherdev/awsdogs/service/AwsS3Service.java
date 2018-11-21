package org.mzherdev.awsdogs.service;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

/**
 * Setup credentials
 * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html
 */
@Service
public class AwsS3Service {

	private static final String UID = UUID.randomUUID().toString();
	private static final String BUCKET_NAME = "dog-breed-" + UID;
	private AmazonS3 s3;

	public AwsS3Service() {
		s3 = AmazonS3ClientBuilder.defaultClient();
		if (!s3.doesBucketExistV2(BUCKET_NAME)) {
			s3.createBucket(BUCKET_NAME);
		}
	}

	public String saveImage(String key, InputStream content) {
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType("image/jpg");
		s3.putObject(BUCKET_NAME, key, content, meta);
		return s3.getUrl(BUCKET_NAME, key).toString();
	}
}
