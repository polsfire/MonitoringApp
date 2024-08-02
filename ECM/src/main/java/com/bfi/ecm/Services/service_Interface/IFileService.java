package com.bfi.ecm.Services.service_Interface;

import com.bfi.ecm.Entities.File;

import java.util.List;

public interface IFileService {
    File saveFile(File file);
    File getFileById(Long id);
    List<File> getAllFiles();
    File updateFile(File file);
    void deleteFile(Long id);
}
