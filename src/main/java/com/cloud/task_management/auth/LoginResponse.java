package com.cloud.task_management.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginResponse (String token) {}
