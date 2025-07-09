package main

import (
	"amankumarsingh77/specmatic-coding-test/internal/logger"
	"amankumarsingh77/specmatic-coding-test/internal/products"
	"context"
	"net/http"
	"os"
	"os/signal"
	"syscall"
	"time"

	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
)

type Server struct {
	logger logger.Logger
	config *Config
	echo   *echo.Echo
}

const (
	maxHeaderBytes = 1 << 20
	ctxTimeout     = 5 * time.Second
)

func NewServer(logger logger.Logger, config *Config) *Server {
	return &Server{
		logger: logger,
		config: config,
		echo:   echo.New(),
	}
}

func (s *Server) Start() error {
	s.echo.Server.ReadTimeout = s.config.ReadTimeout
	s.echo.Server.WriteTimeout = s.config.WriteTimeout
	s.echo.Server.MaxHeaderBytes = maxHeaderBytes

	s.echo.Use(middleware.CORSWithConfig(middleware.CORSConfig{
		AllowOrigins:     []string{"*"},
		AllowMethods:     []string{http.MethodGet, http.MethodPost, http.MethodPut, http.MethodDelete, http.MethodOptions},
		AllowHeaders:     []string{"Content-Type", "Authorization"},
		AllowCredentials: true,
		MaxAge:           300,
	}))

	store := products.NewProductStore()

	s.echo.GET("/products", echo.WrapHandler(products.HandleGetProducts(store, s.logger)))
	s.echo.POST("/products", echo.WrapHandler(products.HandlePostProduct(store, s.logger)))

	s.logger.Infof("Starting server on port %s", s.config.Port)
	go func() {
		if err := s.echo.Start(s.config.Port); err != nil && err != http.ErrServerClosed {
			s.logger.Fatalf("Failed to start server: %v", err)
		}
	}()

	quit := make(chan os.Signal, 1)
	signal.Notify(quit, syscall.SIGINT, syscall.SIGTERM)

	<-quit
	s.logger.Infof("Shutting down server...")

	ctx, cancel := context.WithTimeout(context.Background(), ctxTimeout)
	defer cancel()
	return s.echo.Shutdown(ctx)
}
