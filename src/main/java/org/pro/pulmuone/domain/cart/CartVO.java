package org.pro.pulmuone.domain.cart;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CartVO {

	private int program_no;         		//프로그램번호
	private int img_no;	     	 			//이미지

	private String products_no;      	//상품번호
	private String products_name;      //상품명
	private String program_content;    //프로그램 내용

	private String system_name;		//이미지명
	private int price;							//가격
	private int products_tag;				//상품태그
	private String products_size;		//용량
	private String img_path;				//이미지 경로
	private String dayweek;				//요일

	private int cart_no;						//카트번호  
	private int amount;  					//수량(장바구니)
	private int member_no;  				//멤버번호
	private String member_id;  			//멤버 아이디
	private int mon_cnt;  					//월 수량
	private int tue_cnt;   					//화 수량   
	private int wed_cnt;     				//수 수량
	private int thu_cnt;      				//목 수량
	private int fir_cnt;     					//금 수량
	private String reg_date;				//등록일
	
	private String itemCode;
	private int cartIdx;
	
}

