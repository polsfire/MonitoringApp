package com.bfi.ecm.Services.service_Implimentation;

import com.bfi.ecm.Entities.Metrics;
import com.bfi.ecm.Repositories.IMetricsRepositorie;
import com.bfi.ecm.Services.service_Interface.IMetricsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

@Service
public class MetricsServiceImpl implements IMetricsService {

     private  IMetricsRepositorie metricsRepository;

    @Override
    public Metrics saveMetrics(Metrics metrics) {
        return metricsRepository.save(metrics);
    }

    @Override
    public Optional<Metrics> getMetricsById(Long id) {
        return metricsRepository.findById(id);
    }

    @Override
    public List<Metrics> getAllMetrics() {
        return metricsRepository.findAll();
    }

    @Override
    public Metrics updateMetrics(Metrics metrics) {
        return metricsRepository.save(metrics);
    }

    @Override
    public void deleteMetrics(Long id) {
        metricsRepository.deleteById(id);
    }

     //exemple of the input :
    //List<String> values = List.of("/path/to/directory", ".txt", ".java", ".xml");
     //String jsonResult = logfind(values);
     @Override
     public String logfind(List<String> values) throws IOException {
         // Validate input
         if (values == null || values.size() < 2) {
             throw new IllegalArgumentException("The input list must contain at least a path and one file extension.");
         }

         // Extract path and file extensions
         Path path = Paths.get(values.get(0));
         System.out.println("Searching in directory: " + path.toAbsolutePath());

         List<String> fileExtensions = values.subList(1, values.size());

         // Initialize result map
         Map<String, Object> result = new HashMap<>();

         // Process each file extension
         for (String fileExtension : fileExtensions) {
             // Trim whitespace from the file extension
             String trimmedExtension = fileExtension.trim();
             System.out.println("Processing files with extension: [" + trimmedExtension + "]");

             FileCounter fileCounter = new FileCounter(trimmedExtension);

             try {
                 // Walk the file tree and process files
                 Files.walkFileTree(path, fileCounter);
             } catch (IOException e) {
                 System.err.println("Error while walking the file tree: " + e.getMessage());
                 e.printStackTrace();
             }

             // Print the count and file names
             System.out.println("Count of files with extension " + trimmedExtension + ": " + fileCounter.getCount());
             System.out.println("Files with extension " + trimmedExtension + ": " + fileCounter.getFileNames());

             // Add results to the map
             result.put(trimmedExtension, Map.of(
                     "count", fileCounter.getCount(),
                     "name of the files", fileCounter.getFileNames()
             ));
         }

         // Convert result map to JSON
         ObjectMapper mapper = new ObjectMapper();
         mapper.enable(SerializationFeature.INDENT_OUTPUT);
         String jsonResult = mapper.writeValueAsString(result);

         // Print the final result
         System.out.println("Result map: " + result);
         System.out.println("Final JSON result: " + jsonResult);

         return jsonResult;
     }

    @Override
    public String errormetric(List<String> params, List<String> params2) throws IOException {
        // Validate input
        if (params == null || params.size() < 2) {
            throw new IllegalArgumentException("The first input list must contain at least a path and one file extension.");
        }
        if (params2 == null || !params2.contains("error")) {
            throw new IllegalArgumentException("The second input list must contain 'error' followed by at least one error type.");
        }

        // Extract path and file extensions
        Path path = Paths.get(params.get(0));
        System.out.println("Searching in directory: " + path.toAbsolutePath());

        List<String> fileExtensions = params.subList(1, params.size());
        int errorIndex = params2.indexOf("error");
        List<String> actions = params2.subList(0, errorIndex); // list of performed actions
        List<String> errorTypes = params2.subList(errorIndex + 1, params2.size());

        // Initialize result map
        Map<String, Object> result = new HashMap<>();

        // Process each file extension
        for (String fileExtension : fileExtensions) {
            // Trim whitespace from the file extension
            String trimmedExtension = fileExtension.trim();
            System.out.println("Processing files with extension: [" + trimmedExtension + "]");

            FileCounter fileCounter = new FileCounter(trimmedExtension);

            try {
                // Walk the file tree and process files
                Files.walkFileTree(path, fileCounter);
            } catch (IOException e) {
                System.err.println("Error while walking the file tree: " + e.getMessage());
                e.printStackTrace();
            }

            // Initialize error result map for this extension
            Map<String, Object> errorResultMap = new HashMap<>();

            // Process each file found with the extension
            for (String fileName : fileCounter.getFileNames()) {
                try {
                    Path filePath = path.resolve(fileName);
                    System.out.println("Processing file: " + filePath.toAbsolutePath());
                    String content = Files.readString(filePath);

                    // Process each error type
                    for (String errorType : errorTypes) {
                        long errorCount = Arrays.stream(content.split("\n"))
                                .filter(line -> line.contains(errorType))
                                .count();
                        if (errorCount > 0) {
                            Map<String, Object> errorDetails = new HashMap<>();
                            errorDetails.put("count", errorCount);
                            errorDetails.put("name of the file", fileName);
                            errorResultMap.put(errorType, errorDetails);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file " + fileName + ": " + e.getMessage());
                }
            }

            // Add results for this extension to the main result map
            if (!errorResultMap.isEmpty()) {
                result.put(trimmedExtension, errorResultMap);
            }
        }

        // Convert result map to JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonResult = mapper.writeValueAsString(result);

        // Print the final result
        System.out.println("Result map: " + result);
        System.out.println("Final JSON result: " + jsonResult);

        return jsonResult;
    }

    private static class FileCounter extends SimpleFileVisitor<Path> {
        private final String fileExtension;
        @Getter
        private long count;
        @Getter
        private final List<String> fileNames;

        public FileCounter(String fileExtension) {
            this.fileExtension = fileExtension;
            this.count = 0;
            this.fileNames = new ArrayList<>();
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            System.out.println("Visiting file: " + file.toString());
            // Check if the file has the specified extension
            if (file.toString().endsWith(fileExtension)) {
                System.out.println("Found file with extension: " + file.getFileName());
                fileNames.add(file.getFileName().toString());
                count++;
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            // Optional: Add logging for directory traversal completion
            System.out.println("Completed visiting directory: " + dir.toString());
            return FileVisitResult.CONTINUE;
        }
    }

}
