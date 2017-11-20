<%@ page language="java" import="java.util.*" contentType="text/html;charset=gb2312"%>
<html>
<body>
<h2>Hello World!</h2>
上传图片
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="springmvc 上传文件" />
</form>
</body>
</html>
