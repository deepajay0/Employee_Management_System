package net.employee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.employee.dto.APIResponse;
import net.employee.dto.EmployeeDTO;
import net.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(
        name="Employee Services to manage employee",
        description = "Employee Service - Create employee,Get employee,Update employee,Delete employee"
)
@RestController
@RequestMapping("api/employees")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Create Employee REST API",
            description = "create employee rest api is used to add employee in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 created"
    )
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        System.out.println("employee "+employeeDTO);
        EmployeeDTO empDTO = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(empDTO, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get employee By Id REST API",
            description = "Get employee by id rest api is used to get department by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{emp-id}")
    public ResponseEntity<APIResponse> getEmployee(@PathVariable("emp-id") Long empId){
        APIResponse employeeById = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(employeeById,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PutMapping("/{emp-id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("emp-id") Long empId,@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO updatedEmpDto = employeeService.updateEmployee(empId, employeeDTO);
        return new ResponseEntity<>(updatedEmpDto,HttpStatus.OK);
    }

    @DeleteMapping("/{emp-id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("emp-id") Long empId){
        employeeService.deleteEmployee(empId);
        return new ResponseEntity<>("Employee Successfully Deleted",HttpStatus.OK);
    }

}
