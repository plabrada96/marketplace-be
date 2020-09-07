package com.test.marketplace.common.vo;


public enum SizeType implements IType{
	
	XS("XS"),
	S("S"),
	M("M"),
	L("L"),
	XL("XL"),
	XXL("XXL");

	private String value;

	SizeType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static SizeType searchValue(String value) {
		for (SizeType te : SizeType.values()) {
			if(te.getValue().equals(value)) {
				return te;
			}
		}
		return null;
	}
}
