package se.lexicon.mattias.library.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.mattias.library.data.LibraryUserDAO;
import se.lexicon.mattias.library.dto.LibraryUserDTO;
import se.lexicon.mattias.library.entity.LibraryUser;
import se.lexicon.mattias.library.exceptions.ResourceNotFoundException;
import se.lexicon.mattias.library.service.LibraryUserService;
import se.lexicon.mattias.library.service.MyConversionService;

import static se.lexicon.mattias.library.exceptions.ErrorMessage.*;

import java.util.List;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {

    private LibraryUserDAO userDAO;
    private MyConversionService myConversionService;

    @Autowired
    public LibraryUserServiceImpl(LibraryUserDAO userDAO, MyConversionService myConversionService) {
        this.userDAO = userDAO;
        this.myConversionService = myConversionService;
    }

    @Override
    public LibraryUserDTO findById(Integer id) {

        return myConversionService.convertUserToDto(myConversionService.opToObjUserId(id));
    }

    @Override
    public LibraryUserDTO findByEmail(String email) {

        return myConversionService.convertUserToDto(myConversionService.opToObjEmail(email));
    }

    @Override
    public List<LibraryUserDTO> findAll() {

        return myConversionService.convertUserList(userDAO.findAll());
    }

    @Override
    public LibraryUserDTO create(LibraryUserDTO user) {

        if ( user.getUserId() != null ) {
            throw new IllegalArgumentException(ERROR_NO_ID);
        }

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

        userDAO.findById(id).orElseThrow(()-> new ResourceNotFoundException(ERROR_NOT_FOUND_ID));

        if ( userDAO.existsById(id) ) {
            LibraryUser user = myConversionService.opToObjUserId(id);
            userDAO.delete(user);
            return true;
        } else {
            return false;
        }
    }
}
