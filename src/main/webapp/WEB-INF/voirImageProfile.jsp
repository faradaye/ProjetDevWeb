<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.io.OutputStream"%>
<%
    String id = request.getParameter("id");
    Blob blob = (Blob) request.getAttribute("imageProfile" + id);
    try {
            byte byteArray[] = blob.getBytes(1, (int) blob.length());
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            os.write(byteArray);
            os.flush();
            os.close();
    } catch (Exception e) {

    }
%>
