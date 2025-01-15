package practice.spring.jxls.domain;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.jxls.builder.JxlsOutput;
import org.jxls.transform.poi.JxlsPoiTemplateFillerBuilder;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import practice.spring.jxls.utils.JxlsExcelMaker;

@Slf4j
@Component
public class ExcelDownloader {

  public void processExcelDownload(
      String templateFileName,
      Map<String, Object> data,
      File file,
      JxlsOutput jxlsOutput,
      ResourceLoader resourceLoader) {
    JxlsPoiTemplateFillerBuilder builder = null;
    try {
      builder =
          JxlsExcelMaker.makeBuilder(resourceLoader, templateFileName)
              .orElseThrow(() -> new IllegalArgumentException("Template does not exists."));
    } catch (IOException e) {
      log.error("build TemplateBuilder error");
      throw new RuntimeException(e);
    }

    if (file != null) {
      builder.buildAndFill(data, file);
    } else if (jxlsOutput != null) {
      builder.buildAndFill(data, jxlsOutput);
    } else {
      String message = "file or jxlsOutput required!!";
      log.error(message);
      throw new RuntimeException(message);
    }
  }

}
