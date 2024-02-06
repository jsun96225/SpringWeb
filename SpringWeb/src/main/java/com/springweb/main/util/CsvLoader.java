package com.springweb.main.util;


import com.springweb.main.entity.Cpu;
import com.springweb.main.entity.Memory;
import com.springweb.main.entity.Monitor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// openCsv
public class CsvLoader {

    private final String folder;
    private final ClassLoader classLoader;

    public CsvLoader(String folder) {
        this.folder = folder;
        this.classLoader = Thread.currentThread().getContextClassLoader();
    }

    public List<Cpu> loadCpuList() {
        List<Cpu> cpuList = new ArrayList<>();
        for (String lines: readCsvLines("/cpu.csv")) {
            String[] items = lines.split(",");
            Cpu cpu = new Cpu(items[0], Double.parseDouble(items[1]), Integer.parseInt(items[2]));
            cpuList.add(cpu);
        }
        return cpuList;
    }

    public List<Monitor> loadMonitorList() {
        List<Monitor> monitorList = new ArrayList<>();
        for (String lines: readCsvLines("/monitor.csv")) {
            String[] items = lines.split(",");
            Monitor monitor = new Monitor(items[0], Double.parseDouble(items[1]), Integer.parseInt(items[2]));
            monitorList.add(monitor);
        }
        return monitorList;
    }

    public List<Memory> loadMemoryList() {
        List<Memory> memoryList = new ArrayList<>();
        for (String lines: readCsvLines("/memory.csv")) {
            String[] items = lines.split(",");
            Memory memory = new Memory(items[0], Double.parseDouble(items[1]), Integer.parseInt(items[2]));
            memoryList.add(memory);
        }
        return memoryList;
    }

    private List<String> readCsvLines(String filename) {
        try (InputStream inputStream = classLoader.getResourceAsStream(folder + filename);) {
            assert  inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            Stream<String> streams = bufferedReader.lines().skip(1);
            return streams.collect(Collectors.toList());
        } catch (Exception exception) {
            exception.printStackTrace();
            return Collections.emptyList();

        }

    }
}
