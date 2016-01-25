<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/my.css"/>" type="text/css">
    <script type="application/javascript" src="<c:url value="/public/bower_components/jquery/dist/jquery.js"/>"></script>
    <script type="application/javascript" src="<c:url value="/js/main.js"/>"></script>
    <script type="application/javascript"></script>

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="custom">Departments List</div>
                </div>
                <table class="table-striped">
                    <thead>
                    <tr>
                        <td class="table-header">Title</td>
                        <td class="my-table-cell3"></td>
                        <td class="my-table-cell3"></td>

                            <td class="my-table-cell3">
                                <form action="${pageContext.request.contextPath}/departmentForm" method="get">
                                    <button type="submit" class="btn btn-default btn-lg">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                </form>
                            </td>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="dep" items="${department}">
                        <tr>
                            <td class="my-table-cell"><c:out value="${dep.title}"/></td>

                            <td class="my-table-cell3">
                                <form action="${pageContext.request.contextPath}/Departments/delete" method="post">
                                    <input type="hidden" name="id" value="${dep.id}"/>
                                    <button type="submit" class="btn btn-default btn-lg">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </button>
                                </form>
                            </td>
                            <td class="my-table-cell3">
                                <form action="${pageContext.request.contextPath}/Departments/edit" method="get">
                                    <input type="hidden" name="id" value="${dep.id}"/>
                                    <button type="submit" class="btn btn-default btn-lg">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                    </button>
                                </form>

                            </td>

                            <td class="my-table-cell3">
                                <form action="${pageContext.request.contextPath}/Employees/list" method="get">
                                    <input type="hidden" name="id_dep" value="${dep.id}"/>
                                    <button type="submit" class="btn btn-default btn-lg">
                                        <span class="glyphicon glyphicon-th-list"></span>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-sm-2"></div>
    </div>
</div>
</div>

</body>
</html>
