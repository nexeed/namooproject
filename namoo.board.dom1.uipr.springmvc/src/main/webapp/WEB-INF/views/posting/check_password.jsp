﻿<!-- 
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:eschoi@nextree.co.kr">Choi, Eunsun</a>
 * @since 2015. 1. 9.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<title>소셜보드</title>
<%@ include file="/WEB-INF/views/layout/common.jsp"%>
<script type="text/javascript">
	var cancel = function() {
		location.href = "${ctx}/board/${boardUsid}/posting/${postingUsid}";
	}
</script>
</head>
<body>

	<!-- Main Navigation ========================================================================================== -->
	<%@ include file="/WEB-INF/views/layout/menu.jsp"%>
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
						<li><a href="${ctx}/home">홈</a></li>
						<li><a href="${ctx}/home">게시판</a></li>
						<li class="active">게시판 개설</li>
					</ol>
				</div>
			</div>
		</div>
	</header>

	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="row">

			<div style="z-index: 1020"
				class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
				<div class="list-group panel panel-success">
					<div class="panel-heading list-group-item text-center hidden-xs">
						<h4>게시판</h4>
					</div>
					<div>
						<c:forEach var="board" items="${boardList}">
							<a href="${ctx}/board/${board.usid}/postings?=page1"
								class="list-group-item hidden-xs">${board.name}</a>
						</c:forEach>
					</div>

				</div>
			</div>

			<div class="col-sm-9 col-lg-9">
				<div>
					<h3>비밀번호 확인</h3>
				</div>

				<div class="table-responsive">
					<div class="well">
						<c:if test="${checked eq 'update'}">
							<form class="bs-example form-horizontal"
								action="${ctx}/board/${boardUsid}/posting/${postingUsid}/update/checkPassword"
								method="post">
								<fieldset>
									<input type="hidden" name="boardUsid" value="${boardUsid}">
									<input type="hidden" name="postingUsid" value="${postingUsid}">

									<div class="form-group">
										<label class="col-lg-2 control-label">비밀번호</label>
										<div class="col-lg-10">
											<input type="password" name="password" class="form-control">
										</div>
									</div>

									<div class="form-group">
										<div class="col-lg-10 col-lg-offset-2">
											<button type="submit" class="btn btn-primary">확인</button>
											<button class="btn btn-default"
												onclick="cancel(); return false;">취소</button>
										</div>
									</div>
								</fieldset>
							</form>
						</c:if>
						<c:if test="${checked eq 'delete'}">
							<form class="bs-example form-horizontal"
								action="${ctx}/board/${boardUsid}/posting/${postingUsid}/delete"
								method="post">
								<fieldset>
									<input type="hidden" name="boardUsid" value="${boardUsid}">
									<input type="hidden" name="postingUsid" value="${postingUsid}">

									<div class="form-group">
										<label class="col-lg-2 control-label">비밀번호</label>
										<div class="col-lg-10">
											<input type="password" name="password" class="form-control">
										</div>
									</div>

									<div class="form-group">
										<div class="col-lg-10 col-lg-offset-2">
											<button type="submit" class="btn btn-primary">확인</button>
											<button class="btn btn-default"
												onclick="cancel(); return false;">취소</button>
										</div>
									</div>
								</fieldset>
							</form>
						</c:if>
					</div>
				</div>

			</div>
		</div>

		<!-- Footer ========================================================================================== -->
		<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	</div>

</body>
</html>