package net.deaprtment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.deaprtment.dto.DepartmentDTO;
import net.deaprtment.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(
        name="Department Services to manage department",
        description = "Department Service - Create department,Get Department,Update Department,Delete Department"
)
@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Operation(
            summary = "Create department REST API",
            description = "create department rest api is used to add department in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 created"
    )
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        DepartmentDTO savedDepartment = departmentService.saveDepartment(departmentDTO);
        System.out.println("Saved department is "+savedDepartment);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);

    }

    @Operation(
            summary = "Get department By Id REST API",
            description = "Get department by id rest api is used to get department by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{department-code}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("department-code") String departmentCode){
        DepartmentDTO departmentByCode = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentByCode,HttpStatus.OK);
    }

//    @GetMapping("/checkdepartment/{department-code}")
//    public ResponseEntity<Boolean> departmentExistOrNot(@PathVariable("department-code") String departmentCode){
//
//    }
    @Operation(
            summary = "Get All department  REST API",
            description = "Get all department  rest api is used to get all department  from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getALlDepartment(){
        List<DepartmentDTO> allDepartment = departmentService.getAllDepartment();
        return ResponseEntity.ok(allDepartment);
    }

    @Operation(
            summary = "Update department REST API",
            description = "Update department rest api is used to update the department"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PutMapping("/{department-code}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable("department-code") String departmentCode,
                                                          @RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(departmentCode, departmentDTO);
        return new ResponseEntity<>(updatedDepartment,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete department By Code REST API",
            description = "delete department by code rest api is used to delete department by code from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @DeleteMapping("/{department-code}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("department-code") String departmentCode){
        departmentService.deleteDepartment(departmentCode);
        return new ResponseEntity<>("Department Deleted Successfully",HttpStatus.OK);
    }

}
