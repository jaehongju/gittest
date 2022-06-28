package kr.youhost.ems.api.common;

import kr.youhost.utils.crypt.Sha256;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 3번째 변경

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult {
	protected String resultCd;
	protected String resultMsg;
		
	@JsonIgnore
	public BaseResult (String resultCd) {
		this( resultCd, ResultCd.getResultMsg(resultCd) );
	}
	
	@JsonIgnore
	public boolean isValid() {
		return resultCd!=null ? true : false;
	}
	@JsonIgnore
	public boolean isOk() {
		return ResultCd.OK.equals(resultCd) ;
	}
	
	@JsonIgnore
	public String getHash() {		
		String hashBase = getHashBase();
		if( hashBase!=null ) {
			return Sha256.getChecksum( hashBase );
		}
		return null;
	}
	@JsonIgnore
	public String getHashBase() {		
		if( resultCd!=null || resultMsg!=null ) {
			StringBuffer sb = new StringBuffer();

			if( resultCd!=null ) {
				sb.append(resultCd);
			}
			if( resultMsg!=null ) {
				sb.append(resultMsg);
			}
			return sb.toString();
		}
		return null;
	}
	
	@JsonIgnore
	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString ( this );
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@JsonIgnore
	public static BaseResult getInstanceWithResultCd(String resultCd) {
		if( resultCd==null ) {
			return null;
		}
		return new BaseResult(resultCd, ResultCd.getResultMsg(resultCd));
	}
	
	@JsonIgnore
	public void replace(final String resultCd) {
		replace(resultCd, ResultCd.getResultMsg(resultCd));
	}
	@JsonIgnore
	public void replace(final String resultCd, final String resultMsg) {
		this.resultCd = resultCd;
		this.resultMsg = resultMsg;;
	}	
	@JsonIgnore
	public boolean isResultCd( final String resultCd ) {
		return this.resultCd==null ? false : this.resultCd.equals(resultCd);
	}
	
}
