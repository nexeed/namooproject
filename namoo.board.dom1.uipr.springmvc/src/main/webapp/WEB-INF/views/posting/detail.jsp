<!-- 
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:eschoi@nextree.co.kr">Choi, Eunsun</a>
 * @since 2015. 1. 9.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>

    <title>소셜보드</title>
    <%@ include file="/WEB-INF/views/layout/common.jsp" %>

</head>
<body>

<!-- Main Navigation ========================================================================================== -->
<%@ include file="/WEB-INF/views/layout/menu.jsp" %>
<!-- Header ========================================================================================== -->
<header>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="jumbotron">
                    <h2>게시판</h2>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12 col-lg-12">
                <ol class="breadcrumb">
                    <li><a href="${ctx}/posting">게시판</a></li>
                    <li><a href="${ctx}/board/${boardusid}/postings?page=1" class="active">${socialBoard.name}</a>
                    </li>
                </ol>
            </div>
        </div>
    </div>
</header>

<!-- Container ======================================================================================= -->


<div class="container">
    <div class="row">

        <div style="z-index:1020" class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
            <div class="list-group panel panel-success">
                <div class="panel-heading list-group-item text-center hidden-xs">
                    <h4>게시판 관리</h4>
                </div>
                <div>
                    <c:forEach var="board" items="${boardList}">
                        <a href="${ctx}/board/${board.usid}/postings?page=1" class="list-group-item hidden-xs">${board.name}</a>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="col-sm-9 col-lg-9">
            <div>
                <h3>${socialBoard.name}</h3>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    ${posting.title}
                </div>
                <div class="panel-body">
                    <div class="post">
                        
                         <strong>${posting.writerName}</strong>
                         &nbsp;<span class="text-muted">    <fmt:formatDate value="${posting.registerDate}" pattern="yyyy-MM-dd"/></span>
                         &nbsp;<span class="text-muted">${posting.readCount} 읽음</span>

                         <a href="${ctx}/board/${boardUsid}/posting/${posting.usid}/checkPassword?checked=update"
                            class="glyphicon glyphicon-cog pull-right" style="padding:10px">수정</a>
                         <a href="${ctx}/board/${boardUsid}/posting/${posting.usid}/checkPassword?checked=delete"
                            class="glyphicon glyphicon-trash pull-right" style="padding:10px">삭제</a>
                       
                    </div>
					${posting.contents}

                </div>
                
            </div>

            <div class="text-center">
                <a href="${ctx}/board/${boardUsid}/postings?page=1">
                    <button type="button" class="btn btn-default">목록</button>
                </a>
            </div>
        </div>
    </div>

    <!-- Footer ========================================================================================== -->
    <%@ include file="/WEB-INF/views/layout/footer.jsp" %>
</div>

</body>
</html>