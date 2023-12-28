package valera.gord.magnusmovieproject.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import valera.gord.magnusmovieproject.dto.UserPageDto;
import valera.gord.magnusmovieproject.dto.UserRequestDto;
import valera.gord.magnusmovieproject.dto.UserResponseDto;
import valera.gord.magnusmovieproject.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createNewUser(
            @RequestBody @Valid
            UserRequestDto dto,
            UriComponentsBuilder uriBuilder){
        var saved = userService.createNewUser(dto);
        var uri = uriBuilder.path("/api/v1/users/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
    //api/v1/users/1
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable @Valid @NotNull long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }
    //api/v1/users/1
    //body
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserById(
            @PathVariable @Valid @NotNull long id, @Valid @RequestBody UserRequestDto dto){
        return ResponseEntity.ok(userService.updateUserById(dto,id));
    }
    //api/v1/users/55
    @DeleteMapping ("/{id}")
    public ResponseEntity<UserResponseDto> deleteUserById(
            @Valid @NotNull @PathVariable long id) {
        return ResponseEntity.ok(userService.deleteUserById(id)); }

    @DeleteMapping
    public ResponseEntity<List<UserResponseDto>> deleteAllUsers() {
        List<UserResponseDto> deletedUsers = userService.deleteAllUsers();
        return ResponseEntity.ok(deletedUsers);
    }

    // GET api/v1/users/page
    // GET api/v1/users/pageSize=25&pageNo=1&sortBy=username&sortDir=desc
    @GetMapping("/page")
    public ResponseEntity<UserPageDto> getUsersPage(
            @RequestParam(value = "pageNo", required = false,defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy",required = false,defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir",required = false,defaultValue = "asc") String sortDir
    ){
        return ResponseEntity.ok(userService.getAllUsers(pageNo,pageSize,sortBy,sortDir));


    }
}





