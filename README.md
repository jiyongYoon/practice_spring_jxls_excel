# springboot - excel 관련

## 라이브러리
-  [jxls](https://github.com/jxlsteam/jxls) - Apache POI Wrapper Library

## 사용법

### 준비할 내용

1. application.properties
   ```text
   // file export시 사용하는 디렉토리. root = project directory
   path.output=./output/
   // template으로 사용할 엑셀 fullPath. root = \src\main\resources
   path.template=/templates/template.xlsx

   // template 노트에서 사용하는 item 이름. jx:each(items="employees" var="e" lastCell="C2")
   value.template.item-name=employees
   ```
2. template 파일
   - application.properties의 `path.template` 위치한 파일

### 사용
1. Web Download 
   - [GET] /export/web

2. File Export 
   - [GET] /export/file