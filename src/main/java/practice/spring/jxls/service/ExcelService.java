package practice.spring.jxls.service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import practice.spring.jxls.data.Employee;
import practice.spring.jxls.data.ExcelDownloadRequest;
import practice.spring.jxls.domain.ExcelDownloader;
import practice.spring.jxls.utils.JxlsExcelDataMaker;
import practice.spring.jxls.utils.JxlsOutputMaker;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelService {

  @Value("${path.output}")
  private String outputRootPath;
  @Value("${value.template.item-name}")
  private String itemNames;
  private final ResourceLoader resourceLoader;
  private final ExcelDownloader excelDownloader;

  public void download(ExcelDownloadRequest excelDownloadRequest, HttpServletResponse response) {
    Map<String, Object> data = generateTestData();

    // web download request
    if (response != null) {
      excelDownloader.processExcelDownload(
          excelDownloadRequest.templateFileName(),
          data,
          null,
          JxlsOutputMaker.make(response, excelDownloadRequest.outputFileNameWithExtension()),
          resourceLoader
      );
    }
    // file save request
    else {
      excelDownloader.processExcelDownload(
          excelDownloadRequest.templateFileName(),
          data,
          new File(outputRootPath + excelDownloadRequest.outputFileNameWithExtension()),
          null,
          resourceLoader
      );
    }
  }

  private Map<String, Object> generateTestData() {
    List<Employee> rowList = Employee.generateSampleEmployeeData();
    List<Employee> employeeList = new ArrayList<>();

    int count = 10;

    for (int i = 0; i < count; i++) {
      employeeList.addAll(rowList);
    }

    return JxlsExcelDataMaker.make(itemNames, employeeList);
  }


}
