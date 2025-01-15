package practice.spring.jxls.utils;

import java.io.IOException;
import java.util.Optional;
import lombok.experimental.UtilityClass;
import org.jxls.builder.JxlsStreaming;
import org.jxls.transform.poi.JxlsPoiTemplateFillerBuilder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@UtilityClass
public class JxlsExcelMaker {

  public Optional<JxlsPoiTemplateFillerBuilder> makeBuilder(
      ResourceLoader resourceLoader,
      String templateFileName)
      throws IOException {
    Resource template = resourceLoader.getResource("classpath:" + templateFileName);
    if (template.exists()) {
      return Optional.ofNullable(JxlsPoiTemplateFillerBuilder.newInstance()
          .withTemplate(template.getFile().getAbsolutePath())
          .withStreaming(JxlsStreaming.STREAMING_ON));
    } else {
      return Optional.empty();
    }
  }

}
