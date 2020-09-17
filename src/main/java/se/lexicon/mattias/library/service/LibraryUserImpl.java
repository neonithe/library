package se.lexicon.mattias.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.mattias.library.data.LibraryUserDAO;
import se.lexicon.mattias.library.dto.LibraryUserDTO;
import se.lexicon.mattias.library.entity.LibraryUser;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryUserImpl implements LibraryUserService{

    private LibraryUserDAO userDAO;
    private MyConversionService myConversionService;

    @Autowired
    public LibraryUserImpl(LibraryUserDAO userDAO, MyConversionService myConversionService) {
        this.userDAO = userDAO;
        this.myConversionService = myConversionService;
    }

    @Override
    public LibraryUserDTO findById(Integer id) {

        LibraryUser user = myConversionService.opToObjId(id);

        return myConversionService.convertUserToDto(user);
    }

    @Override
    public LibraryUserDTO findByEmail(String email) {

        LibraryUser user = myConversionService.opToObjEmail(email);

        return myConversionService.convertUserToDto(user);
    }

    @Override
    public List<LibraryUserDTO> findAll() {

        List<LibraryUser> userList = userDAO.findAll();
        List<LibraryUserDTO> dtoList = new ArrayList<>();

        for ( LibraryUser user : userList ){
            dtoList.add(myConversionService.convertUserToDto(user));
        }

        return dtoList;
    }
   // ID | REGDATE | NAME | EMAIL | DEPT
    @Override
    public LibraryUserDTO create(LibraryUserDTO user) {

        // Create new user from userDTO
        LibraryUser newUser =
                new LibraryUser(user.getRegDate(), user.getName(),user.getEmail());

        // Save user
        userDAO.save(newUser);

        // Convert from user to userDTO and return userDTO
        return myConversionService.convertUserToDto(newUser);
    }

    @Override
    public LibraryUserDTO update(LibraryUserDTO user) {

        LibraryUserDTO updateUser = findById(user.getUserId());

        updateUser.setRegDate(user.getRegDate());
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setDept(user.getDept());
        // Convert from DTO to user and save, then return DTO
        userDAO.save(myConversionService.convertDtoToUser(updateUser));

        return updateUser;
    }

    @Override
    public boolean delete(Integer id) {

        if ( userDAO.existsById(id) ) {
            LibraryUser user = myConversionService.opToObjId(id);
            userDAO.delete(user);
            return true;
        } else {
            return false;
        }
    }
}
