package se.lexicon.mattias.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.mattias.library.service.LibraryUserService;
import se.lexicon.mattias.library.service.MyConversionService;

@RestController
public class LibraryUserController {

    private LibraryUserService userService;
    private MyConversionService myConversionService;

    @Autowired
    public LibraryUserController(LibraryUserService userService, MyConversionService myConversionService) {
        this.userService = userService;
        this.myConversionService = myConversionService;
    }



}
