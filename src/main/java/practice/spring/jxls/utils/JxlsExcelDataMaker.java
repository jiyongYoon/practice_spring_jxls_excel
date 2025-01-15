package practice.spring.jxls.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JxlsExcelDataMaker {

  /**
   * Jxls 라이브러리에 기입할 data 만드는 클래스.
   *
   * @param itemsName Template의 `jx:each(items="employees" var="e" lastCell="C2")` 에서 `employees`
   * @param dataList  실제 들어갈 데이터 리스트. 요소 당 row 한 줄
   * @return Jxls에 전달할 데이터 Map. 해당 Map의 이름은 `data`로 고정됨.
   * (https://jxls.sourceforge.net/migration-to-v3-0.html)
   */
  public static Map<String, Object> make(String itemsName, List<?> dataList) {
    Map<String, Object> data = new HashMap<>();
    data.put(itemsName, dataList);
    return data;
  }
}
