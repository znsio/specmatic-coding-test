package main

import (
	"amankumarsingh77/specmatic-coding-test/internal/logger"
)

func main() {
	lg := logger.New()
	lg.Infof("Starting server...")

	v, err := LoadConfig("config.yaml")
	if err != nil {
		lg.Fatalf("Failed to load config: %v", err)
	}
	config, err := ParseConfig(v)
	if err != nil {
		lg.Fatalf("Failed to parse config: %v", err)
	}

	server := NewServer(lg, config)
	if err := server.Start(); err != nil {
		lg.Fatalf("Failed to start server: %v", err)
	}
}
