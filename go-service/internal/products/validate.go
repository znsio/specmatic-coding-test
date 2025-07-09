package products

import (
	"errors"
	"strings"
)

var validProductTypes = map[string]struct{}{
	"book":   {},
	"food":   {},
	"gadget": {},
	"other":  {},
}

func validateProductDetails(p *ProductDetails) error {
	if strings.TrimSpace(p.Name) == "" {
		return errors.New("name is required")
	}
	lowerType := strings.ToLower(p.Type)
	if _, ok := validProductTypes[lowerType]; !ok {
		return errors.New("invalid type")
	}
	p.Type = lowerType
	if p.Inventory < 1 || p.Inventory > 9999 {
		return errors.New("inventory must be between 1 and 9999")
	}
	if p.Cost == nil {
		return errors.New("cost is required and cannot be null")
	}
	if *p.Cost < 0 {
		return errors.New("cost must be greater than 0")
	}
	return nil
}
