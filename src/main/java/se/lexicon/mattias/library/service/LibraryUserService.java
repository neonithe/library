package se.lexicon.mattias.library.service;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.mattias.library.dto.LibraryUserDTO;
import se.lexicon.mattias.library.entity.LibraryUser;

import java.util.List;

public interface LibraryUserService  {

    LibraryUserDTO findById(Integer id);
    LibraryUserDTO findByEmail(String email);
    List<LibraryUserDTO> findAll();
    LibraryUserDTO create(LibraryUserDTO user);
    LibraryUserDTO update(LibraryUserDTO user);
    boolean delete(Integer id);

}
