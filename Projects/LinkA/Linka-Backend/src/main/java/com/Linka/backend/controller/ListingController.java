package com.Linka.backend.controller;

import com.Linka.backend.entity.Category;
import com.Linka.backend.entity.Listing;
import com.Linka.backend.entity.User;
import com.Linka.backend.repository.CategoryRepository;
import com.Linka.backend.repository.ListingRepository;
import com.Linka.backend.repository.UserRepository;
import com.Linka.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/listings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ListingController {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<?> getAllListings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        try {
            Sort sort = sortDir.equals("desc") ? 
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<Listing> listings = listingRepository.findByStatusOrderByCreatedAt(Listing.Status.ACTIVE, pageable);
            
            Map<String, Object> response = new HashMap<>();
            response.put("listings", listings.getContent());
            response.put("currentPage", listings.getNumber());
            response.put("totalPages", listings.getTotalPages());
            response.put("totalElements", listings.getTotalElements());
            response.put("hasNext", listings.hasNext());
            response.put("hasPrevious", listings.hasPrevious());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/featured")
    public ResponseEntity<?> getFeaturedListings() {
        try {
            List<Listing> listings = listingRepository.findFeaturedListings(Listing.Status.ACTIVE);
            return ResponseEntity.ok(listings);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/popular")
    public ResponseEntity<?> getPopularListings(
            @RequestParam(defaultValue = "10") int limit) {
        try {
            Pageable pageable = PageRequest.of(0, limit);
            List<Listing> listings = listingRepository.findPopularListings(Listing.Status.ACTIVE, pageable);
            return ResponseEntity.ok(listings);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestListings(
            @RequestParam(defaultValue = "10") int limit) {
        try {
            Pageable pageable = PageRequest.of(0, limit);
            List<Listing> listings = listingRepository.findLatestListings(Listing.Status.ACTIVE, pageable);
            return ResponseEntity.ok(listings);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/trending")
    public ResponseEntity<?> getTrendingListings() {
        try {
            LocalDateTime since = LocalDateTime.now().minusDays(7);
            List<Listing> listings = listingRepository.findTrendingListings(since, Listing.Status.ACTIVE);
            return ResponseEntity.ok(listings);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getListingById(@PathVariable Long id) {
        try {
            Optional<Listing> listing = listingRepository.findById(id);
            if (listing.isPresent()) {
                // Increment view count
                listing.get().incrementViewCount();
                listingRepository.save(listing.get());
                
                return ResponseEntity.ok(listing.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getListingsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Listing> listings = listingRepository.findByCategoryAndStatus(
                categoryId, Listing.Status.ACTIVE, pageable);
            
            Map<String, Object> response = new HashMap<>();
            response.put("listings", listings.getContent());
            response.put("currentPage", listings.getNumber());
            response.put("totalPages", listings.getTotalPages());
            response.put("totalElements", listings.getTotalElements());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchListings(
            @RequestParam String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Listing> listings = listingRepository.searchListings(search, Listing.Status.ACTIVE, pageable);
            
            Map<String, Object> response = new HashMap<>();
            response.put("listings", listings.getContent());
            response.put("currentPage", listings.getNumber());
            response.put("totalPages", listings.getTotalPages());
            response.put("totalElements", listings.getTotalElements());
            response.put("search", search);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/price-range")
    public ResponseEntity<?> getListingsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Listing> listings = listingRepository.findByPriceRange(
                minPrice, maxPrice, Listing.Status.ACTIVE, pageable);
            
            Map<String, Object> response = new HashMap<>();
            response.put("listings", listings.getContent());
            response.put("currentPage", listings.getNumber());
            response.put("totalPages", listings.getTotalPages());
            response.put("totalElements", listings.getTotalElements());
            response.put("minPrice", minPrice);
            response.put("maxPrice", maxPrice);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/location")
    public ResponseEntity<?> getListingsByLocation(
            @RequestParam String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Listing> listings = listingRepository.findByLocation(location, Listing.Status.ACTIVE, pageable);
            
            Map<String, Object> response = new HashMap<>();
            response.put("listings", listings.getContent());
            response.put("currentPage", listings.getNumber());
            response.put("totalPages", listings.getTotalPages());
            response.put("totalElements", listings.getTotalElements());
            response.put("location", location);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getListingsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Listing> listings = listingRepository.findBySellerAndStatus(
                userId, Listing.Status.ACTIVE, pageable);
            
            Map<String, Object> response = new HashMap<>();
            response.put("listings", listings.getContent());
            response.put("currentPage", listings.getNumber());
            response.put("totalPages", listings.getTotalPages());
            response.put("totalElements", listings.getTotalElements());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/{id}/favorite")
    public ResponseEntity<?> toggleFavorite(@PathVariable Long id) {
        try {
            Optional<Listing> listingOpt = listingRepository.findById(id);
            if (listingOpt.isPresent()) {
                Listing listing = listingOpt.get();
                listing.incrementFavoriteCount();
                listingRepository.save(listing);
                
                return ResponseEntity.ok(Map.of(
                    "message", "Added to favorites",
                    "favoriteCount", listing.getFavoriteCount()
                ));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/{id}/contact")
    public ResponseEntity<?> incrementContactCount(@PathVariable Long id) {
        try {
            Optional<Listing> listingOpt = listingRepository.findById(id);
            if (listingOpt.isPresent()) {
                Listing listing = listingOpt.get();
                listing.incrementContactCount();
                listingRepository.save(listing);
                
                return ResponseEntity.ok(Map.of(
                    "message", "Contact recorded",
                    "contactCount", listing.getContactCount()
                ));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }
}