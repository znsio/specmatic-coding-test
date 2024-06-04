package main

import (
	"server/controller"

	"github.com/gin-gonic/gin"
)

func main() {
	var router *gin.Engine = gin.Default()

	router.GET("/products", controller.GetProducts)
	router.POST("/products", controller.AddProduct)

	router.Run(":8080")
}
