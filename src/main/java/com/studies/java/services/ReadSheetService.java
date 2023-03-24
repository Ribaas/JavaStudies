package com.studies.java.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ReadSheetService {
    @Value("${user.directory.root}")
    private String userDirectoryRoot;

    @Value("${user.directory.sheets}")
    private String userDirectorySheets;

    public List<String> readSheet(String file){
        try {
            Path directoryPath = Paths.get(userDirectoryRoot, userDirectorySheets);
            Path filePath = directoryPath.resolve(file);

            List<String> names = new ArrayList<>();

            FileInputStream fileSheet = new FileInputStream(new File(filePath.toUri()));
            XSSFWorkbook workbook = new XSSFWorkbook(fileSheet);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator rowIterator = sheet.rowIterator();
            while(rowIterator.hasNext()){
                Row row = (Row) rowIterator.next();

                if(row.getRowNum() == 0) continue;

                Iterator cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell = (Cell) cellIterator.next();

                    names.add(cell.getStringCellValue());
                }
            }

            fileSheet.close();
            workbook.close();

            return names;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
