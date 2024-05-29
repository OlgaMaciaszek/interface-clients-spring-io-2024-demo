package com.example.rsocketverificationclient.cmd;

import com.example.rsocketverificationclient.clients.VerificationService;
import com.example.rsocketverificationclient.dto.VerificationRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Olga Maciaszek-Sharma
 */
@Component
public class DemoCommandLineRunner implements CommandLineRunner {

	private static final Log LOG = LogFactory.getLog(DemoCommandLineRunner.class);

	private final VerificationService verificationService;

	public DemoCommandLineRunner(VerificationService verificationService) {
		this.verificationService = verificationService;
	}

	@Override
	public void run(String... args) {
		LOG.info("Verified blocking: " + verificationService.verifyBlocking(new VerificationRequest("Amy", "Young")));
	}
}
