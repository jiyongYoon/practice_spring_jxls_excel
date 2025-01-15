package practice.spring.jxls.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import practice.spring.jxls.data.ExcelDownloadRequest;
import practice.spring.jxls.service.ExcelService;

@RestController
@RequiredArgsConstructor
public class DownloadController {

  @Value("${path.template}")
  private String templateFileName;
  private final ExcelService excelService;

  @GetMapping("/export/web")
  public void exportWeb(HttpServletResponse response, @RequestParam String fileName) {
    System.out.println("Excel Web Download Start!!");
    String outputFileName = (fileName == null ? "report" : fileName) + ".xlsx";
    ExcelDownloadRequest excelDownloadRequest = ExcelDownloadRequest.builder()
        .templateFileName(templateFileName)
        .outputFileNameWithExtension(outputFileName)
        .build();

    excelService.download(excelDownloadRequest, response);

    System.out.println("Response Finish!! file = " + outputFileName);
  }

  @GetMapping("/export/file")
  public String exportFile(@RequestParam String fileName) {
    System.out.println("Excel File Export Start!!");
    String outputFileName = (fileName == null ? "report" : fileName) + ".xlsx";
    ExcelDownloadRequest excelDownloadRequest = ExcelDownloadRequest.builder()
        .templateFileName(templateFileName)
        .outputFileNameWithExtension(outputFileName)
        .build();

    excelService.download(excelDownloadRequest, null);

    String message = "Export File Finish!! file = " + outputFileName;
    System.out.println(message);
    return message;
  }
}
