package com.bfi.ecm.Services.service_Implimentation;

import com.bfi.ecm.Entities.Metrics;
import com.bfi.ecm.Repositories.IMetricsRepositorie;
import com.bfi.ecm.Services.service_Interface.IMetricsService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Optional;

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
    @Override
    public long logfind(List<String> values) throws IOException {

        Path path = Paths.get(values.get(0));//object path being used in the files api
        FileCounter fileCounter = new FileCounter(values.get(1));
        Files.walkFileTree(path, fileCounter);
        System.out.println("Number: " + fileCounter.getCount());

        return fileCounter.getCount();
    }
    private static class FileCounter extends SimpleFileVisitor<Path> {
        private final String fileExtension;
        @Getter
        private long count;

        public FileCounter(String fileExtension) {
            this.fileExtension = fileExtension;
            this.count = 0;
        }
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.toString().endsWith(fileExtension)) {
                count++;
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
