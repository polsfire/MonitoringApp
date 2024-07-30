package com.bfi.ecm.Controllers;

import com.bfi.ecm.Entities.File;
import com.bfi.ecm.Services.service_Interface.IFileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileRestController {
    private final IFileService fileService;

    @Operation(description = "Add File")
    @PostMapping("/add")
    public File addFile(@RequestBody File file) {
        return fileService.saveFile(file);
    }

    @Operation(description = "Retrieve all Files")
    @GetMapping("/all")
    public List<File> retrieveAllFiles() {
        return fileService.getAllFiles();
    }

    @Operation(description = "Retrieve File by Id")
    @GetMapping("/{id}")
    public File retrieveFileById(@PathVariable("id") Long id) {
        return fileService.getFileById(id);
    }

    @Operation(description = "Update File")
    @PutMapping("/update")
    public File updateFile(@RequestBody File file) {
        return fileService.updateFile(file);
    }

    @Operation(description = "Delete File by Id")
    @DeleteMapping("/delete/{id}")
    public void deleteFile(@PathVariable("id") Long id) {
        fileService.deleteFile(id);
    }
}
