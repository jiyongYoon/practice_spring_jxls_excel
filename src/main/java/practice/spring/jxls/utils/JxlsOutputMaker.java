package practice.spring.jxls.utils;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.experimental.UtilityClass;
import org.jxls.builder.JxlsOutput;

@UtilityClass
public class JxlsOutputMaker {

  public JxlsOutput make(HttpServletResponse response, String outputFileName) {
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    String encodedFileName = URLEncoder.encode(outputFileName, StandardCharsets.UTF_8)
        .replaceAll("\\+", "%20");
    response.setHeader("Content-Disposition",
        String.format("attachment; filename*=UTF-8''%s", encodedFileName));

    return new JxlsOutput() {
      @Override
      public OutputStream getOutputStream() throws IOException {
        return response.getOutputStream();
      }
    };
  }

}
