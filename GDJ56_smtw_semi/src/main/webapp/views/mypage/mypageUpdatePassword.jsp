<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<%
	String userId = (String)request.getAttribute("userId");
out.print(userId);
%>

 <section>
            <div class="sidemenu">
                <div><h4 style="text-align: center;">마이 페이지</h4></div>
                <div><p>워홀 프렌즈</p></div>
                <div><p>계정 관리</p></div>
                <div><p>쪽지함</p></div>
                <div><p>내가 쓴 글</p></div>
                <div><p>찜한 나라</p></div>
               
            </div>
            <div class="menuDiv"></div>
            <div class="contentList">
                <div id="menutitle"><h2 style="background-color: cornflowerblue;">계정 관리</h2></div>
            <div id="pwck">
            <p>현재 비밀번호 입력 후, 변경할 비밀번호를 입력해주세요</p>
           	
           <form action="<%=request.getContextPath()%>/mypage/mypagePasswordUpdateEnd.do" method="post" onsubmit="return passwordCheck()";>
           	
           	 <input type="hidden" name="id" value="<%=userId%>">
             현재 비밀번호<input type="password" name="password" id="password" required> <br><br>
             새로운 비밀번호 <input type="password" name="password_new" id="password_chk" value="" required><br><br>
             새로운 비밀번호 확인<input type="password" name="password_chk" value="" required>
            <input type="submit" name="submit" id="" value="취소">
            <input type="submit" name="submit" id="" value="입력">
             
            </form> 
            </div>
            </div>
            
    
        </section>
        <script>
		const passwordCheck=()=>{
			const password=document.querySelector("[name=password_new]").value;
			const passwordck=document.querySelector("#password_chk").value;
			if(password.trim().length<8){
			alert("비밀번호는 8글자 이상 작성해야합니다")
			return false;
			}
			if(password!=passwordck){
			alert("비밀번호가 일치하지않습니다");
			return false;
			}
		}
		
	</script>
        <style>
        #pwck{
           
            position: absolute;
            top: 500px;
            left: 650px;
            width: 600px;
            height: 300px;
            
           
            
        }
        #pwck>*{
            border: 1px solid;
            padding: 2%;
        }
           
    
        #mypsc{
                border: 1px solid;
                border-bottom: 3px solid;
    
                position: absolute;
                top: 500px;
                left: 500px;
             
                width: 300px;
                height: 390px;
            }
    
            #menutitle{
                /* border: 1px solid;
                border-bottom: 3px solid; */
    
                position: absolute;
                top: 350px;
                left: 800px;
                width: 300px;
                height: 100px;
            }
    
           
            section{
                display: flex;
            }
    
            .contentList{
                width: 85%;  
                margin-left: 0 auto;
                margin-right: 0 auto;
                text-align: center;
            }
            
    
            #list{
                margin: 0 auto;
                width: 700px;
                border-collapse: collapse;
            }
            #list th{
                border: 1px solid;
                border-bottom: 3px solid;
            }
            #list td{
                border: 1px solid;
            }
            .sidemenu{
                padding: 10px;
                /* margin-top: 1000px; */
                /* margin: 0 auto; */
                /* border: 1px solid; */
                font-size: 18px;
                font-weight: bold;
                position: fixed;
            }
            
            .menuDiv{
                width: 15%;
            }
    
            .sidemenu p:hover{
                transform: scale(1.1);
                transition-property: transform;
                transition-duration: 1s;
            }
            .sidemenu>*:not(div:nth-child(1)){
                width: 200px;
                height: 50px;
                background-color: lavender;
                border-radius: 50px;
                box-shadow: 5px 2px 8px lavender;
            }
            .sidemenu p{
                text-align: center;
                padding: 13px;
                cursor:pointer
            }
            .sidemenu>div:nth-child(3){
                color: purple;
                
            }
            section{
                border: 1px solid tomato;
                
                margin-left: 50px;
                margin-right: 50px;
                /* ★수정한 부분 */
                /* padding-left: 100px;
                padding-right: 100px; */
                /* ★수정한부분 */
                /* 
                    만약 섹션 안에 div를 만든다면 여기 padding-left,padding-right에서
                    좌우 간격을 조정하세요
                    왼쪽 오른쪽도 웬만하면 다같이 맞추면 좋을 듯 하니 각자 만들어보고 의견주세요
                 */
                margin-top: 100px;
                height: 900px; 
                /*
                    ->내가 사용하는 중간 섹션부분의 크기를 조절하려면 이 height를 조정하세요★★
                    높낮이 조절해도 footer침범하지 않도록 설정해놨으니 마음껏 늘려도 됩니다.
                */
                
            }
            section>div{
                border: 1px solid blue;
                /* 섹션 안의 div테두리 */
                
            }
        </style>
<%@include file="/views/common/footer.jsp" %>