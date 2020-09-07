package com.test.marketplace.common.vo;

public enum StatusType implements IType{
	
	AVALAIBLE('A'),
	NOT_AVAILABLE('I');
	
	private Character value;

	StatusType(Character value) {
		this.value = value;
	}

	public Character getValue() {
		return value;
	}
	
	public static StatusType searchValue(Character value) {
		for (StatusType te : StatusType.values()) {
			if(te.getValue().equals(value)) {
				return te;
			}
		}
		return null;
	}
}
