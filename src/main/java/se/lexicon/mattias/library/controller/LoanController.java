package se.lexicon.mattias.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.mattias.library.dto.LoanDTO;
import se.lexicon.mattias.library.service.LoanService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/findid/{loanId}")
    public ResponseEntity<LoanDTO> findById(@PathVariable Long loanId) {

        return ResponseEntity.ok(loanService.findById(loanId));
    }

    @GetMapping("/find")
    public ResponseEntity<Object> find(
            @RequestParam(value = "type", defaultValue = "all") String type,
            @RequestParam(value = "status", defaultValue = "all") String status,
            @RequestParam(value = "value", defaultValue = "all") String value)
    {

        switch ( type ) {
            case "loanid":
                return ResponseEntity.ok(loanService.findById(Long.parseLong(value)));

            case "bookid":
                return ResponseEntity.ok(loanService.findByBookId(Integer.parseInt(value)));

            case "userid":
                return ResponseEntity.ok(loanService.findByUserId(Integer.parseInt(value)));

            case "terminated":
                return ResponseEntity.ok(loanService.findByTerminated(Boolean.parseBoolean(status)));

            case "all":
                return ResponseEntity.ok(loanService.findAll());

            default: throw new IllegalArgumentException("Not a Valid type: [ " + type +" ]" );
        }

    }

    @PostMapping("/add")
    public ResponseEntity<LoanDTO> create(@Valid @RequestBody LoanDTO loanDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.create(loanDTO));

    }

    @PutMapping("/update")
    public ResponseEntity<LoanDTO> update(@Valid @RequestBody LoanDTO loanDTO) {

        return ResponseEntity.ok(loanService.update(loanDTO));
    }

    @DeleteMapping("/delete/{loanid}")
    public ResponseEntity<Boolean> delete(@Valid @RequestBody Long loanId) {

        if ( loanService.delete(loanId) ) {
            return ResponseEntity.ok(true);
        }
            return ResponseEntity.ok(false);
    }
}
