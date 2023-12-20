<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div>
	<div class="box-partition">
		<div class="part">
			<div class="part-head">
				<h5>배송지정보</h5>
			</div>
			<ul class="vertical-info-list">
				<li>
					<label>받으시는 분</label>
					<p>${ boxShipDTO.box_receiver }</p>
				</li>
				<li>
					<label>연락처</label>
					<p data-call-text="">${ boxShipDTO.box_tel }</p>
				</li>
				<li>
					<label>주소</label>
					<p>[${ boxShipDTO.box_zip_code }]${ boxShipDTO.box_addr }, ${ boxShipDTO.box_addr_detail }</p>
				</li>
				<li>
					<label>배송메모</label>
					<p>${ boxShipDTO.box_memo }</p>
				</li>
			</ul>
		</div>
	</div>
</div>
