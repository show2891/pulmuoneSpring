<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="calendar-ui">
	<div class="calendar-wrap">
		<div class="month-control" style="justify-content: space-between">
			<div style="display: flex; align-items: center">
				<a class="arr" id="prev-month" style="cursor: pointer;">
					<div id="calendar-left-btn"></div>
				</a>
													
				<div class="date">
					<span id="calYear">2023</span>.
					<span id="calMonth">11</span>
				</div>
													
				<a class="arr" id="next-month" style="cursor: pointer;">
					<div id="calendar-right-btn"></div>
				</a>
			</div>
					
			<div id="today-button-action">
				<button class="today-button">오늘</button>
			</div>
		</div>
												
		<table class="scriptCalendar" style="width: 100%;">
			<thead>
				<tr>
					<td>일</td>
					<td>월</td>
					<td>화</td>
					<td>수</td>
					<td>목</td>
					<td>금</td>
					<td>토</td>
				</tr>
			</thead>
													
			<tbody>
														<tr>
															<td style="color: rgb(187, 187, 187); line-height: 3;">29</td>
															<td style="color: rgb(187, 187, 187); line-height: 3;">30</td>
															<td style="color: rgb(187, 187, 187); line-height: 3;">31</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>01</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>02</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>03</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div class=""><span class="">04</span></div>
															</td>
														</tr>
														<tr>
															<td style="color: rgb(239, 103, 95); line-height: 3; background-color: rgb(255, 255, 255);">
																<div class=""><span class="">05</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>06</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div class="day-with-symbol"> <span class="change-shipping">07</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div class="day-with-symbol"><span class="change-shipping">08</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div class="day-with-symbol"> <span class="change-shipping">09</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div class="day-with-symbol"> <span class="change-shipping">10</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div class=""> <span class="">11</span></div>
															</td>
														</tr>
														<tr>
															<td style="color: rgb(239, 103, 95); line-height: 3; background-color: rgb(255, 255, 255);">
																<div class=""> <span class="">12</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div class="day-with-symbol"> <span class="change-shipping">13</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>14</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>15</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>16</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>17</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div class=""> <span class="">18</span></div>
															</td>
														</tr>
														<tr>
															<td style="color: rgb(239, 103, 95); line-height: 3; background-color: rgb(255, 255, 255);">
																<div class=""> <span class="">19</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255);">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>20</span></div>
															</td>
															<td style="color: rgb(51, 51, 51); line-height: 3; background-color: rgb(255, 255, 255);">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>21</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255); cursor: pointer; font-weight: bold;">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>22</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255); cursor: pointer;">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>23</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255); cursor: pointer;">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>24</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255); cursor: pointer;">
																<div class=""> <span class="">25</span></div>
															</td>
														</tr>
														<tr>
															<td style="color: rgb(239, 103, 95); line-height: 3; background-color: rgb(255, 255, 255); cursor: pointer;">
																<div class=""> <span class="">26</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255); cursor: pointer;">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>27</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255); cursor: pointer;">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>28</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255); cursor: pointer;">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>29</span></div>
															</td>
															<td style="color: rgb(102, 102, 102); line-height: 3; background-color: rgb(255, 255, 255); cursor: pointer;">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>30</span></div>
															</td>
															<td style="color: rgb(187, 187, 187); line-height: 3;">
																<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>01</span></div></td>
															<td style="color: rgb(187, 187, 187); line-height: 3;">02</td>
														</tr>
			</tbody>
		</table>
												
		<div>
			<ul class="legend-wrap" style="border-bottom: none">
				<li><span style="background-color: #7acc12"></span>배송(예정)</li>
				<li><span style="border: 2px solid #7acc12"></span>배송일정변경</li>
				<li><span style="background-color: #ddd"></span>미배송</li>
			</ul>
			<p class="calendar-alert">배송일 클릭 시 음용 상품 확인이 가능합니다.</p>
		</div>
	</div>
</div>