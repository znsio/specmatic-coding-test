package main

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"server/models"
	"time"
)

var data []models.Product
var idCounter int = 0

func main() {

	http.HandleFunc("/products", func(w http.ResponseWriter, r *http.Request) {
		if r.Method == http.MethodGet {
			queryVar := r.URL.Query()
			productType := queryVar.Get("type")

			if productType == "" {
				response := []models.Product{}

				for i := 0; i < len(data); i++ {
					if data[i].Type != "" {
						response = append(response, data[i])
					}
				}
				w.Header().Set("Content-Type", "application/json")
				responseStr, _ := json.Marshal(response)

				fmt.Fprintf(w, string(responseStr))
				return
			} else if productType != models.Book.String() && productType != models.Food.String() && productType != models.Gadget.String() && productType != models.Other.String() {
				errorResponse := &models.ErrorResponseBody{Timestamp: time.Now().Format(time.RFC3339), Status: http.StatusBadRequest, Error: "Bad Request", Path: "/products"}
				errResStr, _ := json.Marshal(errorResponse)

				w.Header().Set("Content-Type", "application/json")
				w.WriteHeader(http.StatusBadRequest)
				fmt.Fprintf(w, string(errResStr))
				return
			}

			response := []models.Product{}

			for i := 0; i < len(data); i++ {
				if data[i].Type == models.ProductType(productType) {
					response = append(response, data[i])
				}
			}

			w.Header().Set("Content-Type", "application/json")
			responseStr, _ := json.Marshal(response)

			fmt.Fprintf(w, string(responseStr))
		} else if r.Method == http.MethodPost {

			productDetails := make(map[string]interface{})
			productDetailsStruct := &models.ProductDetails{}

			err := json.NewDecoder(r.Body).Decode(&productDetails)

			if productDetails["name"] == nil || productDetails["type"] == nil || productDetails["inventory"] == nil || productDetails["cost"] == nil || err != nil {
				errorResponse := &models.ErrorResponseBody{Timestamp: time.Now().Format(time.RFC3339), Status: http.StatusBadRequest, Error: "Bad Request", Path: "/products"}
				errResStr, _ := json.Marshal(errorResponse)

				w.Header().Set("Content-Type", "application/json")
				w.WriteHeader(http.StatusBadRequest)
				fmt.Fprintf(w, string(errResStr))
				return
			}

			byteArr, err := json.Marshal(productDetails)
			if err != nil {
				errorResponse := &models.ErrorResponseBody{Timestamp: time.Now().Format(time.RFC3339), Status: http.StatusBadRequest, Error: "Bad Request", Path: "/products"}
				errResStr, _ := json.Marshal(errorResponse)

				w.Header().Set("Content-Type", "application/json")
				w.WriteHeader(http.StatusBadRequest)
				fmt.Fprintf(w, string(errResStr))
				return
			}
			err = json.Unmarshal(byteArr, productDetailsStruct)

			if err != nil {
				errorResponse := &models.ErrorResponseBody{Timestamp: time.Now().Format(time.RFC3339), Status: http.StatusBadRequest, Error: "Bad Request", Path: "/products"}
				errResStr, _ := json.Marshal(errorResponse)

				w.Header().Set("Content-Type", "application/json")
				w.WriteHeader(http.StatusBadRequest)
				fmt.Fprintf(w, string(errResStr))
				return
			}

			product := &models.Product{Id: idCounter, Name: productDetailsStruct.Name, Type: productDetailsStruct.Type, Inventory: productDetailsStruct.Inventory}
			data = append(data, *product)

			w.Header().Set("Content-Type", "application/json")
			w.WriteHeader(http.StatusCreated)

			response := &models.ProductId{Id: idCounter}
			responseStr, _ := json.Marshal(response)

			idCounter++

			fmt.Fprintf(w, string(responseStr))
		}
	})
	log.Fatal(http.ListenAndServe(":8080", nil))

}
