package se.lexicon.mattias.library.service;

import se.lexicon.mattias.library.dto.LibraryUserDTO;

import java.util.List;

public interface LibraryUserService  {

    // FIND
    LibraryUserDTO findById(Integer id);
    LibraryUserDTO findByEmail(String email);
    List<LibraryUserDTO> findAll();

    // CRUD
    LibraryUserDTO create(LibraryUserDTO user);
    LibraryUserDTO update(LibraryUserDTO user);
    boolean delete(Integer id);

}
