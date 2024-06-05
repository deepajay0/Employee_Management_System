package net.orgranization.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.orgranization.dto.OrganizationDto;
import net.orgranization.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(
        name="Organization Services to manage employee",
        description = "Organization Service - Create employee,Get employee,Update employee,Delete employee"
)
@RestController
@RequestMapping("api/organizations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrganizationController {

    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Operation(
            summary = "Create Organization REST API",
            description = "create organization rest api is used to add employee in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 created"
    )
    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody @Valid OrganizationDto organizationDto){
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get organization By Id REST API",
            description = "Get organization by id rest api is used to get organization by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{organizationCode}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String organizationCode){

        OrganizationDto organizationByCode = organizationService.getOrganizationByCode(organizationCode);
        System.out.println("organizationByCode is "+organizationByCode);
        return new ResponseEntity<>(organizationByCode,HttpStatus.OK);
    }

    @Operation(
            summary = "Get all organization  REST API",
            description = "Get all organization rest api is used to get all the organization from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganization(){
        return ResponseEntity.ok(organizationService.getAllOrganization());
    }

    @Operation(
            summary = "Update organization REST API",
            description = "Update organization by code rest api is used to update the organization in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PutMapping("/{organization-code}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("organization-code") String organizationCode,
                                                              @RequestBody @Valid OrganizationDto organizationDto){
        OrganizationDto updatedOrgDto = organizationService.updateOrganization(organizationCode, organizationDto);
        return new ResponseEntity<>(updatedOrgDto,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete organization by code REST API",
            description = "Delete organization by code rest api is used to delete the  organization  from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @DeleteMapping("/{organization-code}")
    public ResponseEntity<String> deleteOrganization(@PathVariable("organization-code")String organizationCode){
        organizationService.deleteOrganization(organizationCode);
        return ResponseEntity.ok("Organization deleted successfully");
    }

}
