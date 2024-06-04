package models

import "time"

type ErrorResponseBody struct {
	Timestamp string `json:"timestamp"`
	Status    int    `json:"status"`
	Error     string `json:"error"`
	Path      string `json:"path"`
}

func ErrorResponse(status int, errorMessage string, path string) ErrorResponseBody {
	errorResponse := ErrorResponseBody{Timestamp: time.Now().Format(time.RFC3339), Status: status, Error: errorMessage, Path: path}
	return errorResponse
}
