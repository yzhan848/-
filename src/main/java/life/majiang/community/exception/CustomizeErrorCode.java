package life.majiang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
	QUESTION_NOT_FOUND("你輸入的問題不存在");
	
	
	@Override
	public String getMessage() {
		return message;
	}



	private String message;

	 CustomizeErrorCode(String message) {
		this.message = message;
	}
	
	

}
