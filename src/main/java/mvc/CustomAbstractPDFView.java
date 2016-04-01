package mvc;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import models.Department;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class CustomAbstractPDFView extends AbstractPdfView{
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked") List<Department> departments = (List<Department>) model.get("list");

        Table table = new Table(2);
        table.addCell("Number");
        table.addCell("Title");

        int counter = 1;

        for (Department dep:departments) {
            table.addCell(String.valueOf(counter));
            table.addCell(dep.getTitle());
            counter++;
        }
        document.add(table);
    }
}
