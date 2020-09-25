package se.lexicon.mattias.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.mattias.library.dto.LibraryUserDTO;
//import se.lexicon.mattias.library.exception.ResourceNotFoundException;
import se.lexicon.mattias.library.service.LibraryUserService;
import se.lexicon.mattias.library.service.MyConversionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class LibraryUserController {

    private LibraryUserService userService;
    private MyConversionService myConversionService;

    @Autowired
    public LibraryUserController(LibraryUserService userService, MyConversionService myConversionService) {
        this.userService = userService;
        this.myConversionService = myConversionService;
    }

    @GetMapping
    public ResponseEntity<String> testMessageController(){
        return ResponseEntity.ok("LibraryUser Controller");
    }

    /** FIND USER ID, EMAIL AND ALL ***********************************************************************************/

    @GetMapping("/findid/{userId}")
    public ResponseEntity<LibraryUserDTO> findById(@Valid @PathVariable Integer userId) {

        LibraryUserDTO userDTO = userService.findById(userId);

        if ( userDTO == null ) {
            throw new RuntimeException("NOT FOUND");
        }

        return ResponseEntity.ok(userDTO);

    }

    @GetMapping("/findemail/{email}")
    public ResponseEntity<LibraryUserDTO> findByEmail(@Valid @PathVariable String email) {

        return ResponseEntity.ok(userService.findByEmail(email));

    }

    @GetMapping("/findall")
    public ResponseEntity<List<LibraryUserDTO>> findAll() {

        return ResponseEntity.ok(userService.findAll());

    }

    /** CREATE, UPDATE ************************************************************************************************/

    @PostMapping("/add")
    public ResponseEntity<LibraryUserDTO> create(@Valid @RequestBody LibraryUserDTO userDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<LibraryUserDTO> update(@Valid @RequestBody LibraryUserDTO userDTO) {

        return ResponseEntity.ok(userService.update(userDTO));
    }
}

















