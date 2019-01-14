package com.reljicd.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reljicd.model.Analyst;
import com.reljicd.model.AnalyticReport;
import com.reljicd.repository.AnalystRepository;
import com.reljicd.repository.AnalyticReportRepository;

@Controller
public class FileUploadController {

    private final AnalyticReportRepository analyticReportRepository;

    private final AnalystRepository analystRepository;

    @Autowired
    public FileUploadController(AnalyticReportRepository analyticReportRepository, AnalystRepository analystRepository) {
        this.analyticReportRepository = analyticReportRepository;
        this.analystRepository = analystRepository;
    }

    @GetMapping("/analytic-reports")
    public ModelAndView listUploadedFiles() throws IOException {
        ModelAndView modelAndView = new ModelAndView("/analytic-reports");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return modelAndView;
    }

    @GetMapping("/list-files")
    public String listUploadedFiles(Model model) throws IOException {

//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                        "serveFile", path.getFileName().toString()).build().toString())
//                .collect(Collectors.toList()));

        model.addAttribute("files", new ArrayList<>());

        return "analytic-reports";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = null;//storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Analyst  analyst = analystRepository.findByUsername(name);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String reportName = name + "___" + sdf.format(new Date());
        AnalyticReport report = new AnalyticReport(null, analyst, reportName, file.getBytes());
        analyticReportRepository.save(report);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");


        return "redirect:/analytic-reports";
    }

}