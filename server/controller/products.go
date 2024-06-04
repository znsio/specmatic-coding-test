package controller

import (
	"net/http"
	"server/data"
	"server/models"

	"github.com/gin-gonic/gin"
)

func GetProducts(context *gin.Context) {
	productType := context.Query("type")

	if !models.IsValidProductType(productType) {
		errorResponse := models.ErrorResponse(400, "Bad Request", "/products")
		context.JSON(http.StatusBadRequest, errorResponse)
		return
	}

	if productType == "" {
		response := []models.Product{}

		for i := 0; i < len(data.Data); i++ {
			if data.Data[i].Type != "" {
				response = append(response, data.Data[i])
			}
		}

		context.JSON(http.StatusOK, response)
		return
	}

	response := []models.Product{}

	for i := 0; i < len(data.Data); i++ {
		if data.Data[i].Type == productType {
			response = append(response, data.Data[i])
		}
	}

	context.JSON(http.StatusOK, response)
}

func AddProduct(context *gin.Context) {
	product := &models.Product{}

	err := context.ShouldBind(&product)
	if err != nil {
		errorResponse := models.ErrorResponse(400, "Bad Request", "/products")
		context.JSON(http.StatusBadRequest, errorResponse)
		return
	}

	product.Id = data.IdCounter
	data.Data = append(data.Data, *product)

	response := &models.ProductId{Id: data.IdCounter}
	data.IdCounter++

	context.JSON(http.StatusCreated, response)
}
