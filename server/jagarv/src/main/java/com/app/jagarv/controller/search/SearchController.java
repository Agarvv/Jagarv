package com.app.jagarv.controller.search; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.app.jagarv.dto.product.read.ProductDTO;

import com.app.jagarv.service.search.SearchService; 


// handles product search
@RestController 
@RequestMapping("/api/jagarv/search")
public class SearchController {
    
    private final SearchService searchService; 
    
    public SearchController(SearchService searchService) {
        this.searchService = searchService; 
    }

    @GetMapping("/{query}") 
    public List<ProductDTO>
    searchProductsByQuery(@PathVariable String query)
    {
        return searchService.searchProductsByQuery(query); 
    }
}