<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  int rowsPerPage = 5;
    int rowNumber=0;
    int pageNumber=0;
    int whichPage=1;
    int pageIndexArray[]=null;
    int pageIndex=0; 
%>

<%  
//     rowNumber=list.size();
    if (rowNumber%rowsPerPage !=0)
         pageNumber=rowNumber/rowsPerPage + 1;
    else pageNumber=rowNumber/rowsPerPage;    

    pageIndexArray=new int[pageNumber]; 
    for (int i=1 ; i<=pageIndexArray.length ; i++)
         pageIndexArray[i-1]=i*rowsPerPage-rowsPerPage;
%>

<%  try {
       whichPage = Integer.parseInt(request.getParameter("whichPage"));
       pageIndex=pageIndexArray[whichPage-1];
    } catch (NumberFormatException e) {
       whichPage=1;
       pageIndex=0;
    } catch (ArrayIndexOutOfBoundsException e) {
         if (pageNumber>0){
              whichPage=pageNumber;
              pageIndex=pageIndexArray[pageNumber-1];
         }
    } 
%>

<%if (pageNumber>0){%>
  <b><font color=red>AAAAA<%=whichPage%>/<%=pageNumber%>AAAAA</font></b>
<%}%>

<b>@<font color=red><%=rowNumber%></font></b>