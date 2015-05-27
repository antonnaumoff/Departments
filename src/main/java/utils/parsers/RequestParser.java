package utils.parsers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class RequestParser {

    public static int checkString(String string, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer i = null;
        try {
            i = Integer.parseInt(string);
        } catch (NullPointerException e) {
            return i;

        } catch (Exception e) {
            (Logger.getLogger("exceptions")).warn("Some message", e);
            request.setAttribute("message", "Some problem with database, please, try later");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
        return i;
    }

    public static Integer parseIntWithDefaultValue(HttpServletRequest request, String value) {
        Integer result = null;
        try {
            result = Integer.parseInt(request.getParameter(value));
        } catch (Exception ignored) {

        }
        return result;
    }

    public static Integer parseIntWithDefaultValueO(HttpServletRequest request, String value) {
        Integer result = null;
        try {
            result = Integer.parseInt(request.getParameter(value));
        } catch (Exception ignored) {
            return 0;
        }
        return result;
    }

    public static Date parseDateDeafultValue(HttpServletRequest request, String value) {

        Date result = null;

        try {
            result = java.sql.Date.valueOf(request.getParameter(value));
        } catch (Exception ignored) {

        }
        if (result == null) {

            result = Date.valueOf("2015-01-01");
        }
        return result;
    }

}






