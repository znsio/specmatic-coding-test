package models

type Product struct {
	Id        int    `json:"id"`
	Name      string `json:"name" binding:"required"`
	Type      string `json:"type" binding:"required"`
	Inventory int    `json:"inventory" binding:"required"`
	Cost      int    `json:"cost" binding:"required"`
}

var ProductType []string = []string{"book", "food", "gadget", "other"}

func IsValidProductType(product string) bool {
	if product == "" {
		return true
	}

	for _, n := range ProductType {
		if product == n {
			return true
		}
	}

	return false
}

type ProductId struct {
	Id int `json:"id"`
}
