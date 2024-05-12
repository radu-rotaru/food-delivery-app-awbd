package web.javaproject.fooddeliveryapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.javaproject.fooddeliveryapp.dto.CourierReviewDTO;
import web.javaproject.fooddeliveryapp.mapper.CourierReviewMapper;
import web.javaproject.fooddeliveryapp.model.Courier;
import web.javaproject.fooddeliveryapp.model.CourierReview;
import web.javaproject.fooddeliveryapp.model.security.CustomUserDetails;
import web.javaproject.fooddeliveryapp.service.CourierReviewService;
import web.javaproject.fooddeliveryapp.service.CourierService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/courier_review")
@Validated
public class CourierReviewController {

    private final CourierReviewService courierReviewService;

    private final CourierReviewMapper courierReviewMapper;

    private final CourierService courierService;

    @Autowired
    public CourierReviewController(CourierReviewService courierReviewService, CourierReviewMapper courierReviewMapper, CourierService courierService) {
        this.courierReviewService = courierReviewService;
        this.courierReviewMapper = courierReviewMapper;
        this.courierService = courierService;
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
        if(firstAuthority == null) {
            return "access_denied";
        }

        CourierReview courierReview = courierReviewService.getCourierReview(Long.parseLong(id));

        if(Objects.equals(firstAuthority.getAuthority(), "CLIENT")) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            if(!Objects.equals(courierReview.getClient().getId(), userDetails.getAssociatedId())) {
                return "access_denied";
            }
        }

        model.addAttribute("courierReview", courierReview);
        return "courierReviewForm";
    }

    @PostMapping("")
    public String saveOrUpdate(HttpServletRequest request, Model model){
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Long restaurantId = Long.parseLong(request.getParameter("orderId"));
            Long clientId = Long.parseLong(request.getParameter("clientId"));
            Long courierId = Long.parseLong(request.getParameter("courierId"));
            int stars = Integer.parseInt(request.getParameter("stars"));

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
            if (firstAuthority == null) {
                return "access_denied";
            }

            if (Objects.equals(firstAuthority.getAuthority(), "CLIENT")) {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

                if (!Objects.equals(clientId, userDetails.getAssociatedId())) {
                    return "access_denied";
                }
            }

            CourierReviewDTO courierReviewDTO = courierReviewMapper.toDto(courierReviewService.getCourierReview(id));
            courierReviewService.save(courierReviewDTO);

            return "redirect:/courier_review/courier/" + courierReviewDTO.getCourier().getId();
        }
        catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errorView";
        }
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
        if(firstAuthority == null) {
            return "access_denied";
        }

        CourierReview courierReview = courierReviewService.getCourierReview(Long.parseLong(id));
        if(Objects.equals(firstAuthority.getAuthority(), "CLIENT")) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            if (!Objects.equals(courierReview.getClient().getId(), userDetails.getAssociatedId())) {
                return "access_denied";
            }
        }

        courierReviewService.deleteById(Long.valueOf(id));
        return "redirect:/client";
    }

    @GetMapping("/courier/{courierId}")
    public String getReviewByCourier(@PathVariable Long courierId, Model model) {
        try {
            List<CourierReview> courierReviews = courierReviewService.getCourierReviewsByCourierId(courierId);
            List<CourierReviewDTO> courierReviewDTOs = courierReviewMapper.toDTOsList(courierReviews);

            Optional<Courier> courier = courierService.getCourier(courierId);

            model.addAttribute("courier", courier.get());
            model.addAttribute("courierReviews", courierReviewDTOs);

            return "courierReviews";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());

            return "errorView";
        }
    }
}
