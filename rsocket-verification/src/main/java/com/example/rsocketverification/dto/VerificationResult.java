package com.example.rsocketverification.dto;

import java.util.UUID;

/**
 * @author Olga Maciaszek-Sharma
 */
public record VerificationResult(String firstName, String lastName, UUID uuid) {
}
