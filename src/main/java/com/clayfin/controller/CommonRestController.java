//package com.clayfin.controller;
//
//import java.util.Objects;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.clayfin.common.util.AppProperties;
//import com.clayfin.response.dto.GeneralResponse;
//import com.clayfin.util.RestUtil;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@RequestMapping("/api")
//@Slf4j
//public class CommonRestController {
//	
//	
//	private @Autowired 
//	AppProperties appProperties;
//	
//	private @Autowired
//	RestUtil restUtil;
//	
//	
//	public @ResponseBody ResponseEntity<GeneralResponse> invokeUnSecureEndpoint(@RequestBody String request, @RequestParam(value = "token", required = false) String token, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        try {
//            String unsecureWhiteListServiceName = appProperties.getUnsecureWhiteListServiceName();
//            String[] serviceNamesWithFunctions = unsecureWhiteListServiceName.split(",");
//            String[] serviceNameFromRequest = getServiceName(request);
//            boolean isWhitListedUrl = isWhitListedUrl(serviceNamesWithFunctions, serviceNameFromRequest);
//            ResponseEntity<GeneralResponse> response =  isWhitListedUrl ? restOperation(request, httpServletRequest, httpServletResponse) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            return response;
//        }
//        catch (Exception e) {
//        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}
//	
//	
//    private ResponseEntity<GeneralResponse> restOperation(String request, HttpServletRequest httpServletRequest,
//			HttpServletResponse httpServletResponse) {
//    	
//		return null;
//	}
//
//
//	private String[] getServiceName(String safeRequest) {
//        return Objects.requireNonNull(restUtil.getServiceName(safeRequest)).split(":");
//    }
//
//    private String getTrackingId(String safeRequest) {
//        return Objects.requireNonNull(restUtil.getTrackingId(safeRequest));
//    }
//    
//    
//    
//    private boolean isWhitListedUrl(String[] serviceNamesWithFunctions, String[] serviceNameFromRequest) {
//        boolean whiteListed = false;
//        for (String serviceNameWithFunction : serviceNamesWithFunctions){
//            String [] serviceNameAndFunction =  serviceNameWithFunction.split(":");
//            for (int i = 0, serviceNameAndFunctionLength = serviceNameAndFunction.length; i < serviceNameAndFunctionLength; i++) {
//                if (serviceNameAndFunction[0].equals(serviceNameFromRequest[0]) && serviceNameAndFunction[1].equals(serviceNameFromRequest[1])) {
//                    whiteListed = true;
//                    break;
//                }
//            }
//        }
//        return whiteListed;
//    }
//	
//}
