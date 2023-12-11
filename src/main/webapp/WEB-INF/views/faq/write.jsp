<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layouts/head.jsp" %>
<body>
<div class="wrapper">

	<%@ include file="/WEB-INF/views/layouts/header.jsp" %>
	<main class="page notice forum">
		<div class="container">
			<div class="breadcrumb-style">
                <div class="wrap">
                    <ul>
                        <li><a href="/">홈</a></li>
                        <li><a href="/forum/faq/list.do">고객기쁨센터</a></li>
                        <li><a class="active">공지사항</a></li>
                    </ul>
                </div>
            </div>
                
            <div class="page-wrap">
				<%@ include file="/WEB-INF/views/layouts/forum/aside.jsp" %>

				<div class="page-content">
					<div class="border-wrapper">
			            <h2 class="container-title">FAQ</h2>
			        </div>
					
					<div class="page-addiction-wrapper" style="align-items: center;">
						<div class="info-copy description" style="margin-top:15px;">
							<p style="padding-left:2px;">관리자 전용 FAQ 등록</p>
						</div>
					</div>

					<form action="" method="post" id="faqForm">
						<div class="board-type2">
							<div class="input-group" style="margin-top: 15px">
								<div class="form-input select">
                                    <dl>
                                        <dt style="padding-left:20px; padding-bottom:3px;"><label>FAQ유형</label></dt>
                                        <dd>
                                            <div class="dropdown faq_cate">
                                                <a class="btn dropdown-toggle" href="javascript:void(0)" role="button" data-toggle="dropdown" aria-expanded="false">회원</a>
                                                <input type="hidden" name="cate" id="cate" value="1">
                                                <div class="dropdown-menu" id="dropdown-menu" style="">
                                                        <a id="dropdown-item" class="dropdown-item dropdown-item-category category" data-value="1" href="javascript:void(0)">회원</a>
                                                    
                                                        <a id="dropdown-item" class="dropdown-item dropdown-item-category category" data-value="2" href="javascript:void(0)">혜택</a>
                                                    
                                                        <a id="dropdown-item" class="dropdown-item dropdown-item-category category" data-value="3" href="javascript:void(0)">주문/배송</a>
                                                    
                                                        <a id="dropdown-item" class="dropdown-item dropdown-item-category category" data-value="4" href="javascript:void(0)">상품</a>
                                                    
                                                        <a id="dropdown-item" class="dropdown-item dropdown-item-category category" data-value="5" href="javascript:void(0)">음용</a>
                                                    
                                                        <a id="dropdown-item" class="dropdown-item dropdown-item-category category" data-value="6" href="javascript:void(0)">기타</a>
                                                </div>
                                            </div>
                                        </dd>
                                    </dl>
                                </div>
                                
								<div class="form-input error-wrap" >
									<dl>
										<dt style="padding-left:20px; padding-bottom:3px;"><label>제목</label></dt>
										<dd>
											<input name="question" value="" id="title" placeholder="제목을 입력하세요." type="text">
										</dd>
									</dl>
									<span class="message" id="titleError"><span class="mark-2" >!</span> 제목을 입력해주세요.</span>
								</div>
								<div class="form-input editor">
											
											<textarea maxlength="500" data-max="500" id="content" name="answer" placeholder="내용을 입력하세요."></textarea>
						
									<div class="count" id="word-count" ><span class="current-char">0</span> / <span class="maxtext">500</span></div>
								</div>
								<span class="message" id="contentsError"><span class="mark-2" >!</span> 내용을 입력해주세요.</span>
							</div>

							<div class="button-set b0 inquiry" style="margin-top:39px;">
								<button type="button" id="faqWriteBtn" data-toggle="modal" data-target="#alertModal" class="basic-big-button primary" style="min-width:280px; max-width:280px;">FAQ등록</button>
								<button type="button" onclick="javascript:history.back()" id="cancelBtn" class="basic-big-button" style="margin-left:20px; min-width:280px; max-width:280px;">취소</button>
							</div>
							
						</div>
					</form>

				</div>
			</div>
		</div>
	</main>
	<%@ include file="/WEB-INF/views/layouts/footer.jsp" %>
	
</div>

<%@ include file="/WEB-INF/views/ui/modal.jsp" %>


<script>
	// editor 
	const $wc = $("#word-count");
	let maxtext = 2000;
	$(".maxtext").text(maxtext);
	let currentChar = 0;
	let editor;
	ClassicEditor
       .create( document.querySelector( '#content' ), {
				language: "ko",
       	wordCount: {
			onUpdate: stats => {
					
				let isLimit = maxtext < stats.characters;
				if( isLimit ) {
					$wc.find(".current-char").text(`-${stats.characters - maxtext}`);	
					$("#faqWriteBtn").prop("disabled", true);
					$wc.find(".current-char").addClass("over");
				}else {
					$wc.find(".current-char").text( stats.characters );
					currentChar = stats.characters;
					$("#faqWriteBtn").prop("disabled", false);
					$wc.find(".current-char").removeClass("over");
				}
			},
       	},
		ckfinder: {
			uploadUrl : "/forum/faq/upload.do",
			withCredentials: true
		}	
		}).then(edt => {
				editor = edt;
			})
       .catch( error => {
           console.error( error );
       } );
	
	let isSubmit = false;
	
	// title valid check
	$("#title").on("blur", function(){
		if( $(this).val() == "" ) {
			$("#titleError").css("visibility", "visible");
		}else {
			$("#titleError").css("visibility", "hidden");
		}
	})
	
	$(".faq_cate .dropdown-item").on("click", function(){
		let val = $(this).data("value");
		$(":input[name=cate]").val(val);
		$(".faq_cate .dropdown-toggle").text($(this).text());
	})
	
	
	// valid check
	$("#faqWriteBtn").on("click", function(e){
		e.preventDefault();
		if( $("#title").val() == "" ) {
			$(".modal-body").text("제목을 입력해주세요");
			$("#alertModal").modal();
			$("#title").focus();
			
			return false;
		}else if( currentChar < 1 ) {
			$(".modal-body").text("내용을 입력해주세요");
			$("#alertModal").modal();
			editor.focus();
			
			return false;
		}
		
		ajaxWrite();
	});
	
	function ajaxWrite () {
		let params = "";
		let cate = $("input[name=cate]").val();
		let question = $("input[name=question]").val();
		let answer = editor.getData();
		params += `cate=\${ cate }&question=\${ question }&answer=\${ answer }`;
		
		$.ajax({
			url: "/forum/faq/write.do",
			dataType: "json",	
			type: "POST",
			data: params,
			cache: false,
			success: function(data, textStatus, jqXHR){
				if( data.result == 1 ) {
					$(".modal-body").text("FAQ 글이 등록 되었습니다.");	
					$("#alertModal").modal();
					
					$("#alertModal").on("click", function(e){
						location.href = data.url;
					})
				}
			},
			error: function(){
				console.log("error!");
			}
		});
	}
	
</script>

</body>
</html>
