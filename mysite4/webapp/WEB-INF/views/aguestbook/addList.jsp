<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->

		<div id="container" class="clearfix">
			<c:import url="/WEB-INF/views/include/aside_guestbook.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">

					<!--  <form action="${pageContext.request.contextPath}/api/guest/" method="get"> -->

					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></th>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></th>
								<td><input id="input-pass" type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4" class="text-center"><button id="add2" type="submit">등록</button></td>
							</tr>
						</tbody>

					</table>
					<!-- //guestWrite -->

					<!--  </form>	-->

					<div id="listArea">
						<!-- list 출력되는곳 -->
					</div>
					<!-- //guestRead -->

				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->


	<!----------------------------------------------------------------------------------------------->
	<!-- 삭제 모달 -->
	<div id="delModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">비밀번호 입력창</h4>
				</div>
				<div class="modal-body">

					비밀번호 : 
					<input id="modalPassword" type="password" name="password" value=""> 
					<input id="modalNo" type="hidden" name="no" value="">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button id="modalDelbtn" type="button" class="btn btn-danger">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>

<script>
	// 로딩되기 전에 요청
	$(document).ready(function() {
		// 리스트 출력
		fetchList();
	});
	
	
	// 파라미터 방식
	// '등록'버튼 클릭될때
	$("#add").on("click", function() {
		console.log("add button click");

		// 데이터 수집
		var name = $("#input-uname").val();
		var password = $("#input-pass").val();
		var content = $("[name='content']").val();

		// 객체 만들기
		var guestbookVo = {
			name : name,
			password : password,
			content : content
		};

		console.log(guestbookVo);

		$.ajax({

			url : "${pageContext.request.contextPath}/api/guest/add",
			type : "post",
			// contentType : "application/json",
			data : guestbookVo,

			dataType : "json",
			success : function(guestbookVo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, "up");

				// 입력 데이터 초기화
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	// json 방식 요청
	// '등록'버튼 클릭될때
	$("#add2").on("click", function() {
		console.log("add button click(json)");

		// 데이터 수집
		var name = $("#input-uname").val();
		var password = $("#input-pass").val();
		var content = $("[name='content']").val();

		// 객체 만들기
		var guestbookVo = {
			name : name,
			password : password,
			content : content
		};

		console.log(guestbookVo);
		
		$.ajax({

			url : "${pageContext.request.contextPath}/api/guest/add2",
			type : "post",
			contentType : "application/json", //보낼때 json으로 보냄
			data : JSON.stringify(guestbookVo), // 자바스크립트 객체 -> json형식으로 변경 

			dataType : "json",
			success : function(guestbookVo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, "up");

				// 입력 데이터 초기화
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});

	// 삭제버튼 눌렀을때
	$("#listArea").on("click", ".delbtn", function() {
		// 데이터 수집
		var $this = $(this);
		var no = $this.data("no");

		// 입력 데이터 초기화
		$("#modalPassword").val("");
		$("#modalNo").val(no);

		// 팝업창 띄우기
		$("#delModal").modal('show');

	});
	
	// 모달의 삭제버튼을 클릭했을때
	$("#modalDelbtn").on("click", function() {
		console.log("click delbtn in modal");
		
		// 데이터 수집
		var no= $("#modalNo").val();
		var password= $("#modalPassword").val();
		
		var delInfoVo= {
				no: no,
				password: password
		};
		
		console.log(delInfoVo);
		// ajax 요청 no, password
		
		$.ajax({

			url : "${pageContext.request.contextPath}/api/guest/delete",
			type : "post",
			// contentType : "application/json",
			data : delInfoVo,

			dataType : "json",
			success : function(state) {
				console.log(state);
				/*성공시 처리해야될 코드 작성*/
				if(state==="success") {
					// 해당 테이블(html) 삭제
					$("#t"+no).remove();
					// 모달 닫기
					$("#delModal").modal('hide');
				}
				else {
					$("#delModal").modal('hide');
					alert("비밀번호를 확인하세요.");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});	
	});

	// 리스트 출력
	function fetchList() {

		$.ajax({

			url : "${pageContext.request.contextPath}/api/guest/list",
			type : "post",
			// contentType : "application/json",
			// data : {name: "홍길동"},

			dataType : "json",
			success : function(gList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(gList);

				for (var i = 0; i < gList.length; i++) {
					render(gList[i], "down"); // 방명록리스트 그리기
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};

	// 리스트 그리기
	function render(vo, updown) {

		var str = '';
		str += ' <table id="t' + vo.no + '" class="guestRead"> ';
		str += ' 	<colgroup> ';
		str += ' 		<col style="width: 10%;"> ';
		str += ' 		<col style="width: 40%;"> ';
		str += ' 		<col style="width: 40%;"> ';
		str += ' 		<col style="width: 10%;"> ';
		str += ' 	</colgroup> ';
		str += ' 	<tr> ';
		str += ' 		<td>' + vo.no + '</td> ';
		str += ' 		<td>' + vo.name + '</td> ';
		str += ' 		<td>' + vo.regDate + '</td> ';
		str += ' 		<td><button class="delbtn" type="button" data-no="' + vo.no + '">삭제</button></td> ';
		str += ' 	</tr> ';
		str += ' 	<tr> ';
		str += ' 		<td colspan=4 class="text-left">' + vo.content + '</td> ';
		str += ' 	</tr> ';
		str += ' </table> ';

		if (updown == 'down') {
			$("#listArea").append(str);
		} else if (updown == 'up') {
			$("#listArea").prepend(str);
		} else {
			console.log("updown error");
		}
	};
</script>


</html>