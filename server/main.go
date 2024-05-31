package main

import (
	"log"
	"net/http"
	"server/controller"
	"server/models"
)

var data []models.Product
var idCounter int = 0

func main() {
	http.HandleFunc("/products", func(w http.ResponseWriter, r *http.Request) {
		if r.Method == http.MethodGet {
			controller.GetProducts(r, w, data)
		} else if r.Method == http.MethodPost {
			controller.AddProduct(r, w, data, idCounter)
		}
	})
	log.Fatal(http.ListenAndServe(":8080", nil))
}
