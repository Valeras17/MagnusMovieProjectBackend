package valera.gord.magnusmovieproject.service;

import valera.gord.magnusmovieproject.dto.UserPageDto;
import valera.gord.magnusmovieproject.dto.UserRequestDto;
import valera.gord.magnusmovieproject.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createNewUser(UserRequestDto userRequestDto);

    List<UserResponseDto> findAllUsers();

    UserResponseDto findUserById(long id);

    UserResponseDto updateUserById(UserRequestDto userRequestDto,  long id );

    UserResponseDto deleteUserById(long id);

    List<UserResponseDto> deleteAllUsers();

 UserPageDto getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);






}
