package com.studies.java.controllers;

import com.studies.java.services.ReadSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sheets")
public class SheetController {
    @Autowired
    ReadSheetService readSheetService;

    @GetMapping
    public ResponseEntity<List<String>> sheetRead(){
        var result = readSheetService.readSheet("Planilha 3.xlsx");

        return ResponseEntity.ok(result);
    }
}
