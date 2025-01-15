package practice.spring.jxls.data;

import lombok.Builder;

@Builder
public record ExcelDownloadRequest(
    String templateFileName, // resources 디렉토리 이후부터
    String outputFileNameWithExtension // .xlsx 확장자까지 포함해야함
) {

}
