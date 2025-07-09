package products

import (
	"strings"
	"sync"
	"time"
)

type ProductDetails struct {
	Name      string   `json:"name"`
	Type      string   `json:"type"`
	Inventory int      `json:"inventory"`
	Cost      *float64 `json:"cost"`
}

type Product struct {
	ID int `json:"id"`
	ProductDetails
}

type ErrorResponse struct {
	Timestamp time.Time `json:"timestamp"`
	Status    int       `json:"status"`
	Error     string    `json:"error"`
	Path      string    `json:"path"`
}

type Store interface {
	Add(ProductDetails) Product
	ListByType(string) []Product
	ListAll() []Product
}

type productStore struct {
	sync.RWMutex
	store  map[int]Product
	nextID int
}

func NewProductStore() Store {
	return &productStore{
		store:  make(map[int]Product),
		nextID: 1,
	}
}

func (ps *productStore) Add(p ProductDetails) Product {
	ps.Lock()
	defer ps.Unlock()
	id := ps.nextID
	ps.nextID++
	product := Product{ID: id, ProductDetails: p}
	ps.store[id] = product
	return product
}

func (ps *productStore) ListByType(t string) []Product {
	ps.RLock()
	defer ps.RUnlock()

	out := make([]Product, 0)
	for _, p := range ps.store {
		if strings.EqualFold(p.Type, t) {
			out = append(out, p)
		}
	}
	return out
}

func (ps *productStore) ListAll() []Product {
	ps.RLock()
	defer ps.RUnlock()

	out := make([]Product, 0, len(ps.store))
	for _, p := range ps.store {
		out = append(out, p)
	}
	return out
}
