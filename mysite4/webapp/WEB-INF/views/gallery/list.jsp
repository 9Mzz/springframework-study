<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/aside_gallery.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">

					<c:if test="${authUser ne null}">
						<button id="btnImgUpload" data-userno="${authUser.no}">이미지올리기</button>
					</c:if>

					<div class="clear"></div>


					<ul id="viewArea">
						<!-- 이미지반복영역 -->
						<c:forEach items="${gList}" var="vo">
							<li id="img${vo.no}">
								<div class="view">
									<img class="imgItem" data-no="${vo.no}" src="${pageContext.request.contextPath}/upload/${vo.saveName}">
									<div class="imgWriter">
										작성자: <strong>${vo.userName}</strong>
									</div>
								</div>
							</li>
						</c:forEach>
						<!-- 이미지반복영역 -->
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->



	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form method="post" action="${pageContext.request.contextPath}/gallery/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label> <input id="addModalContent" type="text" name="content" value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file" type="file" name="file" value="">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
					<input type="hidden" name="userNo" value="${authUser.no}"> <input type="hidden" name="userName" value="${authUser.name}">
				</form>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="viewModelImg" src="">
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

				</div>
				<form method="" action="">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-danger" id="delBtn">삭제</button>
						<input type="hidden" name="modalNo" val="">
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>


<script type="text/javascript">
	
	// '이미지올리기' 버튼 클릭할때
	$("#btnImgUpload").on("click", function() {
		// 업로드창 띄우기
		$("#addModal").modal('show');
	});

	
	// 이미지 클릭할때
	$("#viewArea").on("click", ".imgItem", function() {
		var no = $(this).data("no");
		getImg(no);
	});

	
	// '삭제' 버튼 클릭할때
	$("#delBtn").on("click", function() {
		var no= $("[name='modalNo']").val();
		deleteImg(no);
	});
	
	
	
	// 이미지 불러오기
	function getImg(no) {
		
		var url = '${pageContext.request.contextPath}/upload/';
		var authUserNo= $("#btnImgUpload").data("userno");
		
		$.ajax({

			url : "${pageContext.request.contextPath}/gallery/view",
			type : "post",
			// contentType : "application/json",
			data : {no : no},

			dataType : "json",
			success : function(vo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(vo);
				if (vo.userNo==authUserNo) {
					$("#delBtn").show();
				}
				else {
					$("#delBtn").hide();
				}

				// 이미지보기 모달창 띄우기
				$("#viewModal").modal('show');

				$("#viewModelImg").attr("src", url + vo.saveName);
				$("#viewModelContent").html(vo.content);
				$("[name='modalNo']").val(vo.no);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};
	
	
	// 이미지 삭제
	function deleteImg(no) {
		console.log("delteImg func")
		$.ajax({
			url: "${pageContext.request.contextPath}/gallery/delete",
			type : "post",
			//contentType: "application/json",
			data : {no : no},
			
			dataType: "json",
			success : function(result) {
				/*성공시 처리해야될 코드 작성*/
				console.log(result);
				if (result=="success") {
					$("#img"+no).remove();
					$("#viewModal").modal('hide');
				}
				else {
					$("#viewModal").modal('hide');
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};
	
</script>

</html>

