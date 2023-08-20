package org.Engineering;

import java.util.Objects;

public class RackCoder {
	
	private String code;
	
	RackCoder(String code){
		this.code = code;
	}

	@Override
	public int hashCode() {
		int result = 7;
		result = 31 * result + code.hashCode();
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RackCoder other = (RackCoder) obj;
		return Objects.equals(code, other.code);
	}
	

}
