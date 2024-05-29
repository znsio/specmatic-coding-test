package models

type Product struct {
	Id        int         `json:"id"`
	Name      string      `json:"name"`
	Type      ProductType `json:"type"`
	Inventory int         `json:"inventory"`
}

type ProductDetails struct {
	Name      string      `json:"name"`
	Type      ProductType `json:"type"`
	Inventory int         `json:"inventory"`
}

type ProductType string

const (
	Book   ProductType = "book"
	Food   ProductType = "food"
	Gadget ProductType = "gadget"
	Other  ProductType = "other"
)

func (pt ProductType) String() string {
	return string(pt)
}

type ProductId struct {
	Id int `json:"id"`
}

type ErrorResponseBody struct {
	Timestamp string `json:"timestamp"`
	Status    int    `json:"status"`
	Error     string `json:"error"`
	Path      string `json:"path"`
}
