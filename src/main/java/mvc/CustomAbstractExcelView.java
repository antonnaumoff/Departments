package mvc;

import models.Department;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
public class CustomAbstractExcelView extends AbstractExcelView {
    @SuppressWarnings("unchecked")
    @Override
    protected void buildExcelDocument(Map<String, Object> model, org.apache.poi.hssf.usermodel.HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Department> list = (List<Department>)model.get("list");
        HSSFSheet sheet = workbook.createSheet("List of Departments");
        setExcelHeader(sheet);
        setExcelRows(sheet, list);
    }

    private void setExcelHeader(HSSFSheet excelSheet) {
        HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("Title");
    }

    private void setExcelRows(HSSFSheet excelSheet, List<Department> departments){
        int record = 1;
        for (Department department : departments) {
            HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.createCell(0).setCellValue(department.getTitle());
        }
    }
}
