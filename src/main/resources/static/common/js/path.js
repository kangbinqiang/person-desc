//获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
var url = window.document.location.href;
//获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
var pathName = window.document.location.pathname;
var pos = url.indexOf(pathName);
//获取主机地址，如： http://localhost:8080
var localhostPath = url.substring(0, pos);
//获取带"/"的项目名，如：/ems
var projectName = pathName.substring(0, pathName.indexOf('/', 1) + 1);
var basePath = localhostPath + projectName;

