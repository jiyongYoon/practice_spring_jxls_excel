package practice.spring.jxls.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.jxls.builder.JxlsStreaming;
import org.jxls.transform.poi.JxlsPoiTemplateFillerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import practice.spring.jxls.data.Employee;

@Component
@RequiredArgsConstructor
public class ExcelExportRunner implements ApplicationRunner {

  private final ResourceLoader resourceLoader;
  @Value("${path.output}")
  private String outputRootPath;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("Excel Export Start!!");
    List<Employee> rowList = Employee.generateSampleEmployeeData();
    List<Employee> employeeList = new ArrayList<>();

    int count = 1000;

    for (int i = 0; i < count; i++) {
      employeeList.addAll(rowList);
    }

    Map<String, Object> data = new HashMap<>();
    data.put("employees", employeeList); // key = excel note의 items

    String templateFileName = "/templates/template.xlsx";
    String outputFileName = "report.xlsx";

    Resource template = resourceLoader.getResource("classpath:" + templateFileName);
    if (template.exists()) {
      JxlsPoiTemplateFillerBuilder.newInstance()
          .withTemplate(template.getFile().getAbsolutePath())
          .withStreaming(JxlsStreaming.STREAMING_ON)
          .buildAndFill(data, new File(outputFilePath(outputFileName)));
      System.out.println("Finish!! file = " + template.getFile().getAbsolutePath());
    } else {
      System.out.println("Finish!! template file does not exists. path = " + template.getFile().getAbsolutePath());
    }

  }

  private String outputFilePath(String filePath) {
    return outputRootPath + filePath;
  }
}
