<%@ page import="java.io.*" %>
<%@ page import="com.example.file_system.models.Directory" %>
<%@ page import="com.example.file_system.models.FileModel" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Devcolibri.com</title>
</head>
<body>
<p><%=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date())%></p>
<%
    String FileIcon = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Google_Docs_logo_%282014-2020%29.svg/555px-Google_Docs_logo_%282014-2020%29.svg.png";
    String FolderIcon = "https://seeklogo.com/images/N/new-folder-logo-D68023CCD1-seeklogo.com.png";
    String FolderUpIcon = "https://cdn2.iconfinder.com/data/icons/3d-folder-plus-plus-1/128/folder-directory-file-arrow-upload-up-document-512.png";

    Directory directory = (Directory) request.getAttribute("page");
%>
    <h2><%=directory.ParentDirectoryPath.FullPath%></h2>
    <hr>
<%
    if (directory.ParentDirectoryPath.ParentPath != null) {
%>
       <h3><img src=<%=FolderUpIcon%> width="23"> <a href="./?path=<%=directory.ParentDirectoryPath.ParentPath%>">Вверх</a></h3>
<%
    }
%>
<table>
    <tr width="600" align="left">
        <th width="10"></th>
        <th width="200">Файл</th>
        <th width="100">Размер</th>
        <th width="300">Дата</th>
    </tr>

<%
    for(FileModel child : directory.Children) {
%>
    <tr>
        <td>
            <% if(child.IsFile) {%>
            <img src=<%=FileIcon%> width="20"></a>
            <%}
            else{%>
            <img src=<%=FolderIcon%> width="20"></a>
            <%}%>
        </td>
        <td>
            <a href="./?path=<%=child.FullPath%>"> <p><%=child.Name%></p></a>
        </td>
        <td><%
            if(child.IsFile){%>
                <p><%=child.Size%> B</p>
            <%}%>
        </td>
        <td><%=new SimpleDateFormat("MM/dd/yy, HH:mm:ss").format(child.DateOfLastModified)%></td>
    </tr>
<%
    }
%>
</table>
</body>
</html>