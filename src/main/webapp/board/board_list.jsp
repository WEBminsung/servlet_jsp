<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.demo.mvc.model.Board_dto"%>
<%

  String sessionId = (String) session.getAttribute("JSESSIONID");
  sessionId = "1";
  List boardList = (List) request.getAttribute("board_list");
  int total_record = (Integer) request.getAttribute("total_record");
  int pageNum = (Integer) request.getAttribute("page_num");
  int total_page = ((Integer) request.getAttribute("total_page"));


%>
<html>
<head>

  부트스트랩 링크…

  <title>고객센터 게시판</title>
  <script type="text/javascript">
    function checkForm() {

      var session_check = <%=sessionId == null%>;
      if (session_check) {
        alert("로그인 해주세요.");
        location.href = "../login/login.jsp"
        return false;
      }
      location.href = "./BoardWriteForm.do?id=<%=sessionId%>"
    }
  </script>
</head>
<body>
<jsp:include page="../top_menu.jsp" />
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">게시판(고객센터)</h1>
  </div>
</div>
<div class="container">
  <form action="<%=request.getContextPath()%>/BoardListAction.do/>" method="post">
    <div>
      <div class="text-right">
        <span class="badge badge-success">전체 <%=total_record%>건</span>
      </div>
    </div>
    <div style="padding-top: 50px">
      <table class="table table-hover">
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성일</th>
          <th>조회</th>
          <th>글쓴이</th>
        </tr>
        <%
          for (int j = 0; j < boardList.size(); j++) {
            Board_dto notice = (Board_dto) boardList.get(j);
        %>
        <tr>
          <td><%=notice.getNum()%></td>
          <td><a href="./BoardViewAction.do?num=<%=notice.getNum()%>&pageNum=<%=pageNum%>"><%=notice.getSubject()%></a></td>
          <td><%=notice.getRegist_day()%></td>
          <td><%=notice.getHit()%></td>
          <td><%=notice.getName()%></td>
        </tr>
        <%
          }
        %>
      </table>
    </div>
    <div align="center">
        <%for (int i = 1; i <= total_page; i++){%>

          <a href="<%=request.getContextPath()%>./BoardListAction.do?pageNum=<%=i%>" />
          <%if(pageNum == i){%>
            <font color='4C5317'><b> [<%=i%>]</b></font>
          <%}else{%>
            <font color='4C5317'> [<%=i%>]</font>
          <%}%>
          </a>
        <%}%>
    </div>
    <div align="left">
      <table>
        <tr>
          <td width="100%" align="left">&nbsp;&nbsp;
            <select name="items" class="txt">
              <option value="subject">제목에서</option>
              <option value="content">본문에서</option>
              <option value="name">글쓴이에서</option>
            </select> <input name="text" type="text" /> <input type="submit" id="btnAdd" class="btn btn-primary " value="검색 " />
          </td>
          <td width="100%" align="right">
            <a href="#" onclick="checkForm(); return false;" class="btn btn-primary">&laquo;글쓰기</a>
          </td>
        </tr>
      </table>
    </div>
  </form>
  <hr>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>
